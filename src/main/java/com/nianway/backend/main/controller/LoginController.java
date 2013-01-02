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
public class LoginController {

	private static final String VIEW_NAME = "backend/main/login";

	@Autowired
	private StarDaoImpl starDao;

	@RequestMapping(value = "/backend/login.htm", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap model = new ModelMap();
		return new ModelAndView(VIEW_NAME, model);
	}

	@RequestMapping(value = "/login-processing.htm")
	public ModelAndView doLogin(HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap model = new ModelMap();
		return new ModelAndView(VIEW_NAME, model);
	}

}
