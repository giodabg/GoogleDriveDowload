package nbcircolari2calendar;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * @author scuola
 *
 */
public class ParserConsigli {

    public Eventi parseDocument(String filename)
            throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document document;
        Element root;
        NodeList nodelist;
        Eventi vettoreConsigli = new Eventi();

        // creazione dell’albero DOM dal documento XML
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        document = builder.parse(filename);
        root = document.getDocumentElement();

        nodelist = root.getElementsByTagName(ControlloTabelle.tableTag);
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                parseTable(vettoreConsigli, (Element) nodelist.item(i));
            }
        }
        return vettoreConsigli;
    }

    private Eventi parseTable(Eventi vettoreConsigli, Element root) {
        String titolo = "";
        Evento infoEvento = null;
        Element element;
        NodeList nodelistRow = root.getElementsByTagName(ControlloTabelle.rowTag);
        if (nodelistRow != null && nodelistRow.getLength() > 0) {
            int tipoTabella = 0;
            for (int i = 0; i < nodelistRow.getLength(); i++) {
                element = (Element) nodelistRow.item(i);
                if (i == 0) {
                    // prima riga della tabella
                    infoEvento = new Evento();
                    tipoTabella = ControlloTabelle.Tipo(element);
                    if (tipoTabella == ControlloTabelle.CONSIGLI_CLASSE) {
                        parseTableHeaderConsigli(infoEvento, element);
                    }
                } else if (i == 1) {
                    // seconda riga della tabella
                    if (tipoTabella == ControlloTabelle.CONSIGLI_CLASSE) {
                        parseTableSubHeaderConsigli(infoEvento, element);
                    }
                } else if ((tipoTabella == ControlloTabelle.CONSIGLI_CLASSE)
                        && (ControlloTabelle.isUltimaRigaConsigli(element))) {
                    // ulltima riga della tabella consigli di classe
                    parseTableFooterConsigli(vettoreConsigli, element);
                } else // righe intermedie della tabella
                if (tipoTabella == ControlloTabelle.CONSIGLI_CLASSE) {
                    parseTableBodyConsigli(vettoreConsigli, infoEvento, element);
                }

            }
        }
        // System.out.println("parseTable");
        // System.out.println("vettoreConsigli");
        // System.out.println(vettoreConsigli.toString());
        return vettoreConsigli;
    }

    private void parseTableHeaderConsigli(Evento evento, Element root) {
        NodeList nodeList = root.getElementsByTagName(ControlloTabelle.cellTag);

        if (nodeList != null) {
            String str = "";
            for (int col = 0; col < nodeList.getLength() && col < ControlloTabelle.regExpTabHeaderConsigli.length; col++) {
                str = getTextValue((Element) nodeList.item(col), ControlloTabelle.contentTag);
                ControlloTabelle.interpHeaderConsigli(evento, col, str);
                // System.out.println("parseTableHeaderConsigli");
                // System.out.println(evento.toString());
            }
        }
    }

    private void parseTableSubHeaderConsigli(Evento evento, Element root) {
        NodeList nodeList = root.getElementsByTagName(ControlloTabelle.cellTag);
        if (nodeList != null) {
            String str = "";
            int numInfo = 0;
            for (int col = 0; col < nodeList.getLength() && col < ControlloTabelle.regExpTabSubHeaderConsigli.length; col++) {
                str = getTextValue((Element) nodeList.item(col), ControlloTabelle.contentTag);
                numInfo = ControlloTabelle.interTabSubHeaderConsigli(evento, numInfo, str, col);
            }
            // System.out.println("parseTableSubHeaderConsigli");
            // System.out.println(evento.toString());
        }
    }

    private void parseTableBodyConsigli(Eventi vettoreConsigli, Evento infoEvento, Element root) {
        String info = "";
        //Evento infoEvento = new Event(infoEventoOrig);
        NodeList nodeList = root.getElementsByTagName(ControlloTabelle.cellTag);
        if (nodeList != null) {
            String str = "";
            int numInfo = 0;
            for (int col = 0; col < nodeList.getLength() && col < ControlloTabelle.regExpTabBodyConsigli.length; col++) {
                str = getTextValue((Element) nodeList.item(col), ControlloTabelle.contentTag);
                numInfo = ControlloTabelle.interTabBodyConsigli(vettoreConsigli, infoEvento, numInfo, str, col);
                // System.out.println("\ninfoEvento");
                // System.out.println(infoEvento.toString());
            }
        }
    }

    private void parseTableFooterConsigli(Eventi vettoreConsigli, Element root) {
        String info = "";
        NodeList nodeList = root.getElementsByTagName(ControlloTabelle.cellTag);

        if (nodeList != null) {
            String str = "";
            if (nodeList.getLength() > 1) {
                str = getTextValue((Element) nodeList.item(1), ControlloTabelle.contentTag);
                System.out.println("Aula1 = " + str);
            }
            if (nodeList.getLength() > 2) {
                str = getTextValue((Element) nodeList.item(2), ControlloTabelle.contentTag);
                System.out.println("Aula2 = " + str);
            }
            if (nodeList.getLength() > 3) {
                str = getTextValue((Element) nodeList.item(3), ControlloTabelle.contentTag);
                System.out.println("Aula3 = " + str);
            }
        }
    }

    // restituisce il valore testuale dell’elemento figlio specificato
    private String getTextValue(Element element, String tag) {
        String tmp = "";
        NodeList nodelist;
        nodelist = element.getElementsByTagName(tag);

        if (nodelist != null && nodelist.getLength() > 0) {

            for (int i = 0; i < nodelist.getLength(); i++) {
                element = (Element) nodelist.item(i);
                tmp += element.getFirstChild().getNodeValue() + " ";
            }
        }
        return tmp;
    }

    // restituisce il valore intero dell’elemento figlio specificato
    private int getIntValue(Element element, String tag) {
        return Integer.parseInt(getTextValue(element, tag));
    }

    // restituisce il valore numerico dell’elemento figlio specificato
    private float getFloatValue(Element element, String tag) {
        return Float.parseFloat(getTextValue(element, tag));
    }

}
