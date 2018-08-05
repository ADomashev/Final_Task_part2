package domain;

import java.time.LocalDate;

public class Ticket {

    private LocalDate departureDate;
    private LocalDate returnDate;
    private Double price;

    public void setDepartureDate(LocalDate date) {
        this.departureDate = date;
    }

    public LocalDate getDate() {
        return departureDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "date=" + departureDate +
                ", price=" + price +
                ", returnDates=" + returnDate +
                '}';
    }
}
