// O. Bittel
// 10.03.2017

package aufgabe10;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TelefonBuchGUI extends JFrame {

    private TelefonBuch telBuch;





    public TelefonBuchGUI() {
        // TelefonBuch anlegen:
        telBuch = new TelefonBuch();

        // Menuleiste einbauen:
        // ...
        JMenuBar menuBar = new TelefonBuchMenuBar(telBuch);
        //JMenuBar menuBar = new JMenuBar();

        this.setJMenuBar(menuBar);

        // mainPanel mit Umrandung versehen und das
        // Einfuegen- und  SuchenLoeschenPanel einbauen:
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        // ...
        JPanel einfuegenPanel = new TelefonBuchEinfuegenPanel(telBuch);
        JPanel suchPanel = new TelefonBuchSuchenLoeschenPanel(telBuch);

        mainPanel.add(einfuegenPanel);
        mainPanel.add(suchPanel);

        this.setContentPane(mainPanel);

        // Sonstige Eigenschaften des Hauptfenster setzen:

        this.setResizable(false);
        this.setTitle("Telefonbuch");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new TelefonBuchGUI();
    }
}
