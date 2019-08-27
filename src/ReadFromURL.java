
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;


import java.io.File;


public class ReadFromURL{

    public static File URLread(String url , String fileName ) throws IOException {

        System.out.println("opening connection");

        URL url1 = new URL(url);

        File newFile = new File(fileName);

        InputStream in = url1.openStream();

        FileOutputStream fos = new FileOutputStream(newFile);

        System.out.println("reading");

        int length = -1;

        byte[] buffer = new byte[1024];

        while ((length = in.read(buffer)) > -1) {

            fos.write(buffer, 0, length);
        }

        fos.close();
        in.close();

        System.out.println("File downloaded");

        return newFile;
    }


    public static void main(String[] args) throws IOException {

       //URLread( "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf" , "list.pdf");

    }


}