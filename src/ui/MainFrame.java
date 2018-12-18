package ui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{

    private String version = "0.7";
    private String applicationName = "Windows Media Player Data Extractor";

    private JMenuBar mainMenuBar = new JMenuBar();

    private JMenu fileMenu = new JMenu("File");
    private JMenu helpMenu = new JMenu("Help");

    private JMenu exportMenuItem = new JMenu("File Selection");
    private JMenuItem exitMenuItem = new JMenuItem("Exit");

    private JMenuItem helpMenuItem = new JMenuItem("Help");
    private JMenuItem aboutMenuItem = new JMenuItem("About");

    private JMenuItem wmdbFileSelection = new JMenuItem("Select WMDB File");

    private static NorthPanel northPanel = new NorthPanel();
    private static SouthPanel southPanel = new SouthPanel();

    public MainFrame(){

        MainFrame();

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
        exportMenuItem.add(wmdbFileSelection);

        /**
         *  Set the main menu bar
         */
        this.setJMenuBar(mainMenuBar);

        /**
         * Add the north panel
         */
        add(northPanel, BorderLayout.CENTER);

        /**
         * Add the south panel
         */
        add(southPanel, BorderLayout.SOUTH);

        /**
         *  Run menu action listener
         */
        exportMenuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        });

        /**
         * Select the WMDB file
         */
        wmdbFileSelection.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Select the WMP Database");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("WMP Data Files", "wmdb");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {

                    new Extract(chooser.getSelectedFile().getAbsolutePath());

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

}
