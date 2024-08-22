package laboration;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ElectricPriceService {
    public static void ScvReader() throws Exception {

        String file = "elpriser.csv";

        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {

                String[] values = line.split(";");
                System.out.println("Tid:" + values[0] + " " + "Pris:" + values[1]);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }







    }




}
