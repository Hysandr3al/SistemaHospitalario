package hospital.c.ports.out;
import hospital.c.domain.Reservable;
import java.util.Optional;
public interface ResourceRepository {
    Optional<Reservable> findByCode(String code);
    void save(String code, Reservable r);
}
