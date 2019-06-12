package com.xpjz.wechat.handler;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;

/**
 * Created by chenyuping on 2018/8/6.
 */
public class DruidDataSourceHandler extends DruidDataSource {
    private static final long serialVersionUID = 1L;

    @Override
    public void setUsername(String username) {
        try {
            username = ConfigTools.decrypt(username);
        } catch (Exception e) {
            e.printStackTrace();
            username = "";
        }
        super.setUsername(username);
    }

    @Override
    public void setPassword(String password) {
        try {
            password = ConfigTools.decrypt(password);
        } catch (Exception e) {
            e.printStackTrace();
            password = "";
        }
        super.setPassword(password);
    }

    public static void main(String[] args) {
        try {
            //System.out.println(ConfigTools.decrypt("D20awwDlmNICC1jYIZAx+jxbiC3aUbjc53c6mmJNLX9sSSC7hJKpuL3rSrFEfLyaXMCNGrBVp9U6VjPnnURqlg=="));
            //System.out.println(ConfigTools.encrypt("D20awwDlmNICC1jYIZAx+jxbiC3aUbjc53c6mmJNLX9sSSC7hJKpuL3rSrFEfLyaXMCNGrBVp9U6VjPnnURqlg=="));
            System.out.println(ConfigTools.decrypt("WDiPFLyDj7TBLDgxjmNaVbYROqCzxrWWxmLbhxKUX4zZplnWhBwxntD8uS48j/pGu2tYGxDlHejyxJB1gMH7wA=="));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
