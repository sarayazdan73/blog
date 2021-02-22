package com.example.blog.modules.posts.service;

import com.example.blog.modules.posts.model.Category;
import com.example.blog.modules.posts.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoriesService {
  private CategoriesRepository catrgoresRepository;

  @Autowired
    public CategoriesService(CategoriesRepository catrgoresRepository) {
        this.catrgoresRepository = catrgoresRepository;
    }
    public Category register(Category category){
      return catrgoresRepository.save(category);
    }
    public List<Category> getAllCategories(){
      return catrgoresRepository.findAll();
    }

    public Category findById(Long id) {
      return catrgoresRepository.getOne(id);
    }

@Transactional
  public void deleteById(Long id) {
    catrgoresRepository.deleteById(id);
  }
}
