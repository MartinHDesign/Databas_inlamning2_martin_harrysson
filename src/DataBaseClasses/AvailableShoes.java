package DataBaseClasses;

public class AvailableShoes {
    private Sko sko;
    private double pris;
    private int storlek;
    private String märke;
    private int lagerSaldo;
    private String färg;

    public AvailableShoes(Sko sko,double pris, int storlek, String märke, int lagerSaldo, String färg) {
        this.sko = sko;
        this.pris = pris;
        this.storlek = storlek;
        this.märke = märke;
        this.lagerSaldo = lagerSaldo;
        this.färg = färg;
    }

    public Sko getSko() {
        return sko;
    }

    public void setSko(Sko sko) {
        this.sko = sko;
    }

    public String getFärg() {
        return färg;
    }

    public void setFärg(String färg) {
        this.färg = färg;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public int getStorlek() {
        return storlek;
    }

    public void setStorlek(int storlek) {
        this.storlek = storlek;
    }

    public String getMärke() {
        return märke;
    }

    public void setMärke(String märke) {
        this.märke = märke;
    }

    public int getLagerSaldo() {
        return lagerSaldo;
    }

    public void setLagerSaldo(int lagerSaldo) {
        this.lagerSaldo = lagerSaldo;
    }
}
