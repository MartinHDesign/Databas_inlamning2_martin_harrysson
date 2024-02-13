package DataBaseClasses;

import java.time.LocalDate;

public class Beställning {
    private int id;
    private Kund kund;
    private LocalDate datum;

    public Beställning(int id, Kund kund, LocalDate datum) {
        this.id = id;
        this.kund = kund;
        this.datum = datum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Kund getKund() {
        return kund;
    }

    public void setKund(Kund kund) {
        this.kund = kund;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}
