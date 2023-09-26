package com.awsoftware.bootcamp.clase3;

import java.util.Scanner;

public class CalcularProductoSinMultiplicar {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n1;
        int n2;

        System.out.println("Ingrese dos numeros:");
        n1 = sc.nextInt();
        n2 = sc.nextInt();

        System.out.println(producto(n1,n2));

    }

    //Recursion: Calcular el producto de dos enteros sin utilizar multiplicación
    public static int producto (int n1, int n2){

        if(n2 == 0) {
            return 0;
        } else if(n2 == 1){
            return n1;
        }
        return n1 + producto(n1, n2-1);
    }
}
