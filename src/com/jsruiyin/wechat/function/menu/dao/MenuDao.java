//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jsruiyin.wechat.function.menu.dao;

import com.jsruiyin.wechat.function.menu.*;
import com.jsruiyin.wechat.utils.ConfigUrlUtil;
import com.jsruiyin.wechat.utils.ParameterUtil;

public class MenuDao {
    public MenuDao() {
    }

    public static Menu getMenu() {

        CommonButton btn11 = new CommonButton();
        btn11.setName(ParameterUtil.LOAN_APPLICATION);
        btn11.setType(ParameterUtil.CLICK);
        btn11.setKey(ParameterUtil.LOAN_KEY);
        ViewButton btn21 = new ViewButton();
        btn21.setName(ParameterUtil.SMALL_BUSINESS_PURSE);
        btn21.setType(ParameterUtil.VIEW);
        btn21.setUrl(ParameterUtil.SMALL_URL);
        CommonButton btn22 = new CommonButton();
        btn22.setName(ParameterUtil.LOAN_BUSINESS);
        btn22.setType(ParameterUtil.CLICK);
        btn22.setKey(ParameterUtil.LOAN_B_KEY);
        CommonButton btn23 = new CommonButton();
        btn23.setName(ParameterUtil.ELECTRONIC_BANKING_BUSINESS);
        btn23.setType(ParameterUtil.CLICK);
        btn23.setKey(ParameterUtil.ELECTRONIC_KEY);
        CommonButton btn24 = new CommonButton();
        btn24.setName(ParameterUtil.DEPOSIT_SERVICE);
        btn24.setType(ParameterUtil.CLICK);
        btn24.setKey(ParameterUtil.DEPOSIT_KEY);
        CommonButton btn25 = new CommonButton();
        btn25.setName(ParameterUtil.FINANCIAL_SERVICES);
        btn25.setType(ParameterUtil.CLICK);
        btn25.setKey(ParameterUtil.FINANCIAL_KEY);
        CommonButton btn31 = new CommonButton();
        btn31.setName(ParameterUtil.RECOMMEND_IT);
        btn31.setType(ParameterUtil.CLICK);
        btn31.setKey(ParameterUtil.RECOMMEND_KEY);
        ViewButton btn32 = new ViewButton();
        btn32.setName(ParameterUtil.POINT_OF_VIEW);
        btn32.setType(ParameterUtil.VIEW);
        btn32.setUrl(ParameterUtil.POINT_URL);

        CommonButton btn33 = new CommonButton();
        btn33.setName(ParameterUtil.ACTIVITY);
        btn33.setType(ParameterUtil.CLICK);
        btn33.setKey(ParameterUtil.ACTIVITY_KEY);


//        ViewButton btnGc = new ViewButton();
//        btnGc.setName("文创大赛");
//        btnGc.setType(ParameterUtil.VIEW);
//        btnGc.setUrl("http://jbxqgwh.huhuschool.com/mobile/wechatConnect/wx1a4e3c5b7c5c1619/index");
//


        ViewButton btn44 = new ViewButton();
        btn44.setName(ParameterUtil.SOCIAL_SECURITY);
        btn44.setType(ParameterUtil.VIEW);
        btn44.setUrl(ConfigUrlUtil.SBK_URL);
        CommonButton btn55 = new CommonButton();
        btn55.setName(ParameterUtil.PRIZZE);
        btn55.setType(ParameterUtil.CLICK);
        btn55.setKey(ParameterUtil.PRIZZE_KEY);



        /**
         * 菜单一
         */
        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName(ParameterUtil.ACCOUNT_INFORMATION);
        mainBtn1.setSub_button(new CommonButton[]{btn11});
        /**
         * 菜单二
         */
        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName(ParameterUtil.PRODUCT_CENTER);
        mainBtn2.setSub_button(new Button[]{btn21, btn22, btn23, btn24, btn25});
        /**
         * 菜单三 btn33
         */
        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName(ParameterUtil.MY_BANK);
        //mainBtn3.setSub_button(new Button[]{btn33,btn31,btn32,btn44,btn55});
        mainBtn3.setSub_button(new Button[]{btn33,btn44});

        /**
         * 一级菜单生成
         */
        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2, mainBtn3});
        return menu;
    }
}
