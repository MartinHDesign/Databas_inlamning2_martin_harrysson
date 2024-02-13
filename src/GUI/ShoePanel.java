package GUI;



import DataBaseClasses.AvailableShoes;
import DataBaseClasses.Sko;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShoePanel extends JPanel {
    private JTextArea centerText = new JTextArea();

    private JLabel image = new JLabel(new ImageIcon("src/images/vita nike.jpg"));

    private JPanel buttonPanel = new JPanel();
    private JButton boka = new JButton("Add to cart");
    private JButton next = new JButton(">>");
    private JButton previous = new JButton("<<");

    private JButton cancel = new JButton("Back to Menu");
    private JPanel centerPanel = new JPanel();
    private int showShoeCounter = 0;
    private FrameHandler frameHandler;
    private Integer beställningsId = null;

    public ShoePanel(FrameHandler frameHandler, FormulärPanel formulärPanel){

        this.frameHandler = frameHandler;
        setLayout(new BorderLayout());

        centerPanel.setLayout(new GridLayout(2,1));
        image.setHorizontalAlignment(SwingConstants.CENTER);
        centerText.setEditable(false);
        centerPanel.add(image);
        centerPanel.add(centerText);


        buttonPanel.setLayout(new GridLayout(1,3));
        buttonPanel.add(previous);
        buttonPanel.add(boka);
        buttonPanel.add(next);

        boka.addActionListener(e -> {
            beställningsId = frameHandler.r.addToCartSp(
                    frameHandler.getKundId(),
                    frameHandler.availableShoes.get(showShoeCounter).getSko().getId(),
                    beställningsId);

            System.out.println(beställningsId);
            if (beställningsId != 0){
                JOptionPane.showMessageDialog(null, "Varan Lades till");
                frameHandler.availableShoes.get(showShoeCounter).getSko().setLagerSaldo(
                        frameHandler.availableShoes.get(showShoeCounter).getSko().getLagerSaldo() - 1);
            }

            frameHandler.showPage(Panels.MENU.name());
        });
        cancel.addActionListener(e -> {
            frameHandler.showPage(Panels.MENU.name());});

        previous.addActionListener(e -> {
            displayShoe(-1);
            frameHandler.revalidate();
            frameHandler.repaint();});

        next.addActionListener(e -> {
            displayShoe(1);
            frameHandler.revalidate();
            frameHandler.repaint();
        });

        add(centerPanel,BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(cancel, BorderLayout.NORTH);
        setVisible(true);
    }

    public int displayShoe(int number){
        if (showShoeCounter + number == -1){
            showShoeCounter = frameHandler.getAvailableShoes().size()-1;
        } else if(showShoeCounter + number >= frameHandler.availableShoes.size()-1){
            showShoeCounter = 0;
        } else
            showShoeCounter += number;

        setImage(frameHandler.getAvailableShoes().get(showShoeCounter).getMärke());

        AvailableShoes temp = frameHandler.getAvailableShoes().get(showShoeCounter);

        centerText.setText(String.format(
                "Märke: %s \n Storlek: %s\n Färg: %s \n Pris: %.2f kr \n Lagersaldo: %s",
                temp.getMärke(), temp.getStorlek(), temp.getFärg(),temp.getPris(), temp.getLagerSaldo()));
        return frameHandler.getAvailableShoes().get(showShoeCounter).getSko().getId();
    }
    private void setImage(String märke){
        switch (märke){
            case "nike" -> image.setIcon(new ImageIcon("src/images/vita nike.jpg"));
            case "ecco" -> image.setIcon(new ImageIcon("src/images/svarta ecco.jpg"));
            case "björn borg" -> image.setIcon(new ImageIcon("src/images/grå björnborg.jpg"));
            case "lacost" -> image.setIcon(new ImageIcon("src/images/svart lacost.jpg"));
            case "lidl" -> image.setIcon(new ImageIcon("src/images/svart lidl.jpg"));
            case "adidas" -> image.setIcon(new ImageIcon("src/images/vit adidas.jpg"));
            case "dr martin" -> image.setIcon(new ImageIcon("src/images/grön martens.jpg"));

        }

    }

    public Integer getBeställningsId() {
        return beställningsId;
    }



    public void setBeställningsId(Integer beställningsId) {
        this.beställningsId = beställningsId;
    }
}
