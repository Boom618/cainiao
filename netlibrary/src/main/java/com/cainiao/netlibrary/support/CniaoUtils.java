package com.cainiao.netlibrary.support;

import com.blankj.utilcode.util.EncryptUtils;
import com.cainiao.netlibrary.config.CniaoConfigKt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author boomhe on 2020/9/14.
 */

public class CniaoUtils {
    /**
     * unicode 转中文
     * @param string string
     * @return 中文
     */
    public static String unicodeDecode(String string){
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(string);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            string = string.replace(matcher.group(1), ch + "");
        }
        return string;
    }

    /**
     * 解析返回的data数据
     *
     * @param dataStr
     * @return
     */
    public static String decodeData(String dataStr){
        if (dataStr != null) {
            return new String(EncryptUtils.decryptBase64AES(
                    dataStr.getBytes(), CniaoConfigKt.NET_CONFIG_APPKEY.getBytes(),
                    "AES/CBC/PKCS7Padding",
                    "J#y9sJesv*5HmqLq".getBytes()
            ));
        } else {
            return null;
        }
    }
}

