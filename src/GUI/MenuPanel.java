package GUI;


import DataBaseClasses.Beställning;
import DataBaseClasses.Kund;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private JLabel image = new JLabel(new ImageIcon("src/images/butiks logga.png"));
    private JButton availableProducts = new JButton("See available shoes");
    private JButton rapporter = new JButton("Reports");
    private JButton orderNumber = new JButton("Enter existing order number");
    public MenuPanel(FrameHandler frameHandler, ShoePanel shoePanel){
        setVisible(true);
        setLayout(new BorderLayout());


        availableProducts.addActionListener(e -> {
            frameHandler.availableShoesInStock();
            shoePanel.displayShoe(0);
            frameHandler.showPage(Panels.SHOE.name());
        });

        rapporter.addActionListener(e -> {
            frameHandler.showPage(Panels.RAPPORTMENU.name());
        });

        orderNumber.addActionListener(e -> {
            int temp = Integer.parseInt(JOptionPane.showInputDialog(null,"Order nummer"));
            if (frameHandler.getBeställningList().stream().map(Beställning::getId).anyMatch(id -> id == temp) &&
                    frameHandler.getBeställningList().stream().filter(id -> id.getId() == temp).map(Beställning::getKund).
                            map(Kund::getId).anyMatch(id -> id == frameHandler.getKundId()))
            {
                JOptionPane.showMessageDialog(null, "Beställnings nummer: "+ temp);

                shoePanel.setBeställningsId(temp);
            } else
                JOptionPane.showMessageDialog(null, "Ordernummer hör inte ihop med kund id");

        });


        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(3,1));
        southPanel.setSize(new Dimension(500, 20));
        southPanel.add(availableProducts);
        southPanel.add(rapporter);
        southPanel.add(orderNumber);

        add(image, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

}
