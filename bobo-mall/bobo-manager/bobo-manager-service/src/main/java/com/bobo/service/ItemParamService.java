package com.bobo.service;


import com.bobo.common.pojo.TaotaoResult;
import com.bobo.pojo.TbItemParam;

public interface ItemParamService {

   public TaotaoResult getItemParamByID(long cid);

   public TaotaoResult insertParam(TbItemParam itemParam);
}
