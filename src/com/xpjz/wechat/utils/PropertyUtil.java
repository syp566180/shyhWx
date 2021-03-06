package com.xpjz.wechat.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * Created by chenyuping on 2018/9/5.
 */
public class PropertyUtil {

    private static Logger log = LoggerFactory.getLogger(PropertyUtil.class);

    //配置地址
    private static final String configUrl = "com/xpjz/wechat/config/";
    //配置参数code
    public static int CODE_0 = 0;  //微信参数配置信息
    public static int CODE_1 = 1;  //微信菜单配置信息
    public static int CODE_2 = 2;  //微信静态页面配置信息
    public static int CODE_3 = 3;  //微信自动回复配置信息

    //参数对应的配置文件信息
    private static String[] properties = {"deploy.properties","menu.properties","","req.properties"};


    //读取配置文件
   private static String getProperty(String key,int code){
        String pro = properties[code];
        String url = configUrl + pro;
        Properties prop = new Properties();
        String values = "";
        String path = PropertyUtil.class.getClassLoader().getResource(url).getPath();
        InputStream is = null;
        try {
             is = new BufferedInputStream(new FileInputStream(path));
            prop.load(new InputStreamReader(is, "utf-8"));
            values = prop.getProperty(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if (is != null) {
                try {
                    is.close(); // 关闭流
                } catch (IOException e) {
                    log.debug("inputStream close IOException:" + e.getMessage());
                }
            }
        }
        return values;
    }

    public static String get(String key,int code){
        return getProperty(key,code);
    }


    private String paramsPath = "com/xpjz/wechat/config/deploy.properties";
    private String counterPath = "counter.properties";

    /**
     * <p>Description: 根据key读取value</p>
     * @author cs
     * @version 2013-10-24
     * @modifier cs
     * @modifiDate 2013-10-24
     * <p>modifiContent: 首次创建</p>
     * @param isParamProper 是否是读取参数配置信息，true是，false则读取计数器配置
     * @param key
     * @return
     */
    public String readValue(boolean isParamProper, String key) {
        Properties props = new Properties();
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(1);
        FileInputStream fis = null;
        try {

            fis = new FileInputStream(path+(isParamProper?paramsPath:counterPath));
            //不能用以下的方式，否则必须要重启服务器才能读取到最新的数据，问题就出在这
//			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(isParamProper?paramsPath:counterPath);
//			props.load(in);
            props.load(fis);
            String value = props.getProperty(key);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null!=fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    /**
     * <p>Description: 写入properties信息</p>
     * @author cs
     * @version 2013-10-24
     * @modifier cs
     * @modifiDate 2013-10-24
     * <p>modifiContent: 首次创建</p>
     * @param isParamProper 是否是写入参数配置信息，true是，false则写入计数器配置
     * @param parameterName
     * @param parameterValue
     * @throws IOException
     */
    public void writeProperties(boolean isParamProper,String parameterName,
                                String parameterValue) {
        Properties prop = new Properties();
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(1);

        FileInputStream fis = null;
        OutputStream fos = null;
        try {
//			fis = Thread.currentThread().getContextClassLoader().getResourceAsStream(isParamProper?paramsPath:counterPath);
            // 从输入流中读取属性列表（键和元素对）
            fis = new FileInputStream(path+(isParamProper?paramsPath:counterPath));
            prop.load(fis);
            fis.close();//一定要在修改值之前关闭fis

            fos = new FileOutputStream(path+(isParamProper?paramsPath:counterPath));
            prop.setProperty(parameterName, parameterValue);

            prop.store(fos, null);//第二个参数为注释
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null!=fos) {
                try {
                    fos.close();
                    if(null!=fis) fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




}
