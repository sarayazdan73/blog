package com.example.blog.modules.users.repository;

import com.example.blog.modules.users.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

 Users findByEmail(String email);
 @Query("select u from Users u where u.email =:eMail")
 Users emailuser(@Param("eMail") String email);
}
