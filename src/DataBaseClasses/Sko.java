package DataBaseClasses;

public class Sko {
    private int id;
    private int lagerSaldo;
    private Färg färg;
    private int storlek;

    private Vara vara;

    public Sko(int id, int lagerSaldo, Färg färg, int storlek, Vara vara) {
        this.id = id;
        this.lagerSaldo = lagerSaldo;
        this.färg = färg;
        this.storlek = storlek;

        this.vara = vara;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLagerSaldo() {
        return lagerSaldo;
    }

    public void setLagerSaldo(int lagerSaldo) {
        this.lagerSaldo = lagerSaldo;
    }

    public Färg getFärg() {
        return färg;
    }

    public void setFärg(Färg färg) {
        this.färg = färg;
    }

    public int getStorlek() {
        return storlek;
    }

    public void setStorlek(int storlek) {
        this.storlek = storlek;
    }


    public Vara getVara() {
        return vara;
    }

    public void setVara(Vara vara) {
        this.vara = vara;
    }
}
