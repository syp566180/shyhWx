//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xpjz.wechat.function.menu.dao;

import com.xpjz.wechat.function.menu.*;
import com.xpjz.wechat.utils.ConfigUrlUtil;
import com.xpjz.wechat.utils.MessageUtil;
import com.xpjz.wechat.utils.ParameterUtil;

public class MenuDao {
    public MenuDao() {
    }

    public static Menu getMenu() {

        String CLICK = MessageUtil.EVENT_TYPE_CLICK;

        CommonButton btn11 = new CommonButton();
        btn11.setName(ParameterUtil.MenuConfig.mainBtn1_0_value);
        btn11.setType(CLICK);
        btn11.setKey(ParameterUtil.MenuConfig.mainBtn1_0_key);

        CommonButton btn21 = new CommonButton();
        btn21.setName(ParameterUtil.MenuConfig.mainBtn2_0_value);
        btn21.setType(CLICK);
        btn21.setKey(ParameterUtil.MenuConfig.mainBtn2_0_key);

        CommonButton btn22 = new CommonButton();
        btn22.setName(ParameterUtil.MenuConfig.mainBtn2_1_value);
        btn22.setType(CLICK);
        btn22.setKey(ParameterUtil.MenuConfig.mainBtn2_1_key);

        CommonButton btn23 = new CommonButton();
        btn23.setName(ParameterUtil.MenuConfig.mainBtn2_2_value);
        btn23.setType(CLICK);
        btn23.setKey(ParameterUtil.MenuConfig.mainBtn2_2_key);

        CommonButton btn31 = new CommonButton();
        btn31.setName(ParameterUtil.MenuConfig.mainBtn3_0_value);
        btn31.setType(CLICK);
        btn31.setKey(ParameterUtil.MenuConfig.mainBtn3_0_key);

        CommonButton btn32 = new CommonButton();
        btn32.setName(ParameterUtil.MenuConfig.mainBtn3_1_value);
        btn32.setType(CLICK);
        btn32.setKey(ParameterUtil.MenuConfig.mainBtn3_1_key);





        /**
         * 菜单一
         */
        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName(ParameterUtil.MenuConfig.mainBtn1);
        mainBtn1.setSub_button(new CommonButton[]{btn11});
        /**
         * 菜单二
         */
        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn1.setName(ParameterUtil.MenuConfig.mainBtn3);
        mainBtn2.setSub_button(new Button[]{btn21,btn22,btn23});
        /**
         * 菜单三 btn33
         */
        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn1.setName(ParameterUtil.MenuConfig.mainBtn2);
        mainBtn3.setSub_button(new Button[]{btn31,btn32});

        /**
         * 一级菜单生成
         */
        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2, mainBtn3});
        return menu;
    }
}
