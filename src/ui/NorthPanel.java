package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;


import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;


public class NorthPanel extends JPanel {


    private JTable wmdbEntriesTable;
    private static DefaultTableModel defaultTableModel;
    private String columnNames[] = {"Entry Type", "File Name", "File Location", "Date Added", "Last Viewed"};


    public NorthPanel(){
        NorthPanel();
    }

    private void NorthPanel(){


        this.setLayout(new BorderLayout());

        defaultTableModel = new DefaultTableModel(columnNames, 0);
        wmdbEntriesTable = new JTable(defaultTableModel);
        JScrollPane sp = new JScrollPane(wmdbEntriesTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //wmdbEntriesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        wmdbEntriesTable.setEnabled(false);
        SetVisibleRowCount(wmdbEntriesTable, 10);

        add(sp);

    }

    private void SetVisibleRowCount(JTable wmdbEntriesTable, int rows){

        int height = 0;
        for(int i=0; i<rows; i++)

            height += wmdbEntriesTable.getRowHeight(i);

        wmdbEntriesTable.setPreferredScrollableViewportSize(new Dimension(
                wmdbEntriesTable.getPreferredScrollableViewportSize().width, height
        ));
    }

    private static DefaultTableModel getTableModel(){
        return defaultTableModel;
    }

    public static void addRows(HashMap<String, String> entryMap){ //(String evidenceName, String evidenceGUID, String guid, String fullSerial, String serial, String deviceType, String vendor, String vid, String pid, String product, String revision, String driveLetter, String friendlyName){

        defaultTableModel.addRow(new Object[]{
                entryMap.get("entryType"),
                entryMap.get("fileName"),
                entryMap.get("fileLocation"),
                entryMap.get("lastViewed"),
                entryMap.get("dateAdded"),

        });
        defaultTableModel.fireTableDataChanged();
    }
}
