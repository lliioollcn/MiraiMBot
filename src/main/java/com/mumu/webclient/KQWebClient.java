package com.mumu.webclient;

import cc.plural.jsonij.JSON;
import com.mumu.listenner.KQMSGAdapter;
import com.mumu.msg.*;

import cc.plural.jsonij.parser.ParserException;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;

public class KQWebClient extends WebSocketClient
{
    KQMSGAdapter adapter;
    
    public KQWebClient(URI serverURI) {
        super(serverURI);
        this.adapter = null;
        this.connect();
    }
    
    public void onClose(int arg0, String arg1, boolean arg2) {
        System.out.println("连接已经关闭");
    }
    
    public void onError(Exception arg0) {
        System.out.println("发生未知错误");
    }
    
    public void onMessage(String msg) {

        int type = 0;
        try {
            JSON json = JSON.parse(msg);
            type = Integer.parseInt(String.format("%s", json.get("act")));
        }
        catch (ParserException | IOException e) {
            e.printStackTrace();
        }
        if (this.adapter != null) {
            JSON json = null;
            try {
                json = JSON.parse(msg);
            }
            catch (ParserException | IOException e3) {
                e3.printStackTrace();
            }
            type = Integer.parseInt(String.format("%s", json.get("act")));
            switch (type) {
                case 21: {
                    this.adapter.Re_MSG_Private(new RE_MSG_Private(msg));
                    break;
                }
                case 4: {
                    this.adapter.RE_MSG_FORUM(new RE_MSG_Forum(msg));
                    break;
                }
                case 2: {
                    this.adapter.RE_MSG_Group(new RE_MSG_Group(msg));
                    break;
                }
                case 101: {
                    this.adapter.RE_EXAMPLE_MANAGE(new RE_MSG_AdminChange(msg));
                    break;
                }
                case 102: {
                    this.adapter.RE_EXAMPLE_DEMBER(new DeleteAdmin(msg));
                    break;
                }
                case 103: {
                    this.adapter.RE_EXAMPLE_AMEMBER(new AddAdmin(msg));
                    break;
                }
                case 201: {
                    this.adapter.RE_EXAMPLE_ADDFRIEND(new AddFriendExample(msg));
                    break;
                }
                case 301: {
                    this.adapter.RE_ASK_ADDFRIEND(new AddFriends(msg));
                    break;
                }
                case 302: {
                    this.adapter.RE_ASK_ADDGROUP(new ADDGroupExample(msg));
                    break;
                }
                default: {
                    System.out.println(msg);
                    break;
                }
            }
        }
        else {
            System.out.println("未添加监听");
        }
    }
    
    public void onOpen(ServerHandshake arg0) {
        System.out.println("服务器连接成功");
    }
    
    public void addQQMSGListenner(KQMSGAdapter adapter) {
        this.adapter = adapter;
    }
    
    public void sendPrivateMSG(String qq, String msg) {
        try {
            Thread.sleep(10L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.send("{ \"act\": \"106\",\"QQID\":\"" + qq + "\",\"msg\": \"" + msg + "\"" + "}");
    }
    
    public void sendForumMSG(String qq, String forum, String msg, Boolean isAT) {
        try {
            Thread.sleep(10L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (isAT) {
            msg = "[CQ:at,qq=" + qq + "]" + msg;
        }
        this.send("{ \"act\": \"103\",\"discussid\": \"" + forum + "\",\"msg\": \"" + msg + "\"" + "}");
    }
    
    public void sendGroupMSG(String qq, String groupid, String msg, Boolean isAT) {
        try {
            Thread.sleep(10L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (isAT) {
            msg = "[CQ:at,qq=" + qq + "]" + msg;
        }
        this.send("{ \"act\": \"101\",\"groupid\": \"" + groupid + "\",\"msg\": \"" + msg + "\"" + "}");
    }
    
    public void sendPraise(String qq) {
        try {
            Thread.sleep(10L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.send("{\"act\": \"110\",\"QQID\": \"" + qq + "\"}");
    }
}
