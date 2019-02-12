/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbgoogledrive;

/**
 *
 * @author scuola
 */
// https://o7planning.org/en/11889/manipulating-files-and-folders-on-google-drive-using-java#a20602553
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NBGoogleDrive {

        // Directory to store downloade file for this application.
    private static final java.io.File DOCUMENTS_FOLDER //
            = new java.io.File(System.getProperty("user.home"), "TapSchoolDocuments");

// com.google.api.services.drive.model.File
    public static final List<File> getGoogleFilesByIdDir(String idGoogleDirectory) throws IOException {
        
        Drive driveService = GoogleDriveUtils.getDriveService();
        
        String pageToken = null;
        List<File> list = new ArrayList<File>();
        
        String query = " '"+idGoogleDirectory+"' in parents " //
                + " and mimeType contains 'application/pdf' ";
        
        do {
            FileList result = driveService.files().list().setQ(query).setSpaces("drive") //
                    // Fields will be assigned values: id, name, createdTime, mimeType
                    .setFields("nextPageToken, files(id, name, createdTime, mimeType)")//
                    .setPageToken(pageToken).execute();
            for (File file : result.getFiles()) {
                list.add(file);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                driveService.files().get(file.getId())
                        .executeMediaAndDownloadTo(outputStream);
                String pathNameFile = DOCUMENTS_FOLDER.getAbsolutePath()+"\\"+file.getName();
                FileOutputStream fos = new FileOutputStream(pathNameFile); 
                outputStream.writeTo(fos);

            }
            pageToken = result.getNextPageToken();
        } while (pageToken != null);
        //
        return list;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            
            List<File> rootGoogleFolders = getGoogleFilesByIdDir("1zPYIo-Df3KxqVWZQcwGs7UhkBiUioCo_");
            for (File folder : rootGoogleFolders) {
                
                System.out.println("Mime Type: " + folder.getMimeType() + " --- Name: " + folder.getName());
            }
            
            System.out.println("Done!");
        } catch (IOException ex) {
            Logger.getLogger(NBGoogleDrive.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
