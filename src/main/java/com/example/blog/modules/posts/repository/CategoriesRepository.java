package com.example.blog.modules.posts.repository;

import com.example.blog.modules.posts.model.Category;
import com.example.blog.modules.posts.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Category,Long> {

// Posts findByEmail(String email);
// @Query("select u from Users u where u.email =:eMail")
// Users emailuser(@Param("eMail") String email);
}
