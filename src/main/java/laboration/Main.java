package laboration;
import java.io.File;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)  {


        System.out.print("Welcome to 'Electric prices today co'. \n Elpriser \n ========= \n 1.Se elpriser idag \n 2.Min, Max och Medel \n 3.Sortera \n 4.Bästa Laddningstid (4) \n e. Avsluta \n");
        System.out.print("Välj ditt val här:");
        String userInput = sc.nextLine();



    switch (userInput){
        case "1":
            ElectricPriceToday(); ;
        break;

        case "2": System.out.println("test 2");;
        break;

        case "3": System.out.println("test 3"); ;
        break;

        case "4": System.out.println("test 4");;
        break;

        case "e":
        case"E":
            System.out.println("test e");;
        break;
        default:
            System.out.println("Fel inmatning");
        break;
    }

    }

    static public void ElectricPriceToday(){
        try {
            ElectricPriceService.ScvReader();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Tryck på valfri tangent för att återgå till huvudmenyn");
        sc.nextLine();


    }


}
