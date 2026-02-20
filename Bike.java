public class Bike extends Vehicle{
    private final int engineCapacityCC;

    public Bike(String vehicleID,String brand,String model,double baseRatePerDay,int engineCapacityCC){
        super(vehicleID,brand,model,baseRatePerDay);
        this.engineCapacityCC=engineCapacityCC;
    }
    //overridden method to calculate rent
    public double calculateRentalCost(int days){
        return getBaseRatePerDay()*days+(engineCapacityCC*0.5*days);
    }
    //overridden method used to get bike details from vehicle
    public void displayDetails(){
        super.displayDetails();
        System.out.println("Bike Engine CC:"+engineCapacityCC);
        System.out.println("-----------------------");

    }
}
