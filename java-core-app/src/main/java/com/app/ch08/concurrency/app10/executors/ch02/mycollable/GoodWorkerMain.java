package com.app.ch08.concurrency.app10.executors.ch02.mycollable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GoodWorkerMain {

    public static void main(String[] args) throws Exception {
        int n=3;
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //int sum = add(1,2);

        int workersAmount = 30;

        List<Future<Integer>> results = new ArrayList();
        for(int i=0;i<workersAmount;i++) {
            Future<Integer> future = executorService.submit(new CallableGoodWorker("GW"+i, 3000));
            results.add(future);
           // Object res=future.get();
           // System.out.println("res="+res);
        }
        long sum = 0;

        for(Future future: results) {
            boolean res = future.cancel(false);
            System.out.println("cancel=" + res);
        }

        for(Future future: results){


            //while(!future.isDone()){
               //Thread.sleep(1000);
            //}
            if(future.isCancelled()){
                System.out.println("!!!!!!!!!"+future.get());
            }
            int value = (int) future.get();
            sum+=value;
        }

        /*for(Future future: results){
                int value = (int) future.get();
                sum+=value;
        }*/


        executorService.shutdown();
        System.out.println("sum:"+sum);
        System.out.println("------FINISH------");

    }

}
