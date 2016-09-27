package com.game.utils;

import java.util.concurrent.LinkedBlockingQueue;

import com.game.shop.log.ShopBuyLog;


public class SizeOf  
{  
    public static void main (String [] args) throws Exception  
    {  
        // Warm up all classes/methods we will use  
        runGC ();  
        usedMemory ();  
        // Array to keep strong references to allocated objects  
        final int count = 10000000;  
//        Object [] objects = new Object[count];  
        LinkedBlockingQueue<ShopBuyLog> list=new LinkedBlockingQueue<ShopBuyLog>();
        long heap1 = 0;  
        // Allocate count+1 objects, discard the first one  
        for (int i = -1; i < count; ++ i)  
        {  
//        	Object object=null;
        	ShopBuyLog log = null;  
            
            // Instantiate your data here and assign it to object  
              
        	log = new ShopBuyLog ();  
        	log.setCosttype(1);
        	log.setItems("{\"bind\":false,\"clazz\":\"com.game.backpack.structs.CommonGoods\",\"gridId\":0,\"id\":323117282203989,\"itemModelId\":-1,\"losttime\":0,\"num\":1080,\"packageId\":0}");
        	log.setNpcid(Integer.MAX_VALUE);
        	log.setNum(Integer.MAX_VALUE);
        	log.setResume(Integer.MAX_VALUE);
        	log.setRoleid(Long.MAX_VALUE);
        	log.setRolelevel(Integer.MAX_VALUE);
        	log.setSellid(Integer.MAX_VALUE);
//            log.setTime(System.currentTimeMillis());
//            object=log;
//            object=log.buildSql();
            //object = new Integer (i);  
            //object = new Long (i);  
//            object = new String ();  
//            object = new byte [128][1];  
              
            if (i >= 0)  
            	list.add(log);
            else  
            {  
            	log = null; // Discard the warm up object  
//                runGC ();  
                heap1 = usedMemory (); // Take a before heap snapshot  
            }  
        }  
//        runGC ();  
        long heap2 = usedMemory (); // Take an after heap snapshot:  
          
        final int size = Math.round (((float)(heap2 - heap1))/count);  
        System.out.println ("'before' heap: " + heap1 +  
                            ", 'after' heap: " + heap2);  
        System.out.println ("heap delta: " + (heap2 - heap1) +  
            ", {" + ShopBuyLog.class.getSimpleName() + "} size = " + size + " bytes");  
//        for (int i = 0; i < count; ++ i) objects [i] = null;  
//        objects = null;  
    }  
    private static void runGC () throws Exception  
    {  
        // It helps to call Runtime.gc()  
        // using several method calls:  
        for (int r = 0; r < 4; ++ r) _runGC ();  
    }  
    private static void _runGC () throws Exception  
    {  
        long usedMem1 = usedMemory (), usedMem2 = Long.MAX_VALUE;  
        for (int i = 0; (usedMem1 < usedMem2) && (i < 500); ++ i)  
        {  
            s_runtime.runFinalization ();  
            s_runtime.gc ();  
            Thread.yield ();  
              
            usedMem2 = usedMem1;  
            usedMem1 = usedMemory ();  
        }  
    }  
    private static long usedMemory ()  
    {  
        return s_runtime.totalMemory () - s_runtime.freeMemory ();  
    }  
      
    private static final Runtime s_runtime = Runtime.getRuntime ();  
} // End of class  