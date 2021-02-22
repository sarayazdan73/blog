package com.example.blog.modules.posts.service;

import com.example.blog.MyBeanCopy;
import com.example.blog.modules.posts.model.Posts;
import com.example.blog.modules.posts.repository.PostRepository;
import com.sun.xml.internal.ws.encoding.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class PostService {
  private PostRepository postRepository;

  @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Posts register(Posts posts) throws IOException, InvocationTargetException, IllegalAccessException {
      if (!posts.getFile().isEmpty()) {
        String path = ResourceUtils.getFile("classpath:static/img").getAbsolutePath();
        byte[] bytes = posts.getFile().getBytes();
        String name = UUID.randomUUID() + Objects.requireNonNull(posts.getFile().getContentType()).split("/")[1];
        Files.write(Paths.get(path + File.separator + name), bytes);
        posts.setCover(name);
      }

      if(posts.getId()!=null){
        Posts exist=postRepository.getOne(posts.getId());
        MyBeanCopy myBeanCopy=new MyBeanCopy();
        myBeanCopy.copyProperties(exist,posts);
        return postRepository.save(exist);
      }

      if(posts.getCover()==null){
        Path path = Paths.get("C:/pic/2.png");
        File file = path.toFile();
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(),
        ContentType.class.toString(), fileInputStream);
        posts.setFile(multipartFile);
        posts.setCover("1.png");
      }

      return postRepository.save(posts);
    }

  public List<Posts> getAllPost(){
    return  this.postRepository.findAll();
  }
    public Page<Posts> getAllPost(Pageable pageable){
      return  this.postRepository.findAll(pageable);
    }

  public Posts findById(Long id) {
    return postRepository.getOne(id);
  }

  public void deleteById(Long id) {
    postRepository.deleteById(id);
  }

//  public Page<Posts> findBySearch(Posts posts, Pageable pageable) {
//    return postRepository.findBySearch(posts, (posts.getCategories()!= null ? (long) posts.getCategories().size() : 0),pageable);
//  }
}
