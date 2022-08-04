package by.sidina.it_team.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private int projectID;
    private int customerID;
    private double amount;
    private Date paymentDate;

    private Payment() {
    }

    public Payment(int projectID, int customerID, double amount, Date paymentDate) {
        this.projectID = projectID;
        this.customerID = customerID;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public int getId() {
        return id;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id && projectID == payment.projectID && customerID == payment.customerID &&
                Double.compare(payment.amount, amount) == 0 && Objects.equals(paymentDate, payment.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectID, customerID, amount, paymentDate);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                ", project_id=" + projectID +
                ", customer_id=" + customerID +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                "; ";
    }
}

