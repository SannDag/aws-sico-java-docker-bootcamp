package com.awsoftware.bootcamp.clase2;


import java.util.Scanner;

public class BinarioADecimal {

    public static void main(String[] args) {

        double binario;

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese numero: ");
        binario = sc.nextDouble();

        System.out.println(binarioADecimal(binario));

    }

    //4 - Metodo que recibe un double en formato binario y devuelve un entero en formato decimal.
    public static double binarioADecimal(double binario) {
        int decimal = 0, base = 1;

        String verifBinario = String.valueOf(binario);

        for (int i = 0; i < verifBinario.length(); i++) {
            if (!verifBinario.contains("1") || !verifBinario.contains("0")) {
                throw new NumberFormatException("El número contiene dígitos distintos de 0 y 1.");
            }
        }

        while (binario > 0) {
            int resto = (int) (binario % 10);
            decimal += resto * base;
            base *= 2;
            binario /= 10;
        }

        return decimal;
    }
}
