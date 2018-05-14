package com.bobo.common.pojo;
/**
 * easyUI树形控件节点格式
 * @version 1.0
 */
public class EUTreeNode {

	/**
	 *类目id
	 */
	private long id;
	/**
	 *类目内容
	 */

	private String text;
	/**
	 *类目状态
	 */
	private String state;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
