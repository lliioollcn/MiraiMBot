package com.mumu.listenner;

import com.mumu.msg.*;

public class KQMSGAdapter extends KQMSGListenner
{
    @Override
    public void Re_MSG_Private(RE_MSG_Private msg) {
    }
    
    @Override
    public void RE_MSG_FORUM(RE_MSG_Forum msg) {
    }
    
    @Override
    public void RE_MSG_Group(RE_MSG_Group msg) {
    }
    
    @Override
    public void RE_EXAMPLE_MANAGE(RE_MSG_AdminChange msg) {
        System.out.println("群成员变动 ");
    }
    
    @Override
    public void RE_EXAMPLE_DEMBER(DeleteAdmin msg) {
        System.out.println("群成员减少");
    }
    
    @Override
    public void RE_EXAMPLE_AMEMBER(AddAdmin msg) {
        System.out.println("群成员添加");
    }
    
    @Override
    public void RE_EXAMPLE_ADDFRIEND(AddFriendExample msg) {
        System.out.println("好友添加成功");
    }
    
    @Override
    public void RE_ASK_ADDFRIEND(AddFriends msg) {
        System.out.println("请求添加好友");
    }
    
    @Override
    public void RE_ASK_ADDGROUP(ADDGroupExample msg) {
        System.out.println("请求添加群");
    }
}
