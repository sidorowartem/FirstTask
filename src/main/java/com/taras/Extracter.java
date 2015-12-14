package com.taras;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


public class Extracter {
    public static final Logger log = LogManager.getLogger(Extracter.class);
    public static final char FILE_SEPARATOR = System.getProperty("file.separator").toCharArray()[0];
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private void unpack(String path) {
        String dir_to = path.substring(0, path.lastIndexOf(FILE_SEPARATOR));
        try (ZipFile zip = new ZipFile(path)) {
            Enumeration entries = zip.entries();
            List<ZipEntry> zfiles = new LinkedList<>();
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
                try (InputStream in = zip.getInputStream(entry); OutputStream out = new FileOutputStream(dir_to + FILE_SEPARATOR + entry.getName())) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = in.read(buffer)) >= 0)
                        out.write(buffer, 0, len);
                } catch (IOException ex) {
                    log.error(ex.getMessage());
                }
            }
        } catch (IOException ex) {
            log.error("Can not open zip file: " + ex.getMessage());
        }
    }

    public void enterZipAndRead(String path) throws IOException {
        unpack(path);
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(path))) {
            log.debug("Zip file {} has been opened.", path);
            try {
                ZipEntry entry;

                String name;
                while ((entry = zin.getNextEntry()) != null) {
                    String end = entry.getName().replace('/', FILE_SEPARATOR);
                    name = path.substring(0, path.lastIndexOf(FILE_SEPARATOR)) + FILE_SEPARATOR + end;
                    String format = getFileExtention(name);
                    if (format.equals("txt")) {
                        readFromFile(name);
                    } else if (format.equals("zip"))
                        enterZipAndRead(name);
                }
            } catch (IOException ex) {
                log.error(ex.getMessage());
            }
        } catch (Exception ex) {
            log.error("Can not find or open zip file: " + ex.getMessage());
        }
    }

    private void createPersonFromString(String s) {
        if (s.length() != 0) {
            String[] temp = s.split(";");
            Person.createPerson(temp[0], temp[1], temp[2], temp[3]);
        }
    }

    private void readFromFile(String path) {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            log.debug("File {} has been opened.", path);
            stream.forEach(s -> createPersonFromString(s));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        log.debug("File {} has been closed.", path);
    }

    private static String getFileExtention(String filename) {
        int dotPos = filename.lastIndexOf(".") + 1;
        return filename.substring(dotPos);
    }

    public void writeToFile() {
        String name = PersonManager.getOftenName();

        File file = new File(name + ".txt");
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    log.debug("File {} has been created.", file.getCanonicalPath());
                } else {
                    log.debug("File {} was not created - already exists.", file.getCanonicalPath());
                }
            } else {
                log.debug("File {} has been opened.", file.getCanonicalPath());
            }
            try (PrintWriter out = new PrintWriter(file.getAbsoluteFile())) {
                String[] temp = PersonManager.getAllText();
                for (String str : temp) {
                    out.write(str + LINE_SEPARATOR);
                    log.debug(str);
                }

            } finally {
                log.debug("File {} has been closed.", file.getCanonicalPath());
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
