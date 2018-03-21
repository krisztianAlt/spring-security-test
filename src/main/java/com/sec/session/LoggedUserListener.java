package com.sec.session;

import com.sec.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Configuration
public class LoggedUserListener implements
        ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private HttpSession session;

    // SESSION MANAGEMENT: PUT USER ID INTO SESSION
    // Hint: https://stackoverflow.com/questions/24882007/populate-user-session-after-login
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        UserDetailsImpl userDetails = (UserDetailsImpl) event.getAuthentication().getPrincipal();
        session.setAttribute("user_id", userDetails.getId());
        System.out.println("User id added successfully to session.");
    }

    /*public void logoutEvent(){
        System.out.println("USER_ID FROM LoggedUserListener: " + session.getAttribute("user_id"));
        session.invalidate();
    }*/


}