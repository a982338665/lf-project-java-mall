package com.bobo.common.pojo;

import java.util.List;

/**
 * service接收dao层分页结果信息封装
 */
public class EUDataGridResult {
    /**
     * 数据总数
     */
    private long total;
    /**
     * 具体行数相关信息
     */
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

}
