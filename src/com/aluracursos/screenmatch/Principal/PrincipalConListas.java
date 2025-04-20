package com.aluracursos.screenmatch.Principal;

import com.aluracursos.screenmatch.modelos.Pelicula;
import com.aluracursos.screenmatch.modelos.Serie;
import com.aluracursos.screenmatch.modelos.Titulo;

import java.util.*;

public class PrincipalConListas {

    public static void main(String[] args) {
        Pelicula miPelicula = new Pelicula("Encanto",2021);
        miPelicula.evalua(9);

        Pelicula otrapelicula = new Pelicula("Matrix",1998);
        otrapelicula.evalua(6);

        var peliculaDeBruno = new Pelicula("El señor de los anillos",2001);
        peliculaDeBruno.evalua(10);

        Serie casaDragon = new Serie("La Casa del Dragon",2022);


        List<Titulo> lista = new LinkedList<>();
        lista.add(peliculaDeBruno);
        lista.add(miPelicula);
        lista.add(otrapelicula);
        lista.add(casaDragon);

        for (Titulo item : lista){
            System.out.println(item.getNombre());
            if (item instanceof Pelicula pelicula && pelicula.getClasificacion()>2){
                System.out.println(pelicula.getClasificacion());
            }
        }

        ArrayList<String> listaDeArtistas = new ArrayList<>();

        listaDeArtistas.add("Penelope Cruz");
        listaDeArtistas.add("Antonio Banderas");
        listaDeArtistas.add("Ricardo Darin");

        System.out.println(listaDeArtistas);

        Collections.sort(listaDeArtistas);
        System.out.println("Lista de artistas ordenada " +listaDeArtistas);

        Collections.sort(lista);
        System.out.println("Lista ordenada de titulos: " + lista);

        lista.sort(Comparator.comparing(Titulo::getFechaDeLanzamiento));
        System.out.println("Lista ordenada por fecha: " +lista);
    }
}
