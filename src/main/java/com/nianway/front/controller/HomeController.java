/**
 * 
 */
package com.nianway.front.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * ��ҳ������
 * 
 * @author zhou
 * 
 */
@Controller
public class HomeController {

	private static final String VIEW_NAME = "front/index";

	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap model = new ModelMap();
		return new ModelAndView(VIEW_NAME, model);
	}

}
