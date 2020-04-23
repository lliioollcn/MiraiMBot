package xin.lz1998;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import red.mohist.CommandManager;
import red.mohist.cmds.Ping;
import red.mohist.cmds.Update;
import red.mohist.cmds.WaitFix;
import red.mohist.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.Set;


@SpringBootApplication
public class SpringCQApplication {

    public static String dataFile = "waitFix.json";

    public static void main(String[] args) throws IOException {
        CommandManager.register("更新", new Update());
        CommandManager.register("待修", new WaitFix());
        CommandManager.register("检测", new Ping());
        LogManager.getLogger().info("正在加载数据中，请稍等...");
        File dataFile = new File(SpringCQApplication.dataFile);
        if (dataFile.exists()) {
            try {
                JSONObject j = JSON.parseObject(FileUtil.readContent(dataFile, null));
                Set<String> a = j.keySet();
                for (String s : a) {
                    int x = Integer.parseInt(s);
                    WaitFix.l.put(x, j.getString(s));
                }
            } catch (Exception e) {
            }
        } else {
            dataFile.createNewFile();
        }

        SpringApplication.run(SpringCQApplication.class, args);
    }

}
