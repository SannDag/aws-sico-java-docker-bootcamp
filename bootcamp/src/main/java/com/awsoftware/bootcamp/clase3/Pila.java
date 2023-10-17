package com.awsoftware.bootcamp.clase3;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

//1 - Implementar la clase Pila (Utilizando una Collection que no implemente la interfaz Queue / Deque)

public class Pila<T> {

    private List<T> elementos;

    public Pila(){
        elementos = new ArrayList<>();
    }

    public void push(T elemento){
        elementos.add(elemento);
    }

    public T pop(){
        if (isEmpty()){
            throw new IllegalStateException("La pila está vacía.");
        }
        return elementos.remove(elementos.size()-1);
    }

    public boolean isEmpty(){
        return elementos.isEmpty();
    }

    public int size(){
        return elementos.size();
    }

    public T peek(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return elementos.get(elementos.size()-1);
    }

    public static void main(String[] args) {

        Pila <Integer> pila = new Pila<>();

        pila.push(1);
        pila.push(2);
        pila.push(3);
        pila.push(4);
        pila.push(5);
        pila.push(6);

        System.out.println("Tamaño pila: " + pila.size());
        System.out.println("Elementos pila: " + pila.elementos);
        System.out.println("Cima de la pila: " + pila.peek());
        System.out.println("Desapilar elemento de la cima: " + pila.pop());
        System.out.println("Tamaño después de desapilar: " + pila.size());
        System.out.println("Elementos pila después de desapilar: " + pila.elementos);
        System.out.println("Cima de la pila después del desapilado: " + pila.peek());

        while(!pila.isEmpty()){
            System.out.println("Desapilado: " + pila.pop());
        }

    }

}
