package com.example.blog.modules;

import com.example.blog.modules.posts.model.Category;
import com.example.blog.modules.posts.service.CategoriesService;
import com.example.blog.modules.posts.service.PostService;
import com.example.blog.modules.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    private PostService postService;
    private UserService userService;
    private CategoriesService categoriesService;

    @Autowired
    public MainController(PostService postService,UserService userService,CategoriesService categoriesService) {
        this.postService = postService;
        this.userService = userService;
        this.categoriesService = categoriesService;
    }



    @RequestMapping("/home")
    public String homePage(Model model){
        model.addAttribute("posts",postService.getAllPost());
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginpage(){
        return "login";
    }

    @RequestMapping(value = "/403",method = RequestMethod.GET)
    public String erorpage(){
        return "403";
    }

    @RequestMapping(value = "/categories",method = RequestMethod.GET)
    public String categoriespage(Model model){
        model.addAttribute("categories",categoriesService.getAllCategories());
        return "categories/categories";
    }

    @RequestMapping(value = "/registerCategories" ,method = RequestMethod.GET)
    public String registerCategories(Model model){
        model.addAttribute("category",new Category());
        return "categories/registerCategories";
    }



}
