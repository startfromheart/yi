/**
 * 
 */
package com.nianway.backend.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nianway.core.dao.StarDaoImpl;

/**
 * @author zhou
 * 
 */
@Controller
public class HeadController {

	private static final String VIEW_NAME = "backend/main/head";

	@Autowired
	private StarDaoImpl starDao;

	@RequestMapping(value = "/backend/head.htm", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request,
			HttpServletResponse response) {
		//获取用户名称
		
		
		ModelMap model = new ModelMap();
		
		
		return new ModelAndView(VIEW_NAME, model);
	}

}
