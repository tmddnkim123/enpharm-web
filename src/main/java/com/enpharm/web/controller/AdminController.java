package com.enpharm.web.controller;

import com.enpharm.web.dto.CategoryDTO;
import com.enpharm.web.dto.ContactDTO;
import com.enpharm.web.dto.UserDTO;
import com.enpharm.web.service.ArticleService;
import com.enpharm.web.service.CategoryService;
import com.enpharm.web.service.ContactService;
import com.enpharm.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AdminController {
    @Autowired
    ContactService contactService;
    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/contact/")
    public String contact(@RequestParam(value="field", required=false) String field,
                          @RequestParam(value="order", required=false) String order,
                          Model model) throws Exception {
        List<CategoryDTO> categoryList = categoryService.selectCategoryList();
        List<ContactDTO> contactList;

        if(field == null)
            field = "idx";

        if(order == null)
            order = "desc";

        contactList = contactService.selectContactList(field, order);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("contactList", contactList);

        return "thymeleaf/admin/contact";
    }

    @GetMapping("/contact/{idx}")
    public String contactDetail(@PathVariable("idx") Long idx, Model model) throws Exception {
        List<CategoryDTO> categoryList = categoryService.selectCategoryList();
        ContactDTO contactDTO = contactService.selectContactDetail(idx);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("contact", contactDTO);
        return "thymeleaf/admin/contact_detail";
    }

    @GetMapping("/article/{categoryIdx}")
    public String article(@PathVariable("categoryIdx") Long categoryIdx,
                          Model model) throws Exception {
        List<CategoryDTO> categoryList = categoryService.selectCategoryList();
        CategoryDTO categoryDTO = categoryService.selectCategoryDetail(categoryIdx);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("currentCategory", categoryDTO);
        return "thymeleaf/admin/article_edit";
    }

    @PostMapping("/article/{categoryIdx}/new")
    public String newArticle(@PathVariable("categoryIdx") Long categoryIdx,
                             @RequestParam("title") String title,
                             @RequestParam("contents") String contents,
                             @RequestParam(value="file", required=false) MultipartFile file,
                             RedirectAttributes redirAttrs,
                             Authentication authentication) {

        String userName = authentication.getName();
        UserDTO userDTO = (UserDTO)userService.loadUserByUsername(userName);
        try {
            articleService.insertArticle(categoryIdx, title, contents, file, userDTO);
        } catch (Exception e) {
            e.printStackTrace();
            redirAttrs.addFlashAttribute("message", "게시글 등록이 실패했습니다.");
            return "redirect:/";
        }
        redirAttrs.addFlashAttribute("message", "게시글 등록이 성공했습니다.");
        return "redirect:/";
    }
}
