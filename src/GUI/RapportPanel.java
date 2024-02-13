package GUI;



import javax.swing.*;
import java.awt.*;

public class RapportPanel extends JPanel {
    private String rapportText = """
            här kommer rapporten
            """;
    private JTextArea info = new JTextArea(rapportText);
    private JButton tillbaka = new JButton("tillbaka");
    public RapportPanel(FrameHandler frameHandler){
        setLayout(new BorderLayout());
        info.setSize(new Dimension(500,490));
        setVisible(true);
        tillbaka.addActionListener(e -> {
            frameHandler.showPage(Panels.RAPPORTMENU.name());
        });
        add(info, BorderLayout.CENTER);
        add(tillbaka, BorderLayout.SOUTH);
    }
    public void setRapportText(String text){
        info.setText(text);
    }
}
