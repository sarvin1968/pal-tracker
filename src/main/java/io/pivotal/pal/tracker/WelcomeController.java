package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    private final String externalConfigVal;


    public WelcomeController(@Value("${welcome.message}")String externalConfigVal) {
        this.externalConfigVal = externalConfigVal;
    }

    @GetMapping("/")
    public String sayHello() {
        return externalConfigVal;
    }





}
