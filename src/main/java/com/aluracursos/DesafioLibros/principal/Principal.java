package com.aluracursos.DesafioLibros.principal;

import com.aluracursos.DesafioLibros.model.Datos;
import com.aluracursos.DesafioLibros.service.ConsumoAPI;
import com.aluracursos.DesafioLibros.service.ConvierteDatos;

public class Principal {
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/";

    public void muestraElMenu(){
        var json = consumoAPI.obtenerDatos(URL_BASE);
        var datos = conversor.obtenerDatos(json, Datos.class);
        System.out.println(datos);
    }
}
