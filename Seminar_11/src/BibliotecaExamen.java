import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BibliotecaExamen {
    private final static String dbUrl="jdbc:sqlite:db\\biblioteca.db";
    private final static String dbName="biblioteca";
    private final static String tableName="carte";
    private static Connection connection;
    private static Statement sqlStatement;
    public static void main(String[] args) {
        Carte c1 = new Carte("Cota-0001", "The Adventures of Tom Sawyer",
                "Mark Twain", 1876);
        Carte c2 = new Carte("Cota-0002", "Fratii Karamozov",
                "Teodovich Dostoevski", 1878);
        Carte c3 = new Carte("Cota-0003", "Strainul",
                "Albert Camus", 1942);
        creareConextiune();
        dropTable();
        createTable();
        inserareCarte(c1);
        inserareCarte(c2);
        inserareCarte(c3);
        selectareCarti();
//        stergereCarte(c1);
        selectareCarti();
        actualizareCarti("HuckelberryFinn");
        selectareCarti();
        inchidereConextiun();
    }

    private static void inchidereConextiun() {
        if(sqlStatement!=null){
            try {
                sqlStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void actualizareCarti(String titlu) {
        try {
            sqlStatement=connection.createStatement();
            sqlStatement.execute("update carte set titlu='"+titlu+"' where cota='Cota-0001'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void stergereCarte(Carte c2) {
        try {
            sqlStatement = connection.createStatement();
            sqlStatement.execute(" delete from " + tableName + " where cota='" + c2.getCota()+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void selectareCarti() {
        try {
            sqlStatement= connection.createStatement();
            ResultSet resultSet=sqlStatement.executeQuery("select * from "+tableName);
            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
            int colCount=resultSetMetaData.getColumnCount();
            for (int i=1;i<=colCount;i++){
                System.out.println(resultSetMetaData.getColumnName(i)+"\t");
            }
            List<Carte> lista=new ArrayList<>();
            while(resultSet.next())
            {
                String cota=resultSet.getString("cota");
                String titlu=resultSet.getString("titlu");
                String autori=resultSet.getString("autori");
                int an=resultSet.getInt("an");
                Carte carte=new Carte(cota,titlu,autori,an);
                lista.add(carte);
            }
//            for(var carte : lista){
//                System.out.println(carte);
//            }
           lista.stream().forEach(System.out::println);
//            lista.stream().sorted(Comparator.comparing(Carte::getTitlu)).forEach(System.out::println););
//            lista.stream().forEach(carte->{System.out.println(carte);});


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void inserareCarte(Carte c1) {
        try {
            sqlStatement=connection.createStatement();
            sqlStatement.execute("insert into " + tableName + " values ('" + c1.getCota() + "','"
                    +c1.getTitlu() + "','"
                    + c1.getAutori() +"',"
                    + c1.getAn()
                    + ")");
            System.out.println("Cartea a fost inserata cu succes!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createTable() {
        try {
            sqlStatement=connection.createStatement();
            sqlStatement.execute("create table " + tableName + " (cota varchar(16) Primary key, titlu varchar(64), autori varchar(64), an int)");
            System.out.println("Tabela " + tableName + " a fost creata cu succes!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void dropTable() {
        try {
            sqlStatement=connection.createStatement();
            sqlStatement.execute("drop table "+tableName);
            System.out.println("Tabela "+tableName+" a fost stearsa cu succes!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void creareConextiune() {
        try {
            connection= DriverManager.getConnection(dbUrl);
            System.out.println("Conexiune realizata cu succes!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
