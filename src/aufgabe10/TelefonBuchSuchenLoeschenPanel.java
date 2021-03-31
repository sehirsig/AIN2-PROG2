// O. Bittel
// 10.03.2017

package aufgabe10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.List;
import java.util.concurrent.Flow;

public class TelefonBuchSuchenLoeschenPanel
        extends JPanel implements ActionListener {

    // ...
    private TelefonBuch telBuch;
    private JTextField tsSuchenName;
    private JTextField tsSuchenZusatz;
    private JComboBox tsSuchePrefix;
    private JButton buttonAnwenden;
    private JTextArea ausgabeFeld;

    public TelefonBuchSuchenLoeschenPanel(TelefonBuch tb) {
        // ...
        telBuch = tb;

        JPanel einfuegpanel = new JPanel();

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 1));
        panel1.add(new JLabel("Name"));
        panel1.add(new JLabel("Zusatz"));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2, 1));
        tsSuchenName = new JTextField("", 20);
        panel2.add(tsSuchenName);
        tsSuchenZusatz = new JTextField("", 20);
        panel2.add(tsSuchenZusatz);

        Border border = BorderFactory.createTitledBorder("Suchen/Löschen");
        einfuegpanel.setBorder(border);
        einfuegpanel.add(panel1);
        einfuegpanel.add(panel2);

        String[] comboStrings = { "Exakte Suche", "Prefix-Suche", "Löschen"};
        tsSuchePrefix = new JComboBox(comboStrings);
        einfuegpanel.add(tsSuchePrefix);
        buttonAnwenden = new JButton("Anwenden");
        einfuegpanel.add(buttonAnwenden);
        buttonAnwenden.addActionListener(this);

        JPanel ausgabePanel = new JPanel();
        Border border2 = BorderFactory.createTitledBorder("Ausgabe");
        ausgabePanel.setBorder(border2);
        ausgabePanel.setLayout(new FlowLayout());
        ausgabeFeld = new JTextArea(20, 50);
        ausgabeFeld.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(ausgabeFeld);
        ausgabePanel.add(scrollPane);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(einfuegpanel, BorderLayout.NORTH);
        this.add(ausgabePanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == buttonAnwenden) {
            if (tsSuchePrefix.getSelectedIndex() == 0) {
                String temp1 = telBuch.exactSearch(tsSuchenName.getText(), tsSuchenZusatz.getText());
                if(temp1 != null) {
                    ausgabeFeld.setText(temp1);
                } else {
                    ausgabeFeld.setText("");
                    JOptionPane.showMessageDialog(this, "Es wurde kein Eintrag gefunden!");
                }
            } else if (tsSuchePrefix.getSelectedIndex() == 1) {
                ausgabeFeld.setText("");
                List<String> listk = telBuch.prefixSearch(tsSuchenName.getText());
                int count = 0;
                for (var v : listk) {
                    ausgabeFeld.append(v);
                    count ++;
                    if (!(count == listk.size())) {
                        ausgabeFeld.append("\n");
                    }
                }
            } else if (tsSuchePrefix.getSelectedIndex() == 2) {
                if (telBuch.remove(tsSuchenName.getText(), tsSuchenZusatz.getText())) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Eintrag wurde Erfolgreich gelöscht!"
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Fehler beim löschen!"
                    );
                }
            }
        } else {

        }
    }
}
