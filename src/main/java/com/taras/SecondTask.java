package com.taras;

import java.io.IOException;

/**
 * Created by taras on 26.10.2015.
 */
public class SecondTask {
    public static void main(String[] args) {
        Extracter extracter = new Extracter();
        String path ="src\\main\\resources\\root_folder.zip";
        try{
            extracter.enterZipAndRead(path);
            extracter.writeToFile();

        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
