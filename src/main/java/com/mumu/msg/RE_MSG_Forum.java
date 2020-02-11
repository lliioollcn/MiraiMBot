package com.mumu.msg;


import cc.plural.jsonij.JSON;
import cc.plural.jsonij.parser.ParserException;

import java.io.IOException;

public class RE_MSG_Forum extends RE_MSG
{
    private String subType;
    private String sendTime;
    private String fromDiscuss;
    private String fromQQ;
    private String msg1;
    private String font;
    private String nick;
    private String age;
    private String sex;
    private String act;
    
    public RE_MSG_Forum(String msg) {
        super(msg);
        try {

            JSON json = JSON.parse(msg);
            this.nick = String.format("%s", json.get("nick"));
            this.sex = String.format("%s", json.get("sex"));
            this.age = String.format("%s", json.get("age"));
            this.act = String.format("%s", json.get("act"));
            this.fromQQ = String.format("%s", json.get("fromQQ"));
            this.fromDiscuss = String.format("%s", json.get("fromDiscuss"));
            this.subType = String.format("%s", json.get("subtype"));
            this.sendTime = String.format("%s", json.get("time"));
            this.msg1 = String.format("%s", json.get("msg"));
            this.font = String.format("%s", json.get("font"));
            this.type = 4;
        }
        catch (ParserException | IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getSubType() {
        return this.subType;
    }
    
    public String getSendTime() {
        return this.sendTime;
    }
    
    public String getFromDiscuss() {
        return this.fromDiscuss;
    }
    
    public String getFromQQ() {
        return this.fromQQ;
    }
    
    public String getMsg() {
        return this.msg1;
    }
    
    public String getFont() {
        return this.font;
    }
    
    public String getNick() {
        return this.nick;
    }
    
    public String getAge() {
        return this.age;
    }
    
    public String getSex() {
        return this.sex;
    }
    
    public String getAct() {
        return this.act;
    }
}
