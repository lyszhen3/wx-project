package com.weixin;

import com.lin.redis.RedisIO;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by lys on 2018/9/15.
 *
 * @author lyh
 */
@Component
public class WxTokenUtil {
    /**
     * 应用id
     */
    @Value("${wx.appId}")
    private String appId;
    @Value("${wx.appSecret}")
    private String secret;


    private RedisIO redisIO;
    @Autowired
    public void setRedisIO(RedisIO redisIO) {
        this.redisIO = redisIO;
    }

    public static String getToken(){
        HttpGet get = new HttpGet("xx");


        return null;
    }
}
