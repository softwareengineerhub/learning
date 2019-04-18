package com.app.ch07.references;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        List<WeakReference<byte[]>> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            try {
                byte[] data = new byte[1024 * 1024 * 10];
                list.add(new WeakReference<>(data));
                //System.gc();
                //Thread.sleep(1000);
            } catch(Throwable ex){
                //System.out.println(i);
                ex.printStackTrace();
            }
        }
        System.out.println(list.get(0).get());
        System.out.println(list.get(list.size()-1));
    }



    public static void main3(String[] args) {
        List<SoftReference<byte[]>> list = new ArrayList<>();
        for(int i=0;i<100;i++){
            try {
                byte[] data = new byte[1024 * 1024 * 10];
                list.add(new SoftReference<>(data));
                System.gc();
                Thread.sleep(1000);
            }catch(Throwable ex){
                //System.out.println(i);
                ex.printStackTrace();
            }
        }
        System.out.println(list.get(0).get());
        System.out.println(list.get(100-1));

    }


    public static void main2(String[] args) {
        List<SoftReference<byte[]>> list = new ArrayList<>();
        for(int i=0;i<1000;i++){
            try {
                byte[] data = new byte[1024 * 1024 * 10];
                list.add(new SoftReference<>(data));
            }catch(Error ex){
                //System.out.println(i);
                ex.printStackTrace();
            }
        }
        System.out.println(list.get(0).get());
        System.out.println(list.get(999));

    }



    public static void main1(String[] args) {
        List<byte[]> list = new ArrayList<>();
        for(int i=0;i<1000;i++){
            try {
                byte[] data = new byte[1024 * 1024 * 10];
                list.add(data);
            }catch(Error ex){
                System.out.println(i);
            }
        }
        System.out.println(list.get(999));

    }

    private void m(){
        System.gc();
        String s = new String("test");
        SoftReference<String> softReference = new SoftReference("test2");
        WeakReference<String> weakReference = new WeakReference<>("test3");
        //PhantomReference<String> phantomReference = new PhantomReference<>("test4");

        String value=softReference.get();
    }
}
