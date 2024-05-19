package com.example.usersservice.service;

import com.example.usersservice.dto.PostDTO;
import com.example.usersservice.dto.UserDTO;
import com.example.usersservice.model.User;
import com.example.usersservice.repository.IPostAPI;
import com.example.usersservice.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private IPostAPI apiPosts;

    @Override
    public UserDTO getUserAndPosts(Long user_id) {

        //Datos del usuario de la BD
        User user = userRepo.findById(user_id).orElse(null);

        //Una vez que tengo los datos necesito los POSTEOS
        //Traigo posteos desde la APi de posteos
        List<PostDTO> postsList = apiPosts.getPostsByUserId(user_id);

        //Unificar datos del usuario + posteos
        //User DTO
        UserDTO userDTO = new UserDTO(user.getUser_id(), user.getName(),
        user.getLastname(), user.getCellphone(), postsList);

        return userDTO;
    }
}
