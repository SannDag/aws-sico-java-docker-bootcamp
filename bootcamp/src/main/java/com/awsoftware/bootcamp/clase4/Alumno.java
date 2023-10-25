package com.awsoftware.bootcamp.clase4;

import lombok.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Streams & Lambdas:

Defina la clase Alumno con los siguientes campos:
int id
string dni
string nombre
string apellido
string curso
double nota
int edad

Cree un Lista y cargue un numero considerable de alumnos para que
el ejercicio sea viable (15 o mas) con valores a azar.


a) Muestre todos los alumnos de la lista
b) Muestre todos los alumnos ordenados de menor a mayor por edad
c) Muestre aquellos alumnos cuyo nombre empieza con un caracter
dado (elegir el caracter en base a la lista de nombre que se utilizo)
d) Sume la edad de todos los alumnos
e) Obtenga un mapa donde la clave sea la nota y el valor sea una lista de alumnos que tienen esa nota

*
* */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Alumno {

    private int id;
    private String dni;
    private String nombre;
    private String apellido;
    private String curso;
    private double nota;
    private int edad;

    @Override
    public String toString() {
        return "Alumno: " +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", curso='" + curso + '\'' +
                ", nota=" + nota +
                ", edad=" + edad;
    }

    static String dniRandom(){

        Random r = new Random();
        return r.ints(8,1, 9)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining());
    }

    static String nombreRandom(){
        List<String> nombres = List.of("Alejo", "Damian", "María", "Gonzalo", "Romina", "Gladys",
                                        "Norberto", "Claudia", "Mariana", "Josefina", "Raúl");

        Random r = new Random();

        int iRandom = r.nextInt(nombres.size());
        String nRandom = nombres.get(iRandom);
        return nRandom;
    }

    static String apellidoRandom(){
        List<String> apellidos = List.of("Rodriguez", "Casanova", "Zurich", "Longman",
                "Lopez", "Zapata", "Jakim", "Bussola", "Ragazzi", "Gimenez", "Jameson");

        Random r = new Random();

        int iRandom = r.nextInt(apellidos.size());
        String nRandom = apellidos.get(iRandom);
        return nRandom;
    }

    static String cursoRandom(){
        List<String> cursos = List.of("Java Básico", "Java Avanzado", "Javascript", "Spring Boot",
                "Spring Cloud", "Patrones de diseño", "Kotlin", "C#", "Ciberseguridad", "Docker", "Spring Security");

        Random r = new Random();

        int iRandom = r.nextInt(cursos.size());
        String nRandom = cursos.get(iRandom);
        return nRandom;
    }

    public static void main(String[] args) {

        Random r = new Random();

        List<Alumno> listaAlumnos = Stream.generate(() -> Alumno.builder()
                        .id(r.nextInt(999))
                        .dni(dniRandom())
                        .nombre(nombreRandom())
                        .apellido(apellidoRandom())
                        .curso(cursoRandom())
                        .nota(Math.round(r.doubles(1, 10.0)
                                .findFirst()
                                .getAsDouble() * 100.0) / 100.0)
                        .edad(r.nextInt(18,80))
                        .build())
                .limit(15)
                .collect(Collectors.toList());

        System.out.println("***Lista alumnos***");
        listaAlumnos.forEach(System.out::println);
        System.out.println("************************************************\n");

        System.out.println("***Lista alumnos ordenados de menor a mayor por edad***");
        listaAlumnos.stream()
                .sorted(Comparator.comparing(Alumno::getEdad))
                .forEach(a -> System.out.println(a));
        System.out.println("************************************************\n");

        System.out.println("***Lista alumnos que empiezan con el caracter 'C'***");
        listaAlumnos.stream()
                .filter(a -> a.getNombre().startsWith("C"))
                .forEach(System.out::println);
        System.out.println("************************************************\n");

        System.out.println("***Suma total de la edad de los alumnos***");
        int sumaEdadades = listaAlumnos.stream()
                .mapToInt(Alumno::getEdad)
                .sum();
        System.out.println("Resultado: " + sumaEdadades);
        System.out.println("************************************************\n");

        System.out.println("***Mapa donde la clave sea la nota y el valor sea una lista de alumnos que tienen esa nota***");
        Map<Double, List<Alumno>> mapa = listaAlumnos.stream()
                .collect(Collectors.groupingBy(Alumno::getNota));

        mapa.forEach((nota, alumnos) -> {
            System.out.println("\n");
            System.out.println("Nota: " + nota);
            alumnos.forEach(System.out::println);
        });
        System.out.println("************************************************\n");

    }

}


