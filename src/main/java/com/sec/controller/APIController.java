package com.sec.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class APIController {

    /*@RequestMapping(value = "/", method = {RequestMethod.GET})
    public String mainPage(){
        return "MAIN PAGE COMES HERE";
    }

    @RequestMapping(value = "/stories", method = {RequestMethod.GET})
    public String listStories(){
        return "STORIES";
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.GET})
    public String deleteStory(){
        return "DELETE";
    }*/

    @RequestMapping(value = "/session-check", method={RequestMethod.GET, RequestMethod.POST})
    public String sessionData(HttpSession session){
        if (session.getAttribute("user_id") != null){
            return session.getAttribute("user_id").toString();
        }
        return "No user id in session.";
    }
}
