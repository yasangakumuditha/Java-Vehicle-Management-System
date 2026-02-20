import java.util.ArrayList;
import java.util.Scanner;

public class RentalService{
    private ArrayList<Vehicle> vehicles=new ArrayList<>();//Array to store all vehicles
    private double totalIncome=0.0;//to store all rental income
    private static final String AdminPass="Admin123";

    //ADMIN MODE
    public static void adminMode(Scanner scanner,RentalService service){

        System.out.print("Enter Password:");
        String password=scanner.next();

        if(!password.equals(AdminPass)){
            System.out.println("ACCESS DENIED");
            return;

        }
        System.out.println("ACCESS GRANTED");

        while (true){
            System.out.println("\n===== ADMIN MODE =====");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Remove Vehicle");
            System.out.println("3. View All Vehicles");
            System.out.println("4. Back");

            System.out.println("Choose an option:");
            int choice = Main.readInt(scanner);

            switch(choice){
                case 1->service.addVehicle(scanner);
                case 2->service.removeVehicle(scanner);
                case 3->service.viewVehicles();
                case 4->{return;}
                default -> System.out.println("INVALID OPTION");
            }
        }
    }
    //USER MODE
    public static void userMode(Scanner scanner,RentalService service){

        while(true){
            System.out.println("\n=== USER MODE ===");
            System.out.println("1. View All Vehicles");
            System.out.println("2. Rent Vehicle");
            System.out.println("3. Return Vehicle");
            System.out.println("4. View Total Rental Income");
            System.out.println("5. Search Vehicle By ID");
            System.out.println("6. Back");

            System.out.println("Choose an Option:");
            int choice=Main.readInt(scanner);

            switch(choice){
                case 1->service.viewVehicles();
                case 2->service.rentVehicle(scanner);
                case 3->service.returnVehicle(scanner);
                case 4->service.viewRentalIncome(scanner);
                case 5->service.searchByvehcileID(scanner);
                case 6->{
                    return;
                }
                default -> System.out.println("INVALID OPTION");
            }
        }
    }
    //ADD VEHICLE
    public void addVehicle(Scanner scanner){
        try {
            System.out.println("\n 1.Car 2.Bike 3.Van");
            int type = Main.readInt(scanner);

            System.out.print("Vehicle ID:");
            String vehicleID = scanner.next();

            for (Vehicle v : vehicles) {
                if (v.getVehicleID().equals(vehicleID)) {
                    System.out.println("ID ALREADY EXISTS");
                    return;
                }
            }
            System.out.print("Brand:");
            String brand = scanner.next();
            System.out.print("Model:");
            String model = scanner.next();
            System.out.print("Base Rate per Day:");
            double baseRatePerDay = scanner.nextDouble();

            Vehicle newV = null;

            if (type == 1) {
                System.out.print("Number of Seats:");
                int seats = scanner.nextInt();
                newV = new Car(vehicleID, brand, model, baseRatePerDay, seats);
            } else if (type == 2) {
                System.out.print("Engine CC:");
                int EngineCC = scanner.nextInt();
                newV = new Bike(vehicleID, brand, model, baseRatePerDay, EngineCC);
            } else if (type == 3) {
                System.out.print("Cargo Capacity:");
                double cargoCapacity = scanner.nextDouble();
                newV = new Van(vehicleID, brand, model, baseRatePerDay, cargoCapacity);
            }
            vehicles.add(newV);
            System.out.println("VEHICLE ADDED SUCCESSFULLY");

        } catch (Exception e) {
            //Handle invalid input
            System.out.println("INVALID INPUT, Pleas Enter Correct Data");
            scanner.nextLine();
        }
    }
    //REMOVE VEHICLE
    public void removeVehicle(Scanner scanner){
        System.out.print("Enter Vehicle ID to remove:");
        String vehicleID=scanner.next();

        boolean found=false;
        for(Vehicle v:vehicles){
            if(v.getVehicleID().equalsIgnoreCase(vehicleID)){
                vehicles.remove(v);
                found=true;
                break;
            }
        }
        if(found){
            System.out.println("VEHICLE REMOVED SUCCESSFULLY");
        }else{
            System.out.println("VEHICLE NOT FOUND");
        }

    }
    //VIEW VEHICLE
    public void viewVehicles(){
        if(vehicles.isEmpty()){
            System.out.println("NO VEHICLES AVAILABLE!");
            return;
        }
        System.out.println("====VEHICLE LIST====");
        for (Vehicle v:vehicles){
            v.displayDetails();//polymorphism used
        }
    }
    //RENT VEHICLE
    public void rentVehicle(Scanner scanner) {
        System.out.print("Enter Vehicle ID:");
        String vehicleID=scanner.next();

        for (Vehicle v:vehicles){
            if(v.getVehicleID().equals(vehicleID)){
                if(!v.isAvailable()){
                    System.out.println("VEHICLE IS ALREADY RENTED");
                    return;
                }
                System.out.print("Days:");
                int days=scanner.nextInt();

                if(days<=0){
                    System.out.println("Invalid Days");
                    return;
                }
                double Cost=v.calculateRentalCost(days);//polymorphic method
                v.addIncome(Cost);
                totalIncome+=Cost;
                v.rentVehicle();

                System.out.println("VEHICLE RENTED SUCCESSFULLY, Cost for the vehicle is Rs."+Cost);
                return;

            }
        }
        System.out.println("VEHICLE NOT FOUND");
    }
    //RETURN VEHICLE
    public void returnVehicle(Scanner scanner) {
        System.out.print("Enter Vehicle ID:");
        String vehicleID=scanner.next();

        for(Vehicle v:vehicles){
            if(v.getVehicleID().equals(vehicleID)){
                v.returnVehicle();
                System.out.println("VEHICLE RETURNED");
                return;
            }
        }
        System.out.println("VEHICLE NOT FOUND");
    }
    //VIEW RENTAL INCOME
    public void viewRentalIncome(Scanner scanner){

        if(vehicles.isEmpty()){
            System.out.println("NO VEHICLES AVAILABLE!");
            return;
        }
        System.out.println("\nEnter Vehicle ID");
        String vehicleID=scanner.next();

        for(Vehicle v:vehicles){
            if(v.getVehicleID().equals(vehicleID)){
                System.out.println("\n===VEHICLE RENTAL INCOME REPORT===");
                System.out.println("-----------------------------------------");
                System.out.println("Vehicle ID:"+v.getVehicleID());
                System.out.println("Brand     :"+v.getBrand());
                System.out.println("Model     : "+v.getModel());
                System.out.println("Total Income from the vehicle:"+v.getTotalRentIncome());
                System.out.println("-----------------------------------------");
                return;

            }
        }
        System.out.println("VEHICLE ID NOT FOUND");
    }
    //SEARCH VEHICLE BY ID
    public void searchByvehcileID(Scanner scanner){
        System.out.print("Enter Vehicle ID to search:");
        String vehicleID=scanner.next();

        for (Vehicle v:vehicles){
            if(v.getVehicleID().equalsIgnoreCase(vehicleID)){
                System.out.println("\nVehicle Found");
                System.out.println("------------------");
                v.displayDetails();
                return;
            }
        }
        System.out.println("VEHICLE NOT FOUND!");
    }
}