package com.awsoftware.bootcamp.clase2;

//2 - Factorial - Metodo que recibe un entero y devuelve el factorial del mismo
// (¿lo podemos hacer también con tipos genéricos?)

import java.util.Scanner;

public class Factorial {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int num;

        System.out.println("Ingrese numero:");
        num = sc.nextInt();

        System.out.println("Factorial: " + factorial(num));

    }

    public static int factorial(int num){
        int fact = 1;

        if(num == 1 || num == 0){
            return 1;
        }

        while(num > 1){
            fact *= num;
            num--;
        }
        return fact;
    }
}
