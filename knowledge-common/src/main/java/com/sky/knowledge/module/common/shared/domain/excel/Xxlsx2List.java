package com.sky.knowledge.module.common.shared.domain.excel;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jodd.bean.BeanUtil;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class Xxlsx2List extends DefaultHandler
{
  private SharedStringsTable sst;
  private String lastContents;
  private boolean nextIsString;
  private int sheetIndex = -1;
  private Map<String, Object> rowMap;
  protected String insertErrorMsg;
  private Map<Integer, ExcelColumn> excelHeadMap;
  private Map<String, Map> columnsConvertMap;
  private List<Object> contents;
  private Class cls;
  private int curRow = 0;

  private int curCol = 0;

  public Xxlsx2List(Map<Integer, ExcelColumn> excelHeadMap, Map<String, Map> columnsConvertMap, List<Object> contents, Class cls)
  {
    this.excelHeadMap = excelHeadMap;
    this.columnsConvertMap = columnsConvertMap;
    this.contents = contents;
    this.cls = cls;
    this.rowMap = new HashMap();
  }

  public void optRows(int sheetIndex, int curRow, Map<String, Object> rowMap2)
  {
    if (curRow < 1)
      return;
    try
    {
      Object objClass = this.cls.newInstance();
      BeanUtil.populateBean(objClass, rowMap2);
      this.contents.add(objClass);
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  public void processOneSheet(InputStream fileIS, int sheetId)
    throws Exception
  {
    OPCPackage pkg = OPCPackage.open(fileIS);
    XSSFReader r = new XSSFReader(pkg);
    SharedStringsTable sst = r.getSharedStringsTable();
    XMLReader parser = fetchSheetParser(sst);

    InputStream sheetIS = r.getSheet("rId" + sheetId);
    this.sheetIndex += 1;
    InputSource sheetSource = new InputSource(sheetIS);
    parser.parse(sheetSource);
    sheetIS.close();
  }

  public void process(String filename)
    throws Exception
  {
    OPCPackage pkg = OPCPackage.open(filename);
    XSSFReader r = new XSSFReader(pkg);
    SharedStringsTable sst = r.getSharedStringsTable();
    XMLReader parser = fetchSheetParser(sst);
    Iterator sheets = r.getSheetsData();
    while (sheets.hasNext()) {
      this.curRow = 0;
      this.sheetIndex += 1;
      InputStream sheet = (InputStream)sheets.next();
      InputSource sheetSource = new InputSource(sheet);
      parser.parse(sheetSource);
      sheet.close();
    }
  }

  public void process(InputStream fileIS)
    throws Exception
  {
    try
    {
      OPCPackage pkg = OPCPackage.open(fileIS);
      XSSFReader r = new XSSFReader(pkg);
      SharedStringsTable sst = r.getSharedStringsTable();

      XMLReader parser = fetchSheetParser(sst);

      Iterator sheets = r.getSheetsData();
      while (sheets.hasNext()) {
        this.curRow = 0;
        this.sheetIndex += 1;
        InputStream sheet = (InputStream)sheets.next();
        InputSource sheetSource = new InputSource(sheet);
        parser.parse(sheetSource);
        sheet.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
      this.insertErrorMsg = "要导入的文件解析出错，请确认导入文件是否正确!";
    }
    finally
    {
    }
  }

  public XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
    XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");

    this.sst = sst;
    parser.setContentHandler(this);
    return parser;
  }

  public void startElement(String uri, String localName, String name, Attributes attributes)
    throws SAXException
  {
    if (name.equals("c"))
    {
      String cellType = attributes.getValue("t");

      if ((cellType != null) && (cellType.equals("s")))
        this.nextIsString = true;
      else {
        this.nextIsString = false;
      }
    }

    this.lastContents = "";
  }

  public void endElement(String uri, String localName, String name)
    throws SAXException
  {
    if (this.nextIsString) {
      try {
        int idx = Integer.parseInt(this.lastContents);
        this.lastContents = new XSSFRichTextString(this.sst.getEntryAt(idx)).toString();
      }
      catch (Exception e)
      {
      }

    }

    if (name.equals("c")) {
      Object value = this.lastContents.trim();
      value = (value.equals("")) ? null : value;

      if (this.excelHeadMap.containsKey(Integer.valueOf(this.curCol))) {
        if ((this.columnsConvertMap != null) && (this.columnsConvertMap.containsKey(((ExcelColumn)this.excelHeadMap.get(Integer.valueOf(this.curCol))).getFieldName()))) {
          value = ((Map)this.columnsConvertMap.get(((ExcelColumn)this.excelHeadMap.get(Integer.valueOf(this.curCol))).getFieldName())).get(value);
        }
        value = transformType(((ExcelColumn)this.excelHeadMap.get(Integer.valueOf(this.curCol))).getType(), value);
        this.rowMap.put(((ExcelColumn)this.excelHeadMap.get(Integer.valueOf(this.curCol))).getFieldName(), value);
      }
      this.curCol += 1;
    }
    else if (name.equals("row")) {
      optRows(this.sheetIndex, this.curRow, this.rowMap);
      this.rowMap.clear();
      this.curRow += 1;
      this.curCol = 0;
    }
  }

  private Object transformType(Object excelColumnType, Object value)
  {
    if (excelColumnType instanceof String)
      return value;
    if (excelColumnType instanceof Integer)
      return ((Integer)value);
    if (excelColumnType instanceof Double)
      return ((Double)value);
    if (excelColumnType instanceof Long)
      return ((Long)value);
    if (excelColumnType instanceof Date) {
      return ((Date)value);
    }
    return value;
  }

  public void characters(char[] ch, int start, int length)
    throws SAXException
  {
    this.lastContents += new String(ch, start, length);
  }
}