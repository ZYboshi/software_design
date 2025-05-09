package com.campussecondhand.websocket;

import cn.hutool.json.JSONUtil;
import com.campussecondhand.context.BaseContext;
import com.campussecondhand.exception.BaseException;
import com.campussecondhand.mapper.MessageMapper;
import com.campussecondhand.mapper.UserMapper;
import com.campussecondhand.pojo.entity.ChatMessage;
import com.campussecondhand.pojo.entity.Message;
import com.campussecondhand.pojo.entity.User;
import com.campussecondhand.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat")
@Component
public class ChatEndPoint {

    private static final Map<Long, Session> onlineUser = new ConcurrentHashMap<>();

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMapper userMapper;

    @OnOpen
    public void onOpen(Session session) {
        onlineUser.put(BaseContext.getCurrentId(), session);
        //查询数据库，看该用户是否有未接收信息
        /*
        TODO 现在的数据库貌似实现不了
        Map<String, Object> map = new HashMap<>();
        map.put("userId", BaseContext.getCurrentId());
        map.put("pending", 0);
        List<Message> messages = messageMapper.selectByMap(map);
         */
    }

    @OnMessage
    public void onMessage(String msg) {
        /*
        * 格式：
        * isSystemNotice
        * toUserId
        * content
        * */
        // 将收到的消息转为对象
        ChatMessage message = JSONUtil.parse(msg).toBean(ChatMessage.class);

        //查询对应用户，不存在发送的对象则报错
        User user = userMapper.selectById(message.getToUserId());
        if(user == null) throw new BaseException("发送消息对象不存在！");

        Session session = onlineUser.get(message.getToUserId());
        //若该用户不在线，则存入数据库
        if(session == null) {
            savePendingMessage(message);
        }
        //在线则获取当前信息然后发送
        User user1 = userMapper.selectById(BaseContext.getCurrentId());
        String resultMessage = MessageUtils.getMessage(false, user1.getUserName(), message.getContent());
        try {
            session.getBasicRemote().sendText(resultMessage);
        } catch (IOException e) {
            throw new BaseException("发送失败");
        }
        saveMessage(message);
    }

    private void saveMessage(ChatMessage message) {
        Message mess = new Message();
        mess.setContent(message.getContent());
        mess.setSendTime(LocalDateTime.now());
        mess.setReceiverUserId(message.getToUserId());
        mess.setUserId(BaseContext.getCurrentId());
        messageMapper.insert(mess);
    }

    private void savePendingMessage(ChatMessage message) {
        Message mess = new Message();
        mess.setContent(message.getContent());
        mess.setPending(0);
        mess.setSendTime(LocalDateTime.now());
        mess.setUserId(message.getToUserId());
        messageMapper.insert(mess);
    }

    @OnClose
    public void onClose(Session session) {
        onlineUser.remove(BaseContext.getCurrentId());
    }

    //群发
    public void sendToAllClient(String message) {
        Collection<Session> sessions = onlineUser.values();
        for (Session session : sessions) {
            try {
                //服务器向客户端发送消息
                session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
