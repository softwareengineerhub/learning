package org.app;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MyReflaction {

    public static void main(String[] args) throws Exception {
        Class cl = Class.forName("org.app.Data");
        Object object=cl.newInstance();

        Method[] methods=object.getClass().getDeclaredMethods();
        Method[] methods2=object.getClass().getMethods();
        Field[] fields=object.getClass().getDeclaredFields();
        System.out.println("-------Methods------------");
        for(Method m: methods){
            System.out.println(m);
        }
        System.out.println("-------Methods2------------");
        for(Method m: methods2){
            System.out.println(m);
        }
        System.out.println("-------Fields------------");
        for(Field f: fields){
            System.out.println(f);
        }

        System.out.println("------Method.invoke--------");
        Method m = methods[0];
        m.invoke(object);
        //methods[0].invoke()

        Data data = new Data();
        data.doAction();
        m.invoke(data);

        System.out.println("------Field.invoke--------");
        Field f = fields[0];
        f.setAccessible(true);
        f.set(data, "A");
        m.invoke(data);

        f.set(object, "B");
        m.invoke(object);


        System.out.println("------------------------");
        Annotation[] annotations = f.getDeclaredAnnotations();
        System.out.println(annotations.length);

        for(Annotation annotation: annotations){
           // System.out.println(annotation);
          //  System.out.println(annotation.getClass());
            if(annotation.annotationType()==GoodContent.class){
                System.out.println("This Is good CONTENT!!!!");
                GoodContent val = (GoodContent) annotation;
                String typeOfContent = val.typeOfContent();
                int length = val.length();
                System.out.println("typeOfContent="+typeOfContent);
                System.out.println("length="+length);
            }
        }



    }

}
