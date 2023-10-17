package com.awsoftware.bootcamp.clase3;

//3 - Implementar la clase Pila sin utilizar Collections
class PilaSinCollections {

    private int[] elementos;
    private int top;
    private int tamanioMax;

    public PilaSinCollections(int tamanio) {
        tamanioMax = tamanio;
        elementos = new int[tamanio];
        top = -1;
    }

    public void push(int elemento) {
        if (isFull()) {
            throw new IllegalStateException("La pila está llena.");
        }
        top++;
        elementos[top] = elemento;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("La pila está vacía.");
            return -1;
        }
        int elemento = elementos[top];
        top--;
        return elemento;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("La pila está vacía.");
            return -1;
        }
        return elementos[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == tamanioMax - 1;
    }

    public static void main(String[] args) {
        PilaSinCollections pila = new PilaSinCollections(5);
        pila.push(1);
        pila.push(2);
        pila.push(3);
        pila.push(4);
        pila.push(5);

        System.out.println("Cima de la pila: " + pila.peek());
        System.out.println("Desapilar elemento de la cima: " + pila.pop());
        System.out.println("Cima de la pila después del desapilado: " + pila.peek());

        while(!pila.isEmpty()){
            System.out.println("Desapilado: " + pila.pop());
        }

        }
}