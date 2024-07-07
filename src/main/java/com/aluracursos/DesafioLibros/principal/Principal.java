package com.aluracursos.DesafioLibros.principal;

import com.aluracursos.DesafioLibros.model.Datos;
import com.aluracursos.DesafioLibros.model.DatosLibros;
import com.aluracursos.DesafioLibros.service.ConsumoAPI;
import com.aluracursos.DesafioLibros.service.ConvierteDatos;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/";
    private Scanner teclado = new Scanner(System.in);

    public void muestraElMenu(){
        var json = consumoAPI.obtenerDatos(URL_BASE);
        var datos = conversor.obtenerDatos(json, Datos.class);

        // Top 10 libros mas descargados
        System.out.println("Top 10 libros mas descargados");
        datos.resultados().stream()
                .sorted(Comparator.comparing(DatosLibros::numeroDeDescargas).reversed())
                .limit(10)
                .map(l -> l.titulo().toUpperCase())
                .forEach(System.out::println);

        // Busqueda de libros por nombre
        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        var tituloLibro = teclado.nextLine();
        json = consumoAPI.obtenerDatos(URL_BASE+"?search="+tituloLibro.replace(" ","+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst()
                .ifPresentOrElse(
                        l -> System.out.println("Libro encontrado: \n"+l.titulo()),
                        () -> System.out.println("Libro no encontrado")
                );

        // Trabajando con estadisticas
        DoubleSummaryStatistics est = datos.resultados().stream()
                .filter(d -> d.numeroDeDescargas() > 0)
                .collect(Collectors.summarizingDouble(DatosLibros::numeroDeDescargas));

        System.out.println("El total de descargas es: "+est.getSum());
        System.out.println("El promedio de descargas es: "+est.getAverage());
        System.out.println("El maximo de descargas es: "+est.getMax());
        System.out.println("El minimo de descargas es: "+est.getMin());
        System.out.println("La cantidad de registros evaluados para calcular las estadisticas es: "+est.getCount());
    }
}
