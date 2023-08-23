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
 * 显示的菜单
 * @Author xiaowansheng
 * @Date 2023/8/15 22:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Menu {
    public final static String KEY= WebsiteConfig.MENU;
    public final static SystemConfig DEFAULT_CONFIG =new SystemConfig();
    static {
        String[] split = KEY.split(":");
        DEFAULT_CONFIG.setName(split[split.length-1])
                .setLabel("主页菜单")
                .setDescription("主页菜单信息json数组字符串");
        // 默认的菜单配置
        Menu home = new Menu().setName("Home").setLabel("主页").setShow(1);
        Menu archive = new Menu().setName("Archive").setLabel("归档").setShow(1);
        Menu category = new Menu().setName("Category").setLabel("分类").setShow(1);
        Menu tag = new Menu().setName("Tag").setLabel("标签").setShow(1);
        Menu talk = new Menu().setName("Talk").setLabel("说说").setShow(1);
        Menu album = new Menu().setName("Album").setLabel("相册").setShow(1);
        Menu friend = new Menu().setName("Friend").setLabel("友链").setShow(1);
        Menu guestbook = new Menu().setName("Guestbook").setLabel("留言板").setShow(1);
        Menu about = new Menu().setName("About").setLabel("关于").setShow(1);
        Menu other = new Menu().setName("Other").setLabel("其它").setShow(1);
        Menu[] me={home,archive,category,tag,talk,album,friend,guestbook,about,other};
        DEFAULT_CONFIG.setValue(JSON.toJSONString(me));
    }

    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单标签
     */
    private String label;
    /**
     * 是否显示
     */
    private Integer show;
}
