package GUI;


import javax.swing.*;
import java.awt.*;

public class CardLayoutContainer extends JPanel {
    public CardLayoutContainer(CardLayout layout, FrameHandler frameHandler){
        RapportPanel rapportPanel = new RapportPanel(frameHandler);
        ComboBoxPanel comboBoxPanel = new ComboBoxPanel(frameHandler, rapportPanel);
        RapportMenu rapportMenu = new RapportMenu(frameHandler, rapportPanel, comboBoxPanel);
        FormulärPanel bookRoom = new FormulärPanel(frameHandler);
        ShoePanel shoePanel = new ShoePanel(frameHandler, bookRoom);
        MenuPanel menuPanel = new MenuPanel(frameHandler, shoePanel);
        LoginPanel loginPanel = new LoginPanel(frameHandler);

        setLayout(layout);
        add(menuPanel, Panels.MENU.name());
        add(shoePanel,Panels.SHOE.name());
        add(bookRoom, Panels.FORMULÄR.name());
        add(rapportPanel, Panels.RAPPORT.name());
        add(loginPanel,Panels.LOGIN.name());
        add(rapportMenu, Panels.RAPPORTMENU.name());
        add(comboBoxPanel, Panels.COMBO.name());
    }


}
