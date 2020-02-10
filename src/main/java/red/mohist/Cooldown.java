package red.mohist;

import java.util.HashMap;
import java.util.Map;

public class Cooldown {

    private static Map<String, Cooldown> cooldowns;
    private long start;
    private final int timeInSeconds;
    private final String id;
    private final String cooldownName;

    public Cooldown(final String id, final String cooldownName, final int timeInSeconds) {
        this.id = id;
        this.cooldownName = cooldownName;
        this.timeInSeconds = timeInSeconds;
    }

    public static boolean isInCooldown(final String id, final String cooldownName) {
        if (getTimeLeft(id, cooldownName) >= 1) {
            return true;
        }
        stop(id, cooldownName);
        return false;
    }

    private static void stop(final String id, final String cooldownName) {
        Cooldown.cooldowns.remove(id + cooldownName);
    }

    private static Cooldown getCooldown(final String id, final String cooldownName) {
        return Cooldown.cooldowns.get(id + cooldownName);
    }

    public static int getTimeLeft(final String id, final String cooldownName) {
        final Cooldown cooldown = getCooldown(id, cooldownName);
        int f = -1;
        if (cooldown != null) {
            final long now = System.currentTimeMillis();
            final long cooldownTime = cooldown.start;
            final int totalTime = cooldown.timeInSeconds;
            final int r = (int)(now - cooldownTime) / 1000;
            f = (r - totalTime) * -1;
        }
        return f;
    }

    public void start() {
        this.start = System.currentTimeMillis();
        Cooldown.cooldowns.put(this.id + this.cooldownName, this);
    }

    static {
        Cooldown.cooldowns = new HashMap<>();
    }
}
