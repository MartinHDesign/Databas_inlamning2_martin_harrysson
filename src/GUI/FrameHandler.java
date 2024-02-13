package GUI;

import DataBaseClasses.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FrameHandler extends JFrame {
    private final CardLayout layout = new CardLayout();
    private final CardLayoutContainer allPanels = new CardLayoutContainer(layout, this);

    private String page = Panels.LOGIN.name();
    private int kundId;
    private int beställningsId;
    Repository r = new Repository();

    List<Kund> kunderList = r.getCustomersFromDatabase();
    List<Beställning> beställningList = r.getBeställningFromDatabase(kunderList);
    List<Färg> färgList = r.getFärgFromDatabase();
    List<Vara> varaList = r.getVaraFromDatabase();
    List<Kategori> kategoriList = r.getKategoriFromDatabase();
    List<Sko> skoList = r.getSkoFromDatabase(färgList,varaList);
    List<Innehåller> innehållerList = r.getInnehållerFromDatabase(beställningList,skoList);
    List<Ingår> ingårList = r.getIngårFromDatabase(kategoriList,skoList);
    List<AvailableShoes> availableShoes = new ArrayList<>();
    public FrameHandler(){

        add(allPanels);
        showPage(page);

        setSize(new Dimension(500,500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public void availableShoesInStock(){
        availableShoes.clear();
        for(Sko sko:skoList){
            if (sko.getLagerSaldo() > 0) {
                availableShoes.add(new AvailableShoes(
                        sko,
                        sko.getVara().getPris(),
                        sko.getStorlek(),
                        sko.getVara().getMärke(),
                        sko.getLagerSaldo(),
                        sko.getFärg().getFärgNamn()));
            }
        }
//        availableShoes.add()

    }
    public void showPage(String page){
        layout.show(allPanels, page);
    }

    @Override
    public CardLayout getLayout() {
        return layout;
    }

    public CardLayoutContainer getAllPanels() {
        return allPanels;
    }

    public List<AvailableShoes> getAvailableShoes() {
        return availableShoes;
    }

    public void setAvailableShoes(List<AvailableShoes> availableShoes) {
        this.availableShoes = availableShoes;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Repository getR() {
        return r;
    }

    public void setR(Repository r) {
        this.r = r;
    }

    public List<Kund> getKunderList() {
        return kunderList;
    }

    public void setKunderList(List<Kund> kunderList) {
        this.kunderList = kunderList;
    }

    public List<Beställning> getBeställningList() {
        return beställningList;
    }

    public void setBeställningList(List<Beställning> beställningList) {
        this.beställningList = beställningList;
    }

    public List<Färg> getFärgList() {
        return färgList;
    }

    public void setFärgList(List<Färg> färgList) {
        this.färgList = färgList;
    }

    public List<Vara> getVaraList() {
        return varaList;
    }

    public void setVaraList(List<Vara> varaList) {
        this.varaList = varaList;
    }

    public List<Kategori> getKategoriList() {
        return kategoriList;
    }

    public void setKategoriList(List<Kategori> kategoriList) {
        this.kategoriList = kategoriList;
    }

    public List<Sko> getSkoList() {
        return skoList;
    }

    public void setSkoList(List<Sko> skoList) {
        this.skoList = skoList;
    }

    public List<Innehåller> getInnehållerList() {
        return innehållerList;
    }

    public void setInnehållerList(List<Innehåller> innehållerList) {
        this.innehållerList = innehållerList;
    }

    public List<Ingår> getIngårList() {
        return ingårList;
    }

    public void setIngårList(List<Ingår> ingårList) {
        this.ingårList = ingårList;
    }

    public int getKundId() {
        return kundId;
    }

    public void setKundId(int kundId) {
        this.kundId = kundId;
    }

    public int getBeställningsId() {
        return beställningsId;
    }

    public void setBeställningsId(int beställningsId) {
        this.beställningsId = beställningsId;
    }
}
