package com.mumu.msg;


import cc.plural.jsonij.JSON;
import cc.plural.jsonij.parser.ParserException;

import java.io.IOException;

public class AddFriendExample extends RE_MSG
{
    private String act;
    private String subType;
    private String sendTime;
    private String fromQQ;
    
    public AddFriendExample(String msg) {
        super(msg);
        try {

            JSON json = JSON.parse(msg);
            this.act = String.format("%s", json.get("act"));
            this.subType = String.format("%s", json.get("subType"));
            this.sendTime = String.format("%s", json.get("sendTime"));
            this.fromQQ = String.format("%s", json.get("fromQQ"));
            this.type = 201;
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
    
    public String getFromQQ() {
        return this.fromQQ;
    }
}
