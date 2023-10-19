package com.awsoftware.bootcamp.clase3;

import lombok.Getter;
import lombok.Setter;

//4 - Implementar la clase Arbol Binario con los 3 recorridos básicos (inOrden, preOrden y postOrden)

/*
    **ARBOL BINARIO DE BÚSQUEDA**
    Un árbol binario con raíz R es de búsqueda cuando no es vacío y:
    - si tiene un subárbol izquierdo, la raíz del subárbol izquierdo es menor a R, y a la vez
    el subárbol izquierdo también es de búsqueda.
    - Si tiene un subárbol derecho, la raíz de subárbol derecho es mayor a R y a la vez el subárbol
    derecho también es de búsqueda.

    Un árbol de búsqueda puede almacenar elementos que cumplan una relación de orden. Debe haber una forma
    de ordenar los elementos.

    Ejemplo:
    -libros usando como clave el ISBN
    -personas usando como clave el codigo de empleado
    -cadenas de caracteres por orden lexicográfico: "Alicia < Benito"
    -etc.

    **BÚSQUEDA** (no implementado)
    # Localizar elementos a partir de su clave.
    # Usos:
    - Operación '¿existe?': comprobar si una clave está presente en un árbol o no.
    - Operación 'obtener': obtener el objeto cuyo identificador sea el que pedimos.

    **INSERTAR**
    # Es necesario introducir el elemento de forma ordenada en el árbol.
    # Además, hay que distinguir si el árbol permite duplicados o no.
    -si el árbol, es un árbol VACÍO: insertamos en la raíz
    -si la raíz del árbol es IGUAL al elemento a insertar: si no se acepta duplicados, no insertamos
    o emitimos un error
    -si la raíz del árbol es MAYOR al elemento a insertar: insertamos en el subárbol izquierdo
    -si la raíz del arbol es MENOR al elemento a insertar: insertamos en el subárbol derecho

    INORDEN: recorrido -> hijo izquierdo - raíz - hijo derecho
    PREORDEN: recorrido -> raíz - hijo izquierdo - hijo derecho
    POSTORDEN: recorrido -> hijo izquierdo - hijo derecho - raíz
    */

public class ArbolBinario {

    NodoArbol raiz;

    public ArbolBinario(){
        this.raiz = null;
    }

    public void insertar(int dato){
        if (this.raiz == null){
            this.raiz = new NodoArbol(dato);
        } else {
            this.insertar(this.raiz, dato);
        }
    }

    private void insertar(NodoArbol padre, int dato){
        if (dato > padre.getDato()){
            if (padre.getNodoDer() == null){
                padre.setNodoDer(new NodoArbol(dato));
            } else {
                this.insertar(padre.getNodoDer(), dato);
            }
        } else {
            if (padre.getNodoIzq() == null){
                padre.setNodoIzq(new NodoArbol(dato));
            } else {
                this.insertar(padre.getNodoIzq(), dato);
            }
        }
    }

    public void inOrden(NodoArbol nodo){
        if(nodo != null){
            inOrden(nodo.getNodoIzq());
            nodo.imprimirDato();
            inOrden(nodo.getNodoDer());
        }
    }

    public void preOrden(NodoArbol nodo){
        if(nodo != null){
            nodo.imprimirDato();
            preOrden(nodo.getNodoIzq());
            preOrden(nodo.getNodoDer());
        }
    }

    public void postOrden(NodoArbol nodo){
        if(nodo != null){
            postOrden(nodo.getNodoIzq());
            postOrden(nodo.getNodoDer());
            nodo.imprimirDato();
        }
    }

    public void inOrden() {
        this.inOrden(this.raiz);
    }

    public void preOrden() {
        this.preOrden(this.raiz);
    }

    public void postOrden() {
        this.postOrden(this.raiz);
    }
}

@Getter
@Setter
class NodoArbol{

    private NodoArbol nodoIzq, nodoDer;
    private int dato;

    public NodoArbol(int dato){
        this.dato = dato;
        this.nodoIzq = null;
        this.nodoDer = null;
    }

    public void imprimirDato(){
        System.out.print(this.getDato() + " ");
    }

    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        arbol.insertar(50);
        arbol.insertar(19);
        arbol.insertar(21);
        arbol.insertar(18);
        arbol.insertar(36);

        System.out.println("Recorriendo in orden:");
        arbol.inOrden();
        System.out.println("");

        System.out.println("Recorriendo post orden:");
        arbol.postOrden();
        System.out.println("");

        System.out.println("Recorriendo pre orden:");
        arbol.preOrden();
        System.out.println("");
    }
}


