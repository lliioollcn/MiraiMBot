package com.mumu.msg;


import cc.plural.jsonij.JSON;
import cc.plural.jsonij.parser.ParserException;

import java.io.IOException;

public class RE_MSG_Group extends RE_MSG
{
    private String act;
    private String subType;
    private String sendTime;
    private String fromGroup;
    private String fromQQ;
    private String fromAnonymous;
    private String msg1;
    private String font;
    private String username;
    private String nick;
    private String sex;
    private String age;
    private String fromGroupName;
    
    public RE_MSG_Group(String msg) {
        super(msg);
        try {

            JSON json = JSON.parse(msg);
            this.nick = String.format("%s", json.get("nick"));
            this.sex = String.format("%s", json.get("sex"));
            this.age = String.format("%s", json.get("age"));
            this.act = String.format("%s", json.get("act"));
            this.fromQQ = String.format("%s", json.get("fromQQ"));
            this.fromAnonymous = String.format("%s", json.get("fromAnonymous"));
            this.subType = String.format("%s", json.get("subtype"));
            this.sendTime = String.format("%s", json.get("time"));
            this.msg1 = String.format("%s", json.get("msg"));
            this.font = String.format("%s", json.get("font"));
            this.username = String.format("%s", json.get("username"));
            this.fromGroupName = String.format("%s", json.get("fromGroupName"));
            this.fromGroup = String.format("%s", json.get("fromGroup"));
            this.type = 2;
        }
        catch (ParserException | IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getAct() {
        return this.act;
    }
    
    public String getSubType() {
        return this.subType;
    }
    
    public String getSendTime() {
        return this.sendTime;
    }
    
    public String getFromGroup() {
        return this.fromGroup;
    }
    
    public String getFromQQ() {
        return this.fromQQ;
    }
    
    public String getFromAnonymous() {
        return this.fromAnonymous;
    }
    
    public String getMsg() {
        return this.msg1;
    }
    
    public String getFont() {
        return this.font;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getNick() {
        return this.nick;
    }
    
    public String getSex() {
        return this.sex;
    }
    
    public String getAge() {
        return this.age;
    }
    
    public String getFromGroupName() {
        return this.fromGroupName;
    }
}
