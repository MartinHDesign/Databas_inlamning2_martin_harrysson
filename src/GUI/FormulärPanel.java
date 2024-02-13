package GUI;


import javax.swing.*;
import java.awt.*;

public class FormulärPanel extends JPanel {
    private JTextArea roomInfo = new JTextArea();
    private JPanel textAndFields = new JPanel();
    private JLabel name = new JLabel("Booking namn:");
    private JTextField writeName = new JTextField();
    private JLabel amountOfNights = new JLabel("How many nights do you wish to stay?");
    private JTextField writeAmountOfNigths = new JTextField();

    private JPanel buttonPanel = new JPanel();
    private JButton book = new JButton("Confirm booking");
    private JButton cancel = new JButton("Cancel");


    public FormulärPanel(FrameHandler frameHandler){
        setLayout(new BorderLayout());

        textAndFields.setLayout(new GridLayout(2,2));
        textAndFields.setSize(new Dimension(500,20));
        textAndFields.add(name);
        textAndFields.add(writeName);
        textAndFields.add(amountOfNights);
        textAndFields.add(writeAmountOfNigths);

        buttonPanel.setSize(new Dimension(500,20));
        buttonPanel.setLayout(new GridLayout(1,2));
        buttonPanel.add(book);
        buttonPanel.add(cancel);

        cancel.addActionListener(e -> {
            frameHandler.showPage(Panels.MENU.name());
                });

        book.addActionListener(e -> {
            frameHandler.showPage(Panels.MENU.name());

        });


        add(textAndFields, BorderLayout.NORTH);
        add(roomInfo, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }
}
