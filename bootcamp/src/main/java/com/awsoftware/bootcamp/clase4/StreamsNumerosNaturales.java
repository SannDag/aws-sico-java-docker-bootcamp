package com.awsoftware.bootcamp.clase4;

import java.util.List;

/*
PARTE II:

1 - Construir un Stream de numeros naturales que contenga todos los números pares mayores o iguales a 10 y menores o iguales a 20.  Presentar en una lista de Integer en forma ordenada.
Existen varias formas de realizar esto y me gustaría ver todas las opciones que se les ocurren.

2 - Si pudieron realizar correctamente el anterior, ahora creemos un método como el siguiente:

	List<Integer> obtenerListaSecuencialCondicionada(Integer desde, Integer hasta, Predicate<Integer> condicion)

Como ven, estoy generalizando el ejercicio anterior. Y, como establecerían la posibilidad de recibir múltiples predicados condicionales?

	List<Integer> obtenerListaSecuencialCondicionadaMultiple(Integer desde, Integer hasta, List<Predicate<Integer>> condiciones)
	List<Integer> obtenerListaSecuencialCondicionadaMultiple(Integer desde, Integer hasta, Predicate<Integer>[] condiciones)
	List<Integer> obtenerListaSecuencialCondicionadaMultiple(Integer desde, Integer hasta, Predicate<Integer> ... condicion)

Desarrollar los 3 por favor. Es posible, por que? Si no es posible, defina 2.

Ahora pensemos que cualquiera de los parámetros (desde, hasta, condicion) podrían ser nulos.  Como lo resolverían.  Incluso, implementemos el metodo:

	List<Integer> obtenerListaSecuencial(Integer desde, Integer hasta)  reutilizando el metodo anterior!!!

3 - Utilizando el metodo anterior, obtenga una lista de numeros entre 2 limites y proceda a transformarlos en String para despues obtener una cadena como la siguiente:
10 -> 11 -> 12 -> 13 -> 14 -> 15 ...
Y como haría para obtener un String concatenando n veces el número a concatenar?, ej:

122333444455555....
* */
public class StreamsNumerosNaturales {


    public static void main(String[] args) {



    }
}
