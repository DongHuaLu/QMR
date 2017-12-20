package com.game.cache.structs;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestCache {
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    
    private static final int TEST_TIMES = 100000;
    
    private static CountDownLatch count1 = new CountDownLatch(TEST_TIMES);
    private static CountDownLatch count2 = new CountDownLatch(TEST_TIMES);

    public static void main(String[] args) throws InterruptedException {
        Map<Integer,Integer> cache1 = new ConcurrentHashMap<Integer,Integer>();
        Map<Integer, Integer> lruCache= new LRULinkedHashMap<Integer, Integer>(Integer.MAX_VALUE);
        for(int i=0;i<1000;i++) {
            cache1.put(i, i);
            lruCache.put(i, i);
        }
        
        long start = System.currentTimeMillis();
        for(int i=0;i<TEST_TIMES;i++) {
            getRange(100, 200, cache1,count1);
        }
        count1.await();
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        for(int i=0;i<TEST_TIMES;i++) {
            getRange(100, 200, lruCache,count2);
        }
        count2.await();
        System.out.println(System.currentTimeMillis() - start);
        executor.shutdown();
    }
    
    
    public static void getRange(final int min,final int max,final Map<Integer, Integer> map,final CountDownLatch count) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                for(int i=min;i<max;i++) {
                    map.get(i);
                }
                count.countDown();
            }
        });

    }
}
