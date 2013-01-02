/**
 * 
 */
package com.nianway.backend.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nianway.core.dao.ItemDaoImpl;

/**
 * @author zhou
 * 
 */
@Controller
public class ItemFileController {

	private static final Logger logger = LoggerFactory
			.getLogger(ItemFileController.class);

	private static final String VIEW_NAME = "itemUpload";
	
	private static final String RESULT_NAME = "result";

	@Autowired
	private ItemDaoImpl itemDao;

	@RequestMapping(value = "/backend/itemFile.htm", method = RequestMethod.GET, params = "act=file")
	public ModelAndView showUpload() {
		ModelMap model = new ModelMap();
		return new ModelAndView(VIEW_NAME, model);
	}

	@RequestMapping(value = "/backend/itemFile.htm", method = RequestMethod.POST, params = "act=upload")
	public ModelAndView postUpload(
			@RequestParam("itemFile") MultipartFile itemFile) {

		String fileName = null;
		String userHome = System.getProperty("user.home");
		String fileSeparator = System.getProperty("file.separator");
		try {
			if (!itemFile.isEmpty()) {
				itemFile.getContentType();
				fileName = itemFile.getOriginalFilename();
				itemFile.getSize();

				byte[] bytes = itemFile.getBytes();
				// 去理上传写文件代码
				OutputStream os = new FileOutputStream(userHome + fileSeparator
						+ fileName);
				os.write(bytes);

				os.close();
			}

		} catch (IOException e) {
			logger.error("上传文件失败", e);
		}

		ModelMap model = new ModelMap();
		model.put("fileName", fileName);
		return new ModelAndView(RESULT_NAME, model);
	}

}
