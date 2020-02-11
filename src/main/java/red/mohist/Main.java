package red.mohist;

import com.mumu.webclient.KQWebClient;

import java.net.URI;

public class Main {


    private static KQWebClient kqWebClient;

    public static void runClient(String host) {
        try {
            if (kqWebClient == null) {
                //连接coolq服务器
                kqWebClient = new KQWebClient(new URI(host));
            }
            //消息监听适配器
            MyQQAdapter myQQAdapter = new MyQQAdapter(kqWebClient);
            //监听消息
            kqWebClient.addQQMSGListenner(myQQAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main.runClient("ws://127.0.0.1:25303");
    }
}
