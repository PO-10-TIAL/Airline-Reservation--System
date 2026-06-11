import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Reservation(String reservationId, Flight flight, Passenger passenger, int seatCount, double totalPrice, String bookingDate) {
    public static Reservation create(String reservationId, Flight flight, Passenger passenger, int seatCount) {
        String bookingDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        double totalPrice = flight.getPrice() * seatCount;
        return new Reservation(reservationId, flight, passenger, seatCount, totalPrice, bookingDate);
    }

    @Override
    public String toString() {
        return String.format("Reservation[id=%s, flight=%s, passenger=%s, seats=%d, price=KSh %.2f, booked=%s]",
                reservationId, flight.getFlightNumber(), passenger.name(), seatCount, totalPrice, bookingDate);
    }
}
