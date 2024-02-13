package DataBaseClasses;

import java.util.List;

public class Ingår {
    private int id;
    private Sko sko;
    private Kategori kategori;

    public Ingår(int id, Sko sko, Kategori kategori) {
        this.id = id;
        this.sko = sko;
        this.kategori = kategori;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sko getSko() {
        return sko;
    }

    public void setSko(Sko sko) {
        this.sko = sko;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }
}
