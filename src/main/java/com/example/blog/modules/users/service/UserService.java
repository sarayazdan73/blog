package com.example.blog.modules.users.service;

import com.example.blog.MyBeanCopy;
import com.example.blog.modules.users.model.Users;
import com.example.blog.modules.users.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {
  private UserRepository userRepository;

  @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public Users register(Users user) throws IOException, InvocationTargetException, IllegalAccessException {
      if (!user.getFile().isEmpty()) {
        String path = ResourceUtils.getFile("classpath:static/img").getAbsolutePath();
        byte[] bytes = user.getFile().getBytes();
        String name = UUID.randomUUID() + Objects.requireNonNull(user.getFile().getContentType()).split("/")[1];
        Files.write(Paths.get(path + File.separator + name), bytes);
        user.setCover(name);
      }
      if (!user.getPassword().isEmpty()) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
      }
      if(user.getId()!=null){
        Users exist=userRepository.getOne(user.getId());
        MyBeanCopy myBeanCopy=new MyBeanCopy();
        myBeanCopy.copyProperties(exist,user);
        return userRepository.save(exist);
      }

      return userRepository.save(user);
    }
    public List<Users> getAllusers(){
      return userRepository.findAll();
    }

    public Users findById(Long id) {
      return userRepository.getOne(id);
    }

  @PreAuthorize("#users.email!=authentication.name")
  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }

    public Users findByEmail(String email) {
    return userRepository.findByEmail(email);
    }
}
