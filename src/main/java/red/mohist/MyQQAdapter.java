package red.mohist;

import com.mumu.listenner.KQMSGAdapter;
import com.mumu.msg.RE_MSG_Forum;
import com.mumu.msg.RE_MSG_Group;
import com.mumu.msg.RE_MSG_Private;
import com.mumu.webclient.KQWebClient;

public class MyQQAdapter extends KQMSGAdapter {

    private KQWebClient kqWebClient;


    MyQQAdapter(KQWebClient kqWebClient) {
        this.kqWebClient = kqWebClient;
    }

    /**
     * 接收私聊消息
     */
    public void Re_MSG_Private(RE_MSG_Private msg) {
        Main.logger.info("接收到私聊信息 from:" + msg.getFromqq() + " \t msg:" + msg.getMsg());
        kqWebClient.sendPrivateMSG(msg.getFromqq(), "你好,接收到了你的消息：" + msg.getMsg());
    }

    public void RE_MSG_FORUM(RE_MSG_Forum msg) {
        Main.logger.info("接收到消息 groupName:" + msg.getFromQQ() + "qq:" + msg.getFromQQ() + "msg:" + msg.getMsg());
    }

    /**
     * 接收群消息
     */
    public void RE_MSG_Group(RE_MSG_Group msg) {
        String qqmsg = msg.getMsg();
        if (msg.getFromGroup().equals("782534813") || msg.getFromGroup().equals("793311898")) {
            if (qqmsg.equals("#更新1.12.2")) {
                String cdid = msg.getFromGroup() + "1.12.2";
                Cooldown cooldown = new Cooldown(cdid, msg.getFromQQ(), 15);
                if (!Cooldown.isInCooldown(cdid, msg.getFromQQ())) {
                    Main.logger.info("接收到群聊消息 groupName:" + msg.getFromGroupName() + "\t qq:" + msg.getFromQQ() + "\t msg:" + qqmsg);
                    kqWebClient.sendGroupMSG(msg.getFromQQ(), msg.getFromGroup(), MohistUpdate.hasLatestVersion("1.12.2"), false);
                    cooldown.start();
                }
            }
            if (qqmsg.equals("#更新1.7.10")) {
                String cdid = msg.getFromGroup() + "1.7.10";
                Cooldown cooldown = new Cooldown(cdid, msg.getFromQQ(), 15);
                if (!Cooldown.isInCooldown(cdid, msg.getFromQQ())) {
                    Main.logger.info("接收到群[" + msg.getFromGroupName() + "]消息> " + msg.getFromQQ() + "\t msg:" + qqmsg);
                    kqWebClient.sendGroupMSG(msg.getFromQQ(), msg.getFromGroup(), MohistUpdate.hasLatestVersion("1.7.10"), false);
                    cooldown.start();
                }
            }
        }
    }
}
