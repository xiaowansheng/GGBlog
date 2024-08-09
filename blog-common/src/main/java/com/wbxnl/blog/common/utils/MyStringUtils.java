package com.wbxnl.blog.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/27 2:05
 */
@NoArgsConstructor(access = AccessLevel.NONE)
public class MyStringUtils {

    /**
     * 加密字符串
     * @param str
     * @return
     */
    public static String encrypt(String str) {
        // TODO 待实现
        return str;
    }

    /**
     * 生成验证码
     * @param n 验证码位数
     * @return 验证码
     */
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
