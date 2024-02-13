package GUI;

import DataBaseClasses.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class ComboBoxPanel extends JPanel {
    JComboBox<String> Märke;
    JComboBox<Integer> Storlek;
    JComboBox<String> färg;


    private JPanel boxes = new JPanel();
    private JPanel buttons = new JPanel();
    private JButton search = new JButton("Search");
    private JButton menu = new JButton("Menu");



    public ComboBoxPanel(FrameHandler frameHandler, RapportPanel rapportPanel){
        setVisible(true);

        boxes.setLayout(new GridLayout(2,3));
        boxes.setSize(500,30);

        buttons.setLayout(new GridLayout(1,2));
        buttons.setSize(500,10);

        menu.addActionListener(e -> frameHandler.showPage(Panels.RAPPORTMENU.name()));

        search.addActionListener(e -> {
            final List<Kund> kunder = getSearchedCustomer(frameHandler.innehållerList,
                    listOfSearchedShoeIds1(frameHandler.skoList, Märke.getSelectedItem().toString(),färg.getSelectedItem().toString(), (int )Storlek.getSelectedItem()));
            if (!kunder.isEmpty()){
                //skriv till rapport sidan kunderna som köpt
                StringBuilder temp = new StringBuilder("Kunder som köpt sko med märke: " + Märke.getSelectedItem() +
                        " storlek: " + Storlek.getSelectedItem() +
                        " Färg: " + färg.getSelectedItem() + "\n");

                kunder.forEach(s -> temp.append(s.getName())
                        .append(" ").append(s.getEfternamn())
                        .append(" ").append(s.getAdress())
                        .append("\n"));

                rapportPanel.setRapportText(temp.toString());
            } else {
                //skriv till rapport sidan att inga kunder köpt produkt
                rapportPanel.setRapportText(
                        new StringBuilder("Kunder som köpt sko med märke: " + Märke.getSelectedItem() + " storlek: "
                                + Storlek.getSelectedItem() + " Färg: " + färg.getSelectedItem()
                                + "\n"+"Inga kunder har köpt denna sko \n").toString());
            }

            frameHandler.showPage(Panels.RAPPORT.name());

        });
        buttons.add(search);
        buttons.add(menu);


        add(boxes);
        add(buttons);
    }

    public void updateCombo(FrameHandler frameHandler){
        boxes.removeAll();

        Märke = new JComboBox<>(frameHandler.getVaraList().stream()
                .map(Vara::getMärke).distinct().toList().toArray(new String[0]));
        Märke.addItem("alla");


        Storlek = new JComboBox<>(frameHandler.getSkoList().stream()
                .map(Sko::getStorlek).sorted().distinct().toList().toArray(new Integer[0]));
        Storlek.addItem(0);

        färg = new JComboBox<>(frameHandler.getFärgList().stream()
                .map(Färg::getFärgNamn).distinct().toList().toArray(new String[0]));
        färg.addItem("alla");

        boxes.add(new JLabel("Märke"));
        boxes.add(new JLabel("Storlek"));
        boxes.add(new JLabel( "Färg"));
        boxes.add(Märke);
        boxes.add(Storlek);
        boxes.add(färg);

    }

    private List<Kund> getSearchedCustomer(List<Innehåller> innehållerList, List<Integer> skoIdList){
        return skoIdList.stream().flatMap(skoId -> innehållerList.stream()
                .filter(innehåller -> innehåller.getSko().getId() == skoId).
                map(Innehåller::getBeställning).map(Beställning::getKund)
                .distinct()).toList();
    }
    private Predicate<Sko> filterSearch(String sök, FindListSkoIdInterface FindInterface){
        return s -> FindInterface.filterShoeId(sök, s);
    }
    private FindListSkoIdInterface filterMärke = (m,o) -> m.equalsIgnoreCase("alla") || o.getVara().getMärke().equalsIgnoreCase(m);
    private FindListSkoIdInterface filterFärg = (m,o) -> m.equalsIgnoreCase("alla") || o.getFärg().getFärgNamn().equalsIgnoreCase(m);
    public List<Integer> listOfSearchedShoeIds1(List<Sko> lista, String märke, String färg , Integer storlek) {
        Predicate<Sko> märkePredicate = filterSearch(märke,filterMärke);
        Predicate<Sko> färgPredicate = filterSearch(färg,filterFärg);
        Predicate<Sko> storlekPredicate = s -> Objects.equals(storlek, 0) || s.getStorlek() == storlek;

        return lista.stream().filter(märkePredicate.and(färgPredicate).and(storlekPredicate)).map(Sko::getId).toList();
    }
    public List<Integer> listOfSearchedShoeIds(List<Sko> lista, String märke, String färg, Integer storlek) {
        Predicate<Sko> märkePredicate = s -> märke.equalsIgnoreCase("alla") || s.getVara().getMärke().equalsIgnoreCase(märke);
        Predicate<Sko> färgPredicate = s -> färg.equalsIgnoreCase("alla") || s.getFärg().getFärgNamn().equalsIgnoreCase(färg);
        Predicate<Sko> storlekPredicate = s -> Objects.equals(storlek, 0) || s.getStorlek() == storlek;

        return lista.stream().filter(märkePredicate.and(färgPredicate).and(storlekPredicate)).map(Sko::getId).toList();
    }

}
