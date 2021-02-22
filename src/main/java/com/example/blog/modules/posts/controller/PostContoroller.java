package com.example.blog.modules.posts.controller;


import com.example.blog.modules.posts.model.Posts;
import com.example.blog.modules.posts.service.CategoriesService;
import com.example.blog.modules.posts.service.PostService;
import com.example.blog.modules.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;

@Controller
public class PostContoroller {

    private PostService postService;
    private CategoriesService categoriesService;
    private UserService userService;


    @Autowired
    public PostContoroller(PostService postService,CategoriesService categoriesService,UserService userService) {
        this.postService = postService;
        this.categoriesService = categoriesService;
        this.userService = userService;
    }


    @RequestMapping(value = "/view/posts",method = RequestMethod.GET)
    public String showregisterpost(){
        return "posts/registerPosts";
    }


    @RequestMapping(value = "/addpost",method = RequestMethod.POST)
    public String addPost(@ModelAttribute Posts posts, Principal principal) throws IOException, InvocationTargetException, IllegalAccessException {
        posts.setUser(userService.findByEmail(principal.getName())) ;
        postService.register(posts);
        return "redirect:/posts";
    }
    @RequestMapping(value = "/registerPosts",method = RequestMethod.GET)
    public String registerPosts(Model model){
        model.addAttribute("categories",categoriesService.getAllCategories());
        model.addAttribute("posts",new Posts());
        return "posts/registerPosts";
    }

    @RequestMapping(value = "/posts",method = RequestMethod.GET)
    public String postspage(@ModelAttribute("p") Posts posts,
                            Model model, @PageableDefault(size = 10) Pageable pageable){
        model.addAttribute("categories",categoriesService.getAllCategories());
        model.addAttribute("posts",postService.getAllPost(pageable));
        return "posts/posts";
    }


//    @RequestMapping(value = "/posts",method = RequestMethod.GET)
//    public String postspages(@ModelAttribute Posts posts,Model model, @PageableDefault(size = 10) Pageable pageable){
//        model.addAttribute("categories",categoriesService.getAllCategories());
//        model.addAttribute("posts",postService.findBySearch(posts,pageable));
//        return "posts/posts";
//    }

    @RequestMapping(value = "/deletepost/{id}",method = RequestMethod.GET)
    public String deletepage(Model model, @PathVariable("id") Long id  ){
        postService.deleteById(id);
        return "redirect:/posts";
    }
    @RequestMapping(value = "/editpost/{id}",method = RequestMethod.GET)
    public String editpage(Model model, @PathVariable("id") Long id  ){
        model.addAttribute("categories",categoriesService.getAllCategories());
        model.addAttribute("posts",postService.findById(id));
        return "posts/registerPosts";
    }
//    @RequestMapping(value = "bookSearch/{x}",method = RequestMethod.GET)
//    public @ResponseBody List<Posts> getPostx(@ModelAttribute Posts posts){
//        return  postService.findBySearch(posts);
//
//    }

}
