package com.example.demo2;

import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class RentalCalculations {


    public RentalCalculations(RentalAgreementInfo rentalAgreementInfo){

        LocalDate endDate = rentalAgreementInfo.getStartDate();
        endDate = endDate.plusDays((long) rentalAgreementInfo.getRentalDays() - 1);
        Float invoiceDailyCharge = 0.00F;
        Float totalCharge = 0.00F; // invoice total
        Float afterDiscountTotal = 0.00F;
        int chargeDays = 0;


        LocalDate currentDate = rentalAgreementInfo.getStartDate();
        while(!currentDate.isAfter(endDate)){

            if (rentalAgreementInfo.isHolidayCharge() && ToolRentalSystem.OrderDetails.isHoliday(currentDate)){
                invoiceDailyCharge = rentalAgreementInfo.getDailyRentalFee();
                chargeDays +=1;
            } else if (rentalAgreementInfo.isWeekdayCharge() && ToolRentalSystem.OrderDetails.isWeekday(currentDate)){
                invoiceDailyCharge = rentalAgreementInfo.getDailyRentalFee();
                chargeDays +=1;
            } else if (rentalAgreementInfo.isWeekendCharge() && !ToolRentalSystem.OrderDetails.isWeekday(currentDate)){
                invoiceDailyCharge = rentalAgreementInfo.getDailyRentalFee();
                chargeDays +=1;
            } else {
                invoiceDailyCharge = 0F;
            }
            totalCharge += invoiceDailyCharge;

            currentDate = currentDate.plusDays(1);
        }

        afterDiscountTotal = (float) (totalCharge - (totalCharge * rentalAgreementInfo.getDiscountPct()/100));
        rentalAgreementInfo.setAfterDiscountTotal(afterDiscountTotal);
        rentalAgreementInfo.setTotalCharge(totalCharge);
        rentalAgreementInfo.setChargeDays(chargeDays);
        rentalAgreementInfo.setDiscountAmt(totalCharge * rentalAgreementInfo.getDiscountPct()/100);


    }
}
