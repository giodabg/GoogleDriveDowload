/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbcircolari2calendar;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author scuola
 */
public class FileUtil {

    public static void ScriviCSV(Eventi consigli, String fileCSV, boolean append) {
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(fileCSV, append);
            bw = new BufferedWriter(fw);
            for (int i = 0; i < consigli.numEventi(); i++) {
                Evento e = consigli.getEvento(i);
                bw.write(e.toString() + ";\n");
                bw.flush();
            }

        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
