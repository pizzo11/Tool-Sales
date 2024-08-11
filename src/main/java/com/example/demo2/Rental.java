package com.example.demo2;

// Rental Class
public class Rental {
    public Tool tool;
    private Customer customer;

    public Rental(Tool tool, Customer customer) {
        this.tool = tool;
        this.customer = customer;
        //tool.rent();
    }

    public void returnTool() {
        //tool.returnTool();
    }

/*@Override
public String toString() {
    return "Rental{" +
            "tool=" + tool.getName() +
            ", customer=" + customer.getName() +
            '}';
}*/

}
