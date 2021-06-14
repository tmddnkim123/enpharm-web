package com.enpharm.web.controller;
import com.enpharm.web.dto.ArticleDTO;
import com.enpharm.web.dto.CategoryDTO;
import com.enpharm.web.service.ArticleService;
import com.enpharm.web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ArticleService articleService;

    @RequestMapping("/")
    public String index(Model model) throws Exception {
        List<CategoryDTO> categoryList = categoryService.selectCategoryList();
        List<ArticleDTO> recentArticle = articleService.selectRecentArticle();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("recentArticle", recentArticle);
        return "thymeleaf/index";
    }

    @GetMapping("/company-info")
    public String companyInfo(Model model) throws Exception {
        List<CategoryDTO> categoryList = categoryService.selectCategoryList();
        model.addAttribute("categoryList", categoryList);
        return "thymeleaf/company-info";
    }

    @GetMapping("/contact")
    public String contact(Model model) throws Exception {
        List<CategoryDTO> categoryList = categoryService.selectCategoryList();
        model.addAttribute("categoryList", categoryList);
        return "thymeleaf/contact";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(name="error", required=false) String error, Model model) {

        if(error != null && error.equals("true"))
            model.addAttribute("error", "ID 또는 Password 가 올바르지 않습니다.");

        return "thymeleaf/login";
    }
}
