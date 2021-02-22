package com.example.blog.modules.users.controller;

import com.example.blog.modules.posts.model.Category;
import com.example.blog.modules.users.model.Users;
import com.example.blog.modules.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Controller

public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping(value ="/allusers" ,method = RequestMethod.GET)
    public @ResponseBody List<Users> getusers(){
        return userService.getAllusers();
    }


    @RequestMapping(value = "/adduser",method = RequestMethod.POST)
    public String addUser(@ModelAttribute Users users) throws IOException, InvocationTargetException, IllegalAccessException {
        userService.register(users);
        return "redirect:/users";
    }
    @RequestMapping("/registerUser")
    public String registerUser(){
        return "Users/registerUser";
    }

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public String userspage(Model model){
        model.addAttribute("users",userService.getAllusers());
        return "users/users";
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String editpage(Model model, @PathVariable("id") Long id  ){
        userService.deleteById(id);
        return "redirect:/users";
    }
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String deletepage(Model model, @PathVariable("id") Long id  ){
        model.addAttribute("user",userService.findById(id));
        return "users/registerUser";
    }



    @RequestMapping(value = "/registerUser" ,method = RequestMethod.GET)
    public String registerCategories(Model model){
        model.addAttribute("user",new Users());
        return "users/registerUser";
    }


}
