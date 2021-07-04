package com.example.medicmongo.service;

import com.example.medicmongo.dto.UserJson;

import java.security.Principal;

public interface UserService {
    UserJson login(Principal principal);
}
