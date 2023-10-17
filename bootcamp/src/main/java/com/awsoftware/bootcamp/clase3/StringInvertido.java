package com.awsoftware.bootcamp.clase3;

import java.util.Scanner;

//Recursión: Método que recibe un String y lo devuelve invertido.
public class StringInvertido {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String input;

        System.out.println("Ingrese una palabra: ");
        input = sc.next();

        System.out.println(cadenaInvertida(input));

    }

    public static String cadenaInvertida(String input){
        if (input.length() == 1 || input.isEmpty())
            return input;
        else
            return cadenaInvertida(input.substring(1)) + input.charAt(0);
    }

}
