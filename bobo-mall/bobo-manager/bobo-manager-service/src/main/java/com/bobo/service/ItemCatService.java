package com.bobo.service;


import com.bobo.common.pojo.EUTreeNode;

import java.util.List;

public interface ItemCatService {

	/**
	 * 根据父类目id查询类目列表
	 * @param parentId
	 * @return
	 */
	List<EUTreeNode> getCatList(long parentId);
}
