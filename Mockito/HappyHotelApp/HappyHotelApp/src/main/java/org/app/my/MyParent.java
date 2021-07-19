package org.app.my;

import java.util.Map;

public class MyParent {

    public void doAction(){
        Map<String, String> envMap = System.getenv();
        System.out.println("------------------");
        for(String key: envMap.keySet()){
            System.out.println(key+"="+envMap.get(key));
        }
        System.out.println("------------------");
    }

}
