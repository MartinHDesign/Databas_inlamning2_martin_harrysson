package GUI;


import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class LoginPanel extends JPanel {
    private JLabel text = new JLabel(new ImageIcon("src/images/butiks logga.png"));
    private JButton login = new JButton("Login");
    private JButton exit = new JButton("Exit");
    private JLabel user = new JLabel("Username:");
    private JLabel pass = new JLabel("Password:");
    private JTextField username = new JTextField();
    private JPasswordField password = new JPasswordField();
    public LoginPanel(FrameHandler frameHandler){
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,1));
        buttonPanel.setSize(new Dimension(500,10));



        login.addActionListener(e -> {

            if (username.getText().isEmpty() || String.valueOf(password.getPassword()).isEmpty()){
                JOptionPane.showMessageDialog(null,"Enter username and password");
            } else if (frameHandler.r.checkPassword(username.getText(), password.getPassword())){
                frameHandler.showPage(Panels.MENU.name());
                frameHandler.setKundId(frameHandler.r.kundId(username.getText()));
            }

        });
        exit.addActionListener(e -> System.exit(0));



        buttonPanel.add(user);
        buttonPanel.add(username);
        buttonPanel.add(pass);
        buttonPanel.add(password);

        buttonPanel.add(exit);
        buttonPanel.add(login);


        add(text, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }
}
