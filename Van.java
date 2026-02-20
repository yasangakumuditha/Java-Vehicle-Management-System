public class Van extends Vehicle {
    private final double cargoCapacityKg;

    public Van(String vehicleID,String brand,String model,double baseRatePerDay,double cargoCapacityKg){
        super(vehicleID,brand,model,baseRatePerDay);
        this.cargoCapacityKg=cargoCapacityKg;
    }
    //overridden method to calculate rent
    public double calculateRentalCost(int days){
        return getBaseRatePerDay()*days+(cargoCapacityKg*0.2*days);
    }

    //overridden method used to get bike details from vehicle
    public void displayDetails(){
        super.displayDetails();
        System.out.println("Van Cargo Capacity:"+cargoCapacityKg+"Kg");
        System.out.println("-----------------------");
    }
}
