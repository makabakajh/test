package com.jeesite.common.shiro.realm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.cache.CacheUtils;
import com.jeesite.common.codec.Md5Utils;
import com.jeesite.common.lang.DateUtils;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.web.http.HttpClientUtils;
import com.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Value;


import javax.servlet.http.HttpServletRequest;

/**
 * 多系统单点登录
 *
 * @author lh
 * @version 2023-02-17
 */
public class MyAuthorizingRealm extends AuthorizingRealm {

    @Value("${loginInfo.sysName}")
    private String sysName;
    @Value("${loginInfo.url}")
    private String url;
    @Value("${loginInfo.secretKey}")
    private String secretKey;

    @Override
    public void onLoginSuccess(LoginInfo loginInfo, HttpServletRequest request) {
        String userName = UserUtils.getUser().getLoginCode();
        super.onLoginSuccess(loginInfo, request);
        if (StringUtils.isNotBlank(sysName) && StringUtils.isNotBlank(url) && StringUtils.isNotBlank(secretKey)) {
            String[] sysNameArr = sysName.split(",");
            String[] urlArr = url.split(",");
            String[] secretKeyArr = secretKey.split(",");
            for (int i = 0; i < sysNameArr.length; i++) {
                String token = Md5Utils.md5(secretKeyArr[i] + userName + DateUtils.getDate("yyyyMMdd"));
                String result = HttpClientUtils.ajaxGet(urlArr[i] + "/" + sysNameArr[i] + "/sso/" + userName + "/" + token);
                System.out.println(sysNameArr[i] + ":" + result);
                JSONObject resultJson = JSON.parseObject(result);
                String sid = (String) resultJson.get("sessionid");
                CacheUtils.put("loginInfo", userName + sysNameArr[i], sid);
            }
        }
    }

    @Override
    public void onLogoutSuccess(LoginInfo loginInfo, HttpServletRequest request) {
        String userName = UserUtils.getUser().getLoginCode();
        super.onLogoutSuccess(loginInfo, request);
        if (StringUtils.isNotBlank(sysName) && StringUtils.isNotBlank(url) && StringUtils.isNotBlank(secretKey)) {
            String[] sysNameArr = sysName.split(",");
            String[] urlArr = url.split(",");
            for (int i = 0; i < sysNameArr.length; i++) {
                String sid = (String) CacheUtils.get("loginInfo", userName + sysNameArr[i]);
                String result = HttpClientUtils.ajaxGet(urlArr[i] + "/" + sysNameArr[i] + "/a/logout?__ajax=json&__sid=" + sid);
                System.out.println(sysNameArr[i] + ":" + result);
            }
        }

    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
