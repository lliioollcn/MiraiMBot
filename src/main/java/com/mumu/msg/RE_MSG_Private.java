package com.mumu.msg;

import jsonij.Value;
import jsonij.parser.JSONParser;
import jsonij.parser.ParserException;

public class RE_MSG_Private extends RE_MSG
{
    private String nick;
    private String sex;
    private String age;
    private String error;
    private String act;
    private String fromqq;
    private String subtype;
    private String time;
    private String msg1;
    private String font;
    
    public RE_MSG_Private(String msg) {
        super(msg);
        try {
            JSONParser jsonParser = new JSONParser();
            Value json = jsonParser.parse(msg);
            this.nick = String.format("%s", json.get("nick"));
            this.sex = String.format("%s", json.get("sex"));
            this.age = String.format("%s", json.get("age"));
            this.error = String.format("%s", json.get("error"));
            this.act = String.format("%s", json.get("act"));
            this.fromqq = String.format("%s", json.get("fromQQ"));
            this.subtype = String.format("%s", json.get("subtype"));
            this.time = String.format("%s", json.get("time"));
            this.msg1 = String.format("%s", json.get("msg"));
            this.font = String.format("%s", json.get("font"));
            this.type = 21;
        }
        catch (ParserException e) {
            e.printStackTrace();
        }
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
    
    public String getError() {
        return this.error;
    }
    
    public String getAct() {
        return this.act;
    }
    
    public String getFromqq() {
        return this.fromqq;
    }
    
    public String getSubtype() {
        return this.subtype;
    }
    
    public String getTime() {
        return this.time;
    }
    
    public String getMsg() {
        return this.msg1;
    }
    
    public String getFont() {
        return this.font;
    }
}
