package Prueba1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

   static Pelicula p = null;
   static Pelicula [] array_estaico;
   static ArrayList<Pelicula> lista_peliculas = new ArrayList<>();

    public static void main(String[] args) throws SQLException {
        Peliculas peliculas = new Peliculas();
        Pelicula [] lista_estatica;
        Pelicula p =null;
        int opcion = 0;
        do {
        System.out.println("Elige una opcion");
        opcion = sc.nextInt();

            switch (opcion) {

                case 1 :
                    peliculas.crearTabla();break;

                case 2: peliculas.eliminarTabla();break;

                case 3:
                    System.out.println("ID");
                    int id = sc.nextInt();
                    System.out.println("TITULO");
                    String titulo = sc.next();
                    System.out.println("GENERO");
                    String genero = sc.next().toUpperCase();
                    System.out.println("ESTRENO");
                    int estreno = sc.nextInt();
                    p = new Pelicula(id, titulo, genero, estreno);
                    peliculas.crearPelicula(p);break;


                case 4:
                    System.out.println("ID A ELIMINAR");
                    id = sc.nextInt();
                    peliculas.eliminarPelicula(id);break;


                case 5:
                    System.out.println("ID A BUSCAR");
                    id=sc.nextInt();
                    System.out.println(peliculas.buscarPelicula(id));break;

                case 6:
                    lista_peliculas = peliculas.buscarTodo();
                    for (Pelicula e: lista_peliculas){
                        System.out.println(e);
                    };break;



                case 7 :
                    System.out.println("DIME UN GENERO");
                    genero = sc.next().toUpperCase();
                    lista_peliculas = peliculas.buscarPorGeneroOrdenarEstreno(genero);
                    for (Pelicula e : lista_peliculas){
                        System.out.println(e);
                    };break;

                case 8:
                    peliculas.mostrar_bd();break;

                case 9:
                    lista_estatica = peliculas.almecenar_array_estatico();
                    for (int i = 0;i<lista_estatica.length;i++){
                        System.out.println(lista_estatica[i]);
                    };break;



                case 10:
                    peliculas.mostrar_tablas();break;

                case 11:
                    String version = peliculas.version_sgbd();
                    System.out.println(version);

                case 12:
                    peliculas.insertar_array_estatico_en_bd();

            }
        } while (opcion != 0);


    }
}
