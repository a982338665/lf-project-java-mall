package com.bobo.service.impl;

import com.bobo.common.pojo.TaotaoResult;
import com.bobo.mapper.TbItemParamMapper;
import com.bobo.pojo.TbItemParam;
import com.bobo.pojo.TbItemParamExample;
import com.bobo.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * create by lishengbo on 2018-05-22 15:40
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamMapper paramMapper;

    @Override
    public TaotaoResult getItemParamByID(long cid) {
        TbItemParamExample tbItemParamExample = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = tbItemParamExample.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
//        List<TbItemParam> list = paramMapper.selectByExample(tbItemParamExample);
        //包含大文本的查询
        List<TbItemParam> list = paramMapper.selectByExampleWithBLOBs(tbItemParamExample);
        if(list!=null&&list.size()>0){
            return TaotaoResult.ok(list.get(0));
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult insertParam(TbItemParam itemParam) {
        //补全ppojo
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        paramMapper.insert(itemParam);
        return TaotaoResult.ok();
    }
}
