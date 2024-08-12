package com.example.demo2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;


import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.DayOfWeek;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

// ToolRentalSystem Class
public class ToolRentalSystem {
    private List<Tool> tools;
    private List<Customer> customers;
    private List<RentalFee> rentalFees;
    private List<Rental> rentals;

    public ToolRentalSystem() {
        tools = new ArrayList<>();
        customers = new ArrayList<>();
        rentalFees = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addTool(Tool tool) {
        tools.add(tool);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addRentalFee(RentalFee rentalFee) {
        rentalFees.add(rentalFee);
    }

    private Customer findCustomerById(String id) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    public List<Tool> getTools() {
        return tools;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public static class OrderDetails extends Application {

        private DatePicker datePicker;
        private ComboBox<String> itemIdField;
        private ComboBox<String> customerIdField;
        private TextField discountPercentField;
        private TextField daysToRentField;
        private Button placeOrderButton;

        @Override
        public void start(Stage primaryStage) {
            ToolRentalSystem system;

            system = new ToolRentalSystem();
            system.addTool(new Tool("CHNS", "Chainsaw", "Stihl"));
            system.addTool(new Tool("LADW", "Ladder", "Werner"));
            system.addTool(new Tool("JAKD", "Jackhammer", "DeWalt"));
            system.addTool(new Tool("JAKR", "Jackhammer", "Ridgid"));
            system.addCustomer(new Customer("C001", "Alice"));
            system.addCustomer(new Customer("C002", "Bob"));
            system.addRentalFee(new RentalFee("Ladder", 1.99F, true, true, false));
            system.addRentalFee(new RentalFee("Chainsaw", 1.49F, true, false, true));
            system.addRentalFee(new RentalFee("Jackhammer", 2.99F, true, false, false));

            // Initialize components
            datePicker = new DatePicker();
            itemIdField = new ComboBox<>();
            customerIdField = new ComboBox<>();
            discountPercentField = new TextField();
            daysToRentField = new TextField();
            placeOrderButton = new Button("Place Order");

            datePicker.setValue(LocalDate.now());
            discountPercentField.setText("0");
            daysToRentField.setText("1");

            for (Tool tool : system.tools) {
                itemIdField.getItems().add(tool.getToolCode());
            }

            for (Customer cust : system.customers){
                customerIdField.getItems().add(cust.getId());
            }

            // Set up event handling for the button
            placeOrderButton.setOnAction(event -> placeOrder(system));

            // Create layout and add components
            GridPane gridPane = new GridPane();
            gridPane.setVgap(10);
            gridPane.setHgap(10);

            gridPane.add(new Label("Select Date:"), 0, 0);
            gridPane.add(datePicker, 1, 0);

            gridPane.add(new Label("Item ID:"), 0, 1);
            gridPane.add(itemIdField, 1, 1);

            gridPane.add(new Label("Customer ID:"), 0, 2);
            gridPane.add(customerIdField, 1, 2);

            gridPane.add(new Label("Discount Percent:"), 0, 3);
            gridPane.add(discountPercentField, 1, 3);

            gridPane.add(new Label("Days to Rent:"), 0, 4);
            gridPane.add(daysToRentField, 1, 4);

            gridPane.add(placeOrderButton, 1, 5);

            // Set up the scene and stage
            Scene scene = new Scene(gridPane, 400, 250);
            primaryStage.setTitle("Order Details");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        private void placeOrder(ToolRentalSystem rentalSystem) {

            // Retrieve input values
            String date = datePicker.getValue() != null ? datePicker.getValue().toString() : "No date selected";
            LocalDate startDate = datePicker.getValue();
            LocalDate endDate = datePicker.getValue();
            String DOW = startDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
            WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 1);
            TemporalField weekOfMonth = weekFields.weekOfMonth();
            int weekOfMonthValue = startDate.get(weekOfMonth);
            Calendar calendar = Calendar.getInstance();

            String itemId = itemIdField.getValue();
            String itemToolType = "";
            String itemBrand = "";
            String customerName = "";
            Float dailyCharge = 0.00F; // from table
            Float invoiceDailyCharge = 0.00F;
            Float totalCharge = 0.00F; // invoice total
            Float afterDiscountTotal = 0.00F;
            boolean weekdayCharge = false;
            boolean weekendCharge = false;
            boolean holidayCharge = false;
            int chargeDays = 0;
            //RentalAgreementInfo rentalAgreementInfo = null;

            String customerId = customerIdField.getValue();
            //rentalAgreementInfo.setCustId(customerId);
            String discountPercentStr = discountPercentField.getText();
            String daysToRent = daysToRentField.getText();

            // Validate inputs
            String validationError = validateOrder(itemId, customerId, discountPercentStr, daysToRent);
            if (validationError != null) {
                showError(validationError);
                return;
            }


            int rentalDays = (int) Double.parseDouble(daysToRent);
            float discountPct = (float) Double.parseDouble(discountPercentStr);

            if (rentalDays > 1){
                endDate = startDate.plusDays((long) rentalDays -1);
            }

            for (Tool t : rentalSystem.tools){
                if(t.getToolCode() == itemId){
                    itemToolType = t.getToolType();
                    itemBrand = t.getToolBrand();
                }
            }

            for (Customer c : rentalSystem.customers){
                if(c.getId() == customerId){
                    customerName = c.getName();
                }
            }

            for (RentalFee rf : rentalSystem.rentalFees)
                if(rf.ToolType == itemToolType){
                    dailyCharge = rf.getDailyCharge();
                    weekdayCharge = rf.isWeekdayCharge();
                    weekendCharge = rf.isWeekendCharge();
                    holidayCharge = rf.isHolidayCharge();
                }

            LocalDate currentDate = startDate;
            while(!currentDate.isAfter(endDate)){

                if (holidayCharge && isHoliday(currentDate)){
                    invoiceDailyCharge = dailyCharge;
                    chargeDays +=1;
                } else if (weekdayCharge && isWeekday(currentDate)){
                    invoiceDailyCharge = dailyCharge;
                    chargeDays +=1;
                } else if (weekendCharge && !isWeekday(currentDate)){
                    invoiceDailyCharge = dailyCharge;
                    chargeDays +=1;
                } else {
                    invoiceDailyCharge = 0F;
                }
                totalCharge += invoiceDailyCharge;

                currentDate = currentDate.plusDays(1);
            }

            afterDiscountTotal = (float) (totalCharge - (totalCharge * discountPct/100));

            RentalAgreement(customerId, customerName,
                                itemId, itemToolType, itemBrand,
                                dailyCharge, rentalDays, startDate,
                                chargeDays, totalCharge, discountPct, (totalCharge * discountPct/100),
                                afterDiscountTotal);


            // Handle order placement logic here
            System.out.println("Order placed with the following details:");
            System.out.println("Date: " + date);
            System.out.println("Day of Week: " + DOW);
            System.out.println("Item ID: " + itemId);
            System.out.println("Customer ID: " + customerId);
            System.out.println("Days to Rent: " + daysToRent);
            System.out.println("Discount Percent: " + discountPercentStr);
            System.out.println("Tool Type: " + itemToolType);
            System.out.println("Tool Brand: " + itemBrand);
            System.out.println("Daily Charge: " + dailyCharge);
            System.out.println("Weekday Charge: " + weekdayCharge);
            System.out.println("Weekend Charge: " + weekendCharge);
            System.out.println("Holiday Charge: " + holidayCharge);
            System.out.println("Week Of Month: " + weekOfMonthValue);
            System.out.println("Month: " + startDate.getMonth());
            System.out.println("Month Value: " + startDate.getMonthValue());
        }

        private String validateOrder(String itemId, String customerId, String discountPercentStr, String daysToRent){

            // Validate Item
            if(itemId == null){
                return "Please select an Item";
            }

            // Validate Customer
            if(customerId == null){
                return "Please select a Customer";
            }

            // Validate days to rent
            try {
                double rentalDays = Double.parseDouble(daysToRent);
                if (rentalDays <= 0 ) {
                    return "Rental days must be > 0";
                }
            } catch (NumberFormatException e) {
                return "Rental days must be a valid number.";
            }

            // Validate discount percent
            try {
                double discountPercent = Double.parseDouble(discountPercentStr);
                if (discountPercent < 0 || discountPercent > 100) {
                    return "Discount percent must be between 0 and 100.";
                }
            } catch (NumberFormatException e) {
                return "Discount percent must be a valid number.";
            }
        return null;
        }

        private void showError(String message) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

        public static boolean isHoliday(@NotNull LocalDate inDate){
            int month = inDate.getMonthValue();
            int day = inDate.getDayOfMonth();
            String DOW = inDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());

            if (month == 7){
                if (day == 4){
                    return true;
                } else if (day == 3 && DOW == "Friday") {
                    return true;
                } else if (day == 5 && DOW == "Monday"){
                    return true;
                } else {
                    return false;
                }
            }
           if (month == 9){
                return (day <= 7) && (DOW == "Monday"); // assumes 1st Monday of September - Labor Day
            }
            return false;
        }

        public static boolean isWeekday(@NotNull LocalDate inDate){
            DayOfWeek DOW = inDate.getDayOfWeek();

            return DOW != DayOfWeek.SATURDAY && DOW != DayOfWeek.SUNDAY;
        }

        // RentalAgreement builds a second window with the Agreement info
        private void RentalAgreement(String customerID, String customerName,
                                    String toolCode, String toolType, String toolBrand,
                                    float dailyRentalFee, int rentalDays, LocalDate checkoutDate,
                                    int chargeDays, float preDiscountCharge, float discountPct, float discountAmt,
                                    float finalCharge){


            // Create layout and add components
            Stage secondaryStage = new Stage();
            GridPane gridPane2 = new GridPane();
            gridPane2.setVgap(10);
            gridPane2.setHgap(10);

            Label stringLabel = new Label(toolCode);
            gridPane2.add(new Label("Tool Code: "), 0, 0);
            gridPane2.add(stringLabel, 1, 0);

            stringLabel = new Label(toolType);
            gridPane2.add(new Label("Tool Type: "), 0, 1);
            gridPane2.add(stringLabel, 1, 1);

            stringLabel = new Label(toolBrand);
            gridPane2.add(new Label("Tool Brand: "), 0, 2);
            gridPane2.add(stringLabel, 1, 2);

            stringLabel = new Label(Integer.toString(rentalDays));
            gridPane2.add(new Label("Number of Rental Days: "), 0, 3);
            gridPane2.add(stringLabel, 1, 3);

            stringLabel = new Label(checkoutDate.toString());
            gridPane2.add(new Label("Checkout Date: "), 0, 4);
            gridPane2.add(stringLabel, 1, 4);

            stringLabel = new Label(checkoutDate.plusDays(rentalDays).toString());
            gridPane2.add(new Label("Return Date: "), 0, 5);
            gridPane2.add(stringLabel, 1, 5);

            stringLabel = new Label(checkoutDate.plusDays(rentalDays).toString());
            gridPane2.add(new Label("Return Date: "), 0, 6);
            gridPane2.add(stringLabel, 1, 6);

            stringLabel = new Label(Integer.toString(chargeDays));
            gridPane2.add(new Label("Charge Days: "), 0, 7);
            gridPane2.add(stringLabel, 1, 7);

            String holdString = String.format("%.2f", preDiscountCharge);
            stringLabel = new Label(holdString);
            gridPane2.add(new Label("Subtotal: "), 0, 8);
            gridPane2.add(stringLabel, 1, 8);

            holdString = String.format("%.2f",discountPct);
            stringLabel = new Label(holdString);
            gridPane2.add(new Label("Discount %: "), 0, 9);
            gridPane2.add(stringLabel, 1, 9);

            holdString = String.format("%.2f",discountAmt);
            stringLabel = new Label(holdString);
            gridPane2.add(new Label("Discount Amount: "), 0, 10);
            gridPane2.add(stringLabel, 1, 10);

            holdString = String.format("%.2f",finalCharge);
            stringLabel = new Label(holdString);
            gridPane2.add(new Label("Final Amount: "), 0, 11);
            gridPane2.add(stringLabel, 1, 11);

            
            // Set up the scene and stage
            Scene scene2 = new Scene(gridPane2, 400, 400);
            secondaryStage.setTitle("Rental Agreement");
            secondaryStage.setScene(scene2);
            secondaryStage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }

    }
}