package org.example.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1")
public class Controller {
    @GetMapping("/")
    public String getMethodName() {
        return "Hello";
    }
}
