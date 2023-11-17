package com.wbxnl.blog.model.config;

import com.alibaba.fastjson2.JSON;
import com.wbxnl.blog.constant.keys.WebsiteConfig;
import com.wbxnl.blog.model.entity.SystemConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 系统的封面
 * @Author xiaowansheng
 * @Date 2023/8/15 21:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Cover {

    /**
     * 默认的封面配置
     */
    public final static String KEY= WebsiteConfig.COVER;
    public final static SystemConfig DEFAULT_CONFIG =new SystemConfig();
    static {
        String[] split = KEY.split(":");
        DEFAULT_CONFIG.setName(split[split.length-1])
                .setLabel("封面")
                .setDescription("封面信息json对象数组字符串");
        // 默认的封面配置
        Cover welcome = new Cover().setName("Welcome").setLabel("欢迎页");
        Cover home = new Cover().setName("Home").setLabel("主页");
        Cover archive = new Cover().setName("Archive").setLabel("归档");
        Cover category = new Cover().setName("Category").setLabel("分类");
        Cover tag = new Cover().setName("Tag").setLabel("标签");
        Cover talk = new Cover().setName("Talk").setLabel("说说");
        Cover album = new Cover().setName("Album").setLabel("相册");
        Cover friend = new Cover().setName("Friend").setLabel("友链");
        Cover guestbook = new Cover().setName("Guestbook").setLabel("留言板");
        Cover about = new Cover().setName("About").setLabel("关于");
        Cover information = new Cover().setName("Information").setLabel("个人信息");
        Cover defaultCover = new Cover().setName("Default").setLabel("默认");
        Cover[] covers={welcome,home,archive,category,tag,talk,album,friend,guestbook,about,information,defaultCover};
        DEFAULT_CONFIG.setValue(JSON.toJSONString(covers));
    }

    /**
     * 属性名
     */
    private String name;
    /**
     * 封面名称
     */
    private String label;
    /**
     * 封面地址
     */
    private String url="";
}
