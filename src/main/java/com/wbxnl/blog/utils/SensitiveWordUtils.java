package com.wbxnl.blog.utils;

import com.github.houbb.sensitive.word.api.IWordReplace;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.replace.WordReplaces;

/**
 * 敏感词工具
 * @Author xiaowansheng
 * @Date 2023/8/20 1:16
 */
public class SensitiveWordUtils {
    private final static SensitiveWordBs wordBs ;
    static {
        wordBs = SensitiveWordBs.newInstance()
                .ignoreCase(true)
                .ignoreWidth(true)
                .ignoreNumStyle(true)
                .ignoreChineseStyle(true)
                .ignoreEnglishStyle(true)
                .ignoreRepeat(false)
                .enableNumCheck(false)
                .enableEmailCheck(false)
                .enableUrlCheck(false)
                .enableWordCheck(true)
                .numCheckLen(8)
                .init();
    }
    /**
     * 将文本中的敏感词替换为 *
     * @param text
     * @return
     */
    public static String replace(String text){
        // TODO 关闭敏感词过滤
//        return wordBs.replace(text);
        return text;
    }

    /**
     * 将文本中的敏感词替换为指定的字符
     *
     * @param text      目标字符串
     * @param replaceChar 替换为的 char
     * @return 替换后结果
     */
    public static String replace(final String text, final char replaceChar) {
        final IWordReplace replace = WordReplaces.chars(replaceChar);
        return replace(text, replace);
    }

    /**
     * 替换所有内容
     *
     * @param text      目标字符串
     * @param replace 替换策略
     * @return 替换后结果
     */
    public static String replace(final String text, final IWordReplace replace) {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance().wordReplace(replace).init();
        return sensitiveWordBs.replace(text);
    }

}
