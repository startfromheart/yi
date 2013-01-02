/**
 * 
 */
package com.nianway.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nianway.core.dao.ItemDaoImpl;
import com.nianway.core.mapping.Item;
import com.nianway.core.vo.PageForm;
import com.nianway.core.vo.QueryResult;

/**
 * @author zhou
 * 
 */
@Controller
public class ItemController {

	private static final String VIEW_NAME = "item";
	private static final String ADD_NAME = "itemAdd";
	private static final String EDIT_NAME = "itemEdit";

	@Autowired
	private ItemDaoImpl itemDao;

	@RequestMapping(value = "/backend/item.htm", method = RequestMethod.GET)
	public ModelAndView show(
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
			@RequestParam(value = "orderBy", required = false, defaultValue = "itemName") String orderBy,
			@RequestParam(value = "ascending", required = false, defaultValue = "false") boolean ascending) {
		// 组织分页参数
		PageForm pageForm = new PageForm();
		pageForm.setPageNo(pageNo);
		pageForm.setAscending(ascending);
		pageForm.setOrderBy(orderBy);

		QueryResult queryResult = itemDao.queryItem(pageForm);
		ModelMap model = new ModelMap();
		model.put("queryResult", queryResult);
		model.put("pageVO", queryResult.getPageVO());
		model.put("itemList", queryResult.getResult());
		return new ModelAndView(VIEW_NAME, model);
	}

	@RequestMapping(value = "/backend/item.htm", method = RequestMethod.GET, params = "act=add")
	public ModelAndView showAdd(
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
			@RequestParam(value = "orderBy", required = false, defaultValue = "itemName") String orderBy,
			@RequestParam(value = "ascending", required = false, defaultValue = "false") boolean ascending) {
		// 组织分页参数
		PageForm pageVO = new PageForm();
		pageVO.setPageNo(pageNo);
		pageVO.setAscending(ascending);
		pageVO.setOrderBy(orderBy);

		ModelMap model = new ModelMap();
		model.put("pageVO", pageVO);
		return new ModelAndView(ADD_NAME, model);
	}

	@RequestMapping(value = "/backend/item.htm", method = RequestMethod.POST, params = "act=add")
	public ModelAndView postAdd(
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
			@RequestParam(value = "orderBy", required = false, defaultValue = "itemName") String orderBy,
			@RequestParam(value = "ascending", required = false, defaultValue = "false") boolean ascending,
			@RequestParam("categoryId") String categoryId,
			@RequestParam("itemName") String itemName,
			@RequestParam("itemUrl") String itemUrl,
			@RequestParam("itemIntroduce") String itemIntroduce) {

		// 组织分页参数
		PageForm pageVO = new PageForm();
		pageVO.setPageNo(pageNo);
		pageVO.setAscending(ascending);
		pageVO.setOrderBy(orderBy);

		Item item = new Item();
		item.setCategoryId(categoryId);
		item.setItemName(itemName);
		item.setItemUrl(itemUrl);
		item.setItemIntroduce(itemIntroduce);
		itemDao.add(item);

		QueryResult queryResult = itemDao.queryItem(pageVO);
		ModelMap model = new ModelMap();
		if (queryResult != null) {
			model.put("itemList", queryResult.getResult());
			model.put("pageVO", queryResult.getPageVO());
		}
		return new ModelAndView(VIEW_NAME, model);
	}

	@RequestMapping(value = "/backend/item.htm", method = RequestMethod.POST, params = "act=delete")
	public ModelAndView postDelete(
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
			@RequestParam(value = "orderBy", required = false, defaultValue = "itemName") String orderBy,
			@RequestParam(value = "ascending", required = false, defaultValue = "false") boolean ascending,
			@RequestParam(value = "ids", required = false, defaultValue = "") String[] ids) {

		// 组织分页参数
		PageForm pageVO = new PageForm();
		pageVO.setPageNo(pageNo);
		pageVO.setAscending(ascending);
		pageVO.setOrderBy(orderBy);

		itemDao.delete(ids);

		QueryResult queryResult = itemDao.queryItem(pageVO);
		ModelMap model = new ModelMap();
		model.addAttribute("queryResult", queryResult);
		return new ModelAndView(VIEW_NAME, model);
	}

	@RequestMapping(value = "/backend/item.htm", method = RequestMethod.GET, params = "act=edit")
	public ModelAndView showEdit(
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
			@RequestParam(value = "orderBy", required = false, defaultValue = "itemName") String orderBy,
			@RequestParam(value = "ascending", required = false, defaultValue = "false") boolean ascending,
			@RequestParam("itemId") String itemId) {

		// 组织分页参数
		PageForm pageVO = new PageForm();
		pageVO.setPageNo(pageNo);
		pageVO.setAscending(ascending);
		pageVO.setOrderBy(orderBy);

		Item item = itemDao.queryItemById(itemId);
		ModelMap model = new ModelMap();
		model.addAttribute("item", item);
		return new ModelAndView(EDIT_NAME, model);
	}

	@RequestMapping(value = "/backend/item.htm", method = RequestMethod.POST, params = "act=edit")
	public ModelAndView postEdit(
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
			@RequestParam(value = "orderBy", required = false, defaultValue = "itemName") String orderBy,
			@RequestParam(value = "ascending", required = false, defaultValue = "false") boolean ascending,
			@RequestParam("itemId") String itemId,
			@RequestParam("categoryId") String categoryId,
			@RequestParam("itemName") String itemName,
			@RequestParam("itemIntroduce") String itemIntroduce) {

		// 组织分页参数
		PageForm pageVO = new PageForm();
		pageVO.setPageNo(pageNo);
		pageVO.setAscending(ascending);
		pageVO.setOrderBy(orderBy);

		Item item = itemDao.queryItemById(itemId);
		item.setItemName(itemName);
		item.setItemIntroduce(itemIntroduce);

		itemDao.edit(item);

		QueryResult queryResult = itemDao.queryItem(pageVO);
		ModelMap model = new ModelMap();
		model.addAttribute("queryResult", queryResult);
		return new ModelAndView(VIEW_NAME, model);
	}

}
