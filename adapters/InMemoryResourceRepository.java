package hospital.c.adapters;

import hospital.c.ports.out.ResourceRepository;
import hospital.c.domain.Reservable;
import java.util.*;

public class InMemoryResourceRepository implements ResourceRepository {
    private final Map<String, Reservable> data = new LinkedHashMap<>();

    @Override public Optional<Reservable> findByCode(String code) {
        return Optional.ofNullable(data.get(code));
    }
    @Override public void save(String code, Reservable r) { data.put(code, r); }

    // utilidades para demo
    public void put(String code, Reservable r){ save(code, r); }
    public Collection<Reservable> all(){ return data.values(); }
}
