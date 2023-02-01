package fr.insy2s.commerce.shoponlineback.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/shopping-online")
@RequiredArgsConstructor
public class HelloController {


    @Secured("ROLE_ADMIN")
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }
}
