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
public class MenuController {

	private static final String MENU_NAME = "backend/main/menu";

	private static final String SETTING_MENU_NAME = "backend/main/settingMenu";

	@Autowired
	private StarDaoImpl starDao;

	@RequestMapping(value = "/backend/menu.htm", method = RequestMethod.GET)
	public ModelAndView menu(HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap model = new ModelMap();
		return new ModelAndView(MENU_NAME, model);
	}

	@RequestMapping(value = "/settingMenu.htm", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap model = new ModelMap();
		return new ModelAndView(SETTING_MENU_NAME, model);
	}

}
