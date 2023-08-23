package com.wbxnl.blog.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author wansheng
 * @createDate 2023/2/24 22:53
 */
@Slf4j
public class StrUtils {

    public static String getVerificationCode(int n) {
        String code = "";
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            int type = r.nextInt(3);   //0, 1, 2
            switch (type) {
                case 0:
                    //大写字符 (A 65 - Z 65 + 25)   (0 - 25) + 65
                    char ch1 = (char) (r.nextInt(26) + 65);
                    code+=ch1;
                    break;
                case 1:
                    //小写字符 (a 97 - Z 97 + 25)   (0 - 25) + 97
                    char ch2 = (char) (r.nextInt(26) + 97);
                    code+=ch2;
                    break;
                case 2:
                    //大写字符 (A 65 - Z 65 + 25)   (0 - 25) + 65
                    int ch3 = r.nextInt(10);
                    code+=ch3;
                    break;
                default:
                    //小写字符 (a 97 - Z 97 + 25)   (0 - 25) + 97
                    char ch4 = (char) (r.nextInt(26) + 97);
                    code+=ch4;
                    break;
            }
        }

        return code;
    }
}
