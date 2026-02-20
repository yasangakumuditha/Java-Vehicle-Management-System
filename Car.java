public class Car extends Vehicle{
    private final int numberOfSeats;

    public Car(String vehicleID,String brand,String model,Double baseRatePerDay,int numberOfSeats){
        super(vehicleID,brand,model,baseRatePerDay);
        this.numberOfSeats=numberOfSeats;

    }
    //overridden method to calculate rent
    public double calculateRentalCost(int days){
        return getBaseRatePerDay()*days + (numberOfSeats*200*days);
    }

    //overridden method used to get bike details from vehicle
    public void displayDetails(){
        super.displayDetails();
        System.out.println("Car Seats:"+numberOfSeats);
        System.out.println("-----------------------");

    }
}
