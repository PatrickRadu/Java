// loc de descarcat jdbc-sqlite. jar
// https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.30.1/
// https://dbeaver.io/


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Biblioteca {
    private static final String dbURL = "jdbc:sqlite:db\\biblioteca.db";
    // serverul de bazse de date va rula in aceeasi masina virtuala Java cu apicatia noastra
    private static final String dbName = "biblioteca";
    private static final String tableName = "carte";

    private static Connection conn;
    private static Statement sqlStatement;

    public static void main(String[] args) {
        Carte c1 = new Carte("Cota-0001", "The Adventures of Tom Sawyer",
                "Mark Twain", 1876);
        Carte c2 = new Carte("Cota-0002", "Fratii Karamozov",
                "Teodovich Dostoevski", 1878);
        Carte c3 = new Carte("Cota-0003", "Strainul",
                "Albert Camus", 1942);

        creareConexiune();
        dropTableCarte();
        creareTabelaCarte();

        inserareCarte(c1);
        inserareCarte(c2);
        inserareCarte(c3);

        selectareCarti();
        stergereCarte(c2);
        selectareCarti();
        actualizareCarte(c3);
        selectareCarti();

        inchidereConexiune();
    }

    private static void creareConexiune() {
        try {
            conn = DriverManager.getConnection(dbURL);
            System.out.println("Conexiune realizata cu succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void dropTableCarte() {
        try {
            sqlStatement = conn.createStatement();
            sqlStatement.execute("drop table " + tableName);
            System.out.println("Tabela " + tableName + " a fost stearsa cu succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void creareTabelaCarte() {
        try {
            sqlStatement = conn.createStatement();
            sqlStatement.execute("create table " + tableName +
                    " (cota varchar(16) primary key, titlu varchar(64), autori varchar(64), an int)");
            System.out.println("Tabela " + tableName + " a fost creata cu succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void inserareCarte(Carte carte) {
        try {
            sqlStatement = conn.createStatement();
            sqlStatement.execute("insert into " + tableName +
                    " values('" + carte.getCota() + "', '" +
                    carte.getTitlu() + "', '" +
                    carte.getAutori() + "', " +
                    carte.getAn() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void stergereCarte(Carte carte) {
        try {
            sqlStatement = conn.createStatement();
            int nrLinii = sqlStatement.executeUpdate("delete from " + tableName +
                    " where an = " + carte.getAn());
            System.out.println("Au fost sterse " + nrLinii + " linii din tabela " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void actualizareCarte(Carte carte) {
        try {
            sqlStatement = conn.createStatement();
            int nrLinii = sqlStatement.executeUpdate("update " + tableName +
                    " set cota = 'Cota-schimbata' where cota = '" + carte.getCota() + "'");
            System.out.println("Au fost actualizate " + nrLinii + " linii din tabela " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void selectareCarti() {
        try {
            sqlStatement = conn.createStatement();
            ResultSet rezultate = sqlStatement.executeQuery("select * from " +
                    tableName + " where cota like '%Cota%'");

            // Tiparire la consola a header-ului tabelei carte
            ResultSetMetaData metaDate = rezultate.getMetaData();
            int nrColoane = metaDate.getColumnCount();
            for (int i=1; i<=nrColoane; i++) {
                System.out.printf("%-20s", metaDate.getColumnName(i));
            }
            System.out.println();

            // Adaugare articole din tabela intr-o lista de carti
            List<Carte> listaCarti = new ArrayList<>();

            // Parcurgem ResultSet-ul rezultate
            while(rezultate.next()) {
                Carte carte = new Carte();

                carte.setCota(rezultate.getString(1));
                carte.setTitlu(rezultate.getString("titlu"));
                carte.setAutori(rezultate.getString(3));
                carte.setAn(rezultate.getInt("an"));

                listaCarti.add(carte);
            }

            listaCarti.stream()
                    .forEach(carte -> System.out.println(carte.toString()));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void inchidereConexiune() {
        try {
            if (sqlStatement != null)
                sqlStatement.close();

            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
