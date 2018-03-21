package com.sec.controller;

import com.sec.model.User;
// import com.sec.repository.UserRepository;
import com.sec.service.EmailService;
import com.sec.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private UserService userService;

    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String home(HttpSession session){
        System.out.println("USER_ID FROM SESSION: " + session.getAttribute("user_id"));

        /*Enumeration<String> attributes = session.getAttributeNames();
        while (attributes.hasMoreElements()) {
            System.out.println(attributes.nextElement().toString());
        }*/

        return "index";
    }

    @RequestMapping("/bloggers")
    public String bloggers(){
        return "bloggers";
    }

    @RequestMapping("/stories")
    public String stories(){
        return "stories";
    }

    @RequestMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    //	@RequestMapping(value = "/reg", method = RequestMethod.POST)
    @PostMapping("/reg")
    public String reg(@ModelAttribute User user) {
        log.info("Uj user!");
        userService.registerUser(user);
        return "auth/login";
    }

    @RequestMapping(path = "/activation/{code}", method = RequestMethod.GET)
    public String activation(@PathVariable("code") String code, HttpServletResponse response) {
        String result = userService.userActivation(code);
        return "auth/login";
    }

}