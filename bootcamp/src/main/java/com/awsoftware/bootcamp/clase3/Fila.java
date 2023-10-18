package com.awsoftware.bootcamp.clase3;

import java.util.*;

//2 - Implementar la clase Fila (Utilizando una Collection que no implemente la interfaz Queue / Deque)
public class Fila<T> {

    private List<T> elementos;

    public Fila(){
        elementos = new ArrayList<>();
    }

    public void add(T elemento){
        elementos.add(elemento);
    }

    public T poll() {
        if (isEmpty()){
            throw new IllegalStateException("La fila está vacía.");
        }
        return elementos.remove(0);
    }

    public int size() {
        return elementos.size();
    }

    public boolean isEmpty() {
        return elementos.isEmpty();
    }

    public T front(){
        if(isEmpty()){
            throw new IllegalStateException("La fila está vacía.");
        }
        return elementos.get(0);
    }

    public static void main(String[] args) {

        Fila<Integer> fila = new Fila<>();

        fila.add(1);
        fila.add(2);
        fila.add(3);
        fila.add(4);
        fila.add(5);


        System.out.println("Elementos: " + fila.elementos);
        System.out.println("Tamaño fila: " + fila.size());
        System.out.println("Primer elemento introducido: " + fila.front());
        System.out.println("Desencolado primer elemento: " + fila.poll());
        System.out.println("Tamaño después de desencolar primer elemento: " + fila.size());
        System.out.println("Elementos fila después de desencolar primer elemento: " + fila.elementos);
        System.out.println("Primer elemento despues de desencolar el anterior " + fila.front());

        while(!fila.isEmpty()){
            System.out.println("Desencolado: " + fila.poll());
        }




    }

}
