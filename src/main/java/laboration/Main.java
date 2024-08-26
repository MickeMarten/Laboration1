package laboration;
import java.util.*;
import java.util.Collections;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)  {
        boolean userOnline = true;
         String file = "elpriser.csv";
        while (userOnline) {


        System.out.print("Välkommen till 'El-Priser idag co'. \n Elpriser \n ========= \n 1.Se elpriser idag \n 2.Min, Max och Medel \n 3.Sortera \n 4.Bästa Laddningstid (4) \n e. Avsluta \n");
        System.out.print("Skriv ditt val här:");
        String userInput = sc.nextLine();


        switch (userInput){
        case "1":
            electricPricesToday(file);
            break;

        case "2":
            comparePrices(file);
        break;

        case "3":
            sortCheapestToMostExpensive(file);
        break;

        case "4": System.out.println("test 4");
        break;

        case "e":
        case"E":
            System.out.println("Ha en trevlig dag.");
            userOnline = false;
        break;
        default:
            System.out.println("Fel inmatning");
        break;
    }
    }

    }

     static void electricPricesToday(String file){
        try {


            List<String[]> electricData = ElectricPriceService.ScvReader(file);
                    for(String[] electricDataDetails : electricData){
                        float price = Float.parseFloat(electricDataDetails[1]);
                        System.out.println("Klockan:" + electricDataDetails[0] + "\t" + "Pris:"  + price);
                    }

        }  catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fel vid hämtning av data");
        }
        System.out.println("Tryck på valfri tangent för att återgå till huvudmenyn");
        sc.nextLine();


    }


     static void comparePrices(String file){
        try{


            List<String[]> electricData = ElectricPriceService.ScvReader((file));
            float lowestPrice = Float.MAX_VALUE;
            float highestPrice = Float.MIN_VALUE;
            String highestPriceTime = "";
            String lowestPriceTime = "";
            float priceSummary = 0;
            for(String[] electricDataDetails : electricData){
                float electricPrice = Float.parseFloat(electricDataDetails[1]);
                if(electricPrice < lowestPrice){
                    lowestPrice = electricPrice;
                    lowestPriceTime = electricDataDetails[0];
                }
                if(electricPrice > highestPrice){
                    highestPrice = electricPrice;
                    highestPriceTime = electricDataDetails[0];

                }


                priceSummary += electricPrice / 24;

            }
            System.out.println("Bäst tid att använda el:" + lowestPriceTime + "Priset är då:" + lowestPrice + "öre per KWh" );
            System.out.println("Sämsta tid att använda el:" + highestPriceTime + "Priset är då:" + highestPrice + "öre per KWh" );
            System.out.println("Medelvärdet på dygnet är:" + priceSummary + "öre per KWh");
        } catch (Exception e){
            System.out.println("Fel vid hätmnigen av data!" + e);

        }


    }

     static void sortCheapestToMostExpensive(String file){
        try {

        List<String[]> electricData = ElectricPriceService.ScvReader((file));
        List<TimePriceObject> electricDataSorted = new ArrayList<>();


        for(String[] electricDataDetails : electricData){
            float electricPrice = Float.parseFloat(electricDataDetails[1]);
            String time = electricDataDetails[0];

            electricDataSorted.add(new TimePriceObject(time, electricPrice));

        }

            Collections.sort(electricDataSorted, Comparator.comparing(TimePriceObject::getPrice));


            for (TimePriceObject object : electricDataSorted) {
                System.out.println("Tid:" + object.getTime() + "Pris:" + object.getPrice());
            }

            System.out.println("Tryck på valfri tanget för att återgå till huvudmenyn.");
            sc.nextLine();
        } catch(Exception e){
            e.printStackTrace();
        }



    }
    static void bestChargingTime(String file){
        try {

        int k = 4;
        List<String[]> electricData = ElectricPriceService.ScvReader((file));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


     static class TimePriceObject {
        String time;
        float price;

        public TimePriceObject(String time, float price){
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
