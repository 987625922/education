package com.project.gelingeducation.common.utils;

import java.util.concurrent.*;

/**
 * 线程池
 */
public class ExecutorsUtils {

    /**
     * 线程池
     */
    ExecutorService es = null;

    /**
     * 线程池的初始化
     */
    private void init() {
        //固定5个线程的线程池
        es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
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

    /**
     * 跑线程
     *
     * @param runnable
     */
    public void execute(Runnable runnable) {
        es.execute(runnable);
    }

    /**
     * 初始化
     */
    private ExecutorsUtils() {
        init();
    }

    /**
     * 静态内部类的单例线程池
     */
    private static class DaemonExecutorHopler {
        private static ExecutorsUtils instance = new ExecutorsUtils();
    }

    public static ExecutorsUtils getInstance() {
        return DaemonExecutorHopler.instance;
    }
}
