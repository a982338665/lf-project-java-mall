package com.bobo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					response.sendRedirect("http://www.baidu.com");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();*/
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
