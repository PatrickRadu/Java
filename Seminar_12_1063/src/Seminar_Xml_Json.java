import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Seminar_Xml_Json {

    public static List<Produs> citireXml(String caleFisier) throws Exception {
        var rezultat = new ArrayList<Produs>();

        var factory = DocumentBuilderFactory.newInstance();
        var builder = factory.newDocumentBuilder();

        // 2. Citim conținutul din fișier
        var document = builder.parse(caleFisier);
        var radacina = document.getDocumentElement();

        // TODO
        var nodProduse=radacina.getElementsByTagName("produs");
        for (int index=0;index<nodProduse.getLength();index++)
        {
            Element nodProdus=(Element)nodProduse.item(index);
            int cod=Integer.parseInt(nodProdus.getElementsByTagName("cod").item(0).getTextContent())
        }


        return rezultat;
    }

    public static void salvareXml(String caleFisier, List<Produs> produse) throws Exception {
        // 1. Construire document XML gol
        var factory = DocumentBuilderFactory.newInstance();
        var builder = factory.newDocumentBuilder();

        var document = builder.newDocument();

        // 2. Construire și atașare elemente
        var radacina = document.createElement("produse");
        document.appendChild(radacina);

        // TODO

    }

    public static void salvareJson(String caleFisier, List<Produs> produse) throws Exception {

        var jsonProduse = new JSONArray();

        // TODO

    }

    public static List<Produs> citireJson(String caleFisier) throws Exception {
        var rezultat = new ArrayList<Produs>();

        try (var fisier = new FileInputStream(caleFisier)) {

            // TODO

        }

        return rezultat;
    }

    public static void main(String[] args) throws Exception {

        // Testare clase:
//        var p = new Produs(1, "test", Arrays.asList(
//                new Tranzactie(TipTranzactie.INTRARE, 10),
//                new Tranzactie(TipTranzactie.INTRARE, 5),
//                new Tranzactie(TipTranzactie.IESIRE, 11)
//        ));
//        System.out.println(p);
//        System.out.println(p.getStoc());
        var produse = citireXml("dateIN\\produse.xml");
        produse.stream()
            .forEach(produs -> System.out.println(produs.toString()));

        salvareXml("dateOUT\\produse_generat.xml", produse);
        salvareJson("dateOUT\\produse_generat.json", produse);
        produse = citireJson("dateOUT\\produse_generat.json");
        produse.stream()
                .forEach(produs -> System.out.println(produs.toString()));
    }

}
