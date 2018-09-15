package com.weixin.wxapplet.qrcode;

import com.weixin.wxapplet.AbstractDefaultWxAppletRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lys on 6/27/2018.
 *
 * @author lys
 * @version 3.0.0-SNAPSHOT
 * @since 3.0.0-SNAPSHOT
 */
@Service
public class ObtainWxAcodeRequest extends AbstractDefaultWxAppletRequest {
	private static final String URL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit";

	@Override
	public HttpUriRequest buildPost(List<NameValuePair> params) {

		setAccessToken(params);
		super.url = URL;
		return super.buildPost(params);
	}



}
