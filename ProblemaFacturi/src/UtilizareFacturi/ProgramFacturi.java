package UtilizareFacturi;

import facturi.Factura;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class ProgramFacturi {
//    Să se definească o funcție salvareFacturi pentru salvarea unei liste de facturi într-un fișier binar.
//    Fișierul va fi compus din înregistrări de forma:
//
//    denumire client - string
//    an / luna / zi - întregi
//    număr linii - întreg
//            (produs - string, preț - double, cantitate - întreg) x număr linii
//    EOF

    public static void salvareFacturi(String caleFisier,List<Factura> facturi) throws IOException
    {
        try (var file=new DataOutputStream(new FileOutputStream(caleFisier)))
        {
            for (var el : facturi)
            {
                file.writeUTF(el.getDenumireClient());
                file.writeInt(el.getDatEmitere().getYear());
                file.writeInt(el.getDatEmitere().getMonthValue());
                file.writeInt(el.getDatEmitere().getDayOfMonth());
                file.writeInt(el.getLinii().size());
                for(var elem:el.getLinii())
                {
                    file.writeUTF(elem.getProdus());
                    file.writeDouble(elem.getPret());
                    file.writeInt(elem.getCantitate());
                }
            }
            file.close();
        }
    }
//    public static void salvareFacturi(String caleFisier,List<Factura> facturi) throws IOException {
//        try(var file=new DataOutputStream(new FileOutputStream(new File(caleFisier))))
//        {
//            for(var el:facturi)
//            {
//                file.writeUTF(el.getDenumireClient());
//                file.writeInt(el.getDatEmitere().getYear());
//                file.writeInt(el.getDatEmitere().getMonthValue()+1);
//                file.writeInt(el.getDatEmitere().getDayOfMonth());
//                file.writeInt(el.getLinii().size());
//                for(var l:el.getLinii())
//                {
//                    file.writeUTF(l.getProdus());
//                    file.writeDouble(l.getPret());
//                    file.writeInt(l.getCantitate());
//                }
//            }
//            file.writeUTF("EOF");
//        }
//    }
        public static List<Factura> generareListaFacturi(LocalDate data,int n)
    {
        List<Factura> facturi=new ArrayList<>();
        for (int i=0;i<n;i++)
        {
            List<Factura.Linie> linii=new ArrayList<>();
            for (int j=0;j<3;j++)
            {
                Factura.Linie linie=new Factura.Linie("Produs "+j,10+j,j);
                linii.add(linie);
            }
            Factura factura=new Factura("Client "+i,data.plusDays(i),linii);
            facturi.add(factura);
        }
        return facturi;
    }
//    Să se scrie o funcție incarcareFacturi care să citească o listă de facturi dintr-un fișier binar în formatul de mai sus.
//public static List<Factura> incarcareFacturi(String caleFisier) throws IOException {
//    List<Factura> facturi = new ArrayList<>();
//
//    try(var filefacturi=new DataInputStream(new FileInputStream(new File(caleFisier)))) {
//        while (filefacturi.available()>0 )
//        {
//            try {
//
//                String denumireFactura = filefacturi.readUTF();
//                int an = filefacturi.readInt();
//                int luna = filefacturi.readInt();
//                int ziua = filefacturi.readInt();
//                LocalDate localDate = LocalDate.of(an, luna, ziua);
//                int numarlinii = filefacturi.readInt();
//                List<Factura.Linie> linii = new ArrayList<>();
//                for (int i = 0; i < numarlinii; i++) {
//                    String denumire = filefacturi.readUTF();
//                    Double pret = filefacturi.readDouble();
//                    int cantitate = filefacturi.readInt();
//                    Factura.Linie linie = new Factura.Linie(denumire, pret, cantitate);
//                    linii.add(linie);
//                }
//                Factura factura = new Factura(denumireFactura, localDate, linii);
//                facturi.add(factura);
//            } catch (EOFException eof){
//                break;
//            }
//        }
//    }
//    return facturi;
//}
    public static List<Factura> incarcareFacturi(String caleFisier) throws IOException
    {
        List<Factura> facturi=new ArrayList<>();
        try(var file=new DataInputStream(new FileInputStream(caleFisier)))
        {
            while(file.available()!=0)
            {
                String nume=file.readUTF();
                int an=file.readInt();
                int luna=file.readInt();
                int zi=file.readInt();
                LocalDate data=LocalDate.of(an,luna,zi);
                int numarlinii=file.readInt();
                List<Factura.Linie> linii=new ArrayList<>();
                for(int i=0;i<numarlinii;i++)
                {
                    String produs=file.readUTF();
                    double pret=file.readDouble();
                    int cantitate=file.readInt();
                    Factura.Linie linie=new Factura.Linie(produs,pret,cantitate);
                    linii.add(linie);
                }
                Factura factura=new Factura(nume,data,linii);
                facturi.add(factura);
            }
        }
        return facturi;
    }
        public static void main(String[] args) throws IOException {
//            1) Să se definească o funcție generareListaFacturi care să primească dată minimă și un număr n de facturi și care:
////         să genereze o lista de n obiecte factură cu 1-10 linii
//////    datele (clienți, produse, preturi) din liste fixe
            List<Factura> facturi = generareListaFacturi(LocalDate.now(), 5);
//            for (var el : facturi)
//                System.out.println(el.toString());
            salvareFacturi("salvare.bin", facturi);
            try {
                facturi = incarcareFacturi("ColectieFacturi.dat");
                for (Factura f : facturi) {
                    System.out.println(f);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
//            Map<String, Long> numar = facturi.stream()
//                    .collect(Collectors.groupingBy(Factura::getDenumireClient, Collectors.counting()));
//
////            Map<String, Double> total = facturi.stream().collect(Collectors.groupingBy(Factura::getDenumireClient,
////                    Collectors.summingDouble(value ->
////                            value.getLinii().stream()
////                                    .mapToDouble(value1 -> value1.getCantitate()*value1.getPret()).sum())));
//            Map<String,Double> total=facturi.stream()
//                    .collect(Collectors.toMap(
//                            Factura::getDenumireClient,
//                            factura -> factura.getLinii()
//                                    .stream()
//                                    .map(linie -> linie.getCantitate()* linie.getPret())
//                                    .reduce(0.0,Double::sum),
//                            (aDouble, aDouble2) -> aDouble+aDouble2)
//                    );
//            for (var el : numar.entrySet()) {
//                System.out.println(el.getKey() + "---" + el.getValue()+"----"+total.get(el.getKey()));
//            }
//            var Total= facturi.stream().map(factura -> factura.getLinii()
//                    .stream()
//                    .map(linie -> linie.getCantitate()* linie.getPret()).reduce(0.0,Double::sum)).reduce(0.0,Double::sum);
                Map<String,Long> numarfacturi=
                        facturi.stream()
                                .collect(Collectors.groupingBy(Factura::getDenumireClient,
                                        Collectors.counting()));
            var Total=facturi.stream().collect(
                    Collectors.groupingBy(Factura::getDenumireClient,
                            Collectors.summingDouble(value ->
                                    value.getLinii().stream()
                                            .mapToDouble(value1 -> value1.getCantitate()*value1.getPret()).sum()))

            );
            var total=facturi.stream()
                    .collect(Collectors.toMap(Factura::getDenumireClient,
                            factura -> factura.getLinii()
                                    .stream()
                                    .map(linie -> linie.getCantitate()*linie.getPret())
                                    .reduce(0.0,Double::sum),(o, o2) -> o+o2));
            for (var el : numarfacturi.entrySet()) {
                System.out.println(el.getKey() + "---" + el.getValue()+"----"+total.get(el.getKey()));
            }
////4) Să se scrie o funcție generareRaport care să primească o listă de facturi și o denumire de fișier și
////    să genereze un raport text de forma:
////
////    SC DOMINO COSTI SRL                        5 facturi, TOTAL:  2184.23 RON
////    EURIAL BROKER DE ASIGURARE SRL             5 facturi, TOTAL:  1662.47 RON
////    ALEXANDER SRL                              4 facturi, TOTAL:  1540.13 RON
////    METAL INOX IMPORT EXPOSRT SRL              4 facturi, TOTAL:  1441.17 RON
////    INTERFLOOR SYSTEM SRL                      4 facturi, TOTAL:  1331.01 RON
////    SIBLANY SRL                                3 facturi, TOTAL:  1274.65 RON
////    ALCOR CONSTRUCT SRL                        2 facturi, TOTAL:   874.66 RON
////    MERCURY  IMPEX  2000  SRL                  2 facturi, TOTAL:   750.09 RON
////    SC TRANSCRIPT SRL                          1 facturi, TOTAL:   129.16 RON
////    Unde:
////
////    există o linie pentru fiecare client care conține denumirea, numărul de facturi și valoarea totală a acestora
////    lista de clienți este sortată descrescător în funcție de valoarea totală
// TODO
        }
}
