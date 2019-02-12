# GoogleDriveDowload

Per testare il programma procedere come segue:

1.	consultare pagina https://developers.google.com/drive/api/v3/quickstart/java e seguire le istruzioni 

oppure

1.	andare alla pagina https://console.developers.google.com/cloud-resource-manager
2.	creare un nuovo progetto con il nome GoogleDriveDownload
3.	cliccare in alto a sinistra sulle tre lineette e selezionare DashBoard
4.	cliccare su Abilita API 
5.	selezionare e abilitare Google DRIVE API
6.	creare le credenziali con i seguenti parametri
6.a.	Google Drive API
6.b.	Altra UI
6.c.	Dati utente
7.	Creare un ID (per esempio GoogleDriveMyID)
8.	Selezione come indirizzo email per il consenso quello dell’utente Google al cui drive si vuole accedere (per esempio ….@gmail.com) e come nome prodotto un nome che permetta all’utente di identificare il software a cui sta dando l’autorizzazione di accedere ai file  (per esempio TapSchool)
9.	Scarica le credenziali nella cartella "C:\Windows\Users\\<nome_utente>\credentials" rinominandolo in "client_secret.json" 

e poi

1. eseguire il programma "DriveQuickstart.java" 
2. collegarsi con il proprio account e abilitare l'accesso a GoogleDrive della applicazione Java
3. chiudere la pagina del browser
4. controllare se nella directory "C:\Windows\Users\\<nome_utente>\credentials" è stato caricato l file StoredCredential

e poi

1. aprire il file NBGoogleDrive.java
2. trovare l'istruzione nel main che chiama il metodo getGoogleFilesByIdDir("1zPYIo-Df3KxqVWZQcwGs7UhkBiUioCo_");
3. sostituire la stringa con l'id di una cartella presente nel proprio Google Drive (l'id è la sequenza alfanumerica presente in fondo all'url della pagina con cui si sta visualizzando il contenuto della propria cartella nel browser)
4. il programam scarica solo file con estensione pdf per cui è necessario inserire qualche file nella cartella per testare il funzionamento
4. eseguire il main associato al file NBGoogleDrive.java
5. controllare il contenuto della cartella locale "C:\Windows\Users\\<nome_utente>\TapSchoolDocuments"
