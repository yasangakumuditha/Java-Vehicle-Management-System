import java.util.Scanner;

public class Main{
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);//to read user inputs
        RentalService service=new RentalService();//to manage system operations

            while (true) {
                System.out.println("====VEHICLE RENTAL SYSTEM CLI====");
                System.out.println("1.Admin Mode");
                System.out.println("2.User Mode");
                System.out.println("3.Exit");
                System.out.println("SELECT MODE:");

                int choice = readInt(scanner);
                switch (choice) {
                    case 1 -> RentalService.adminMode(scanner, service);
                    case 2 -> RentalService.userMode(scanner, service);
                    case 3 -> {
                        System.out.println("GOOD BYE!");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("INVALID OPTION");
                }
            }
    }
    public static int readInt(Scanner scanne){
        int number;
        while(true){
            try{
                number=scanne.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("ENTER VALID NUMBER");
                scanne.nextLine();//clear the  invalid input
            }
        }
        return number;
    }
}