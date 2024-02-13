package DataBaseClasses;

public class Kategori {
    private int id;
    private String KategoriNamn;

    public Kategori(int id, String kategoriNamn) {
        this.id = id;
        KategoriNamn = kategoriNamn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKategoriNamn() {
        return KategoriNamn;
    }

    public void setKategoriNamn(String kategoriNamn) {
        KategoriNamn = kategoriNamn;
    }
}
