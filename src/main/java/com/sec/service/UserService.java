package com.sec.service;

import com.sec.model.User;

public interface UserService {

    public String registerUser(User user);

    public User findByEmail(String email);

    public String userActivation(String code);

}