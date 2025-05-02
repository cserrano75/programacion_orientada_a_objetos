package com.aluracursos.screenmatch.Principal;

import com.aluracursos.screenmatch.excepcion.ErrorDeDuracionDeConversionException;
import com.aluracursos.screenmatch.modelos.Titulo;
import com.aluracursos.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
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

        String direccion = "https://www.omdbapi.com/?apikey=30402e61&t="+busqueda.replace(" ", "+");

        try{

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
                System.out.println("Titulo ya convertido: " +miTitulo);

            FileWriter escritura = new FileWriter("pelicula.text");
            escritura.write(miTitulo.toString());
            escritura.close();

        }catch (NumberFormatException e){
            System.out.println("Ocurrio un error");
            System.out.println(e.getMessage());
        }catch (IllegalArgumentException e){
            System.out.println("Error en la direccion");
        }catch (ErrorDeDuracionDeConversionException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Finalizo la ejecucion del programa!!");
        //System.out.println("Pelicula: " +miTituloOmdb);
        //Fin codigo de la clase

    }
}
