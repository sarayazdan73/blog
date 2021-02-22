package com.example.blog.modules.posts.controller;

import com.example.blog.modules.posts.model.Category;
import com.example.blog.modules.posts.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/categories")
public class CategoriesContoroller {

    private CategoriesService categoriesService;
    @Autowired
    public CategoriesContoroller(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @RequestMapping(value = "/addcategory",method = RequestMethod.POST)
    public String addCategory(@ModelAttribute @Valid Category category, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "categories/registerCategories";
        }

        categoriesService.register(category);
        return "redirect:/categories";
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String deletepage(Model model, @PathVariable("id") Long id  ){
        categoriesService.deleteById(id);
        return "redirect:/categories";
    }
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String editpage(Model model, @PathVariable("id") Long id  ){
        model.addAttribute("category",categoriesService.findById(id));
        return "categories/registerCategories";
    }


}
