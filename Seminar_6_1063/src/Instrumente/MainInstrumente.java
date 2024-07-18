package Instrumente;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class MainInstrumente {
    private static void salvareaPortofoliu(String cale,PortofoliuGenerics<Instrument> portofoliu) throws IOException
    {
        //verificam daca exista folder-ul
//        if(new File(cale).getParentFile()!=null)
//        {
//            new File(cale).getParentFile().mkdirs();
//        }
//        try(var file=new PrintWriter(new BufferedWriter(new FileWriter(cale))))
//        {
//            for (var entry : portofoliu.getPortfoliu().entrySet()) { //valoare in portofoliu //furnizeaza lista cheie-valoare
//                file.write(entry.getValue().toString());
//            }
//        }
        try(var file=new PrintWriter(new BufferedWriter(new FileWriter(cale))))
        {
            for(var entry:portofoliu.getPortfoliu().entrySet())
                file.write(portofoliu.getPortfoliu().toString());
        }
    }
    static PortofoliuGenerics<Instrument> incarcarePortofoliu(String caleFisier) throws IOException
    {
        PortofoliuGenerics<Instrument> portofoliu=new PortofoliuGenerics<>();
//        BRD,561.0
//        CUMPARARE,2023,11,15,60.5,20,CUMPARARE,2023,11,16,59.3,30,VANZARE,2024,1,15,60.7,40,
        try (var fileScanner=new Scanner(new BufferedReader(new FileReader(caleFisier))))
        {
            while (fileScanner.hasNext())
            {
                String linie=fileScanner.nextLine();
                Scanner linieScanner=new Scanner(linie);
                linieScanner.useDelimiter(",");
                String symbol=linieScanner.next();
                String valoare=linieScanner.next();
                double dividend=-1;
                if(linieScanner.hasNext())
                {
                    dividend=Double.parseDouble(linieScanner.next());
                }
                linie=fileScanner.nextLine();
                linieScanner=new Scanner(linie);
                linieScanner.useDelimiter("[\\,]+");
                List<Instrument.Operatiune> list=new ArrayList<>();
                while(linieScanner.hasNext())
                {
                    TipOperatiune tip= TipOperatiune.valueOf(linieScanner.next());
                    int an=linieScanner.nextInt();
                    int luna=linieScanner.nextInt();
                    int zi=linieScanner.nextInt();
                    LocalDate localDate=LocalDate.of(an,luna,zi);
                    double pret=Double.parseDouble(linieScanner.next());
//                    double pret=linieScanner.nextDouble();
                    int cantitate=linieScanner.nextInt();
                    Instrument.Operatiune operatiune=new Instrument.Operatiune(tip,localDate,pret,cantitate);
                    list.add(operatiune);
                }
                if(dividend==-1)
                {
                    Instrument instrument=new Instrument(symbol,list);
                    portofoliu.adaugaInstrument(symbol,instrument);
                }
                else
                {
                    Actiune actiune=new Actiune(symbol,list,dividend);
                    portofoliu.adaugaInstrument(symbol,actiune);
                }
            }
        }
//        try(var fileScanner=new Scanner(new BufferedReader(new FileReader(caleFisier))))
//        {
//            while(fileScanner.hasNext())
//            {
//                String line=fileScanner.nextLine();
//                Scanner linieScanner=new Scanner(line);
//                linieScanner.useDelimiter("[\\,]+");
//                String symbol=linieScanner.next();
////                double valoare=linieScanner.nextDouble();
//                double valoare1=Double.valueOf(linieScanner.next());
//                double dividend=-1;
//                if(linieScanner.hasNext())
//                {
//                    dividend=Double.valueOf(linieScanner.next());
//                }
//                line=fileScanner.nextLine();
//                linieScanner=new Scanner(line);
//                linieScanner.useDelimiter("[\\,]+");
//                List<Instrument.Operatiune> operatiuni=new ArrayList<>();
//                while(linieScanner.hasNext())
//                {
//                    TipOperatiune tip=TipOperatiune.valueOf(linieScanner.next());
//                    LocalDate data=LocalDate.of(linieScanner.nextInt(),linieScanner.nextInt(),linieScanner.nextInt());
////                    double pret=linieScanner.nextDouble();
//                    double pret=Double.valueOf(linieScanner.next());
//                    int cantitate=linieScanner.nextInt();
//                    Instrument.Operatiune operatiune=new Instrument.Operatiune(tip,data,pret,cantitate);
//                    operatiuni.add(operatiune);
//                }
//                if(dividend==-1)
//                {
//                    //Avem un Instrument
//                    Instrument instrument=new Instrument(symbol,operatiuni);
//                    portofoliu.adaugaInstrument(symbol,instrument);
//                }
//                else
//                {
//                    //avem o Actiune
//                    Actiune actiune=new Actiune(symbol,operatiuni,dividend);
//                    portofoliu.adaugaInstrument(symbol,actiune);
//                }
//            }
//        }
        return portofoliu;
    }
    public static void main(String[] args) {
        Instrument instrument1 = new Instrument();
        instrument1.setSymbol("BRD");
        instrument1.adaugaOperatiune(new Instrument.Operatiune(TipOperatiune.CUMPARARE,
                LocalDate.of(2023, 11, 15),
                60.5, 20));
        instrument1.adaugaOperatiune(new Instrument.Operatiune(TipOperatiune.CUMPARARE,
                LocalDate.of(2023, 11, 16),
                59.3, 30));
        instrument1.adaugaOperatiune(new Instrument.Operatiune(TipOperatiune.VANZARE,
                LocalDate.of(2024, 1, 15),
                60.7, 40));
//        System.out.println(instrument1.toString());

        List<Instrument.Operatiune> lista = new ArrayList<>();
        Instrument.Operatiune operatiune1 = new Instrument.Operatiune(TipOperatiune.CUMPARARE,
                LocalDate.of(2022, 10, 5),
                12.5, 100);
        Instrument.Operatiune operatiune2 = new Instrument.Operatiune(TipOperatiune.CUMPARARE,
                LocalDate.of(2022, 12, 12),
                11.7, 200);
        Instrument.Operatiune operatiune3 = new Instrument.Operatiune(TipOperatiune.VANZARE,
                LocalDate.of(2023, 2, 19),
                13.1, 150);
        Collections.addAll(lista, operatiune1, operatiune2, operatiune3);
        Actiune actiune1 = new Actiune("TLV", lista, 3.5);
//        System.out.println(actiune1.toString());

        PortofoliuGenerics<Instrument> portofoliu = new PortofoliuGenerics<>();
        portofoliu.adaugaInstrument(instrument1.getSymbol(), instrument1);
        portofoliu.adaugaInstrument(actiune1.getSymbol(), actiune1);
        var value = 0.0f;
//        for (var entry : portofoliu.getPortfoliu().entrySet()) { //valoare in portofoliu //furnizeaza lista cheie-valoare
//            System.out.println(entry.getValue().toString());
//            value+=entry.getValue().valoare();
//            LocalDate firstDate=Collections.min(entry.getValue().getOperatiuni(),
//                    new Comparator<Instrument.Operatiune>() {
//                @Override
//                public int compare(Instrument.Operatiune o1, Instrument.Operatiune o2) {
//                    return o1.getData().compareTo(o2.getData());
//                }
//            }).getData();
//            System.out.println("Data primei operatiuni: "+firstDate);
//        }
        for (var entry : portofoliu.getPortfoliu().entrySet()) {
            LocalDate firstDate = Collections.min(entry.getValue().getOperatiuni(), new Comparator<Instrument.Operatiune>() {
                @Override
                public int compare(Instrument.Operatiune o1, Instrument.Operatiune o2) {
                    return o1.getData().compareTo(o2.getData());
                }
            }).getData();

        }
        System.out.println("Valoarea portofoliului este: " + value);
        try {
            salvareaPortofoliu("./Portofolii/Portofoliivechi/portofoliu1.mamamea", portofoliu);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            PortofoliuGenerics<Instrument> portofoliufisier = incarcarePortofoliu("Portofolii/Portofoliivechi/portofoliu1.txt");
            for (var entry : portofoliufisier.getPortfoliu().entrySet()) { //valoare in portofoliu //furnizeaza lista cheie-valoare
                System.out.println(entry.getValue().toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
