package com.bobo.service.impl;


import com.bobo.common.pojo.EUDataGridResult;
import com.bobo.common.pojo.TaotaoResult;
import com.bobo.common.utils.IDUtils;
import com.bobo.mapper.TbItemDescMapper;
import com.bobo.mapper.TbItemMapper;
import com.bobo.mapper.TbItemParamItemMapper;
import com.bobo.mapper.TbItemParamMapper;
import com.bobo.pojo.*;
import com.bobo.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 商品管理service
 */
@Service
public class  ItemServiceImpl implements ItemService{


	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper descMapper;
	@Autowired
	private TbItemParamMapper paramMapper;
	@Autowired
	private TbItemParamItemMapper ppMapper;

	@Override
	public TbItem getItemById(long itemId) {

		TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);

		//设置查询条件--如过不设置查询条件就全查
		TbItemExample e =new TbItemExample();

		//设置具体查询条件
		TbItemExample.Criteria criteria = e.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> tbItems = tbItemMapper.selectByExample(e);
		if(tbItems!=null && tbItems.size()>0){
			TbItem tbItem1=tbItems.get(0);
			return tbItem1;
		}
		return null;
	}

	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		//设置查询条件--如过不设置查询条件就全查
		TbItemExample e =new TbItemExample();
		//分页设置
		PageHelper.startPage(page, rows);
		List<TbItem> list = tbItemMapper.selectByExample(e);
		//创建一个返回值对象
		EUDataGridResult result=new EUDataGridResult();
		result.setRows(list);
		//去记录总条数
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult getItemParamList(int page, int rows) {
		//设置查询条件--如过不设置查询条件就全查
		TbItemParamExample e =new TbItemParamExample();
		//分页设置
		PageHelper.startPage(page, rows);
		List<TbItemParam> list = paramMapper.selectByExample(e);
		//创建一个返回值对象
		EUDataGridResult result=new EUDataGridResult();
		result.setRows(list);
		//去记录总条数
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception {
		//item补全
		//生成商品id
		long itemId = IDUtils.genItemId();
		item.setId(itemId);
		//商品状态 1-正常 2-下架 3-删除
		item.setStatus((byte)1);
		item.setUpdated(new Date());
		item.setCreated(new Date());
		tbItemMapper.insert(item);
		//添加商品描述信息
		TaotaoResult taotaoResult = insertItemDesc(itemId, desc);
		TaotaoResult result = insertItemParam(itemId, itemParam);
		if(taotaoResult.getStatus()==200 && result.getStatus()==200){
			return TaotaoResult.ok();
		}
		//抛异常-便于事务回滚
		throw new Exception();
	}

	/**
	 * 添加商品描述
	 * @param desc
	 */
	private TaotaoResult insertItemDesc(long itemId,String desc ){
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setUpdated(new Date());
	    descMapper.insert(tbItemDesc);
	    return TaotaoResult.ok();
	}

	/**
	 * 添加商品规格参数信息
	 * @param
	 */
	private TaotaoResult insertItemParam(long itemId,String itenParam ){
		TbItemParamItem tbItemDesc = new TbItemParamItem();
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setParamData(itenParam);
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setUpdated(new Date());
		ppMapper.insert(tbItemDesc);
	    return TaotaoResult.ok();
	}
}
