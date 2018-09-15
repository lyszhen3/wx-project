package com.weixin.wxapplet.message;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by lys on 6/6/2018.
 *
 * @author lys
 * @version 3.0.0-SNAPSHOT
 * @since 3.0.0-SNAPSHOT
 */
public class SendData {

	private JSONObject keyword1;
	private JSONObject keyword2;
	private JSONObject keyword3;
	private JSONObject keyword4;

	public JSONObject getKeyword1() {
		return keyword1;
	}

	public void setKeyword1(JSONObject keyword1) {
		this.keyword1 = keyword1;
	}

	public JSONObject getKeyword2() {
		return keyword2;
	}

	public void setKeyword2(JSONObject keyword2) {
		this.keyword2 = keyword2;
	}

	public JSONObject getKeyword3() {
		return keyword3;
	}

	public void setKeyword3(JSONObject keyword3) {
		this.keyword3 = keyword3;
	}

	public JSONObject getKeyword4() {
		return keyword4;
	}

	public void setKeyword4(JSONObject keyword4) {
		this.keyword4 = keyword4;
	}
}
