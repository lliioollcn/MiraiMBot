package xin.lz1998;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import xin.lz1998.cq.robot.CoolQ;

public class Global {
    public static Map<Long, CoolQ> robots = new ConcurrentHashMap<>();


}
