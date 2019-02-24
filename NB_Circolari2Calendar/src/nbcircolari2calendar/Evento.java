/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbcircolari2calendar;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author scuola
 */
public class Evento {

    Map<String, String> map;

    public Evento() {
        map = new HashMap<String, String>();
        map.put("calendario", "");
        map.put("giorno", "");
        map.put("mese", "");
        map.put("anno", "");
        map.put("oraInizio", "");
        map.put("oraFine", "");
        map.put("descrizione", "");
    }

    public Evento(String calendario, String giorno, String mese, String anno, String oraInizio, 
            String oraFine, String descrizione) {
        map = new HashMap<String, String>();
        map.put("calendario", calendario);
        map.put("giorno", giorno);
        map.put("mese", mese);
        map.put("anno", anno);
        map.put("oraInizio", oraInizio);
        map.put("oraFine", oraFine);
        map.put("descrizione", descrizione);
    }

    public void setEvento(String calendario, String giorno, String mese, String anno, String oraInizio, 
            String oraFine, String descrizione) {
        map.replace("calendario", calendario);
        map.replace("giorno", giorno);
        map.replace("mese", mese);
        map.replace("anno", anno);
        map.replace("oraInizio", oraInizio);
        map.replace("oraFine", oraFine);
        map.replace("descrizione", descrizione);
    }
        
    public String getCalendario() {
        return map.get("calendario");
    }

    public void setCalendario(String calendario) {
        map.replace("calendario", calendario);
    }

    public String getGiorno() {
        return map.get("giorno");
    }

    public void setGiorno(String giorno) {
        map.replace("giorno", giorno);
    }
    
    public String getMese() {
        return map.get("mese");
    }

    public void setMese(String mese) {
        map.replace("mese", mese);
    }

    public String getAnno() {
        return map.get("anno");
    }

    public void setAnno(String anno) {
        map.replace("anno", anno);
    }

    public String getOraInizio() {
        return map.get("oraInizio");
    }
    public void setOraInizio(String oraInizio) {
        map.replace("oraInizio", oraInizio);
    }

    public String getOraFine() {
        return map.get("oraFine");
    }
    
    public void setOraFine(String oraFine) {
        map.replace("oraFine", oraFine);
    }

    public String getDescrizione() {
        return map.get("descrizione");
    }
    
    public void setDescrizione(String descrizione) {
        map.replace("descrizione", descrizione);
    }

    public String getGenericInfo(String name) {
        return map.get(name);
    }
    
    public void setGenericInfo(String name, String value) {
        if (getGenericInfo(name) != null)
            map.replace(name, value);
        else
            map.put(name, value);
    }
    
    public String toCSV() {
        // return "Evento{" + "titolo=" + titolo + ", Descrizione=" + giorno + ", orarioDocenti=" + orarioDocenti + ", orarioStudenti=" + orarioStudenti + '}';
        return (getCalendario()
                + ";" + getGiorno()
                + ";" + getMese()
                + ";" + getOraInizio()
                + ";" + getOraFine());

    }
    
    public String toString() {
        String str = "";
        Iterator<String> itrK = map.keySet().iterator();
        Iterator<String> itrV = map.values().iterator();
	while (itrK.hasNext()) {
		str += (";"+itrK.next()+":"+itrV.next());
	}
        return str;
        //return map.values().toString();
    }
    
}
