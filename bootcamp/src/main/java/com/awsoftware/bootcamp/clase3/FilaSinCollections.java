package com.awsoftware.bootcamp.clase3;

//3 - Implementar la clase Fila sin utilizar Collections
public class FilaSinCollections {

    private int[] elementos;
    private int ultimo, frente, tamanio, tamanioMax;

    public FilaSinCollections(int tamanioMax) {
        this.tamanioMax = tamanioMax;
        this.elementos = new int[tamanioMax];
        this.tamanio = 0;
        this.frente = 0;
        this.ultimo = -1;
    }

    public boolean add(int elemento) {
        if (isFull()) {
            throw new IllegalStateException("La cola está llena.");
        }

        ultimo = (ultimo + 1) % elementos.length;
        elementos[ultimo] = elemento;
        tamanio++;

        return true;
    }

    public int poll() {
        if (isEmpty()){
            throw new IllegalStateException("La fila está vacía");
        }

        int elementoDesencolado = elementos[frente];
        frente = (frente + 1) % elementos.length;
        tamanio--;

        return elementoDesencolado;
    }


    public boolean isEmpty() {
        return tamanio == 0;
    }

    public boolean isFull() {
        return tamanio == tamanioMax;
    }

    public int size() {
        return tamanio;
    }

    public int front(){
        if (isEmpty()){
            throw new IllegalStateException("La cola está vacía.");
        }
        return elementos[frente];
    }


    public static void main(String[] args) {
        FilaSinCollections fila = new FilaSinCollections(5);

        fila.add(1);
        fila.add(2);
        fila.add(3);
        fila.add(4);
        fila.add(5);

        System.out.println("Tamaño fila: " + fila.size());
        System.out.println("Primer elemento introducido: " + fila.front());
        System.out.println("Desencolado primer elemento: " + fila.poll());
        System.out.println("Tamaño después de desencolar primer elemento: " + fila.size());
        System.out.println("Primer elemento despues de desencolar el anterior: " + fila.front());

        while(!fila.isEmpty()){
            System.out.println("Desencolado: " + fila.poll());
        }

    }
}
