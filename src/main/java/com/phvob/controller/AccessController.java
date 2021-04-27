package com.phvob.controller;

import com.phvob.daoimpl.AccessDaoImpl;
import com.phvob.pojo.Access;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author phvob
 * @email hbuphvob@163.com
 * @create 2021-04-26 19:44
 */
@Controller
public class AccessController {
    //ACCESS
    @GetMapping("/getAllHostAccess")
    @ResponseBody
    public List<Access> getAllHostAccess(){
        return new AccessDaoImpl().queryAllHostAccess();
    }

    @PostMapping("/queryAccessByHost")
    @ResponseBody
    public List<Access> queryAccessByHost(@ApiParam @RequestParam String host){
        return new AccessDaoImpl().queryAccessByHost(host);
    }

}
