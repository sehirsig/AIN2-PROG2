// O. Bittel
// 10.03.2017

package aufgabe10;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.nio.file.NoSuchFileException;


public class TelefonBuchMenuBar
        extends JMenuBar implements ActionListener {

    private TelefonBuch telBuch;
    // ...
    JMenu menu1;
    JMenuItem menuItem1;
    JMenuItem menuItem2;
    JMenuItem menuItem3;
    JFileChooser fc;


    public TelefonBuchMenuBar(TelefonBuch tb) {
        telBuch = tb;
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(200, 20));
        this.setToolTipText("Optionen-Menü");
        // ...
        menu1 = new JMenu("Datei");
        menu1.setMnemonic(KeyEvent.VK_A); // Alt + A zum aufrufen
        this.add(menu1);

        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        menuItem1 = new JMenuItem("TelefonBuch lesen ...");
        menuItem1.addActionListener(this);
        menu1.add(menuItem1);
        menuItem2 = new JMenuItem("TelefonBuch speichern ...");
        menuItem2.addActionListener(this);
        menu1.add(menuItem2);
        menu1.addSeparator();
        menuItem3 = new JMenuItem("TelefonBuch beenden");
        menuItem3.addActionListener(this);
        menu1.add(menuItem3);

    }

    public void actionPerformed(ActionEvent e) {
        // ...
        Object source = e.getSource();
        if(source == menuItem1) {
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {

                try {
                    File file = fc.getSelectedFile();
                    telBuch.read(file);
                } catch (Exception nf) {
                    System.err.println("Diese Datei existiert nicht!");
                }
                //System.out.println("Opening: " + file.getName() + ".\n");

            } else {
                //System.out.println("Open command cancelled by user.\n");
            }
            //text.setCaretPosition(text.getDocument().getLength());
            //
        } else if (source == menuItem2){
            int returnVal = fc.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                telBuch.save(file);
                //text.append("Saving: " + file.getName() + ".\n");
            } else {
                //text.append("Save command cancelled by user.\n");
            }
            //text.setCaretPosition(text.getDocument().getLength());
            //
        } else if (source == menuItem3){
            int n = JOptionPane.showConfirmDialog(
                    this,
                    "Soll das Programm wirklich geschlossen werden?",
                    "Programm beenden",
                    JOptionPane.YES_NO_OPTION
            );
            if (n == JOptionPane.YES_OPTION) {
                System.exit(0);
            } else if (n == JOptionPane.NO_OPTION){

            } else {

            }
            //
        }

    }
}

