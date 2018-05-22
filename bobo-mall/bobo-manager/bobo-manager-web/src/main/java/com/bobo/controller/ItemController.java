package com.bobo.controller;

import com.bobo.common.pojo.EUDataGridResult;
import com.bobo.common.pojo.TaotaoResult;
import com.bobo.pojo.TbItem;
import com.bobo.pojo.TbItemParam;
import com.bobo.service.ItemParamItemService;
import com.bobo.service.ItemParamService;
import com.bobo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    private ItemParamService paramService;
    @Autowired
    private ItemParamItemService service;

    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;
    }
    @RequestMapping("/test/{itemId}")
    public String  showItemParam(@PathVariable Long itemId, Model md) {
        String tbItem = service.getItemParamByItemId(itemId);
        md.addAttribute("itemParam",tbItem);
        return "item";
    }

    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows) {
        EUDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }
    @RequestMapping("/param/list")
    @ResponseBody
    public EUDataGridResult getItemParamList(Integer page, Integer rows) {
        EUDataGridResult result = itemService.getItemParamList(page, rows);
        return result;
    }

    /**
     * 查询规格参数
     * @return
     */
    @RequestMapping("/param/query/itemcatid/{itemCatId}")
    @ResponseBody
    public TaotaoResult getItemCatId(@PathVariable Long itemCatId) {
        TaotaoResult result = paramService.getItemParamByID(itemCatId);
        return result;
    }

    /**
     * 规格参数保存
     * @return
     */
    @RequestMapping(value = "/param/save/{cid}",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult saveItemParam(@PathVariable Long cid,String paramData) {
        TaotaoResult result = null;
        TbItemParam itemParam=new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        try {
            result = paramService.insertParam(itemParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public TaotaoResult saveItem(TbItem item,String desc,String itemParams) {
        TaotaoResult result = null;
        try {
            result = itemService.createItem(item,desc,itemParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
