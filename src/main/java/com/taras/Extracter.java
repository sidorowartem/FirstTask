package com.taras;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.*;
import java.util.zip.*;

/**
 * Created by taras on 26.10.2015.
 */

public class Extracter {
    public static final Logger log = LogManager.getLogger(Extracter.class);

    private void unpack(String path) throws IOException {
        String dir_to = path.substring(0,path.lastIndexOf('\\'));
        ZipFile zip = new ZipFile(path);
        Enumeration entries = zip.entries();
        LinkedList<ZipEntry> zfiles = new LinkedList<ZipEntry>();
        log.debug("Zip file " + path + " has been opened.");
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            if (entry.isDirectory()) {
                new File(dir_to+"\\"+entry.getName()).mkdir();
            } else {
                zfiles.add(entry);
            }
        }
        for (ZipEntry entry : zfiles) {
            InputStream in = zip.getInputStream(entry);
            OutputStream out = new FileOutputStream(dir_to + "\\" + entry.getName());
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) >= 0)
                out.write(buffer, 0, len);
            in.close();
            out.close();
        }
        log.debug("Zip file " + path + " has been closed.");
        zip.close();
    }

    private String changeSlashes(String end) {
        int index = end.lastIndexOf('/');
        if (index == -1)
            return end;
        else {
            String result = end.substring(0, index) + "\\" + end.substring(index + 1);
            return changeSlashes(result);
        }
    }

    public void enterZipAndRead(String path) throws IOException {
        unpack(path);
        ZipInputStream zin = null;
        try {
            log.debug("Zip file " + path + " has been opened.");
            zin = new ZipInputStream(new FileInputStream(path));
            ZipEntry entry;
            String name;
            while ( (entry = zin.getNextEntry() ) != null) {
                String end = changeSlashes(entry.getName());
                name = path.substring(0,path.lastIndexOf('\\')) + "\\" + end;
                String format = getFileExtention(name);
                if (format.equals("txt")) {
                    readFromFile(name);
                } else if (format.equals("zip"))
                    enterZipAndRead(name);
            }
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            if (zin != null)
                zin.close();
                log.debug("Zip file " + path + " has been closed.");
        }
    }

    private void readFromFile(String path){
        String s;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
            log.debug("File " + path + " has been opened.");
            while ((s = reader.readLine()) != null) {
                if (s.length() !=0) {
                    String[] temp = s.split(";");
                    Person.addPerson(temp[0], temp[1], temp[2], temp[3]);
                }
            }
            reader.close();
            log.debug("File " + path + " has been closed.");
        } catch(FileNotFoundException exx) {
            System.out.println(exx);
        } catch (IOException ex){
            System.out.println(ex);
        }
    }

    public static String getFileExtention(String filename){
        int dotPos = filename.lastIndexOf(".") + 1;
        return filename.substring(dotPos);
    }

    public static void writeToFile(){
        String name = Person.getOftenName();
        File file = new File(name + ".txt");
        try {
            if(!file.exists()){
                file.createNewFile();
                log.debug("File " + file.getCanonicalPath() + " has been created.");
            } else {
                log.debug("File " + file.getCanonicalPath() + " has been opened.");
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try {
                //Запись
                String [] temp = Person.getAllText();
                for (String str : temp) {
                    out.println(str);
                }

            }finally {
                out.close();
                log.debug("File " + file.getCanonicalPath() + " has been closed.");
            }
        }catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
