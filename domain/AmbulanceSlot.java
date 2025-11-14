package hospital.c.domain;

public final class AmbulanceSlot extends Resource implements Reservable, Locatable {
    private final String baseLocation; private boolean free = true;
    public AmbulanceSlot(String runId, String label, String baseLocation){ super(runId, label); this.baseLocation = baseLocation; }
    public String location(){ return baseLocation; }
    public boolean canReserve(int q){ return q == 1 && free; }
    public void reserve(int q){ if(!canReserve(q)) throw new IllegalStateException("Unidad no disponible"); free = false; }
    public int available(){ return free ? 1 : 0; }
}
