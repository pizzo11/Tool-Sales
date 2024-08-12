package com.example.demo2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

    public class OrderDetailsTest {

        private ToolRentalSystem toolRentalSystem;

        @BeforeEach
        public void setUp() {
            toolRentalSystem = new ToolRentalSystem();
        }

        @Test
        public void testValidateOrder_AllValidInputs() {
            String daysToRent = "5";
            String itemId = "CHNS";
            String customerId = "C001";
            String discountPercent = "10";

            String result = ToolRentalSystem.OrderDetails.validateOrder(itemId, customerId, discountPercent, daysToRent);
            assertNull(result, "Validation should pass with all valid inputs.");
        }

        @Test
        public void testValidateOrder_InvalidDiscountPercent() {
            String daysToRent = "5";
            String itemId = "CHNS";
            String customerId = "C001";
            String discountPercent = "101";

            String result = ToolRentalSystem.OrderDetails.validateOrder(itemId, customerId, discountPercent, daysToRent);
            assertEquals("Discount percent must be between 0 and 100.", result);
        }

        @Test
        public void testValidateOrder_NoItemSelected() {
            String daysToRent = "5";
            String itemId = null;
            String customerId = "C001";
            String discountPercent = "10";

            String result = ToolRentalSystem.OrderDetails.validateOrder(itemId, customerId, discountPercent, daysToRent);
            assertEquals("Please select an Item", result);
        }

        @Test
        public void testValidateOrder_NoCustomerSelected() {
            String daysToRent = "5";
            String itemId = "CHNS";
            String customerId = null;
            String discountPercent = "10";

            String result = ToolRentalSystem.OrderDetails.validateOrder(itemId, customerId, discountPercent, daysToRent);
            assertEquals("Please select a Customer", result);
        }

        @Test
        public void testValidateOrder_InvalidDaysToRent() {
            String daysToRent = "0";
            String itemId = "CHNS";
            String customerId = "C001";
            String discountPercent = "10";

            String result = ToolRentalSystem.OrderDetails.validateOrder(itemId, customerId, discountPercent, daysToRent);
            assertEquals("Rental days must be > 0", result);
        }

        @Test
        public void testValidateOrder_InvalidDaysToRent2() {
            String daysToRent = "A";
            String itemId = "CHNS";
            String customerId = "C001";
            String discountPercent = "10";

            String result = ToolRentalSystem.OrderDetails.validateOrder(itemId, customerId, discountPercent, daysToRent);
            assertEquals("Rental days must be a valid number.", result);
        }
//
//        @Test
//        public void testValidateOrder_NonNumericDiscountPercent() {
//            String date = "2024-08-10";
//            String itemId = "Item1";
//            String customerId = "Customer1";
//            String discountPercent = "abc";
//
//            String result = ToolRentalSystem.orderDetails.validateOrder(date, itemId, customerId, discountPercent);
//            assertEquals("Discount percent must be a valid number.", result);
//        }
//
//        @Test
//        public void testValidateOrder_MissingDate() {
//            String date = null;
//            String itemId = "Item1";
//            String customerId = "Customer1";
//            String discountPercent = "10";
//
//            String result = ToolRentalSystem.orderDetails.validateOrder(date, itemId, customerId, discountPercent);
//            assertEquals("Please select a valid date.", result);
//        }
//
//        @Test
//        public void testValidateOrder_MissingItem() {
//            String date = "2024-08-10";
//            String itemId = null;
//            String customerId = "Customer1";
//            String discountPercent = "10";
//
//            String result = ToolRentalSystem.orderDetails.validateOrder(date, itemId, customerId, discountPercent);
//            assertEquals("Please select a valid item.", result);
//        }
//
//        @Test
//        public void testValidateOrder_MissingCustomer() {
//            String date = "2024-08-10";
//            String itemId = "Item1";
//            String customerId = null;
//            String discountPercent = "10";
//
//            String result = orderDetails.validateOrder(date, itemId, customerId, discountPercent);
//            assertEquals("Please select a valid customer.", result);
//        }
    }

