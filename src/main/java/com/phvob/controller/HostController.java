package com.phvob.controller;

import com.phvob.daoimpl.HostDaoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author phvob
 * @email hbuphvob@163.com
 * @create 2021-04-26 19:44
 */
@Controller
public class HostController {
    //HOST
    @GetMapping("/getAllHost")
    @ResponseBody
    public List<String> getAllHost(){
        return new HostDaoImpl().queryAllHost();
    }
}
