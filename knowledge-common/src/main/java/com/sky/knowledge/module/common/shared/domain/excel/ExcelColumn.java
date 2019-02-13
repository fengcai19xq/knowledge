package com.sky.knowledge.module.common.shared.domain.excel;

public class ExcelColumn
{
  private int index;
  private String fieldName;
  private String fieldDispName;
  private int width;
  private String dateFormat;
  private Object type;
  private boolean verticalMerge = false;

  private boolean crosswiseMerge = false;

  int megreStartCell = 0;

  int megreStartRow = 0;

  boolean first = true; boolean megre = false;

  public ExcelColumn()
  {
  }

  public ExcelColumn(int index, String fieldName, String fieldDispName)
  {
    this.index = index;
    this.fieldName = fieldName;
    this.fieldDispName = fieldDispName;
  }

  public ExcelColumn(int index, String fieldName, String fieldDispName, int width)
  {
    this.index = index;
    this.fieldName = fieldName;
    this.fieldDispName = fieldDispName;
    this.width = width;
  }

  public ExcelColumn(int index, String fieldName, String fieldDispName, boolean verticalMerge)
  {
    this.index = index;
    this.fieldName = fieldName;
    this.fieldDispName = fieldDispName;
    this.verticalMerge = verticalMerge;
  }

  public ExcelColumn(int index, String fieldName, String fieldDispName, boolean crosswiseMerge, int megreStartCell)
  {
    this.index = index;
    this.fieldName = fieldName;
    this.fieldDispName = fieldDispName;
    this.crosswiseMerge = crosswiseMerge;
    this.megreStartCell = megreStartCell;
  }

  public ExcelColumn(int index, String fieldName, Object type)
  {
    this.index = index;
    this.fieldName = fieldName;
    this.type = type;
  }

  public ExcelColumn(int index, String fieldName, String fieldDispName, int width, boolean verticalMerge)
  {
    this.index = index;
    this.fieldName = fieldName;
    this.fieldDispName = fieldDispName;
    this.width = width;
    this.verticalMerge = verticalMerge;
  }

  public ExcelColumn(int index, String fieldName, String fieldDispName, String dateFormat, boolean verticalMerge)
  {
    this.index = index;
    this.fieldName = fieldName;
    this.fieldDispName = fieldDispName;
    setDateFormat(dateFormat);
    this.verticalMerge = verticalMerge;
  }

  public ExcelColumn(int index, String fieldName, String fieldDispName, int width, String dateFormat, boolean verticalMerge)
  {
    this.index = index;
    this.fieldName = fieldName;
    this.fieldDispName = fieldDispName;
    this.width = width;
    setDateFormat(dateFormat);
    this.verticalMerge = verticalMerge;
  }

  public int getIndex() {
    return this.index;
  }

  public String getFieldName() {
    return this.fieldName;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getFieldDispName() {
    return this.fieldDispName;
  }

  public void setFieldDispName(String fieldDispName) {
    this.fieldDispName = fieldDispName;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getWidth() {
    return this.width;
  }

  public String toString()
  {
    return "ExcelColumn [fieldDispName=" + this.fieldDispName + ", fieldName=" + this.fieldName + ", index=" + this.index + ", width=" + this.width + "]";
  }

  public void setDateFormat(String dateFormat)
  {
    this.dateFormat = dateFormat;
  }

  public String getDateFormat() {
    return this.dateFormat;
  }

  public Object getType() {
    return this.type;
  }

  public void setType(Object type) {
    this.type = type;
  }

  public void setVerticalMerge(boolean verticalMerge) {
    this.verticalMerge = verticalMerge;
  }

  public boolean isVerticalMerge() {
    return this.verticalMerge;
  }

  public int getMegreStartRow() {
    return this.megreStartRow;
  }

  public void setMegreStartRow(int megreStartRow) {
    this.megreStartRow = megreStartRow;
  }

  public boolean isFirst() {
    return this.first;
  }

  public void setFirst(boolean first) {
    this.first = first;
  }

  public boolean isMegre() {
    return this.megre;
  }

  public void setMegre(boolean megre) {
    this.megre = megre; }

  public boolean isCrosswiseMerge() {
    return this.crosswiseMerge;
  }

  public void setCrosswiseMerge(boolean crosswiseMerge) {
    this.crosswiseMerge = crosswiseMerge;
  }

  public int getMegreStartCell() {
    return this.megreStartCell;
  }

  public void setMegreStartCell(int megreStartCell) {
    this.megreStartCell = megreStartCell;
  }
}