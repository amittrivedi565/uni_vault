package com.univault.css.Concurrency;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * Manages a thread pool for asynchronous task execution across the application.
 */
@Component
public class ThreadManager {

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int KEEP_ALIVE_TIME = 60;
    private static final int QUEUE_CAPACITY = 100;

    private final ExecutorService executorService;

    public ThreadManager() {
        this.executorService = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy() // Fallback if queue is full
        );
    }


    /**
     * Provides access to the internal executor service.
     * Useful for CompletableFuture and advanced async control.
     *
     * @return ExecutorService
     */
    public ExecutorService getExecutor() {
        return executorService;
    }
}
