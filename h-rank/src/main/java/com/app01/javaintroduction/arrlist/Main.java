package com.app01.javaintroduction.arrlist;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int n = Integer.parseInt(line.trim());
        java.util.List[] data = new java.util.ArrayList[n];
        for(int i=0;i<n;i++){
            line = scanner.nextLine();
            String [] tmp = line.split(" ");
            java.util.List dataTmp = new java.util.ArrayList();
            for(int j=1;j<tmp.length;j++){
                dataTmp.add(tmp[j]);
            }
            data[i] = dataTmp;
        }

        line = scanner.nextLine();
        int count = Integer.parseInt(line.trim());
        for(int i=0;i<count;i++){
            line = scanner.nextLine();
            String [] tmp = line.split(" ");
            int index = Integer.parseInt(tmp[0])-1;
            System.out.println("index="+index);
            int position = Integer.parseInt(tmp[1])-1;
            System.out.println("position="+position);
            java.util.List list = data[index];
            System.out.println("list="+list);


            if(list.size()<=position){
                System.out.println("ERROR!");
            } else {
                System.out.println(list.get(position));
            }
        }
    }

}
