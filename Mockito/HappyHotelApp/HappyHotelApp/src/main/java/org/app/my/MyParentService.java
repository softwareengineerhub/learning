package org.app.my;

import java.util.HashMap;
import java.util.Map;

public class MyParentService {

    private MyChildService myChildService;
    public Map<String, Long> statisticsMap = new HashMap<>();

    public MyParentService(MyChildService myChildService) {
        this.myChildService = myChildService;
    }


    public Map<String, String> doAction() {
        try {
            Map<String, String> map = new HashMap<>();
            map.put(myAction(), null);
            return map;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    private String myAction() {
        try {
            return myChildService.makeAction();
        } catch (Exception ex) {
            handleException(ex);
            throw new RuntimeException(ex);
        }
    }


    private void handleException(Exception ex) {
        if (ex.getClass() == NullPointerException.class) {
            updateStatistics("npe");
        } else {
            updateStatistics("some-exp");
        }
    }

    private void updateStatistics(String key) {
        if (!statisticsMap.containsValue(key)) {
            statisticsMap.put(key, 0L);
        }
        long current = statisticsMap.get(key);
        statisticsMap.put(key, ++current);
    }


}
