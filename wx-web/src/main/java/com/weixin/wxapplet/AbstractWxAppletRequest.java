package com.weixin.wxapplet;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.IOException;
import java.util.List;

/**
 * Created by lys on 6/11/2018.
 *
 * @author lys
 * @version 3.0.0-SNAPSHOT
 * @since 3.0.0-SNAPSHOT
 */
public abstract class AbstractWxAppletRequest {

	public abstract HttpUriRequest buildGet(List<NameValuePair> params) throws IOException;

	public abstract HttpUriRequest buildPost(List<NameValuePair> params);

	public abstract HttpEntity excute(HttpUriRequest request) throws IOException;
}
