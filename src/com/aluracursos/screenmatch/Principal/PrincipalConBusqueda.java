package com.aluracursos.screenmatch.Principal;

import com.aluracursos.screenmatch.modelos.Titulo;
import com.aluracursos.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {

        //Codigo de la clase
        Scanner lectura = new Scanner(System.in);
        System.out.println("Escriba el nombre de una pelicula: ");

        var busqueda =lectura.nextLine();

        String direccion = "https://www.omdbapi.com/?apikey=30402e61&t=" + busqueda;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        System.out.println(json);

        Gson gson =  new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();


        TituloOmdb miTituloOmdb = gson.fromJson(json, TituloOmdb.class);
        Titulo miTitulo = new Titulo(miTituloOmdb);

        //System.out.println("Pelicula: " +miTituloOmdb);
        System.out.println("Pelicula: " +miTitulo);

        //Fin codigo de la clase





    }
}
