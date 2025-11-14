package hospital.c.domain;

public abstract class Resource {
    private final String code;
    private final String name;
    protected Resource(String code, String name){ this.code = code; this.name = name; }
    public String code(){ return code; }
    public String name(){ return name; }
}
