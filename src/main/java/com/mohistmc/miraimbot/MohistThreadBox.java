package com.mohistmc.miraimbot;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class MohistThreadBox {

    public static ScheduledExecutorService GitHubAuto = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("WatchMohist"));

    public static class AssignableThread extends Thread {
        public AssignableThread(Runnable run) {
            super(run);
        }
        public AssignableThread() {
            super();
        }
    }
}
