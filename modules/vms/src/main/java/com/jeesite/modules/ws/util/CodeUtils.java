package com.jeesite.modules.ws.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName CodeUtils
 * @Author oqm
 * @Date 2023/2/3 11:03
 * @Description TODO
 * @Version 1.0
 */
public class CodeUtils {
    public static String getCode(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
}
