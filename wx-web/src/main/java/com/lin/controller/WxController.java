package com.lin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weixin.wxapplet.AccessTokenReqeust;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by lys on 2018/9/15.
 *
 * @author lys
 * @version 3.0.0-SNAPSHOT
 * @since 3.0.0-SNAPSHOT
 */
@Controller
public class WxController {


	private AccessTokenReqeust accessTokenReqeust;

	@Autowired
	public void setAccessTokenReqeust(AccessTokenReqeust accessTokenReqeust) {
		this.accessTokenReqeust = accessTokenReqeust;
	}
	@GetMapping("wx_token")
	public ResponseEntity<JSON> jsondemo() {
		JSONObject jsonObject = null;
		try {
			jsonObject = accessTokenReqeust.doExcute(accessTokenReqeust.buildGet(new ArrayList<>()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(jsonObject, HttpStatus.OK);
	}
}
