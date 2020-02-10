package com.mumu.listenner;

import com.mumu.msg.*;

public abstract class KQMSGListenner
{
    public abstract void Re_MSG_Private(RE_MSG_Private p0);
    
    public abstract void RE_MSG_FORUM(RE_MSG_Forum p0);
    
    public abstract void RE_MSG_Group(RE_MSG_Group p0);
    
    public abstract void RE_EXAMPLE_MANAGE(RE_MSG_AdminChange p0);
    
    public abstract void RE_EXAMPLE_DEMBER(DeleteAdmin p0);
    
    public abstract void RE_EXAMPLE_AMEMBER(AddAdmin p0);
    
    public abstract void RE_EXAMPLE_ADDFRIEND(AddFriendExample p0);
    
    public abstract void RE_ASK_ADDFRIEND(AddFriends p0);
    
    public abstract void RE_ASK_ADDGROUP(ADDGroupExample p0);
}
