package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.CustomerModel;
import ca.ubc.cs304.model.TimeInterval;
import ca.ubc.cs304.model.VehicleModel;

public class CustomerHandler {

    private DatabaseConnectionHandler cdh;

    public CustomerHandler (DatabaseConnectionHandler cdh) {
        this.cdh = cdh;
    }

    // any of the arguments can be null
    public int viewNumberOfVehicles(String carType, String location, TimeInterval timeInterval) {
        int numberOfVehicles = cdh.numberOfVehiclesNotRented(carType, location, timeInterval);
        if (timeInterval != null) {
            int numberOfReservedVehicles = cdh.numberOfReservedVehicles(carType, timeInterval);
            if (numberOfVehicles <= numberOfReservedVehicles) {
                return 0;
            }
        }
        return numberOfVehicles;
    }

    // any of the arguments can be null
    public VehicleModel[] viewVehicles(String carType, String location, TimeInterval timeInterval) {
        return cdh.getVehicles(carType, location, timeInterval);
    }

    public void addCustomerToDatabase(String dlicense, String cellphone, String name, String address) {
        CustomerModel model = new CustomerModel(dlicense, cellphone, name, address);
        cdh.insertCustomer(model);
    }

    public boolean isCustomerInDatabase() {
        CustomerModel[] models = cdh.getCustomerInfo();
        if (models.length == 0) {
            return false;
        } else {
            return true;
        }
    }

    public double estimateCost(String carType) {
        return -1;
    }

    // Returns the confno of the created reservation
    //
    public String makeReservation(String location, String carType, String name, String cellphone, TimeInterval timeInterval) {
        return null;
    }
}
