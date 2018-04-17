package com.yf.lib;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PushService {

    @Value("${define.push.jpush.app_key}")
    private  String appKey;

    private  void  initPushClient(){


    }


    public  void  pushMsg(){


    }

}
