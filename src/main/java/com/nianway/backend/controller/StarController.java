/**
 * 
 */
package com.nianway.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nianway.core.dao.StarDaoImpl;
import com.nianway.core.mapping.Star;

/**
 * @author zhou
 * 
 */
@Controller
public class StarController {

	private static final String VIEW_NAME = "star";
	private static final String ADD_NAME = "starAdd";
	private static final String EDIT_NAME = "starEdit";

	@Autowired
	private StarDaoImpl starDao;

	@RequestMapping(value = "/backend/star.htm", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request,
			HttpServletResponse response) {
		List<Star> starList = starDao.queryStar();
		ModelMap model = new ModelMap();
		model.addAttribute("starList", starList);
		return new ModelAndView(VIEW_NAME, model);
	}

	@RequestMapping(value = "/backend/star.htm", method = RequestMethod.GET, params = "act=add")
	public ModelAndView showAdd(HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap model = new ModelMap();
		return new ModelAndView(ADD_NAME, model);
	}

	@RequestMapping(value = "/backend/star.htm", method = RequestMethod.POST, params = "act=add")
	public ModelAndView postAdd(@RequestParam("starName") String starName,
			@RequestParam("starAliasName1") String starAliasName1,
			@RequestParam("starAliasName2") String starAliasName2,
			@RequestParam("sex") int sex,
			@RequestParam("starIntroduce") String starIntroduce) {

		Star star = new Star();
		star.setStarName(starName);
		star.setStarIntroduce(starIntroduce);
		star.setCompanyId("1");
		star.setCountry("中国");
		star.setStarAliasName1(starAliasName1);
		star.setStarAliasName2(starAliasName2);
		star.setSex(sex);
		starDao.add(star);

		List<Star> starList = starDao.queryStar();
		ModelMap model = new ModelMap();
		model.addAttribute("starList", starList);
		return new ModelAndView(VIEW_NAME, model);
	}

	@RequestMapping(value = "/backend/star.htm", method = RequestMethod.GET, params = "act=edit")
	public ModelAndView showEdit(@RequestParam("starId") String starId) {
		Star star = starDao.queryStarById(starId);
		ModelMap model = new ModelMap();
		model.addAttribute("star", star);
		return new ModelAndView(EDIT_NAME, model);
	}

	@RequestMapping(value = "/backend/star.htm", method = RequestMethod.POST, params = "act=edit")
	public ModelAndView postEdit(@RequestParam("starId") String starId,
			@RequestParam("starName") String starName,
			@RequestParam("starAliasName1") String starAliasName1,
			@RequestParam("starAliasName2") String starAliasName2,
			@RequestParam("sex") int sex,
			@RequestParam("starIntroduce") String starIntroduce) {

		Star star = starDao.queryStarById(starId);
		star.setStarName(starName);
		star.setStarIntroduce(starIntroduce);
		star.setCompanyId("1");
		star.setCountry("中国");
		star.setStarAliasName1(starAliasName1);
		star.setStarAliasName2(starAliasName2);
		star.setSex(sex);

		starDao.edit(star);

		List<Star> starList = starDao.queryStar();
		ModelMap model = new ModelMap();
		model.addAttribute("starList", starList);
		return new ModelAndView(VIEW_NAME, model);
	}

}
