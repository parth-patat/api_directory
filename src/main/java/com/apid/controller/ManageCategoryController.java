package com.apid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.apid.model.ManageCategoryVO;
import com.apid.service.ManageCategoryService;

@Controller
public class ManageCategoryController {

	@Autowired
	ManageCategoryService manageCategoryService;

	@GetMapping(value = "addCategory")
	public ModelAndView addApi() {
		return new ModelAndView("admin/addCategory", "manageCategoryVO", new ManageCategoryVO()).addObject("formName",
				"Add new API Category");
	}

	@GetMapping(value = "insertCategory")
	public ModelAndView insertCategory(@ModelAttribute ManageCategoryVO manageCategoryVO) {
		manageCategoryService.insertCategory(manageCategoryVO);
		return new ModelAndView("redirect:addCategory");
	}

	@GetMapping(value = "viewCategory")
	public ModelAndView viewCategory() {
		List viewCategoryList = manageCategoryService.viewCategory();
		return new ModelAndView("admin/viewCategory", "viewCategoryList", viewCategoryList);
	}

	@GetMapping(value = "deleteCategory")
	public ModelAndView deleteCategory(@RequestParam("categoryId") int categoryId) {
		ManageCategoryVO manageCategoryVO = new ManageCategoryVO();
		manageCategoryVO.setCategoryId(categoryId);
		manageCategoryService.deleteCategory(manageCategoryVO);
		return new ModelAndView("redirect:viewCategory");
	}

	@GetMapping(value = "editCategory")
	public ModelAndView editCategory(@RequestParam("categoryId") int categoryId) {
		ManageCategoryVO manageCategoryVO = new ManageCategoryVO();
		manageCategoryVO.setCategoryId(categoryId);
		List editCategoryList = manageCategoryService.editCategory(manageCategoryVO);
		manageCategoryVO = (ManageCategoryVO) editCategoryList.get(0);
		return new ModelAndView("admin/addCategory", "manageCategoryVO", manageCategoryVO).addObject("formName",
				"Edit Api Category");
	}
}