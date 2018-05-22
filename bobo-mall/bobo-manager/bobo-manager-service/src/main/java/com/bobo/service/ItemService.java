package com.bobo.service;


import com.bobo.common.pojo.EUDataGridResult;
import com.bobo.common.pojo.TaotaoResult;
import com.bobo.pojo.TbItem;

public interface ItemService {

    /**
     * 查询商品详情根据ID
     * @param itemId
     * @return
     */
	TbItem getItemById(long itemId);

    /**
     * 查询商品列表--分页插件实现
     * @param page
     * @param rows
     * @return
     */
	EUDataGridResult getItemList(int page, int rows);
    /**
     * 查询商品规格参数列表--分页插件实现
     * @param page
     * @param rows
     * @return
     */
	EUDataGridResult getItemParamList(int page, int rows);


    /**
     * 添加商品信息
     * @param item
     * @param desc
     * @param itemParam
     * @return
     * @throws Exception
     */
	TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception;
}
