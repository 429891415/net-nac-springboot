package com.phvob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author phvob
 * @email hbuphvob@163.com
 * @create 2021-04-21 21:46
 */
@Controller
public class MainController {
    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
