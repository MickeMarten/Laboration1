package laboration;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ElectricPriceService {
    public static List<String[]> ScvReader(String filePath) throws Exception {

        List<String[]> valuesList = new ArrayList<>();

        String file = filePath;

        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {

                String[] values = line.split(";");
                valuesList.add(values);

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return valuesList;







    }




}
