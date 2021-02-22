package com.example.blog.modules.users.model;

import com.example.blog.enums.Roles;
import com.example.blog.modules.posts.model.Posts;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "name")
public class Users implements Serializable {
    @Id
    @GeneratedValue
    private  Long id;

    private boolean enable= true;

    @ElementCollection(targetClass = Roles.class)
    @CollectionTable(name = "authorities",joinColumns =@JoinColumn(name = "email",referencedColumnName = "email") )
    @Enumerated(EnumType.STRING)
    private List<Roles> roles;

    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @Transient
    @JsonIgnore
    private MultipartFile file;

    @Column(nullable = false)
    private String name;
    @Column
    private String cover;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user")
    private List<Posts> posts;

    public Users() {
    }


    public Users(Long id, String email, String password, String name, String cover, LocalDateTime createdAt, LocalDateTime updatedAt,List<Posts> posts) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.cover = cover;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.posts = posts;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
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

    public List<Posts> getPosts() {
        return posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

}
