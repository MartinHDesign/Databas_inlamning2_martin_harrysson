package DataBaseClasses;

public class Vara {
    private int id;
    private String märke;
    private double pris;

    public Vara(int id, String märke, double pris) {
        this.id = id;
        this.märke = märke;
        this.pris = pris;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMärke() {
        return märke;
    }

    public void setMärke(String märke) {
        this.märke = märke;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }
}
