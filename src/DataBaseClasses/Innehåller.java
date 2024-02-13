package DataBaseClasses;

public class Innehåller {
    private int id;
    private Beställning beställning;
    private Sko sko;

    public Innehåller(int id, Beställning beställning, Sko sko) {
        this.id = id;
        this.beställning = beställning;
        this.sko = sko;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Beställning getBeställning() {
        return beställning;
    }

    public void setBeställning(Beställning beställning) {
        this.beställning = beställning;
    }

    public Sko getSko() {
        return sko;
    }

    public void setSko(Sko sko) {
        this.sko = sko;
    }
}
