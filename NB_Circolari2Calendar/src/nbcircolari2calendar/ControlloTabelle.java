/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbcircolari2calendar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author scuola
 */
public class ControlloTabelle {

    public static int CONSIGLI_CLASSE = 0;
    public static int PROGETTO_SESSUALITA = 1;

    // https://www.tutorialspoint.com/java/java_regular_expressions.htm
    // parola seguita da numero seguita da parola
    public static final String REGEX_WORD = "([^\\s ]*)";
    public static final String REGEX_LINE = "(.*)";
    public static final String REGEX_ORAIN_ORAFIN = "([^\\s ]*)/([^\\s ]*)";
    public static final String REGEX_ORAIN_ORAFIN1 = "([^\\s ]*) - ([^\\s ]*)";

    public static final String[] regExpTabHeaderConsigli = {"[^\\s\\d]*\\s([\\d]*)\\s(\\w*)"};
    public static final String[] interTabHeaderConsigli = "giorno:mese".split(":");

    public static final String[] regExpTabSubHeaderConsigli = {REGEX_LINE, REGEX_LINE, REGEX_LINE};
    public static final String[] interTabSubHeaderConsigli = "descrizione1:descrizione2:descrizione3".split(":");

    public static final String[] regExpTabBodyConsigli = {REGEX_ORAIN_ORAFIN, REGEX_ORAIN_ORAFIN, REGEX_WORD, REGEX_WORD, REGEX_WORD};
    public static final String[] interTabBodyConsigli = "oraInizio1:oraFine1:oraInizio2:oraFine2:classe1:classe2:classe3".split(":");

    public static final String[] interTabFooterConsigli = "descrizione:luogo1:luogo2:luogo3".split(":");

    public static final String[] regExpTabHeaderProgetto = {REGEX_WORD, REGEX_ORAIN_ORAFIN1, REGEX_ORAIN_ORAFIN1};
    public static final String[] interTabHeaderProgetto = "descrizione:oraInizio1:oraFine1:oraInizio2:oraFine2".split(":");

    public static final String tableTag = "Table";
    public static final String rowTag = "TR";
    public static final String cellTag = "TD";
    public static final String contentTag = "P";

    static int Tipo(Element element) {
        return CONSIGLI_CLASSE;
    }

    static boolean isUltimaRigaConsigli(Element el) {
        return (el.getElementsByTagName(cellTag).getLength() == interTabFooterConsigli.length);
    }

    static void interpHeaderConsigli(Evento evento, int col, String str) {
        int numInfo = 0;

        Pattern p = Pattern.compile(ControlloTabelle.regExpTabHeaderConsigli[col]);
        Matcher m = p.matcher(str);
        if (m.find()) {
            for (int g = 0; g < m.groupCount(); g++) {
                // il gruppo 0 contiene tutta la stringa di partenza
                // groupCount considera anche il gruppo 0 quindi i gruppi
                // trovati partono dall'indice 1
                evento.setGenericInfo(interTabHeaderConsigli[numInfo], m.group(g + 1));
                numInfo++;
            }
        }
    }

    static int interTabSubHeaderConsigli(Evento evento, int col, String str, int numInfo) {
        Pattern p = Pattern.compile(ControlloTabelle.regExpTabSubHeaderConsigli[col]);
        Matcher m = p.matcher(str);
        if (m.find()) {
            for (int g = 0; g < m.groupCount(); g++) {
                // il gruppo 0 contiene tutta la stringa di partenza
                // groupCount considera anche il gruppo 0 quindi i gruppi
                // trovati partono dall'indice 1
                // System.out.println(ControlloTabelle.interTabSubHeaderConsigli[numInfo] + ":" + m.group(g + 1) + ":");
                evento.setGenericInfo(interTabSubHeaderConsigli[numInfo], m.group(g + 1));
                numInfo++;
            }
        }
        return numInfo;
    }

    static int interTabBodyConsigli(Eventi vettoreConsigli, Evento infoEvento, int numInfo, String str, int col) {

        Pattern p = Pattern.compile(ControlloTabelle.regExpTabBodyConsigli[col]);
        Matcher m = p.matcher(str);
        if (m.find()) {
            for (int g = 0; g < m.groupCount(); g++) {
                // il gruppo 0 contiene tutta la stringa di partenza
                // groupCount considera anche il gruppo 0 quindi i gruppi
                // trovati partono dall'indice 1
                if (ControlloTabelle.interTabBodyConsigli[numInfo].equals("classe1")) {
                    String calendario = m.group(g + 1);
                    creaEventi(vettoreConsigli, calendario, infoEvento);
                } else if (ControlloTabelle.interTabBodyConsigli[numInfo].equals("classe2")) {
                    String calendario = m.group(g + 1);
                    creaEventi(vettoreConsigli, calendario, infoEvento);
                } else if (ControlloTabelle.interTabBodyConsigli[numInfo].equals("classe3")) {
                    String calendario = m.group(g + 1);
                    creaEventi(vettoreConsigli, calendario, infoEvento);
                }

                infoEvento.setGenericInfo(interTabBodyConsigli[numInfo], m.group(g + 1));
                numInfo++;
            }
        }
        return numInfo;
    }

    private static void creaEventi(Eventi vettoreConsigli, String calendario, Evento infoEvento) {
        Evento evento = createEvento(infoEvento, calendario, "descrizione1",
                "oraInizio1", "oraFine1");
        vettoreConsigli.add(evento);

        evento = createEvento(infoEvento, calendario, "descrizione2",
                "oraInizio2", "oraFine2");
        vettoreConsigli.add(evento);

    }

    private static Evento createEvento(Evento infoEvento, String calendario, String kdescrizione,
            String koraInizio, String koraFine) {

        String descrizione = infoEvento.getGenericInfo("descrizione");
        descrizione += " " + infoEvento.getGenericInfo(kdescrizione);
        String oraInizio = infoEvento.getGenericInfo(koraInizio);
        String oraFine = infoEvento.getGenericInfo(koraFine);
        Evento evento = new Evento(calendario, infoEvento.getGiorno(),
                infoEvento.getMese(), infoEvento.getAnno(),
                oraInizio, oraFine, descrizione);
        return evento;

    }
}
