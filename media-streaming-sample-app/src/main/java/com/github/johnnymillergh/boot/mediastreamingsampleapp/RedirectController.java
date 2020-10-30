package com.github.johnnymillergh.boot.mediastreamingsampleapp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.RedirectView;

/**
 * <h1>RedirectController</h1>
 * <p>
 * HTTP Redirect Controller
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/28/2020 3:01 PM
 **/
@Slf4j
@Controller
@RequiredArgsConstructor
public class RedirectController {
    @GetMapping("/home")
    public RedirectView handleHomeRequest() {
        // Redirect to home page
        return new RedirectView("/static/home.html");
//        return Rendering.redirectTo("/static/home.html").build();
    }
}
