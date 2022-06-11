package com.shopvalorant.Controller;

import com.shopvalorant.Exception.InternalServerError;
import com.shopvalorant.Exception.UserNotFoundException;
import com.shopvalorant.Model.Login;
import com.shopvalorant.Model.User;
import com.shopvalorant.Model.UserItems;
import com.shopvalorant.Repositories.implantation.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/my-profile")
    public String profile(HttpSession session, Model model) {
        if (session.getAttribute("login") == null)
            return "login-page";

        try {
            User user = (User) session.getAttribute("user");
            List<UserItems> items = user.getItems();

            model.addAttribute("user", user);
            model.addAttribute("items", items);
        } catch (NumberFormatException numberFormatException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id");
        }

        return "profile";
    }

    @PostMapping(path = "/register-user", produces = "text/plain")
    public String registerUser(HttpSession session, @ModelAttribute User user) {
        if (session.getAttribute("login") != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are already logined (CODE 400)");

        try {
            userRepository.save(user);
        } catch (InternalServerError internalServerError) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR");
        }

        return "index";
    }

    @PostMapping(path = "/login")
    public String loginUser(HttpSession session, @ModelAttribute Login login) {
        if (session.getAttribute("login") != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are already logined (CODE 400)");

        try {
            /* findByEmailAndPassword(String email, String password) */
            User user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
            session.setAttribute("user", user);
            session.setAttribute("login", true);
        } catch (InternalServerError internalServerError) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "HTTP Status will be INTERNAL_SERVER_ERROR (CODE 500)\n");
        } catch (UserNotFoundException userNotFoundException){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, userNotFoundException.getMessage() + " (CODE 404)\n");
        }

        return "index";
    }

    @GetMapping(path = "/logout")
    public String logoutUser(HttpSession session) {
        session.setAttribute("login", null);
        return "index";
    }
}
