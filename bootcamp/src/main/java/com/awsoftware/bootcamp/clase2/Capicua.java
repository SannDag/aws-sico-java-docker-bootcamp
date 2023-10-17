package com.awsoftware.bootcamp.clase2;


//1 - Número entero capicua - Construir un metodo que reciba un entero como parámetro y devuelva boolean
// indicando si es capicúa o no dicho número. (Version No Recursiva y Recursiva)

import java.util.Scanner;

public class Capicua {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int num;

        System.out.println("Ingrese numero:");
        num = sc.nextInt();

        System.out.println("(noRecursivo)El numero " + num + " es capicúa? " + capicuaNoRecursivo(num));
        System.out.println("(recursivo)El numero " + num + " es capicúa? " + capicuaRecursivo(num));

    }

    public static boolean capicuaNoRecursivo(int num) {

        if(num > 0) {
            String numStr = String.valueOf(num);

            for (int i = 0; i < numStr.length() / 2; i++) {
                if (numStr.charAt(i) != numStr.charAt(numStr.length() - i - 1)) {
                    return false;
                }
            }
        } else {
            throw new IllegalArgumentException("Debe ingresar un numero entero positivo");
        }
        return true;
    }

    public static boolean capicuaRecursivo(int num) {

        String numStr = String.valueOf(num);

        if (numStr.length() > 2) {

            int digitoInicio = numStr.charAt(0);
            int digitoFinal = numStr.charAt(numStr.length() - 1);

            if(digitoInicio != digitoFinal){
                return false;
            }

            int nuevoNumero = Integer.parseInt(numStr.substring(1, numStr.length()-1));
            return capicuaRecursivo(nuevoNumero);
        } else {
            return true;
        }

    }
}