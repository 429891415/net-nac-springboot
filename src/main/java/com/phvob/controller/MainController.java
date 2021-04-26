package com.phvob.controller;

import com.phvob.dao.AccessDaoImpl;
import com.phvob.dao.HostDaoImpl;
import com.phvob.pojo.Access;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/getAllHost")
    @ResponseBody
    public List<String> getAllHost(){
        return new HostDaoImpl().queryAllHost();
    }
    @GetMapping("/getAllAccess")
    @ResponseBody
    public List<Access> getAllAccess(){
        return new AccessDaoImpl().queryAllAccess();
    }
}
