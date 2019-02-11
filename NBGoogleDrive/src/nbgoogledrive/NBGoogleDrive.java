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

public class NBGoogleDrive {

// com.google.api.services.drive.model.File
    public static final List<File> getGoogleFilesByName(String fileNameLike) throws IOException {
        
        Drive driveService = GoogleDriveUtils.getDriveService();
        
        String pageToken = null;
        List<File> list = new ArrayList<File>();
        
        String query = " name contains '" + fileNameLike + "' " //
                + " and mimeType != 'application/vnd.google-apps.folder' ";
        
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
                FileOutputStream fos = new FileOutputStream("Documents\\"+file.getName()); 
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
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        List<File> rootGoogleFolders = getGoogleFilesByName("pdf");
        for (File folder : rootGoogleFolders) {
            
            System.out.println("Mime Type: " + folder.getMimeType() + " --- Name: " + folder.getName());
        }
        
        System.out.println("Done!");
    }
    
}
