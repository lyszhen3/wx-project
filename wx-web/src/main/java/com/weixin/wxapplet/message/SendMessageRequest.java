package com.weixin.wxapplet.message;

import com.weixin.wxapplet.AbstractDefaultWxAppletRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lys on 6/5/2018.
 *
 * @author lys
 * @version 3.0.0-SNAPSHOT
 * @since 3.0.0-SNAPSHOT
 */
@Service
public class SendMessageRequest extends AbstractDefaultWxAppletRequest {
	public final static String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send";

	@Override
	public HttpUriRequest buildPost(List<NameValuePair> params) {

		setAccessToken(params);
		super.url = url;
		return super.buildPost(params);
	}
}
