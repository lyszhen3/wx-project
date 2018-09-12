package com.lin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Future;

@Controller
public class HelloController {


    @RequestMapping("/helloworld")
    @ResponseBody
    public String helloWorld(){
        return "hello world";
    }
    @RequestMapping("hellohtml")
    public String helloHtml(Model model){
        model.addAttribute("name","hello world");
        return "test";
    }

    @RequestMapping("/jsondemo")
    public ResponseEntity<JSON> jsondemo(){
        JSONObject json = new JSONObject();
        json.put("key","value");

        return new ResponseEntity<>(json, HttpStatus.OK);

    }


}
