package com.enpharm.web.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.enpharm.web.service.ContactService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contact/")
@MapperScan(basePackages="com.enpharm.web.dao")
public class ContactController {
    @Autowired
    ContactService contactService;

    @PostMapping("/new")
    public String newContact(@RequestParam("name") String name,
                             @RequestParam("email") String email,
                             @RequestParam("contents") String contents,
                             RedirectAttributes redirAttrs) {
        try {
            contactService.insertContact(name, email, contents);
        }
        catch (Exception e) {
            e.printStackTrace();
            redirAttrs.addFlashAttribute("message", "문의 등록이 실패했습니다.");
            return "redirect:/";
        }
        redirAttrs.addFlashAttribute("message", "문의가 등록되었습니다.");
        return "redirect:/";
    }
}
