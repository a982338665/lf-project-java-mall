package com.bobo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 页面跳转controller
 * @version 1.0
 */
@Controller

public class PageController {


	/**
	 * 打开首页
	 */
	@RequestMapping("/")
	public String showIndex(final HttpServletResponse response) {
		return "index";
	}
	/**
	 * 展示其他页面
	 * <p>Title: showpage</p>
	 * <p>Description: </p>
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String showpage(@PathVariable String page) {
		return page;
	}
}
