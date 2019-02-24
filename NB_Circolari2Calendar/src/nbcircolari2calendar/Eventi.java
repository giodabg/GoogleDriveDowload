/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbcircolari2calendar;

import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author scuola
 */
public class Eventi {

    private Vector<Evento> eventi;
    private ArrayList<Evento> eventiComuni;
    private String dataComune;
    private String descrizioneComune;

    public Eventi() {
        this.eventi = new Vector();
        this.eventiComuni = new ArrayList();
        this.dataComune = "";
        this.descrizioneComune = "";
    }

    public Eventi(Vector<Evento> eventi, ArrayList<Evento> eventiComuni,
            String dataComune, String descrizioneComune) {
        this.eventi = eventi;
        this.eventiComuni = eventiComuni;
        this.dataComune = dataComune;
        this.descrizioneComune = descrizioneComune;
    }

    public void add(Evento e) {
        eventi.add(e);
    }

    public Evento getEvento(int i) {
        return eventi.elementAt(i);
    }

    public int numEventi() {
        return eventi.size();
    }

    public String toString() {
        String str = "";

        for (int i = 0; i < eventi.size(); i++) {
            Evento e = eventi.elementAt(i);
            str = str + e.toString() + ";\n";
        }
        return str;
    }

}
