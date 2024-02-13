package DataBaseClasses;

public class Färg {
    private int id;
    private String färgNamn;

    public Färg(int id, String färgNamn) {
        this.id = id;
        this.färgNamn = färgNamn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFärgNamn() {
        return färgNamn;
    }

    public void setFärgNamn(String färgNamn) {
        this.färgNamn = färgNamn;
    }
}
