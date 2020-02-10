package com.mumu.msg;

import jsonij.Value;
import jsonij.parser.JSONParser;
import jsonij.parser.ParserException;

public class AddAdmin extends RE_MSG
{
    private String act;
    private String subType;
    private String sendTime;
    private String fromGroup;
    private String fromQQ;
    private String beingOperateQQ;
    
    public AddAdmin(String msg) {
        super(msg);
        try {
            JSONParser jsonParser = new JSONParser();
            Value json = jsonParser.parse(msg);
            this.act = String.format("%s", json.get("act"));
            this.subType = String.format("%s", json.get("subType"));
            this.sendTime = String.format("%s", json.get("sendTime"));
            this.fromGroup = String.format("%s", json.get("fromGroup"));
            this.fromQQ = String.format("%s", json.get("fromQQ"));
            this.beingOperateQQ = String.format("%s", json.get("beingOperateQQ"));
            this.type = 103;
        }
        catch (ParserException e) {
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
    
    public String getBeingOperateQQ() {
        return this.beingOperateQQ;
    }
}
