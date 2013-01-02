/**
 * 
 */
package com.nianway.front.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nianway.core.BizConstants;
import com.nianway.core.dao.HomeDaoImpl;
import com.nianway.core.mapping.Blog;

/**
 * Ê×Ò³¿ØÖÆÆ÷
 * 
 * @author zhou
 * 
 */
@Controller
public class HomeController {

	private static final String VIEW_NAME = "front/index";

	@Autowired
	private HomeDaoImpl homeDao;

	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request,
			HttpServletResponse response) {
		List<Blog> blogList = homeDao.queryBlog(BizConstants.BLOG_CATEGORY_ID);
		ModelMap model = new ModelMap();
		model.addAttribute("blogList", blogList);
		return new ModelAndView(VIEW_NAME, model);
	}

}
