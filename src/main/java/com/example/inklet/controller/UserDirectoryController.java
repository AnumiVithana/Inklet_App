package com.example.inklet.controller;

import com.example.inklet.dto.UserDTO;
import com.example.inklet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserDirectoryController {
    private final UserService userService;

    @GetMapping("/directory")
    public String showUserDirectory(Model model, Authentication auth) {
        String email = ((UserDetails) auth.getPrincipal()).getUsername();
        List<UserDTO> users = userService.getAllUsersExcept(email);
        model.addAttribute("users", users);
        return "directory";
    }
}