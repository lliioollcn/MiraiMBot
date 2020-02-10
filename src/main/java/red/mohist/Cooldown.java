package red.mohist;

import java.util.HashMap;
import java.util.Map;

public class Cooldown {

    private static Map<String, Cooldown> cooldowns = new HashMap<>();
    private long start;
    private int timeInSeconds;
    private String id;
    private String cooldownName;

    public Cooldown(String id, String cooldownName, int timeInSeconds) {
        this.id = id;
        this.cooldownName = cooldownName;
        this.timeInSeconds = timeInSeconds;
    }

    public static boolean isInCooldown(String id, String cooldownName) {
        if (getTimeLeft(id, cooldownName) >= 1) {
            return true;
        }
        stop(id, cooldownName);
        return false;
    }

    private static void stop(String id, String cooldownName) {
        Cooldown.cooldowns.remove(id + cooldownName);
    }

    private static Cooldown getCooldown(String id, String cooldownName) {
        return Cooldown.cooldowns.get(id + cooldownName);
    }

    public static int getTimeLeft(String id, String cooldownName) {
        Cooldown cooldown = getCooldown(id, cooldownName);
        int f = -1;
        if (cooldown != null) {
            long now = System.currentTimeMillis();
            long cooldownTime = cooldown.start;
            int totalTime = cooldown.timeInSeconds;
            int r = (int) (now - cooldownTime) / 1000;
            f = (r - totalTime) * -1;
        }
        return f;
    }

    public void start() {
        this.start = System.currentTimeMillis();
        Cooldown.cooldowns.put(this.id + this.cooldownName, this);
    }
}
