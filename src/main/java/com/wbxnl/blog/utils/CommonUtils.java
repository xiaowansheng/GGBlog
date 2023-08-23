package com.wbxnl.blog.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wansheng
 * @createDate 2023/1/17 19:22
 */
public class CommonUtils {
    /**
     * 获取六位随机验证码
     * @return
     */
   public static String getVerificationCode(){
       return String.valueOf((int)((Math.random()*9+1)*Math.pow(10,5))).toString();
   }

    /**
     * 检验邮箱的合法性
     * @param mailBox
     * @return
     */
   public static boolean checkMailBox(String mailBox){
       //电子邮件
       String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
       Pattern regex = Pattern.compile(check);
       Matcher matcher = regex.matcher(mailBox);
       return matcher.matches();
   }
}
