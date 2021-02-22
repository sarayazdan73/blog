package com.example.blog.modules.posts.model;

import com.example.blog.modules.users.model.Users;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Posts {

        @Id
        @GeneratedValue
        private  Long id;
        @Column
        private String title;
        @Column
        private String body;
        @Transient
        @JsonIgnore
        private MultipartFile file;
        @Column
        private String cover;
        @Column(updatable = false)
        @CreationTimestamp
        private LocalDateTime createdAt;
        @Column
        @UpdateTimestamp
        private LocalDateTime updatedAt;

        @ManyToOne
        private Users user;

        @ManyToMany
        @JoinTable(name = "post_categories")
        private List<Category> categories;


    public Posts() {
    }

    public Posts(Long id, String title, String body, String cover, LocalDateTime createdAt, LocalDateTime updatedAt, Users user, List<Category> categories) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.cover = cover;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
        this.categories = categories;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
