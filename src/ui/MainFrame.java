package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainFrame extends JFrame{

    private String version = "0.1";
    private String applicationName = "Windows Media Player Data Extractor";

    private JMenuBar mainMenuBar = new JMenuBar();

    private JMenu fileMenu = new JMenu("File");
    private JMenu helpMenu = new JMenu("Help");

    private JMenu exportMenuItem = new JMenu("Export");
    private JMenuItem exitMenuItem = new JMenuItem("Exit");

    private JMenuItem helpMenuItem = new JMenuItem("Help");
    private JMenuItem aboutMenuItem = new JMenuItem("About");

    private JMenuItem csvExportOption = new JMenuItem(".csv");

    private static NorthPanel np = new NorthPanel();

    public MainFrame(){

        MainFrame();

    }

    public void addText(final String txt) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                fileMenu.setText(txt);
            }
        });
    }

    private void MainFrame(){

        this.setTitle("Windows Media PLayer Extractor " + version);

        /**
         *  Add items to the main menu bar
         */
        mainMenuBar.add(fileMenu);
        mainMenuBar.add(helpMenu);

        /**
         *  Add items to the File menu
         */
        fileMenu.add(exportMenuItem);
        fileMenu.add(exitMenuItem);

        /**
         *  Add items to the Help menu
         */
        helpMenu.add(helpMenuItem);
        helpMenu.add(aboutMenuItem);

        /**
         *  Add export items to export menu
         */
        exportMenuItem.add(csvExportOption);

        /**
         *  Set the main menu bar
         */
        this.setJMenuBar(mainMenuBar);

        /**
         * Add the north panel
         */
        add(np, BorderLayout.CENTER);

        /**
         *  Run menu action listener
         */
        exportMenuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        });

        /**
         * Export to CSV
         */
        csvExportOption.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to save");

                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();

                }
            }
        });


        /**
         *  Exit menu action listener
         */
        exitMenuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to close?", "Close?",  JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION)
                {
                    dispose();
                }
            }
        });

        /**
         *  About menu action listener
         */
        aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null,
                        "Research & Development - James Silman" +
                                "\njamessilman.com" +
                                "" +
                                "",
                        "About - " + applicationName + " v" + version,
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        /**
         *  Help MenuAction listener
         */
        helpMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "\nPlease note this tool is provided as is with no guarantees. All results should be verified." +
                                "\nFor help or support please contact James Silman",
                        "Help - " + applicationName + " v" + version,
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        pack();
    }

    public NorthPanel getNorthPanel(){
        return np;
    }
}
