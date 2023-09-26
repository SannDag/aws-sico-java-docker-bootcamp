package com.awsoftware.bootcamp.clase2;

import java.util.Scanner;

public class DecimalABinario {

    public static void main(String[] args) {

        int num;

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese numero: ");
        num = sc.nextInt();
        System.out.println(decimalABinario(num));

    }

    //3 - Metodo que recibe un entero en formato decimal y devuelve un double en formato binario.
    public static double decimalABinario(int num) {
        double binario = 0;
        int base = 1;

        String verifBinario = String.valueOf(num);

        for (int i = 0; i < verifBinario.length(); i++) {
            if (!verifBinario.contains("1") || !verifBinario.contains("0")) {
                throw new NumberFormatException("El número contiene al menos un dígito distinto de 0 y 1.");
            }
        }

        while (num > 0) {
            int resto = num % 2;
            binario += resto * base;
            base *= 10;
            num /= 2;
        }

        return binario;

    }
}