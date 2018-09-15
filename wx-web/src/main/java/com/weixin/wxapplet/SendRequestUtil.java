package com.weixin.wxapplet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lin.redis.RedisIO;
import com.lin.springUtils.WebSpringFactory;
import com.weixin.wxapplet.qrcode.ObtainWxAcodeRequest;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;

/**
 * Created by lys on 6/11/2018.
 *
 * @author lys
 * @version 3.0.0-SNAPSHOT
 * @since 3.0.0-SNAPSHOT
 */
@Component
public class SendRequestUtil {
	private static final Logger logger = LoggerFactory.getLogger(SendRequestUtil.class);
//	private WxAppletMessageProperties wxAppletMessageProperties;
	private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
//	@Autowired
//	public void setWxAppletMessageProperties(WxAppletMessageProperties wxAppletMessageProperties) {
//		this.wxAppletMessageProperties = wxAppletMessageProperties;
//	}

	private RedisIO redisIO;

	@Autowired
	public void setRedisIO(RedisIO redisIO) {
		this.redisIO = redisIO;
	}

	//	public JSONObject sendMessage(List<NameValuePair> param, WxAppletMessageProperties.Template template) {
//
//		String templateId = wxAppletMessageProperties.getTemplateIds().get(template.name());
//		//模板id
//		param.add(new BasicNameValuePair("template_id", templateId));
//		AbstractDefaultWxAppletRequest wxRequest = SpringUtils.getBean(SendMessageRequest.class);
//		JSONObject response = null;
//		try {
//			HttpEntity httpEntity = sendRequestPost(wxRequest, param);
//			response = JSON.parseObject(EntityUtils.toString(httpEntity,DEFAULT_CHARSET));
//		} catch (IOException e) {
//			logger.error(String.format("发送微信消息[%s]失败: due to %s", template.getTitle(), e), e);
//		}
//		return response;
//	}

	public HttpEntity getAQrCode(List<NameValuePair> param) {

		AbstractDefaultWxAppletRequest wxRequest = WebSpringFactory.getBean(ObtainWxAcodeRequest.class);

		HttpEntity httpEntity = null;
		try {
			httpEntity = sendRequestPost(wxRequest, param);
		} catch (IOException e) {
			logger.error(String.format("获取小程序码失败 due to %s", e), e);
		}
		return httpEntity;
	}

	private HttpEntity sendRequestPost(AbstractDefaultWxAppletRequest wxRequest, List<NameValuePair> param) throws IOException {
		HttpEntity response;
		HttpUriRequest httpUriRequest = wxRequest.buildPost(param);

		response = wxRequest.excute(httpUriRequest);

		if (response.getContentType().getValue().contains("application/json")) {
			String s = EntityUtils.toString(response,DEFAULT_CHARSET);
			JSONObject resJson = JSON.parseObject(s);
			if (resJson != null && Objects.equals(resJson.getInteger("errcode"), 40001)) {
				//access_token 被替换后补偿 。。替换环境为测试或者开发
				redisIO.del("wx_token");
				return wxRequest.excute(wxRequest.buildPost(param));
			}
		}
		return response;
	}
}
