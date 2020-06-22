package com.project.gelingeducation.common.utils;

import java.util.concurrent.*;

public class ExecutorsUtils {
    ExecutorService es = null;

    private void init() {
        es = new ThreadPoolExecutor(5, 5, 0l, TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        //把线程设置为守护线程，主线程退出后，强制销毁
                        t.setDaemon(true);
                        System.out.println("create " + t);
                        return t;
                    }
                });
    }

    public void execute(Runnable runnable) {
        es.execute(runnable);
    }

    private ExecutorsUtils() {
        init();
    }

    private static class DaemonExecutorHopler {
        private static ExecutorsUtils instance = new ExecutorsUtils();
    }

    public static ExecutorsUtils getInstance() {
        return DaemonExecutorHopler.instance;
    }
}
