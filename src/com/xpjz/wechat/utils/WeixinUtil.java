//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xpjz.wechat.utils;

import com.xpjz.wechat.function.menu.Menu;
import com.xpjz.wechat.wxpay.pay.UnifiedOrder;
import com.xpjz.wechat.wxpay.sdk.*;
import com.xpjz.wechat.wxpay.sdk.WXPayUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class WeixinUtil {
    private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

    public WeixinUtil() {
    }

    public static int createMenu(Menu menu, String accessToken) {
        int result = 0;
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
        String jsonMenu = JSONObject.fromObject(menu).toString();
        JSONObject jsonObject = HttpUtil.httpRequest(url, "POST", jsonMenu);
        if(null != jsonObject && 0 != jsonObject.getInt("errcode")) {
            result = jsonObject.getInt("errcode");
            log.error("创建菜单失败 errcode:{} errmsg:{}", Integer.valueOf(jsonObject.getInt("errcode")), jsonObject.getString("errmsg"));
        }

        return result;
    }


    /**
     * 元转换成分
     * @param
     * @return
     */
    public static String getMoney(String amount) {
        if(amount==null){
            return "";
        }
        // 金额转化为分为单位
        String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额
        int index = currency.indexOf(".");
        int length = currency.length();
        Long amLong = 0l;
        if(index == -1){
            amLong = Long.valueOf(currency+"00");
        }else if(length - index >= 3){
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));
        }else if(length - index == 2){
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);
        }else{
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");
        }
        return amLong.toString();
    }



    //组装
    public static SortedMap<String, String> getPackage(UnifiedOrder order){
        SortedMap<String, String> packageParams = new TreeMap<>();
        packageParams.put("appid",  order.getAppid());
        packageParams.put("mch_id",  order.getMch_id());
        packageParams.put("nonce_str", order.getNonce_str());
        packageParams.put("body", "费用");
        packageParams.put("out_trade_no",order.getOut_trade_no());
        packageParams.put("total_fee", order.getTotal_fee());
        packageParams.put("spbill_create_ip", order.getSpbill_create_ip());
        packageParams.put("notify_url", order.getNotify_url());
        packageParams.put("trade_type", order.getTrade_type());
        packageParams.put("openid", order.getOpenid());
        packageParams.put("sign_type", WXPayConstants.MD5);
        return packageParams;
    }


    public static String setXML(String return_code, String return_msg) {
        return "<xml><return_code><" + return_code
                + "></return_code><return_msg><" + return_msg
                + "></return_msg></xml>";
    }


    /**
     * 针对微信支付生成商户订单号，为了避免微信商户订单号重复（下单单位支付），
     *
     * @return
     */
    public static String generateOrderSN() {
        StringBuffer orderSNBuffer = new StringBuffer();
        orderSNBuffer.append(WXPayUtil.generateNonceStr());
        return orderSNBuffer.toString();
    }


    /*
    * 获取签名
    * */
    public static String generateSignature(Map<String,String> map,String key) throws Exception{
        String sign = WXPayUtil.generateSignature(map,key);
        map.put("sign",sign);
        System.out.println("签名是否正确："+WXPayUtil.isSignatureValid(map,key));
        return sign;
    }



    //字节数组转换为十六进制字符串
    public static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }




    /**
     * 检测是否有emoji字符
     *
     * @param source
     * @return 一旦含有就抛出
     */
    public static boolean containsEmoji(String source) {
        if (StringUtils.isBlank(source)) {
            return false;
        }
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isEmojiCharacter(codePoint)) {
                //do nothing，判断到了这里表明，确认有表情字符
                return true;
            }
        }
        return false;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        source = source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
        if (!containsEmoji(source)) {
            return source;//如果不包含，直接返回
        }
        //到这里铁定包含
        StringBuilder buf = null;

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }

                buf.append(codePoint);
            } else {
                buf.append("*");
            }
        }

        if (buf == null) {
            return source;//如果没有找到 emoji表情，则返回源字符串
        } else {
            if (buf.length() == len) {//这里的意义在于尽可能少的toString，因为会重新生成字符串
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }

    }



    /**
     * 拼接url地址
     * @param url
     * @param params
     * @return
     */
    public static String getUrl(String url,JSONObject params){
        // 向url中添加参数
		StringBuffer sb = null;
		if (params != null) {
			Iterator<String> it = params.keySet().iterator();

			while (it.hasNext()) {
				String key = it.next();
				String value = params.getString(key);
				if (sb == null) {
					sb = new StringBuffer();
					sb.append(url);
					sb.append("?");
				} else {
					sb.append("&");
				}
				sb.append(key);
				sb.append("=");
				sb.append(value);
			}
		}
		if(sb.toString().isEmpty()){
            sb.append("http://www.baidu.com");
        }
		return sb.toString();
    }

}
