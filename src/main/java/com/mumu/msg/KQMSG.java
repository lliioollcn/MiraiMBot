package com.mumu.msg;

public abstract class KQMSG
{
    public static int RE_MSG_PRIVATE = 21;
    public static int RE_MSG_GROUP = 2;
    public static int RE_MSG_FORUM = 4;
    public static int RE_EXAMPLE_MANAGE = 101;
    public static int RE_EXAMPLE_DEMBER = 102;
    public static int RE_EXAMPLE_AMEMBER = 103;
    public static int RE_EXAMPLE_ADDFRIEND = 201;
    public static int RE_ASK_ADDFRIEND = 301;
    public static int RE_ASK_ADDGROUP = 302;
    public static int SEND_MSG_GROUP = 101;
    public static int SEND_MSG_DISCUSS = 103;
    public static int SEND_MSG_PRIVATE = 106;
    protected int type;
}
