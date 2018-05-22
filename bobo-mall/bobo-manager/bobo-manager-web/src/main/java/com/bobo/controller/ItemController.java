package com.bobo.controller;

import com.bobo.common.pojo.EUDataGridResult;
import com.bobo.common.pojo.TaotaoResult;
import com.bobo.pojo.TbItem;
import com.bobo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品管理controller
 * create by lishengbo on 2018-05-14 11:20
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;
    }

    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows) {
        EUDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }

    /**
     * 接收表单参数内容问题：
     *  1.使用pojo接收表单内容
     *  2.保证属性字段跟表单字段相同
     * @param item
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult saveItem(TbItem item) {
        TaotaoResult result = null;
        try {
            result = itemService.createItem(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
