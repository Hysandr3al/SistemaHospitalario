package hospital.c.domain;
public interface Reservable {
    boolean canReserve(int qty);
    void reserve(int qty);
    int available();
}
