//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xpjz.wechat.service;

import com.xpjz.wechat.function.menu.dao.MenuDao;
import com.xpjz.wechat.message.resp.TextMessage;
import com.xpjz.wechat.message.resp.dao.BaseDao;
import com.xpjz.wechat.utils.MessageUtil;

import com.xpjz.wechat.utils.WeixinUtil;
import com.xpjz.wechat.utils.redis.JedisClientSingle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.Map;

@Service
@Transactional(
        readOnly = true
)
public class ShWeChatService {
    private static Logger log = LoggerFactory.getLogger(ShWeChatService.class);
    @Autowired
    private JedisClientSingle redisUtil;
    @Autowired
    private BaseDao baseDao;

    public ShWeChatService() {
    }

    public String processRequest(HttpServletRequest request) {
        String respXml = null;
        String respContent = "未知的消息类型！";

        try {
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            String fromUserName = requestMap.get("FromUserName");
            String toUserName = requestMap.get("ToUserName");
            String msgType = requestMap.get("MsgType");
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime((new Date()).getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            System.out.println(requestMap);
            if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                //文本内容 TTX
                String Content = requestMap.get("Content");
                respXml = baseDao.NewsCreate(fromUserName, toUserName, msgType,Content);
                return respXml;
            }

            if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
            } else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是语音消息！";
            } else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
                respContent = "您发送的是视频消息！";
            } else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
                respContent = "您发送的是小视频消息！";
            } else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            } else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            } else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                String eventType = requestMap.get("Event");
                if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "谢谢您的关注！";
                } else if(eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    String eventKey = requestMap.get("EventKey");
                    respXml = baseDao.NewsCreate(fromUserName, toUserName, eventKey,"");
                    return respXml;
                }
            }

            textMessage.setContent(respContent);
            respXml = MessageUtil.messageToXml(textMessage);
        } catch (Exception var11) {
            var11.printStackTrace();
        }

        return respXml;
    }

    public void createMenu() {
        String accessToken = null;
        accessToken = this.AccessToken();
        if(null != accessToken) {
            int result = WeixinUtil.createMenu(MenuDao.getMenu(), accessToken);
            if(0 == result) {
                System.out.println("菜单创建成功！！！");
                log.info("菜单创建成功！");
            } else {
                System.out.println("菜单创建失败！！！   " + result);
                log.info("菜单创建失败，错误码：" + result);
            }

            accessToken = null;
        }

    }

    public String AccessToken() {
        String accessToken = null;
        accessToken = this.redisUtil.get("accessToken");
        return accessToken;
    }


//    /**
//     * 授权获取用户信息
//     */
//
//    public String getTheCode(HttpSession session, String code, String openId) {
//        if (code != null)
//        {
//            openId = getOpenid(code);// 调用根据用户的code得到信息
//        }
//        // 获取基础刷新的接口访问凭证
//        AccessToken accessToken = AccessTokenUtil.queryAccessToken();
//        User userinfo = userinof.getUserInfo(accessToken.getAccess_token(),openId);
//        System.out.println("昵称：" + userinfo.getNickname());
//
//        if (userinfo.getOpenid() == null)
//        {
//            // 得到保存在session的微信信息
//            User loginUser = BaseUserController.getLoginUser(session);
//            openId = loginUser.getOpenid();
//            System.out.println(openId + "你是");
//        }
//
//        else
//        {
//            addSessionUser(session, userinfo);
//        }
//
//        return openId;
//    }
//
//
//
//    /**
//     * 识别得到用户id必须的一个值
//     *
//     * @param code
//     * @return
//     */
//    // 根据用户的code得到用户OpenId
//    public String getOpenid(String code) {
//        UserinofService weixinGetCode = new UserinofService();
//        Map<String, Object> result = weixinGetCode.oauth2GetOpenid(code);
//        String OpenId = (String) result.get("Openid");// 得到用户id
//        return OpenId;
//    }



}
