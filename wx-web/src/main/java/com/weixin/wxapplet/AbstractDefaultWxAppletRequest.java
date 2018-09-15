package com.weixin.wxapplet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lin.springUtils.WebSpringFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lys on 6/5/2018.
 *
 * @author lys
 * @version 3.0.0-SNAPSHOT
 * @since 3.0.0-SNAPSHOT
 */
public abstract class AbstractDefaultWxAppletRequest extends AbstractWxAppletRequest {
	protected String url;
	private RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();

	private String appId;
	@Value("${wx.appId}")
	public void setAppId(String appId) {
		this.appId = appId;
	}

	private String secret;
	@Value("${wx.appSecret}")
	public void setSecret(String secret) {
		this.secret = secret;
	}

	private Charset defaultCharset = Charset.forName("UTF-8");
	private List<NameValuePair> basicParam = new ArrayList<NameValuePair>(2);


	@PostConstruct
	public void init() {
		basicParam.add(new BasicNameValuePair("appid", appId));
		basicParam.add(new BasicNameValuePair("secret", secret));
	}

	@Override
	public HttpUriRequest buildGet(List<NameValuePair> params) throws IOException {
		params.addAll(basicParam);
		String param = EntityUtils.toString(new UrlEncodedFormEntity(params, defaultCharset));
		HttpGet httpGet = new HttpGet(url + "?" + param);
		httpGet.setConfig(requestConfig);
		return httpGet;
	}

	@Override
	public HttpUriRequest buildPost(List<NameValuePair> params) {
		String accessToken = "";
		JSONObject jsonObject = new JSONObject();
		for (NameValuePair param : params) {
			if ("data".equals(param.getName())) {
				jsonObject.put(param.getName(), JSON.parse(param.getValue()));
				continue;
			}
			if ("access_token".equals(param.getName())) {
				accessToken = param.getValue();
				continue;
			}
			jsonObject.put(param.getName(), param.getValue());
		}
//		params.addAll(basicParam);
		HttpPost httpPost = new HttpPost(url + "?access_token=" + accessToken);
		httpPost.setConfig(requestConfig);

		System.out.println(jsonObject.toJSONString());
		httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
		StringEntity stringEntity = new StringEntity(jsonObject.toJSONString(), defaultCharset);
		stringEntity.setContentType("application/json");
		httpPost.setEntity(stringEntity);
		System.out.println(httpPost.getEntity());
		return httpPost;
	}

	@Override
	public HttpEntity excute(HttpUriRequest request) throws IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpResponse httpResponse = httpClient.execute(request);
		return new BufferedHttpEntity(httpResponse.getEntity());
	}


	protected void setAccessToken(List<NameValuePair> params) {

		AccessTokenReqeust accessTokenReqeust = WebSpringFactory.getBean(AccessTokenReqeust.class);
		JSONObject tokenJson;
		try {
			tokenJson = accessTokenReqeust.doExcute(accessTokenReqeust.buildGet(new ArrayList<>()));
			params.add(new BasicNameValuePair("access_token", tokenJson.getString("access_token")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
