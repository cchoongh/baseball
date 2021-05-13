//package com.team18.baseball.controller;
//
//import com.team18.baseball.entity.User;
//import com.team18.baseball.repository.UserRepository;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpSession;
//
//@RestController
//public class UserController {
//    private final UserRepository userRepository;
//    public static final String USER_SESSION_KEY = "sessionedUser";
//
//    public UserController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @PostMapping("/users/{id}")
//    public String login(@PathVariable Long id, HttpSession session) {
//        User user = userRepository.findById(id).orElseThrow(IllegalStateException::new);
//        session.setAttribute(USER_SESSION_KEY, user);
//        return "Login Success!";
//    }
//}
