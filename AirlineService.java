import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AirlineService {
    private final List<Flight> flights = new ArrayList<>();
    private final Map<String, Passenger> passengers = new HashMap<>();
    private final Map<String, Reservation> reservations = new HashMap<>();
    private final Map<String, User> usersByEmail = new HashMap<>();
    private final Map<String, User> usersById = new HashMap<>();
    private int passengerCounter = 1;
    private int reservationCounter = 1;
    private int userCounter = 1;
    private static final String ADMIN_SECRET = "SKYLINE-ADMIN";

    public AirlineService() {
        initializeFlights();
        initializeUsers();
    }

    private void initializeFlights() {
        flights.add(new Flight("KE101", "Nairobi", "Mombasa", "07:30", "08:45", 180, 14500.00));
        flights.add(new Flight("KE102", "Mombasa", "Kisumu", "09:15", "11:55", 160, 19000.00));
        flights.add(new Flight("KE103", "Nairobi", "Kisumu", "10:00", "11:30", 170, 16000.00));
        flights.add(new Flight("KE104", "Nairobi", "Eldoret", "08:45", "10:00", 150, 12000.00));
        flights.add(new Flight("KE105", "Nairobi", "Nakuru", "12:00", "13:00", 180, 9000.00));
        flights.add(new Flight("KE106", "Nairobi", "Malindi", "14:30", "15:50", 140, 18000.00));
        flights.add(new Flight("KE107", "Mombasa", "Nyeri", "06:45", "08:10", 150, 17000.00));
        flights.add(new Flight("KE108", "Nairobi", "Meru", "11:15", "12:35", 140, 11500.00));
        flights.add(new Flight("KE109", "Kisumu", "Nakuru", "13:00", "14:10", 150, 9500.00));
        flights.add(new Flight("KE110", "Kisumu", "Kakamega", "15:00", "16:15", 130, 8500.00));
        flights.add(new Flight("KE111", "Eldoret", "Turkana", "16:30", "18:15", 120, 21000.00));
        flights.add(new Flight("KE112", "Nairobi", "Garissa", "17:00", "18:40", 150, 22000.00));
        flights.add(new Flight("KE113", "Nairobi", "Kitale", "07:00", "08:10", 160, 13000.00));
        flights.add(new Flight("KE114", "Mombasa", "Lamu", "10:00", "11:00", 140, 14000.00));
        flights.add(new Flight("KE115", "Nairobi", "Nyeri", "09:30", "10:40", 150, 10500.00));
        flights.add(new Flight("KE116", "Nairobi", "Embu", "11:00", "12:05", 150, 10800.00));
        flights.add(new Flight("KE117", "Mombasa", "Voi", "12:30", "13:20", 140, 9500.00));
        flights.add(new Flight("KE118", "Nairobi", "Thika", "08:15", "09:00", 170, 6800.00));
        flights.add(new Flight("KE119", "Nairobi", "Kitui", "13:15", "14:25", 140, 14500.00));
        flights.add(new Flight("KE120", "Kisumu", "Homa Bay", "07:45", "08:40", 150, 7600.00));
        flights.add(new Flight("KE121", "Nairobi", "Naivasha", "14:00", "15:00", 160, 8200.00));
        flights.add(new Flight("KE122", "Nakuru", "Kericho", "09:20", "10:10", 140, 7800.00));
        flights.add(new Flight("KE123", "Eldoret", "Kitale", "11:45", "12:25", 150, 6700.00));
        flights.add(new Flight("KE124", "Nairobi", "Mwingi", "15:10", "16:30", 130, 12800.00));
        flights.add(new Flight("KE125", "Mombasa", "Kwale", "06:30", "07:10", 140, 7200.00));
        flights.add(new Flight("KE126", "Nairobi", "Mandera", "07:50", "09:45", 120, 26000.00));
        flights.add(new Flight("KE127", "Nairobi", "Marsabit", "10:30", "12:15", 130, 23000.00));
        flights.add(new Flight("KE128", "Nairobi", "Isiolo", "13:00", "14:20", 140, 19000.00));
        flights.add(new Flight("KE129", "Nairobi", "Wajir", "15:30", "17:10", 120, 27000.00));
        flights.add(new Flight("KE130", "Kisumu", "Migori", "12:00", "12:45", 140, 7500.00));
        flights.add(new Flight("KE131", "Nairobi", "Bungoma", "08:30", "09:50", 150, 11300.00));
        flights.add(new Flight("KE132", "Nairobi", "Busia", "10:00", "11:35", 140, 15800.00));
        flights.add(new Flight("KE133", "Nairobi", "Kajiado", "09:20", "10:10", 150, 9000.00));
        flights.add(new Flight("KE134", "Nairobi", "Narok", "11:00", "11:55", 150, 9300.00));
        flights.add(new Flight("KE135", "Nairobi", "Nyahururu", "13:45", "14:40", 140, 9800.00));
        flights.add(new Flight("KE136", "Mombasa", "Malindi", "16:00", "17:10", 140, 9000.00));
        flights.add(new Flight("KE137", "Nairobi", "Kisii", "07:20", "08:40", 150, 12500.00));
        flights.add(new Flight("KE138", "Nairobi", "Murang'a", "10:15", "11:10", 150, 8700.00));
        flights.add(new Flight("KE139", "Kisumu", "Siaya", "14:20", "15:05", 140, 7800.00));
        flights.add(new Flight("KE140", "Nairobi", "Busia", "16:15", "17:55", 140, 16000.00));
        flights.add(new Flight("KE141", "Nairobi", "Lamu", "18:00", "19:10", 130, 19000.00));
        flights.add(new Flight("KE142", "Nairobi", "Malindi", "06:30", "07:55", 150, 17500.00));
        flights.add(new Flight("KE143", "Nakuru", "Nyahururu", "12:30", "13:10", 140, 7000.00));
        flights.add(new Flight("KE144", "Nairobi", "Emali", "08:00", "08:55", 150, 7200.00));
        flights.add(new Flight("KE145", "Nairobi", "Homabay", "17:00", "18:30", 140, 15000.00));
    }

    private void initializeUsers() {
        createAdmin("Administrator", "admin@system.com", "admin123", "0000000000");
    }

    public List<Flight> getAllFlights() {
        return new ArrayList<>(flights);
    }

    public List<Flight> searchFlights(String origin, String destination) {
        if (origin == null || destination == null) {
            return new ArrayList<>();
        }
        String normalizedOrigin = origin.trim();
        String normalizedDestination = destination.trim();
        return flights.stream()
                .filter(f -> f.getOrigin().equalsIgnoreCase(normalizedOrigin) && f.getDestination().equalsIgnoreCase(normalizedDestination))
                .collect(Collectors.toList());
    }

    public Reservation bookFlight(String flightNumber, String passengerName, String email, String phone, int seats) {
        Flight flight = findFlightByNumber(flightNumber);
        if (flight == null) {
            throw new IllegalArgumentException("Flight not found: " + flightNumber);
        }
        if (seats <= 0) {
            throw new IllegalArgumentException("Seat count must be at least 1.");
        }
        if (!flight.reserveSeats(seats)) {
            throw new IllegalArgumentException("Not enough seats available.");
        }

        String passengerId = generatePassengerId();
        Passenger passenger = new Passenger(passengerId, passengerName, email, phone);
        passengers.put(passengerId, passenger);

        String reservationId = generateReservationId();
        Reservation reservation = Reservation.create(reservationId, flight, passenger, seats);
        reservations.put(reservationId, reservation);
        return reservation;
    }

    public boolean cancelReservation(String reservationId) {
        Reservation reservation = reservations.remove(reservationId);
        if (reservation == null) {
            return false;
        }
        reservation.flight().releaseSeats(reservation.seatCount());
        return true;
    }

    public User authenticate(String email, String password) {
        if (email == null || password == null) {
            throw new IllegalArgumentException("Invalid login credentials.");
        }
        User user = usersByEmail.get(email.trim().toLowerCase());
        if (user == null || !user.password().equals(password)) {
            throw new IllegalArgumentException("Email or password is incorrect.");
        }
        return user;
    }

    public User registerCustomer(String name, String email, String password, String phone) {
        if (name == null || name.isBlank() || email == null || email.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("Name, email, and password are required.");
        }
        String normalizedEmail = email.trim().toLowerCase();
        if (usersByEmail.containsKey(normalizedEmail)) {
            throw new IllegalArgumentException("An account already exists with that email.");
        }
        String passengerId = generatePassengerId();
        Passenger passenger = new Passenger(passengerId, name.trim(), normalizedEmail, phone == null ? "" : phone.trim());
        passengers.put(passengerId, passenger);

        String userId = generateUserId();
        User user = new User(userId, name.trim(), normalizedEmail, password, Role.CUSTOMER, phone == null ? "" : phone.trim(), passengerId);
        usersByEmail.put(normalizedEmail, user);
        usersById.put(userId, user);
        return user;
    }

    public User registerAdmin(String name, String email, String password, String phone, String adminCode) {
        if (name == null || name.isBlank() || email == null || email.isBlank() || password == null || password.isBlank() || adminCode == null || adminCode.isBlank()) {
            throw new IllegalArgumentException("Name, email, password, and admin code are required.");
        }
        if (!ADMIN_SECRET.equals(adminCode.trim())) {
            throw new IllegalArgumentException("Invalid admin code.");
        }
        String normalizedEmail = email.trim().toLowerCase();
        if (usersByEmail.containsKey(normalizedEmail)) {
            throw new IllegalArgumentException("An account already exists with that email.");
        }
        String userId = generateUserId();
        User admin = new User(userId, name.trim(), normalizedEmail, password, Role.ADMIN, phone == null ? "" : phone.trim(), null);
        usersByEmail.put(normalizedEmail, admin);
        usersById.put(userId, admin);
        return admin;
    }

    public boolean emailExists(String email) {
        return email != null && usersByEmail.containsKey(email.trim().toLowerCase());
    }

    public User getUserById(String userId) {
        return usersById.get(userId);
    }

    public List<User> getAllCustomers() {
        return usersById.values().stream().filter(User::isCustomer).collect(Collectors.toList());
    }

    public List<Passenger> getAllPassengers() {
        return new ArrayList<>(passengers.values());
    }

    public List<Reservation> getReservationsForUser(User user) {
        if (user == null) {
            return new ArrayList<>();
        }
        if (user.isAdmin()) {
            return getAllReservations();
        }
        if (user.passengerId() == null) {
            return new ArrayList<>();
        }
        return reservations.values().stream()
                .filter(r -> r.passenger().id().equals(user.passengerId()))
                .collect(Collectors.toList());
    }

    public Reservation bookFlightForUser(String flightNumber, String userId, int seats) {
        User user = getUserById(userId);
        if (user == null || !user.isCustomer()) {
            throw new IllegalArgumentException("Invalid customer account.");
        }
        if (user.passengerId() == null) {
            throw new IllegalStateException("Customer does not have an associated passenger profile.");
        }
        Flight flight = findFlightByNumber(flightNumber);
        if (flight == null) {
            throw new IllegalArgumentException("Flight not found: " + flightNumber);
        }
        if (seats <= 0) {
            throw new IllegalArgumentException("Seat count must be at least 1.");
        }
        if (!flight.reserveSeats(seats)) {
            throw new IllegalArgumentException("Not enough seats available.");
        }
        Passenger passenger = passengers.get(user.passengerId());
        if (passenger == null) {
            throw new IllegalStateException("Passenger profile missing for user.");
        }
        String reservationId = generateReservationId();
        Reservation reservation = Reservation.create(reservationId, flight, passenger, seats);
        reservations.put(reservationId, reservation);
        return reservation;
    }

    public boolean cancelReservation(String reservationId, String userId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation == null) {
            return false;
        }
        User user = getUserById(userId);
        if (user == null) {
            return false;
        }
        boolean allowed = user.isAdmin() || (user.isCustomer() && user.passengerId() != null && reservation.passenger().id().equals(user.passengerId()));
        if (!allowed) {
            return false;
        }
        reservations.remove(reservationId);
        reservation.flight().releaseSeats(reservation.seatCount());
        return true;
    }

    public void addFlight(String flightNumber, String origin, String destination, String departureTime, String arrivalTime, int totalSeats, double price) {
        if (flightNumber == null || flightNumber.isBlank()) {
            throw new IllegalArgumentException("Flight number is required.");
        }
        if (findFlightByNumber(flightNumber) != null) {
            throw new IllegalArgumentException("A flight already exists with this number.");
        }
        if (totalSeats <= 0) {
            throw new IllegalArgumentException("Total seats must be greater than zero.");
        }
        Flight flight = new Flight(flightNumber.trim().toUpperCase(), origin.trim(), destination.trim(), departureTime.trim(), arrivalTime.trim(), totalSeats, price);
        flights.add(flight);
    }

    public void updateFlight(String flightNumber, String origin, String destination, String departureTime, String arrivalTime, int totalSeats, double price) {
        Flight flight = findFlightByNumber(flightNumber);
        if (flight == null) {
            throw new IllegalArgumentException("Flight not found: " + flightNumber);
        }
        flight.updateDetails(origin.trim(), destination.trim(), departureTime.trim(), arrivalTime.trim(), totalSeats, price);
    }

    public boolean deleteFlight(String flightNumber) {
        Flight flight = findFlightByNumber(flightNumber);
        if (flight == null) {
            return false;
        }
        boolean hasReserved = reservations.values().stream().anyMatch(r -> r.flight().getFlightNumber().equalsIgnoreCase(flightNumber.trim()));
        if (hasReserved) {
            throw new IllegalArgumentException("Cannot delete a flight with existing reservations.");
        }
        return flights.remove(flight);
    }

    public List<Reservation> getReservationsByFlight(String flightNumber) {
        return reservations.values().stream()
                .filter(r -> r.flight().getFlightNumber().equalsIgnoreCase(flightNumber.trim()))
                .collect(Collectors.toList());
    }

    public List<User> searchCustomers(String query) {
        if (query == null || query.isBlank()) {
            return getAllCustomers();
        }
        String normalized = query.trim().toLowerCase();
        return usersById.values().stream()
                .filter(User::isCustomer)
                .filter(u -> u.name().toLowerCase().contains(normalized) || u.email().contains(normalized))
                .collect(Collectors.toList());
    }

    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations.values());
    }

    public int getTotalFlightCount() {
        return flights.size();
    }

    public int getTotalReservationCount() {
        return reservations.size();
    }

    public int getTotalCustomerCount() {
        return (int) usersById.values().stream().filter(User::isCustomer).count();
    }

    public int getAvailableSeatCount() {
        return flights.stream().mapToInt(Flight::getAvailableSeats).sum();
    }

    private void createAdmin(String name, String email, String password, String phone) {
        String userId = generateUserId();
        User admin = new User(userId, name.trim(), email.trim().toLowerCase(), password, Role.ADMIN, phone == null ? "" : phone.trim(), null);
        usersByEmail.put(admin.email(), admin);
        usersById.put(admin.id(), admin);
    }

    private Flight findFlightByNumber(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber.trim())) {
                return flight;
            }
        }
        return null;
    }

    private String generatePassengerId() {
        return String.format("P%03d", passengerCounter++);
    }

    private String generateReservationId() {
        return String.format("R%04d", reservationCounter++);
    }

    private String generateUserId() {
        return String.format("U%03d", userCounter++);
    }
}
