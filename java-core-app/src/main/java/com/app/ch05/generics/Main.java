package com.app.ch05.generics;

import com.app.ch05.covariant.A;
import com.app.ch05.covariant.B;
import com.app.ch05.covariant.C;

public class Main {

    public static void main(String[] args) {
        A[] array1 = new A[1];
        A[] array2 = new B[1];
        A[] array3 = new C[1];
        B[] array4 = new C[1];

        method(array1);
        method(array2);
        method(array3);
        method(array4);

        Object[] array5 = {new A(), new B(), new C()};
    }

    public static void method(A[] data){

    }
}
