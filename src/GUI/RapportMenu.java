package GUI;


import DataBaseClasses.*;

import javax.swing.*;
import java.awt.*;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RapportMenu extends JPanel {
    private JButton menu = new JButton("Back to menu");
    private JButton cancelBooking = new JButton("Cancel booking");
    private JPanel reportButtons = new JPanel();
    private JButton report1 = new JButton("Alla kunder som har köpt specifik sko");
    private JButton report2 = new JButton("Antal ordrar för varje kund");
    private JButton report3 = new JButton("Alla kunder och hur mycket dom har beställt för");
    private JButton report4 = new JButton("Beställningsvärde per ort");


    public RapportMenu(FrameHandler frameHandler, RapportPanel rapportPanel, ComboBoxPanel comboBoxPanel){
        setLayout(new BorderLayout());

        reportButtons.setLayout(new GridLayout(10,1));
        reportButtons.add(report1);
        reportButtons.add(report2);
        reportButtons.add(report3);
        reportButtons.add(report4);

        report1.addActionListener( e -> {
            comboBoxPanel.updateCombo(frameHandler);
            frameHandler.showPage(Panels.COMBO.name());
        });

        //antal ordrar per kund
        report2.addActionListener(e -> {
            Map<Integer, Long> antal = frameHandler.innehållerList.stream()
                    .map(Innehåller::getBeställning)
                    .map(Beställning::getKund)
                    .collect(Collectors.groupingBy(Kund::getId, Collectors.counting()));

            StringBuilder temp = new StringBuilder("Antal ordrar kunder har lagt");

            frameHandler.kunderList.forEach( k ->
                    temp.append(k.getName())
                            .append(" ")
                            .append(k.getEfternamn())
                            .append(" har lagt ")
                            .append(antal.getOrDefault(k.getId(),0L))
                            .append(" ordrar\n")
            );

            rapportPanel.setRapportText(temp.toString());
            frameHandler.showPage(Panels.RAPPORT.name());
        });

        report3.addActionListener(e -> {
            //alla kunder och hur mycket dom beställt för
            Map<Integer, DoubleSummaryStatistics> pris = frameHandler.innehållerList.stream()
                    .map(Innehåller::getSko).map(Sko::getVara)
                    .collect(Collectors.groupingBy(Vara::getId, Collectors.summarizingDouble(Vara::getPris)));


            Map<Kund, Double> totalt = frameHandler.innehållerList.stream()
                    .collect(Collectors.groupingBy(innehåller -> innehåller.getBeställning().getKund(),
                            Collectors.summingDouble(innehåller -> pris.get(innehåller.getSko().getVara().getId()).getSum())));

            StringBuilder temp = new StringBuilder("Antal ordrar kunder har lagt");

            totalt.forEach( (k,p) -> {
                temp.append(k.getName())
                        .append(" ")
                        .append(k.getEfternamn())
                        .append(" har handlat för ")
                        .append(p)
                        .append(" kr.\n");
            });

            rapportPanel.setRapportText(temp.toString());
            frameHandler.showPage(Panels.RAPPORT.name());
        });

        report4.addActionListener(e -> {
            // per ort
            Map<Integer, DoubleSummaryStatistics> pris = frameHandler.innehållerList.stream()
                    .map(Innehåller::getSko).map(Sko::getVara)
                    .collect(Collectors.groupingBy(Vara::getId, Collectors.summarizingDouble(Vara::getPris)));

            Map<String,Double> ort = frameHandler.innehållerList.stream()
                    .collect(Collectors.groupingBy( i -> i.getBeställning().getKund().getOrt(),
                            Collectors.summingDouble(i -> pris.get(i.getSko().getVara().getId()).getSum())));

            StringBuilder temp = new StringBuilder("Beställningsvärde per ort \n");
            ort.forEach( (k,p) -> {
                temp.append(k)
                        .append(" har handlat för ")
                        .append(p)
                        .append(" kr.\n");
            });
            rapportPanel.setRapportText(temp.toString());
            frameHandler.showPage(Panels.RAPPORT.name());
        });




        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(new Dimension(500,10));

        menu.addActionListener(e -> {
            frameHandler.showPage(Panels.MENU.name());
        });


        buttonPanel.add(menu);


        add(reportButtons, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    private JFrame comboFrame(){
        JFrame jFrame = new JFrame();
        JLabel hej = new JLabel("hej");

        jFrame.add(hej);

        jFrame.setSize(new Dimension(500,500));
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        setVisible(true);
        return jFrame;
    }

}
