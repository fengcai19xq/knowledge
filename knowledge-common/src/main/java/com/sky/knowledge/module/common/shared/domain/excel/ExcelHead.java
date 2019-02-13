package com.sky.knowledge.module.common.shared.domain.excel;

import java.util.List;
import java.util.Map;

public class ExcelHead
{
  private List<ExcelColumn> columns;
  private Map<String, Map> columnsConvertMap;
  private int rowCount;
  private int columnCount;
  private List<ExcelColumn> colspan;

  public ExcelHead()
  {
    this.rowCount = 1;
  }

  public List<ExcelColumn> getColspan()
  {
    return this.colspan;
  }

  public void setColspan(List<ExcelColumn> colspan) {
    this.colspan = colspan;
  }

  public List<ExcelColumn> getColumns() {
    return this.columns;
  }

  public int getRowCount() {
    return this.rowCount;
  }

  public int getColumnCount() {
    return this.columnCount;
  }

  public void setColumns(List<ExcelColumn> columns) {
    this.columns = columns;
  }

  public void setRowCount(int rowCount) {
    this.rowCount = rowCount;
  }

  public void setColumnCount(int columnCount) {
    this.columnCount = columnCount;
  }

  public Map<String, Map> getColumnsConvertMap() {
    return this.columnsConvertMap;
  }

  public void setColumnsConvertMap(Map<String, Map> columnsConvertMap) {
    this.columnsConvertMap = columnsConvertMap;
  }

  public String toString()
  {
    return "ExcelHead [columnCount=" + this.columnCount + ", columns=" + this.columns + ", columnsConvertMap=" + this.columnsConvertMap + ", rowCount=" + this.rowCount + "]";
  }
}