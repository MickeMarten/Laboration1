package laboration;

import java.util.*;
import java.util.Collections;


public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args ) {
        boolean userOnline = true;

        while (userOnline) {

            menuDisplay();
            String userInput = sc.nextLine();


            switch (userInput) {
                case "1":
                    electricPricesToday();
                    break;

                case "2":
                    comparePrices();
                    break;


                case "3":
                    sortCheapestToMostExpensive();
                    break;

                case "4":
                    bestChargingTime();
                    break;

                case "e":
                case "E":
                    System.out.println("Ha en trevlig dag.");
                    userOnline = false;
                    break;
                default:
                    System.out.println("Fel inmatning, mata in symbolerna/siffrorna som visas i menyn och avsluta med enter");
                    break;
            }


        }
    }

    static void menuDisplay() {

        System.out.print("Välkommen till 'El-Priser idag co'. \n " +
                "Elpriser \n" +
                " ========= " + "\n " +
                "1.Se elpriser idag \n" +
                "2.Min, Max och Medel \n " +
                "3.Sortera \n " +
                "4.Bästa Laddningstid (4h) " +
                "\n e. Avsluta \n " +
                "Skriv ditt val här:");


    }

    static void returnToMenu() {
        System.out.println("Tryck på valfri tangent för att återgå till huvudmenyn. Avsluta med enter.");
        sc.nextLine();
    }

    static List<String[]> getCSVFile() {
        try {
            String file = "elpriser.csv";

            return ElectricPriceService.ScvReader(file);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }

    static void electricPricesToday() {
        try {

            List<String[]> electricData = getCSVFile();
            for (String[] electricDataDetails : electricData) {
                float price = Float.parseFloat(electricDataDetails[1]);
                System.out.println("Klockan:" + electricDataDetails[0] + "\t" + "Pris:" + price + "öre/kWh");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fel vid hämtning av data");
        }
        returnToMenu();

    }


    static void comparePrices() {
        try {


            List<String[]> electricData = getCSVFile();
            float lowestPrice = Float.MAX_VALUE;
            float highestPrice = Float.MIN_VALUE;
            String highestPriceTime = "";
            String lowestPriceTime = "";
            float priceSummary = 0;
            for (String[] electricDataDetails : electricData) {
                float electricPrice = Float.parseFloat(electricDataDetails[1]);
                if (electricPrice < lowestPrice) {
                    lowestPrice = electricPrice;
                    lowestPriceTime = electricDataDetails[0];
                }
                if (electricPrice > highestPrice) {
                    highestPrice = electricPrice;
                    highestPriceTime = electricDataDetails[0];

                }


                priceSummary += electricPrice / 24;

            }
            System.out.println("Bäst tid att använda el:" + lowestPriceTime + "Priset är då:" + lowestPrice + "öre/kWh");
            System.out.println("Sämsta tid att använda el:" + highestPriceTime + "Priset är då:" + highestPrice + "öre/kWh");
            System.out.println("Medelvärdet på dygnet är:" + priceSummary + "öre/kWh");

        } catch (Exception e) {
            System.out.println("Fel vid hätmnigen av data!" + e);

        }
        returnToMenu();

    }

    static void sortCheapestToMostExpensive() {
        try {

            List<String[]> electricData = getCSVFile();
            List<TimePriceObject> electricDataSorted = new ArrayList<>();


            for (String[] electricDataDetails : electricData) {
                float electricPrice = Float.parseFloat(electricDataDetails[1]);
                String time = electricDataDetails[0];

                electricDataSorted.add(new TimePriceObject(time, electricPrice));

            }

            Collections.sort(electricDataSorted, Comparator.comparing(TimePriceObject::getPrice));


            for (TimePriceObject object : electricDataSorted) {
                System.out.println("Tid:" + object.getTime() + "Pris:" + object.getPrice() + "öre/kWh");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fel vid hätmnigen av data!" + e);

        }
        returnToMenu();

    }

    static void bestChargingTime() {
        try {
            List<TimePriceObject> electricPriceList = new ArrayList<>();

            List<String[]> electricData = getCSVFile();
            for (String[] electricDataDetails : electricData) {
                float electricPrice = Float.parseFloat(electricDataDetails[1]);
                electricPriceList.add(new TimePriceObject(electricDataDetails[0], electricPrice));
            }
            int windowSize = 4;
            float minAvgPrice = Float.MAX_VALUE;
            String bestStartTime = null;

            for (int i = 0; i <= electricPriceList.size() - windowSize; i++) {
                float totalPrice = 0;

                for (int j = 0; j < windowSize; j++) {
                    totalPrice += electricPriceList.get(i + j).getPrice();

                }
                float avgPrice = totalPrice / windowSize;
                if (avgPrice < minAvgPrice) {
                    minAvgPrice = avgPrice;
                    bestStartTime = electricPriceList.get(i).getTime();


                }

            }
            ;
            System.out.println("Bästa starttid för laddning är mellan" + bestStartTime + "och 4 timmar framåt: ");
            System.out.println("Medelrpriset för dessa 4 timmar är: " + minAvgPrice + " öre/kWh");


        } catch (Exception e) {

            e.printStackTrace();

        }
        returnToMenu();
    }


    static class TimePriceObject {
        String time;
        float price;

        public TimePriceObject(String time, float price) {
            this.time = time;
            this.price = price;
        }

        public String getTime() {
            return time;
        }

        public float getPrice() {
            return price;
        }

    }


}
