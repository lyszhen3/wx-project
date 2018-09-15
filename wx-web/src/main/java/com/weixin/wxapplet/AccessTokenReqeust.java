package com.weixin.wxapplet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lin.redis.RedisIO;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by lys on 6/5/2018.
 *
 * @author lys
 * @version 3.0.0-SNAPSHOT
 * @since 3.0.0-SNAPSHOT
 */
@Service
public class AccessTokenReqeust extends AbstractDefaultWxAppletRequest {
	public final static String URL = "https://api.weixin.qq.com/cgi-bin/token";
	public final static String GRANT_TYPE = "client_credential";

	private RedisIO redisIO;

	@Autowired
	public void setRedisIO(RedisIO redisIO) {
		this.redisIO = redisIO;
	}

	@Override
	public HttpUriRequest buildGet(List<NameValuePair> params) throws IOException {
		super.url = URL;
		params.add(new BasicNameValuePair("grant_type", GRANT_TYPE));
		return super.buildGet(params);
	}

	public JSONObject doExcute(HttpUriRequest request) throws IOException {
		//1.先从缓存获取 有效期大约7200秒
		String accessToken = redisIO.get("wx_token");
		JSONObject json = new JSONObject();
		if (StringUtils.hasText(accessToken)) {

			json.put("access_token", accessToken);
			return json;
		}
		//2.诺缓存取不到数据则调用微信
		HttpEntity httpEntity = super.excute(request);
		String s = EntityUtils.toString(httpEntity);
		json = JSON.parseObject(s);
		if (json.getString("access_token") != null) {
			//3.存入缓存
			redisIO.setKey("wx_token",json.getString("access_token"), json.getInteger("expires_in") - 100);
		}
		return json;
	}
}
