package com.app01.ch01.request;

import com.app01.ch01.shape.DrawShape;
import com.app01.ch01.shape.DrawSquare;
import com.google.inject.Provider;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ShapeRequest {

   private Map<String, DrawShape> shapeNameVSDrawShape;

    @Inject
    public ShapeRequest(Set<DrawShape> set) {
        shapeNameVSDrawShape = new HashMap<>();
        for(DrawShape d: set){
            shapeNameVSDrawShape.put(d.getShapeName(), d);
        }
    }

    public void makeRequest(String shapeName){
        DrawShape d = shapeNameVSDrawShape.get(shapeName);
        d.draw();
    }
}
