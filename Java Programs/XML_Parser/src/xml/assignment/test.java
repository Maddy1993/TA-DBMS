package xml.assignment;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class test {

        public static void main(String args[]) {
            try {
                Path filePath = Paths.get(System.getProperty("user.dir"),
                        Paths.get("src",
                                Paths.get("xml",
                                        "assignment").toString()).toString());

                FileInputStream fis = new FileInputStream(filePath.toString() + "\\site1.xml");
                BufferedReader r = new BufferedReader(new InputStreamReader(fis,
                        "UTF8"));
                for (String s = ""; (s = r.readLine()) != null; ) {
                    System.out.println(s);
                }
                r.close();
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
}
