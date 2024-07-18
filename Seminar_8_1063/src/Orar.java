import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;




class Profesor {
    private final int idProfesor;
    private final String prenume;
    private final String nume;
    private final String departament;

    public Profesor(int idProfesor, String prenume, String nume, String departament) {
        this.idProfesor = idProfesor;
        this.prenume = prenume;
        this.nume = nume;
        this.departament = departament;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getNume() {
        return nume;
    }

    public String getNumeComplet(){
        return getNume() + " " + getPrenume();
    }

    public String getDepartament() {
        return departament;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Profesor{");
        sb.append("idProfesor=").append(idProfesor);
        sb.append(", prenume='").append(prenume).append('\'');
        sb.append(", nume='").append(nume).append('\'');
        sb.append(", departament='").append(departament).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

class Programare {
    private final String ziua;
    private final String interval;
    private final Profesor profesor;
    private final String disciplina;
    private final String sala;
    private final boolean esteCurs;
    private final String formatie;

    public Programare(String ziua, String interval, Profesor profesor, String disciplina, String sala, boolean esteCurs, String formatie) {
        this.ziua = ziua;
        this.interval = interval;
        this.profesor = profesor;
        this.disciplina = disciplina;
        this.sala = sala;
        this.esteCurs = esteCurs;
        this.formatie = formatie;
    }

    public String getZiua() {
        return ziua;
    }

    public String getInterval() {
        return interval;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public String getSala() {
        return sala;
    }

    public boolean esteCurs() {
        return esteCurs;
    }

    public String getFormatie() {
        return formatie;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Programare{");
        sb.append("ziua='").append(ziua).append('\'');
        sb.append(", interval='").append(interval).append('\'');
        sb.append(", profesor=").append(profesor);
        sb.append(", disciplina='").append(disciplina).append('\'');
        sb.append(", sala='").append(sala).append('\'');
        sb.append(", esteCurs=").append(esteCurs);
        sb.append(", formatie='").append(formatie).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

public class Orar {
    public static void afisareOrarGrupa(String grupa, List<Programare> programari,
                                        Map<String, List<String>> componentaSerii) {
//        System.out.println(programari);
//        System.out.println(componentaSerii);

        // Determinarea seriei din care face parte grupa primita ca parametru
        // TODO

        // Tiparire antet
        System.out.printf("%-10s %-20s %-50s %-10s %-10s%n",
                "Ziua", "Interval orar", "Disciplina", "Sala", "Curs/Seminar");

        // Afisare orar grupa
        // TODO
    }

    public static void main(String[] args) throws Exception {

        // 1. Citire date
        Map<Integer, Profesor> profesori;
        List<Programare> programari;
        // TODO
//        try(var fisierProfesori  = new BufferedReader(new FileReader("./dataIN/profesori.txt")))
//        {
//            profesori = fisierProfesori.lines()
//                    .map(linie->new Profesor(Integer.parseInt(linie.split("\t")[0]),
//                            linie.split("\t")[1],
//                            linie.split("\t")[2],
//                            linie.split("\t")[3]))
//                    .collect(Collectors.toMap(Profesor::getIdProfesor, Function.identity()));
//
//        }
        /*for (var entry: profesori.entrySet()) {
            System.out.println(entry.getValue().toString());

        }*/
//        40968	CONSTANTIN	MINDRICELU	(Departament) ECONOMIA AGROALIMENTARA SI A MEDIULUI
//        40969	ELENA	MUŞEANU	(Departament) LIMBI MODERNE SI COMUNICARE IN AFACERI
        try(var fiserProfesori =new BufferedReader(new FileReader("./dataIN/profesori.txt")))
        {
            profesori=fiserProfesori.lines()
                    .map(linie -> new Profesor(Integer.parseInt(linie.split("\\t")[0]),
                            linie.split("\\t")[1],
                            linie.split("\\t")[2],
                            linie.split("\\t")[3]))
                    .collect(Collectors.toMap(Profesor::getIdProfesor,Function.identity()));
        }

        try(var fisierProgramari = new BufferedReader(new FileReader("./dataIN/programari.txt")))
        {
            programari = fisierProgramari.lines()
                            .map(linie->new Programare(linie.split("\t")[0],
                                                        linie.split("\t")[1],
                                                        profesori.get(Integer.parseInt(linie.split("\t")[2])),
                                                        linie.split("\t")[3],
                                                        linie.split("\t")[4],
                                                        Boolean.parseBoolean(linie.split("\t")[5]),
                                                        linie.split("\t")[6]))
                            .collect(Collectors.toList());

        }
//        for(var programare: programari)
//        {
//            System.out.println(programare.toString());
//        }

        // 2. Prelucrari
        // Afișare lista cursuri în ordine alfabetică
        programari.stream()
                .filter(Programare::esteCurs)
                .map(Programare::getDisciplina)
                        .distinct()
                                .sorted();
        // TODO
        /*programari.stream()
                    .filter(Programare::esteCurs)
                    .map(Programare::getDisciplina)
                    .distinct()
                    .sorted()
                    //.forEach(System.out::println);
                    .forEach(disciplina->System.out.println(disciplina));
*/
        // Afișare număr de activități pentru fiecare profesor
        // TODO
        System.out.printf("%-40s\t%4s\t%4s%n","Profesor","Curs","Sem.");
        //PENTRU DATA VIITOARE
        programari.stream()
                .collect(Collectors.groupingBy(Programare::getProfesor))
                .forEach((profesor, listaProgramari)->{
                    System.out.printf("%-40s\t%4d\t%4d%n",
                            profesor.getNumeComplet(),
                            listaProgramari.stream()
                                    .filter(Programare::esteCurs)
                                    .count(),
                            listaProgramari.stream()
                                    .filter(programare->!programare.esteCurs())
                                    .count()
                            );
                } );

        // Lista departamentelor ordonate descrescator dupa numărul de activități

        // Definire clasa Departament
        class Departament {
            String denumire;
            Long nrActivitati;

            public Departament(String denumire, long nrActivitati) {
                this.denumire = denumire;
                this.nrActivitati = nrActivitati;
            }

            @Override
            public String toString() {
                return "Departament{" +
                        "denumire='" + denumire + '\'' +
                        ", nrActivitati=" + nrActivitati +
                        '}';
            }
        }
            List<Departament> departaments=programari.stream()
                    .map(programare -> programare.getProfesor().getDepartament())
                    .distinct()
                    .map(s ->
                    {
                        Long nractiv=programari.stream()
                                .filter(programare -> programare.getProfesor().getDepartament().equals(s))
                                .count();
                        return new Departament(s,nractiv);
                    }
                   )
                    .collect(Collectors.toList());
        for (var el:departaments)
            System.out.println(el.toString());

    }
}
