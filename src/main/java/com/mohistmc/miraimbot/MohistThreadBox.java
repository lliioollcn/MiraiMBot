package com.mohistmc.miraimbot;

public class MohistThreadBox {

    public static class AssignableThread extends Thread {
        public AssignableThread(Runnable run) {
            super(run);
        }
        public AssignableThread() {
            super();
        }
    }
}
