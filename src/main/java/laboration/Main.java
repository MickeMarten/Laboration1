package laboration;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)  {



        System.out.print("Welcome to 'Electric prices today co'. \n Elpriser \n ========= \n 1.Se elpriser idag \n 2.Min, Max och Medel \n 3.Sortera \n 4.Bästa Laddningstid (4) \n e. Avsluta \n");
        System.out.print("Skriv ditt val här:");
        String userInput = sc.nextLine();



    switch (userInput){
        case "1":
            ElectricPriceToday();
        break;

        case "2":
            ComparePrices();
        break;

        case "3": System.out.println("test 3");
        break;

        case "4": System.out.println("test 4");
        break;

        case "e":
        case"E":
            System.out.println("test e");
        break;
        default:
            System.out.println("Fel inmatning");
        break;
    }

    }

    static public void ElectricPriceToday(){
        String file = "elpriser.csv";
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


    static public void ComparePrices(){
        String file = "elpriser.csv";
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
        System.out.println("Tryck på valfri tanget för att återgå till huvudmenyn.");
        sc.nextLine();

    }




}
