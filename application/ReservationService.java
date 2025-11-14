package hospital.c.application;
import hospital.c.ports.in.ReserveUseCase;
import hospital.c.ports.out.ResourceRepository;
import hospital.c.domain.Reservable;

public class ReservationService implements ReserveUseCase {
    private final ResourceRepository repo;
    public ReservationService(ResourceRepository repo){ this.repo = repo; }
    public void reserve(String code, int qty){
        Reservable r = repo.findByCode(code).orElseThrow(() -> new IllegalArgumentException("No existe recurso"));
        if(!r.canReserve(qty)) throw new IllegalStateException("No disponible");
        r.reserve(qty);
        repo.save(code, r);
    }
}
