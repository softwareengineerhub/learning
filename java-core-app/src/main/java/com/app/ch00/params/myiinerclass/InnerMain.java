package com.app.ch00.params.myiinerclass;

public class InnerMain {

    public static void main(String[] args) {
            MyOuterClass.MyInnerD myInnerD =
                    new MyOuterClass(). new MyInnerD();

            MyOuterClass myOuterClass = new MyOuterClass();
            myInnerD = myOuterClass.new MyInnerD();

            MyOuterClass.MyInnerStatic myInnerStatic
                    = new MyOuterClass.MyInnerStatic();


            myInnerD.doAction();

    }
}
