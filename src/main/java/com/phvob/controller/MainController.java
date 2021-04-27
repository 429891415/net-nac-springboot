package com.phvob.controller;

import com.phvob.daoimpl.AccessDaoImpl;
import com.phvob.daoimpl.HostDaoImpl;
import com.phvob.daoimpl.NetWorkDaoImpl;
import com.phvob.pojo.Access;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author phvob
 * @email hbuphvob@163.com
 * @create 2021-04-21 21:46
 *
 *  类注解 @Api(value = "desc of class")
 *  方法注解 @ApiOperation(value = "desc of method", notes = "")
 *  参数注解  @ApiParam(value = "desc of param" , required=true )
 */

@Controller
public class MainController {
    @GetMapping("/index")
    public String index(){
        return "index";
    }

}
