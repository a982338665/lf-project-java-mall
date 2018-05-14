package com.bobo.service.impl;


import com.bobo.common.pojo.EUDataGridResult;
import com.bobo.mapper.TbItemMapper;
import com.bobo.pojo.TbItem;
import com.bobo.pojo.TbItemExample;
import com.bobo.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品管理service
 */
@Service
public class  ItemServiceImpl implements ItemService{


	@Autowired
	private TbItemMapper tbItemMapper;

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
}
