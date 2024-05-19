package com.todocodeacademy.postsservice.service;

import com.todocodeacademy.postsservice.model.Post;

import java.util.List;

public interface IPostService {

    public List<Post> getPostsByUser(Long user_id);
}
