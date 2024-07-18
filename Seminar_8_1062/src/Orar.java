import java.io.*;
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
        String seria="";
        // TODO
        for(var entry : componentaSerii.entrySet())
        {
            if(entry.getValue().contains(grupa))
                seria=entry.getKey();
        }

        // Tiparire antet
        System.out.printf("%-10s %-20s %-50s %-10s %-10s%n",
                "Ziua", "Interval orar", "Disciplina", "Sala", "Curs/Seminar");
        String finalSeria = seria;
        programari.stream().filter(programare -> programare.getFormatie().equals(finalSeria))
                .sorted(Comparator.comparing(programare -> programare.getZiua()))
                .forEach(programare ->
                        System.out.printf("%-10s %-20s %-50s %-10s %-10s%n",
                                programare.getZiua(),
                                programare.getInterval(),
                                programare.getDisciplina(),
                                programare.getSala(),
                                programare.esteCurs()?"Curs":"Seminar"));
        // Afisare orar grupa
        // TODO
    }

    public static void main(String[] args) throws Exception {

        // 1. Citire date
        Map<Integer, Profesor> profesori;
        List<Programare> programari;
        // TODO
        try(var fisierProfesori  = new BufferedReader(new FileReader("./dataIN/profesori.txt")))
        {
            profesori = fisierProfesori.lines()
                    .map(linie->new Profesor(Integer.parseInt(linie.split("\t")[0]),
                            linie.split("\t")[1],
                            linie.split("\t")[2],
                            linie.split("\t")[3]))
                    .collect(Collectors.toMap(profesor -> (Integer) profesor.getIdProfesor(), Function.identity()));

        }
        /*for (var entry: profesori.entrySet()) {
            System.out.println(entry.getValue().toString());

        }*/

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
        class Departament{
            String denumire;
            Long nrActivitati;

            public Departament(String denumire, long nrActivitati) {
                this.denumire = denumire;
                this.nrActivitati = nrActivitati;
            }

            @Override
            public String toString() {
                return String.format("%-75s\t%4d", denumire, nrActivitati);
            }
        }

        System.out.printf("%-75s\t%s%n","Departament","Nr Activitati");
        programari.stream()
                .map(programare -> programare.getProfesor().getDepartament())
                .distinct()
                .map(denumire->{
                    var nrActivitati = programari.stream()
                            .filter(programare->programare.getProfesor().getDepartament().equals(denumire))
                            .count();
                    return new Departament(denumire,nrActivitati);
                })
                .forEach(departament -> System.out.println(departament.toString()));


//        C: 1045,1046,1047,1048,1049
//        D: 1050,1051,1052,1053,1054
//        E: 1055,1056,1057,1058

//        Map<String,List<String>> serieC = new HashMap<>();
        List<String> grupeC = new ArrayList<>();
        Collections.addAll(grupeC,"1045","1046","1047","1048","1049");
//        serieC.put("C",grupeC);


        List<String> grupeD = Arrays.asList("1050","1051","1052","1053","1054");
        List<String> grupeE = Arrays.asList("1055","1056","1057","1058");

        Map<String,List<String>> serii = Map.ofEntries(new AbstractMap.SimpleEntry<>("C",grupeC),
                new AbstractMap.SimpleEntry<>("D",grupeD),
                new AbstractMap.SimpleEntry<>("E",grupeE));

        for (var entry: serii.entrySet()) {
            System.out.print(entry.getKey()+ "\t");
            System.out.println(entry.getValue());
        }
        afisareOrarGrupa("1045",programari,serii);
    }


}
