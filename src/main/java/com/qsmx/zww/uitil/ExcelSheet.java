package com.qsmx.zww.uitil;

import java.util.Collection;

/**
 * Created by zww on 2019-04-09.
 * <p>
 * 用于汇出多个sheet的Vo
 */

public class ExcelSheet<T> {

    private String sheetName; // sheet的名字
    private String[] headers; // sheet的表头
    private Collection<T> dataset; // sheet的数据集

    /**
     * @return the sheetName
     */
    public String getSheetName() {
        return sheetName;
    }

    /**
     * @param sheetName the sheetName to set
     */
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    /**
     * @return the headers
     */
    public String[] getHeaders() {
        return headers;
    }

    /**
     * @param headers the headers to set
     */
    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    /**
     * @return the dataset
     */
    public Collection<T> getDataset() {
        return dataset;
    }

    /**
     * @param dataset the dataset to set
     */
    public void setDataset(Collection<T> dataset) {
        this.dataset = dataset;
    }

}

