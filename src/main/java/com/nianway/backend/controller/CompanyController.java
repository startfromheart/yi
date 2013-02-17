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

import com.nianway.core.dao.CompanyDaoImpl;
import com.nianway.core.mapping.Company;

/**
 * @author zhou
 * 
 */
@Controller
public class CompanyController {

	private static final String VIEW_NAME = "backend/company/company";
	private static final String ADD_NAME = "companyAdd";
	private static final String EDIT_NAME = "companyEdit";

	@Autowired
	private CompanyDaoImpl companyDao;

	@RequestMapping(value = "/backend/company.htm", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request,
			HttpServletResponse response) {
		List<Company> companyList = companyDao.queryCompany();
		ModelMap model = new ModelMap();
		model.addAttribute("companyList", companyList);
		return new ModelAndView(VIEW_NAME, model);
	}

	@RequestMapping(value = "/backend/company.htm", method = RequestMethod.GET, params = "act=add")
	public ModelAndView showAdd(HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap model = new ModelMap();
		return new ModelAndView(ADD_NAME, model);
	}

	@RequestMapping(value = "/backend/company.htm", method = RequestMethod.POST, params = "act=add")
	public ModelAndView postAdd(
			@RequestParam("companyName") String companyName,
			@RequestParam("companyIntroduce") String companyIntroduce) {

		Company company = new Company();
		company.setCompanyName(companyName);
		company.setCompanyIntroduce(companyIntroduce);
		companyDao.add(company);

		List<Company> companyList = companyDao.queryCompany();
		ModelMap model = new ModelMap();
		model.addAttribute("companyList", companyList);
		return new ModelAndView(VIEW_NAME, model);
	}

	@RequestMapping(value = "/backend/company.htm", method = RequestMethod.GET, params = "act=edit")
	public ModelAndView showEdit(@RequestParam("companyId") String companyId) {
		Company company = companyDao.queryCompanyById(companyId);
		ModelMap model = new ModelMap();
		model.addAttribute("company", company);
		return new ModelAndView(EDIT_NAME, model);
	}

	@RequestMapping(value = "/backend/company.htm", method = RequestMethod.POST, params = "act=edit")
	public ModelAndView postEdit(@RequestParam("companyId") String companyId,
			@RequestParam("companyName") String companyName,
			@RequestParam("companyIntroduce") String companyIntroduce) {
		
		Company company = companyDao.queryCompanyById(companyId);
		company.setCompanyName(companyName);
		company.setCompanyIntroduce(companyIntroduce);
		
		companyDao.edit(company);
		
		List<Company> companyList = companyDao.queryCompany();
		ModelMap model = new ModelMap();
		model.addAttribute("companyList", companyList);
		return new ModelAndView(VIEW_NAME, model);
	}

}
