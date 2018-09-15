package com.lin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/wx")
public class HelloController {

    @RequestMapping("/fuck")
    public void hello(Model model, String echostr, HttpServletResponse response) throws IOException {
        try(PrintWriter writer = response.getWriter()){
            writer.write(echostr);
            writer.flush();
        }
    }

    @RequestMapping("/helloworld")
    @ResponseBody
    public String helloWorld() {
        return "hello world";
    }

    @RequestMapping("hellohtml")
    public String helloHtml(Model model) {
        model.addAttribute("name", "hello world");
        return "test";
    }

    @RequestMapping("/jsondemo")
    public ResponseEntity<JSON> jsondemo() {
        JSONObject json = new JSONObject();
        json.put("key", "value");

        return new ResponseEntity<>(json, HttpStatus.OK);

    }


}
