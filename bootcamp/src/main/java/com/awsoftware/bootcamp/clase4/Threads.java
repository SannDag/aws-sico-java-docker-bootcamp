package com.awsoftware.bootcamp.clase4;

import java.util.stream.IntStream;

/*
1) Mostrar los numeros del 1 al 20 con un retardo de 3 segundos entre cada numero

2) Generar un Thread o una Implementacion de Runnable que imprima numeros pares (1 al 10) y calcule la suma.
   Lanzar junto a otro Thread o Runnable que imprima impares (1 al 10) y calcula la suma.
   Cada Thread muestra la suma resultante cuando finaliza.
   Utilizar las pausas necesarias para que se observe el paralelismo.

 */
public class Threads implements Runnable {

    public void run(){

//        try{
//            for(int i = 1; i < 21; i++){
//                System.out.println(i);
//                Thread.sleep(3000);
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        IntStream.range(1,21)
                .forEach(i ->{
                    System.out.println(i);
                    try{
                        Thread.sleep(3000);
                    } catch (InterruptedException e){
                        throw new RuntimeException();
                    }
                });
    }

    static class ThreadsPares implements Runnable{

        int totalPares;

        @Override
        public void run() {

//            for(int i = 1; i < 11; i++){
//                if(i % 2 == 0){
//                    System.out.println(i);
//                    totalPares += i;
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }

            totalPares = IntStream.range(1, 11)
                    .filter(i -> i % 2 == 0)
                    .peek(i -> {
                        System.out.println("Par: " + i);
                        try{
                            Thread.sleep(2000);
                        } catch (InterruptedException e){
                            throw new RuntimeException();
                        }
                    })
                    .sum();

            System.out.println("Total pares:" + totalPares);
        }
    }

    static class ThreadsImpares implements Runnable{

        int totalImpares;

        public void run(){

//            for(int i = 1; i<11; i++){
//                if (i % 2 == 1){
//                    System.out.println(i);
//                    totalImpares += i;
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }

            totalImpares = IntStream.range(1,11)
                    .filter(i -> i % 2 == 1)
                    .peek(i -> {
                        System.out.println("Impar: " + i);
                        try{
                            Thread.sleep(2000);
                        } catch (InterruptedException e){
                            throw new RuntimeException();
                        }
                    })
                    .sum();

            System.out.println("Total impares:" + totalImpares);
        }
    }

    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new Thread(new Threads());
        Thread tPar = new Thread(new ThreadsPares());
        Thread tImpar = new Thread(new ThreadsImpares());

        System.out.println("***Ejercicio 1***");
        t1.start();
        t1.join();
        System.out.println("FIN");

        System.out.println("***Ejercicio 2 - pares e impares***");
        tPar.start();
        tImpar.start();

        try{
            tPar.join();
            tImpar.join();
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        System.out.println("FIN");

    }


}
