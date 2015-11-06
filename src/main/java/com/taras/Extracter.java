package com.taras;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.*;
import java.util.zip.*;



public class Extracter {
    public static final Logger log = LogManager.getLogger(Extracter.class);
    public static final char FILE_SEPARATOR = System.getProperty("file.separator").toCharArray()[0];
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private void unpack(String path) throws IOException {
        String dir_to = path.substring(0,path.lastIndexOf(FILE_SEPARATOR));
        ZipFile zip = new ZipFile(path);
        Enumeration entries = zip.entries();
        List<ZipEntry> zfiles = new LinkedList<ZipEntry>();
        log.debug("Zip file {} has been opened.", path);
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            if (entry.isDirectory()) {
                new File(dir_to + FILE_SEPARATOR + entry.getName()).mkdir();
            } else {
                zfiles.add(entry);
            }
        }
        for (ZipEntry entry : zfiles) {
            InputStream in = zip.getInputStream(entry);
            OutputStream out = new FileOutputStream(dir_to + FILE_SEPARATOR + entry.getName());
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) >= 0)
                out.write(buffer, 0, len);
            in.close();
            out.close();
        }
        log.debug("Zip file {} has been closed.", path);
        zip.close();
    }

//    private String changeSlashes(String end) {
//        int index = end.lastIndexOf('/');
//
//        if (index == -1)
//            return end;
//        else {
//            String result = end.substring(0, index) + "\\" + end.substring(index + 1);
//            return changeSlashes(result);
//        }
//    }

    public void enterZipAndRead(String path) throws IOException {
        unpack(path);
        ZipInputStream zin = null;
        try {
            log.debug("Zip file {} has been opened.", path);
            zin = new ZipInputStream(new FileInputStream(path));
            ZipEntry entry;
            String name;
            while ( (entry = zin.getNextEntry() ) != null) {
                String end = entry.getName().replace('/', FILE_SEPARATOR);
                name = path.substring(0,path.lastIndexOf(FILE_SEPARATOR)) + FILE_SEPARATOR + end;
                String format = getFileExtention(name);
                if (format.equals("txt")) {
                    readFromFile(name);
                } else if (format.equals("zip"))
                    enterZipAndRead(name);
            }
        } catch(Exception ex){
            log.error(ex.getMessage());
        } finally {
            if (zin != null)
                zin.close();
                log.debug("Zip file {} has been closed.", path);
        }
    }

    private void readFromFile(String path){
        String s;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
            log.debug("File {} has been opened.", path);
            while ((s = reader.readLine()) != null) {
                if (s.length() !=0) {
                    String[] temp = s.split(";");
                    Person.addPerson(temp[0], temp[1], temp[2], temp[3]);
                }
            }
            reader.close();
            log.debug("File {} has been closed.", path);
        } catch(FileNotFoundException exx) {
            log.error(exx);
        } catch (IOException ex){
            log.error(ex);
        }
    }

    public static String getFileExtention(String filename) {
        int dotPos = filename.lastIndexOf(".") + 1;
        return filename.substring(dotPos);
    }

    public void writeToFile() {
        String name = Person.getOftenName();

        File file = new File(name + ".txt");
        try {
            if(!file.exists()){
                file.createNewFile();
                log.debug("File {} has been created.", file.getCanonicalPath());
            } else {
                log.debug("File {} has been opened.", file.getCanonicalPath());
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try {
                String [] temp = Person.getAllText();
                for (String str : temp) {
                    out.write(str + LINE_SEPARATOR);
                    log.debug(str);
                }

            }finally {
                out.close();
                log.debug("File {} has been closed.", file.getCanonicalPath());
            }
        }catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
