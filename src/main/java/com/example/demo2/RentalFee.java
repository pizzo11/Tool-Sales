package com.example.demo2;

//Rental Fee Class
public class RentalFee{
    public String ToolType;
    public Float DailyCharge;
    public boolean WeekdayCharge;
    public boolean WeekendCharge;
    public boolean HolidayCharge;

    public RentalFee(String ToolType, Float DailyCharge, boolean WeekdayCharge, boolean WeekendCharge, boolean HolidayCharge){
        this.ToolType = ToolType;
        this.DailyCharge = DailyCharge;
        this.WeekdayCharge = WeekdayCharge;
        this.WeekendCharge = WeekendCharge;
        this.HolidayCharge = HolidayCharge;
    }

    public String getToolType(){
        return ToolType;
    }

    public Float getDailyCharge() {
        return DailyCharge;
    }

    public boolean isWeekdayCharge() {
        return WeekdayCharge;
    }

    public boolean isWeekendCharge() {
        return WeekendCharge;
    }

    public boolean isHolidayCharge() {
        return HolidayCharge;
    }
}
