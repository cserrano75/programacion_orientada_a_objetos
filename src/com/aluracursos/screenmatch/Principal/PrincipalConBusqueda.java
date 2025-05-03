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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {

        //Codigo de la clase
        Scanner lectura = new Scanner(System.in);
        List<Titulo> titulos = new ArrayList<>();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);
        gsonBuilder.setPrettyPrinting();
        Gson gson =  gsonBuilder.create();

        while(true){
            System.out.println("Escriba el nombre de una pelicula: ");

            var busqueda =lectura.nextLine();

            if (busqueda.equalsIgnoreCase("salir")){
                break;
            }
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

                TituloOmdb miTituloOmdb = gson.fromJson(json, TituloOmdb.class);


                Titulo miTitulo = new Titulo(miTituloOmdb);
                System.out.println("Titulo ya convertido: " +miTitulo);

                titulos.add(miTitulo);

            }catch (NumberFormatException e){
                System.out.println("Ocurrio un error");
                System.out.println(e.getMessage());
            }catch (IllegalArgumentException e){
                System.out.println("Error en la direccion");
            }catch (ErrorDeDuracionDeConversionException e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println(titulos);

        FileWriter escritura = new FileWriter("titulos.json");
        escritura.write(gson.toJson(titulos));
        escritura.close();

        try {
            FileWriter writer = new FileWriter("archivo");

            writer.write("Hello");
            writer.close();
            writer.write(" World!");
            writer.close();
        } catch (Exception e) {
            System.out.println("Error!");
        }


        System.out.println("Finalizo la ejecucion del programa!!");
        //System.out.println("Pelicula: " +miTituloOmdb);
        //Fin codigo de la clase

    }
}
