package com.phvob.controller;

import com.phvob.daoimpl.NetWorkDaoImpl;
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
public class NetWorkController {
    //NetWork
    @PostMapping("/queryHostByNetWork")
    @ResponseBody
    public List<String> queryHostByNetWork(@ApiParam @RequestParam String network){
        return new NetWorkDaoImpl().queryHostByNetWork(network);
    }
    @GetMapping("/queryAllNetWork")
    @ResponseBody
    public List<String> queryAllNetWork(){
        return new NetWorkDaoImpl().queryAllNetWork();
    }
}
