# Airline Reservation System

This workspace contains a Java-based airline reservation system with a Swing user interface.

## Files

- `AirlineReservationApp.java` - main application entry point.
- `AirlineReservationUI.java` - Swing user interface for booking, searching, and managing reservations.
- `AirlineService.java` - business logic for flights, bookings, and cancellations.
- `Flight.java` - flight model with seat availability tracking.
- `Passenger.java` - passenger data record.
- `Reservation.java` - reservation record with booking metadata.
- `TestSwing.java` - sample Swing window class left from earlier workspace state.

## Compile

If your `javac` is not on the PATH, use the explicit JDK 23 install path:

```powershell
cd "c:\Users\HP\Desktop\Airline Reservation system"
& "C:\Users\HP\AppData\Local\Java\jdk-23.0.1+11\bin\javac.exe" *.java
```

## Run

```powershell
cd "c:\Users\HP\Desktop\Airline Reservation system"
& "C:\Users\HP\AppData\Local\Java\jdk-23.0.1+11\bin\java.exe" AirlineReservationApp
```

The application opens the Swing UI by default.

To run the original console version instead:

```powershell
& "C:\Users\HP\AppData\Local\Java\jdk-23.0.1+11\bin\java.exe" AirlineReservationApp console
```

## Usage

The application supports:

- listing all flights
- searching flights by origin and destination
- booking a flight
- cancelling a reservation
- viewing current reservations

## Notes

- Data is stored in memory while the program runs.
- You can extend the system later with file persistence, a database backend, or a Swing/JavaFX UI.
