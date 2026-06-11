public record Passenger(String id, String name, String email, String phone) {
    @Override
    public String toString() {
        return String.format("Passenger[id=%s, name=%s, email=%s, phone=%s]", id, name, email, phone);
    }
}
