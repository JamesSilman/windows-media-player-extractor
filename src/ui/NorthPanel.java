package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;

/**
 * Created by jsilman01 on 15/06/2016.
 */
public class NorthPanel extends JPanel {


    private JTable usbTable;
    private static DefaultTableModel defaultTableModel;
    private String columnNames[] = {"File Name", "File Location"};
    //private static StringBuilder usbEntries = new StringBuilder();
    private static LinkedList<String> usbEntires = new LinkedList<>();


    public NorthPanel(){
        NorthPanel();
    }

    private void NorthPanel(){


        this.setLayout(new BorderLayout());

        defaultTableModel = new DefaultTableModel(columnNames, 0);
        usbTable = new JTable(defaultTableModel);
        JScrollPane sp = new JScrollPane(usbTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //usbTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        SetVisibleRowCount(usbTable, 10);

        add(sp);

    }

    private void SetVisibleRowCount(JTable usbTable, int rows){

        int height = 0;
        for(int i=0; i<rows; i++)

            height += usbTable.getRowHeight(i);

        usbTable.setPreferredScrollableViewportSize(new Dimension(
                usbTable.getPreferredScrollableViewportSize().width,
                height
        ));
    }

    private static DefaultTableModel getTableModel(){
        return defaultTableModel;
    }

    public static void addRows(HashMap<String, String> entryMap){ //(String evidenceName, String evidenceGUID, String guid, String fullSerial, String serial, String deviceType, String vendor, String vid, String pid, String product, String revision, String driveLetter, String friendlyName){

        defaultTableModel.addRow(new Object[]{
        entryMap.get("fileName"),
        entryMap.get("fileLocation"),

        });

        entryMap.put("fileName", "");
        entryMap.put("fileLocation", "");
    }
}
