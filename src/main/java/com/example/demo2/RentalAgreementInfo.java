package com.example.demo2;

import java.time.LocalDate;

// Rental Agreement Info
public class RentalAgreementInfo {

    private String itemId;
    private String custId;
    private String itemToolType;
    private String itemToolBrand;
    private String customerName;
    private Float dailyRentalFee;
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;
    private LocalDate startDate;
    private int rentalDays;
    private int chargeDays;
    private Float totalCharge;
    private Float discountPct;
    private Float discountAmt;
    private Float afterDiscountTotal;

    public RentalAgreementInfo( /*String itemId,
                                String custId,
                                String itemToolType,
                                String itemToolBrand,
                                String customerName,
                                Float dailyRentalFee,
                                boolean weekdayCharge,
                                boolean weekendCharge,
                                boolean holidayCharge,
                                LocalDate startDate,
                                int rentalDays,
                                int chargeDays,
                                Float totalCharge,
                                Float discountPct,
                                Float discountAmt,
                                Float afterDiscountTotal*/
                                ) {
        /*this.itemId = itemId;
        this.custId = custId;
        this.itemToolType = itemToolType;
        this.itemToolBrand = itemToolBrand;
        this.customerName = customerName;
        this.dailyRentalFee = dailyRentalFee;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
        this.startDate = startDate;
        this.rentalDays = rentalDays;
        this.chargeDays = chargeDays;
        this.totalCharge = totalCharge;
        this.discountPct = discountPct;
        this.discountAmt = discountAmt;
        this.afterDiscountTotal = afterDiscountTotal;*/
    }

    public String getItemId() {
        return itemId;
    }
    public String getCustId() {
        return custId;
    }
    public String getItemToolType() {
    return itemToolType;
}
    public String getItemToolBrand() {
        return itemToolBrand;
    }
    public String getCustomerName() {
        return customerName;
    }
    public Float getDailyRentalFee() { return dailyRentalFee; }
    public boolean isWeekdayCharge() { return weekdayCharge; }
    public boolean isWeekendCharge() {
        return weekendCharge;
    }
    public boolean isHolidayCharge() {
        return holidayCharge;
    }
    public LocalDate getStartDate() { return startDate; }
    public int  getRentalDays() {
        return rentalDays;
    }
    public int getChargeDays() {
        return chargeDays;
    }
    public Float getTotalCharge() {
        return totalCharge;
    }
    public Float getDiscountPct() {
        return discountPct;
    }
    public Float getDiscountAmt() {
        return discountAmt;
    }
    public Float getAfterDiscountTotal() {
        return afterDiscountTotal;
    }

    public void setItemId(String itemId) { this.itemId = itemId;}
    public void setCustId(String custId){ this.custId = custId;}
    public void setItemToolType(String itemToolType){ this.itemToolType = itemToolType;}
    public void setItemToolBrand(String itemToolBrand){ this.itemToolBrand = itemToolBrand;}
    public void setCustomerName(String customerName){ this.customerName = customerName;}
    public void setDailyRentalFee(Float dailyRentalFee){ this.dailyRentalFee = dailyRentalFee;}
    public void setWeekdayCharge(boolean weekdayCharge){ this.weekdayCharge = weekdayCharge;}
    public void setWeekendCharge(boolean weekendCharge){ this.weekendCharge = weekendCharge;}
    public void setHolidayCharge(boolean holidayCharge){ this.holidayCharge = holidayCharge;}
    public void setStartDate(LocalDate startDate){ this.startDate = startDate;}
    public void setRentalDays(int rentalDays){ this.rentalDays = rentalDays;}
    public void setChargeDays(int chargeDays){ this.chargeDays = chargeDays;}
    public void setTotalCharge(Float totalCharge){ this.totalCharge = totalCharge;}
    public void setDiscountPct(Float discountPct){ this.discountPct = discountPct;}
    public void setDiscountAmt(Float discountAmt){ this.discountAmt = discountAmt;}
    public void setAfterDiscountTotal(Float afterDiscountTotal){ this.afterDiscountTotal = afterDiscountTotal;}



}




//    @Override
//        public String toString() {
//            return name;
//        }

 //   }
