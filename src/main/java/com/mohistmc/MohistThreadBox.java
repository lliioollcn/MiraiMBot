package com.mohistmc;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class MohistThreadBox {

    public static ScheduledThreadPoolExecutor GitHubAuto = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("WatchMohist"));

    public static class AssignableThread extends Thread {
        public AssignableThread(Runnable run) {
            super(run);
        }
        public AssignableThread() {
            super();
        }
    }
}
