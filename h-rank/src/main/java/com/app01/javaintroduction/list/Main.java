package com.app01.javaintroduction.list;

public class Main {



    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        String line = scanner.nextLine();
        int size = Integer.parseInt(line.trim());
        line = scanner.nextLine();
        //System.out.println("line="+line);
        String[] data = line.split(" ");
        java.util.List list = new java.util.ArrayList();
        for(String s: data){
            list.add(s);
        }
        //System.out.println(list);

        line = scanner.nextLine();
        int steps = Integer.parseInt(line.trim());
        for(int i=0;i<steps;i++){
            String actionLine = scanner.nextLine();
            String tmpLine = scanner.nextLine();
            String[] arr = tmpLine.split(" ");
            int index = Integer.parseInt(arr[0]);
            if("Insert".equals(actionLine)){
                list.add(index, arr[1]);
            }else if("Delete".equals(actionLine)){
                list.remove(index);
            }
        }




        String s = "";
        for(Object i: list){
            s+=" "+i;
        }
        s = s.trim();
        System.out.println(s);
    }

    /*public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int size = -1;
        java.util.List list = null;
        String lastAction = null;
        while(scanner.hasNext()){
            String line = scanner.nextLine();
            if(size==-1){
                size = Integer.parseInt(line.trim());
            } else if(list==null){
                String[] data = line.split(" ");
                list = java.util.Arrays.asList();
            } else{
                if("Insert".equals(line.trim()) || "Delete".equals(line.trim())){
                    lastAction = line.trim();
                }else{
                    String[] data = line.split(" ");
                    int index = Integer.parseInt(data[0]);
                    if(data.length==1){
                        //list.remove(index);
                    }else{
                        //list.add(index, data[1]);
                    }
                }
            }

        }
        System.out.println(list);
    }*/
}
