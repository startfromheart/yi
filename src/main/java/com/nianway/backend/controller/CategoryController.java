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

import com.nianway.core.dao.CategoryDaoImpl;
import com.nianway.core.mapping.Category;

/**
 * @author zhou
 * 
 */
@Controller
public class CategoryController {

	private static final String VIEW_NAME = "category";
	private static final String ADD_NAME = "categoryAdd";
	private static final String EDIT_NAME = "categoryEdit";

	@Autowired
	private CategoryDaoImpl categoryDao;

	@RequestMapping(value = "/backend/category.htm", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request,
			HttpServletResponse response) {
		List<Category> categoryList = categoryDao.queryCategory();
		ModelMap model = new ModelMap();
		model.addAttribute("categoryList", categoryList);
		return new ModelAndView(VIEW_NAME, model);
	}

	@RequestMapping(value = "/backend/category.htm", method = RequestMethod.GET, params = "act=add")
	public ModelAndView showAdd(HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap model = new ModelMap();
		return new ModelAndView(ADD_NAME, model);
	}

	@RequestMapping(value = "/backend/category.htm", method = RequestMethod.POST, params = "act=add")
	public ModelAndView postAdd(@RequestParam("categoryName") String categoryName,
			@RequestParam("categoryIntroduce") String categoryIntroduce) {

		Category category = new Category();
		category.setCategoryName(categoryName);
		category.setCategoryIntroduce(categoryIntroduce);
		categoryDao.add(category);

		List<Category> categoryList = categoryDao.queryCategory();
		ModelMap model = new ModelMap();
		model.addAttribute("categoryList", categoryList);
		return new ModelAndView(VIEW_NAME, model);
	}

	@RequestMapping(value = "/backend/category.htm", method = RequestMethod.GET, params = "act=edit")
	public ModelAndView showEdit(@RequestParam("categoryId") String categoryId) {
		Category category = categoryDao.queryCategoryById(categoryId);
		ModelMap model = new ModelMap();
		model.addAttribute("category", category);
		return new ModelAndView(EDIT_NAME, model);
	}

	@RequestMapping(value = "/backend/category.htm", method = RequestMethod.POST, params = "act=edit")
	public ModelAndView postEdit(@RequestParam("categoryId") String categoryId,
			@RequestParam("categoryName") String categoryName,
			@RequestParam("categoryIntroduce") String categoryIntroduce) {

		Category category = categoryDao.queryCategoryById(categoryId);
		category.setCategoryName(categoryName);
		category.setCategoryIntroduce(categoryIntroduce);

		categoryDao.edit(category);

		List<Category> categoryList = categoryDao.queryCategory();
		ModelMap model = new ModelMap();
		model.addAttribute("categoryList", categoryList);
		return new ModelAndView(VIEW_NAME, model);
	}

}
