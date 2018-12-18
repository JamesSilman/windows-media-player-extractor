package ui;

import javax.swing.*;
import java.awt.*;

public class SouthPanel extends JPanel{

    final static JTextArea textArea = new JTextArea(15,10);
    final static JScrollPane sp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    public SouthPanel(){
        SouthPanel();
    }

    private void SouthPanel(){

        this.setLayout(new BorderLayout());

        //textArea.setLineWrap(true);
        textArea.setEditable(false);
        //textArea.setWrapStyleWord(true);
        //textArea.setPreferredSize(new Dimension(100, 100));

        JScrollPane sp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        add(sp);

    }

    public static void updateTextArea(String message){
        textArea.append(message + "\n");
        //textArea.repaint();
    }
}
