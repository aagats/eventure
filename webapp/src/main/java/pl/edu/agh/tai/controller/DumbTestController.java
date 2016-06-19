package pl.edu.agh.tai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DumbTestController {

    @RequestMapping(path = "/admin")
    public String loginAsAdmin() {
        return "admin";
    }

}
