package DataBaseClasses;

public class Kund {
    private int id;
    private String name;
    private String efternamn;
    private String adress;
    private String ort;

    public Kund(int id, String name, String efternamn, String adress, String ort) {
        this.id = id;
        this.name = name;
        this.efternamn = efternamn;
        this.adress = adress;
        this.ort = ort;
    }

    public Kund() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEfternamn() {
        return efternamn;
    }

    public void setEfternamn(String efternamn) {
        this.efternamn = efternamn;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
}
