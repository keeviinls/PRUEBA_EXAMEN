public class aaaa {
}
/*
* package Prueba1;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Peliculas implements PeliculasInterface{

    static Scanner sc = new Scanner(System.in);

    public static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebaexamen",
                    "root", "Noteloesperas1");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void insertar_array_estatico_en_bd() throws SQLException{
        Pelicula [] lista_estatico;
        Pelicula p = null;

        System.out.println("CANTIDAD PELICULAS QUIERES METER");
        int dimesion = sc.nextInt();
        lista_estatico = new Pelicula[dimesion];

        // RELLENAR ARRAY

        for (int i = 0;i<lista_estatico.length;i++){
            System.out.println("ID de la pelicula "+i);
            int id = sc.nextInt();

            System.out.println("TITULO de la pelicula "+i);
            String titulo = sc.next();

            System.out.println("GENERO de la pelicula "+i);
            String genero = sc.next().toUpperCase();

            System.out.println("ESTRENO de la pelicula "+i);
            int estreno = sc.nextInt();

            p = new Pelicula(id, titulo, genero, estreno);
            lista_estatico[i] = p;
        }

        // INSERTAR REGISTOS EN BD
        for (int i = 0;i< lista_estatico.length;i++){
            PreparedStatement ps = conn.prepareStatement("insert into pelicula values (?,?,?,?);");
            ps.setInt(1, lista_estatico[i].getId());
            ps.setString(2, lista_estatico[i].getTitulo());
            ps.setString(3, lista_estatico[i].getGenero().toString());
            ps.setInt(4, lista_estatico[i].getEstreno());

            ps.executeUpdate();
        }


    }

    public String version_sgbd() throws SQLException{
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        return databaseMetaData.getDatabaseProductVersion();

    }

    public void mostrar_tablas () throws SQLException{
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet rs = databaseMetaData.getTables("pruebaexamen", "pruebaexamen", null, null);
        while (rs.next()){
            System.out.println(rs.getString(3));
        }

        System.out.println("MI URL ES "+databaseMetaData.getURL());
    }

    public  Pelicula[] almecenar_array_estatico () throws SQLException{
        Pelicula [] array_estatico_peliculas;
        Pelicula p = null;

        // CONTAMOS LA CANTIDAD DE PELICUALS QUE HAY PARA LA DIMENSION
        PreparedStatement ps = conn.prepareStatement("select count(*) from pelicula");
        ResultSet rs = ps.executeQuery();
        int dimesion = 0;
        while (rs.next()){
            dimesion = rs.getInt(1);
        }
        array_estatico_peliculas = new Pelicula [dimesion];

        //RECORREMOS TODOS E INSERTAMOS EN EL ARRAY

        ps = conn.prepareStatement("select * from pelicula");
        rs=ps.executeQuery();
        int i = 0;
        while (rs.next()){
            p = new Pelicula(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            array_estatico_peliculas [i] = p;
            i++;
        }

        return array_estatico_peliculas;

    }

    public void mostrar_bd () throws SQLException{
       DatabaseMetaData databaseMetaData = conn.getMetaData();
       ResultSet rs = databaseMetaData.getCatalogs();
       while (rs.next()){
           System.out.println(rs.getString(1));
       }
    }

    public ArrayList<Pelicula> buscarPorGeneroOrdenarEstreno (String genero) throws SQLException{
        Pelicula p = null;
        ArrayList<Pelicula> lista = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("select * from pelicula where genero = ?");
        ps.setString(1, genero);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            p = new Pelicula(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));

            lista.add(p);
        }

        lista.sort(Comparator.comparing(Pelicula :: getEstreno).reversed());


        return lista;
    }

    public ArrayList<Pelicula> buscarTodo() throws SQLException{
        Pelicula p = null;
        ArrayList<Pelicula> lista = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("select * from pelicula");
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            p = new Pelicula(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));

            lista.add(p);
        }

        return lista;
    }

    public Pelicula buscarPelicula (int id) throws SQLException{
        Pelicula p = null;
        PreparedStatement ps = conn.prepareStatement("select * from pelicula where id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            p = new Pelicula(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
        }



        return p;
    }

    public void eliminarPelicula(int id) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("delete from pelicula where id = ?;");
        ps.setInt(1,id);
        ps.executeUpdate();
        System.out.println("PELICULA ELIMINADA");


    }

    public void crearPelicula (Pelicula p) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("insert into pelicula values (?,?,?,?)");
        ps.setInt(1, p.getId());
        ps.setString(2, p.getTitulo());
        ps.setString(3, p.getGenero().toString());
        ps.setInt(4, p.getEstreno());

        ps.executeUpdate();
        System.out.println("PELICULA CREADA CORRECTAMENTE");



    }

    public void eliminarTabla() throws SQLException{
        Statement st = conn.createStatement();
        st.executeUpdate("drop table pelicula");
        System.out.println("TABLA ELIMINADA CORRECTAMENTE");

    }
    public void crearTabla () throws SQLException{
        Statement st = conn.createStatement();
        st.executeUpdate("create table pelicula (" +
                "id int primary key not null," +
                "titulo VARCHAR(100)," +
                "genero VARCHAR(50)," +
                "estreno int not null);");
        System.out.println("TABLA CREADA CORRECTAMENTE");

    }


 /*   public void crear_bd() throws SQLException{
       Statement st = conn.createStatement();
       st.executeUpdate("create database pruebaexamen");
        System.out.println("BD CREADA CORRECTAMENTE");

    }*/
