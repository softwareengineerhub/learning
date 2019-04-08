package com.app.ch00.params.myiinerclass;

public class MyOuterClass {

    private int n=1;

    public void printData(){
        System.out.println("Printdata. OUTER");
    }

    public void printData(int value){
        System.out.println("Printdata.(value)="+value);
    }



    private class MyInnerA{

    }

    protected class MyInnerB{

    }

    class MyInnerC{

    }

    public class MyInnerD {
        private int n=10;

        public MyInnerD(){
            printData();
        }

        public void doAction(){
            //printData(n);
            printData(MyOuterClass.this.n);
        }



    }

    static public class MyInnerStatic{

        public MyInnerStatic(){
        //    printData();
        }

    }


}


class MyC {

}
