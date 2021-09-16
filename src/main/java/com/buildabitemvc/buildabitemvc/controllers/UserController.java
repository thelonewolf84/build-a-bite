package com.buildabitemvc.buildabitemvc.controllers;

import com.buildabitemvc.buildabitemvc.data.UserRepository;
import com.buildabitemvc.buildabitemvc.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("profile")
public class UserController {

    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    UserRepository userRepository;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping("/{userId}")
    public String renderProfilePage(Model model, @PathVariable int userId, HttpServletRequest request){
        HttpSession session = request.getSession();
        User sessionUser = authenticationController.getUserFromSession(session);
        int id = sessionUser.getId();

        model.addAttribute("profile", userRepository.findById(id).get());
        model.addAttribute("user", new User());

        return "users/profile";
    }

    @PostMapping("/{userId}")
    public String updateProfile(@RequestParam(required = false) String profileImage,
                                @RequestParam(required = false) String email,
                                @RequestParam(required = false) String password,
                                Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User sessionUser = authenticationController.getUserFromSession(session);
        int id = sessionUser.getId();
        User user = userRepository.findById(id).get();

        if(!profileImage.equals("")){
            user.setProfileImage(profileImage);
        }

        if(!(email == null || email.equals(""))){
            user.setEmail(email);
        }

        if(!(password == null || password.equals(""))){
            user.setPwHash(encoder.encode(password));
        }

        userRepository.save(user);
        return "redirect:/profile/{userId}";
    }
}
