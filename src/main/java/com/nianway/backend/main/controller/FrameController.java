/**
 * 
 */
package com.nianway.backend.main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nianway.core.dao.StarDaoImpl;
import com.nianway.core.mapping.Star;

/**
 * @author zhou
 * 
 */
@Controller
public class FrameController {

	private static final String VIEW_NAME = "backend/main/index";

	@Autowired
	private StarDaoImpl starDao;

	@RequestMapping(value = "/backend/index.htm", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request,
			HttpServletResponse response) {
		List<Star> maleStarList = starDao.queryStar();
		ModelMap model = new ModelMap();
		model.addAttribute("maleStarList", maleStarList);
		return new ModelAndView(VIEW_NAME, model);
	}

}
