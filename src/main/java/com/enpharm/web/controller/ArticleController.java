package com.enpharm.web.controller;

import com.enpharm.web.dto.ArticleDTO;
import com.enpharm.web.dto.CategoryDTO;
import com.enpharm.web.service.ArticleService;
import com.enpharm.web.service.CategoryService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@Controller
@RequestMapping("/article/")
@MapperScan(basePackages="com.enpharm.web.dao")
public class ArticleController {
    @Autowired
    ServletContext context;
    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/category/{categoryIdx}")
    public String articleList(@PathVariable("categoryIdx") Long categoryIdx,
                          Model model) throws Exception {
        List<ArticleDTO> articleList = articleService.selectArticleList(categoryIdx);
        CategoryDTO categoryDTO = categoryService.selectCategoryDetail(categoryIdx);
        List<CategoryDTO> categoryList = categoryService.selectCategoryList();
        model.addAttribute("currentCategory", categoryDTO);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("articleList", articleList);

        return "thymeleaf/article_list";
    }

    @GetMapping("/{articleIdx}")
    public String articleDetail(@PathVariable("articleIdx") Long articleIdx,
                                Model model) throws Exception {
        ArticleDTO articleDTO = articleService.selectArticleDetail(articleIdx);
        List<CategoryDTO> categoryList = categoryService.selectCategoryList();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("article", articleDTO);

        return "thymeleaf/article_detail";
    }

    @GetMapping("/download/{articleIdx}")
    public ResponseEntity<InputStreamResource> download(@PathVariable("articleIdx") Long articleIdx,
                           Model model) throws Exception {
        ArticleDTO articleDTO = articleService.selectArticleDetail(articleIdx);
        File file = new File(context.getRealPath("/") + "/" + articleDTO.getFilePath());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + articleDTO.getOriginalFileName())
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }
}
