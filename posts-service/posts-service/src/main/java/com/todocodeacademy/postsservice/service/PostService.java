package com.todocodeacademy.postsservice.service;

import com.todocodeacademy.postsservice.model.Post;
import com.todocodeacademy.postsservice.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService{

    @Autowired
    private IPostRepository repoPost;

    @Override
    public List<Post> getPostsByUser(Long user_id) {

        return repoPost.findPostByUserId(user_id);
    }
}
