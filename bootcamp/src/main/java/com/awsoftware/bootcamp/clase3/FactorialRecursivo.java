package com.awsoftware.bootcamp.clase3;

import java.util.Scanner;

public class FactorialRecursivo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int num;


        System.out.println("Ingrese numero:");
        num = sc.nextInt();

        System.out.println("Factorial recursivo: " + factorialRecursivo(num));

    }

    public static int factorialRecursivo(int num){
        if(num == 1 || num == 0){
            return 1;
        }

        return num * factorialRecursivo(num-1);
    }
}
