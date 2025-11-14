package hospital.c.domain;
import java.time.LocalDate;

public final class StockItem extends Resource implements Reservable, Locatable {
    private final String location, lot; private final LocalDate expiry; private int quantity;
    public StockItem(String gtin, String name, String location, String lot, LocalDate expiry, int qty){
        super(gtin, name); this.location = location; this.lot = lot; this.expiry = expiry; this.quantity = qty;
    }
    public String location(){ return location; }
    public boolean canReserve(int q){ return q > 0 && quantity >= q; }
    public void reserve(int q){ if(!canReserve(q)) throw new IllegalStateException("Stock insuficiente"); quantity -= q; }
    public int available(){ return quantity; }
}
