package com.mohistmc;

import java.util.concurrent.TimeUnit;

public class GitHubAuto implements Runnable {

    @Override
    public void run() {

    }

    public static void start() {
        MohistThreadBox.GitHubAuto.scheduleAtFixedRate(new GitHubAuto(), 30000L, 500L, TimeUnit.MILLISECONDS);
    }

    public static void stop() {
        MohistThreadBox.GitHubAuto.shutdown();
    }
}