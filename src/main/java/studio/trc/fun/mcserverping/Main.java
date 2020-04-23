package studio.trc.fun.mcserverping;

public class Main
{

    public static class AsyncThread extends Thread
    {
        public AsyncThread(final Runnable r) {
            super(r);
        }
    }
    
    private static class ColorText
    {
        private static final String Color_1 = "\u001b[0;34;22m";
        private static final String Color_2 = "\u001b[0;32;22m";
        private static final String Color_3 = "\u001b[0;36;22m";
        private static final String Color_4 = "\u001b[0;31;22m";
        private static final String Color_5 = "\u001b[0;35;22m";
        private static final String Color_6 = "\u001b[0;33;22m";
        private static final String Color_7 = "\u001b[0;37;22m";
        private static final String Color_8 = "\u001b[0;30;1m";
        private static final String Color_9 = "\u001b[0;34;1m";
        private static final String Color_0 = "\u001b[0;30;22m";
        private static final String Color_a = "\u001b[0;32;1m";
        private static final String Color_b = "\u001b[0;36;1m";
        private static final String Color_c = "\u001b[0;31;1m";
        private static final String Color_d = "\u001b[0;35;1m";
        private static final String Color_e = "\u001b[0;33;1m";
        private static final String Color_f = "\u001b[0;37;1m";
        private static final String Color_l = "\u001b[21m";
        private static final String Color_o = "\u001b[3m";
        private static final String Color_n = "\u001b[4m";
        private static final String Color_m = "\u001b[9m";
        private static final String Color_r = "\u001b[0m";
        
        public static String getColorText(String text) {
            if (text == null) {
                return null;
            }
            text = text.replace("&1", "\u001b[0;34;22m");
            text = text.replace("&2", "\u001b[0;32;22m");
            text = text.replace("&3", "\u001b[0;36;22m");
            text = text.replace("&4", "\u001b[0;31;22m");
            text = text.replace("&5", "\u001b[0;35;22m");
            text = text.replace("&6", "\u001b[0;33;22m");
            text = text.replace("&7", "\u001b[0;37;22m");
            text = text.replace("&8", "\u001b[0;30;1m");
            text = text.replace("&9", "\u001b[0;34;1m");
            text = text.replace("&0", "\u001b[0;30;22m");
            text = text.replace("&a", "\u001b[0;32;1m");
            text = text.replace("&b", "\u001b[0;36;1m");
            text = text.replace("&c", "\u001b[0;31;1m");
            text = text.replace("&d", "\u001b[0;35;1m");
            text = text.replace("&e", "\u001b[0;33;1m");
            text = text.replace("&f", "\u001b[0;37;1m");
            text = text.replace("&l", "\u001b[21m");
            text = text.replace("&o", "\u001b[3m");
            text = text.replace("&n", "\u001b[4m");
            text = text.replace("&m", "\u001b[9m");
            text = text.replace("&r", "\u001b[0m");
            text = text.replace("&k", "");
            text = text.replace("§1", "\u001b[0;34;22m");
            text = text.replace("§2", "\u001b[0;32;22m");
            text = text.replace("§3", "\u001b[0;36;22m");
            text = text.replace("§4", "\u001b[0;31;22m");
            text = text.replace("§5", "\u001b[0;35;22m");
            text = text.replace("§6", "\u001b[0;33;22m");
            text = text.replace("§7", "\u001b[0;37;22m");
            text = text.replace("§8", "\u001b[0;30;1m");
            text = text.replace("§9", "\u001b[0;34;1m");
            text = text.replace("§0", "\u001b[0;30;22m");
            text = text.replace("§a", "\u001b[0;32;1m");
            text = text.replace("§b", "\u001b[0;36;1m");
            text = text.replace("§c", "\u001b[0;31;1m");
            text = text.replace("§d", "\u001b[0;35;1m");
            text = text.replace("§e", "\u001b[0;33;1m");
            text = text.replace("§f", "\u001b[0;37;1m");
            text = text.replace("§l", "\u001b[21m");
            text = text.replace("§o", "\u001b[3m");
            text = text.replace("§n", "\u001b[4m");
            text = text.replace("§m", "\u001b[9m");
            text = text.replace("§r", "\u001b[0m");
            text = text.replace("§k", "");
            return text + "\u001b[0m";
        }
    }
}
