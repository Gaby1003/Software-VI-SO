package view;

import controller.*;
import model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddPartitionReports extends JPanel {
    private JTabbedPane tabbedPanePartitions;
    private JTable jtElement;
    private JScrollPane scroll;
    private JTabbedPane tabbedPane;

    public AddPartitionReports() {
        tabbedPanePartitions = new JTabbedPane();
        tabbedPanePartitions.setBackground(Color.WHITE);
        setLayout(new BorderLayout());
    }

    public void addTab(ActionListener listener, ArrayList<Partition> partitions,
                       ArrayList<Object[]> getReadyList,
                       ArrayList<Object[]> getDispatchList, ArrayList<Object[]> getExpirationTimeList,
                       ArrayList<Object[]> getInExecutionList, ArrayList<Object[]> getWakeUpList,
                       ArrayList<Object[]> getBlockList, ArrayList<Object[]> getBlockedList,
                       ArrayList<Object[]> getOutputList, String name) {
        tabbedPanePartitions.add(new AddListProcessPanel(listener, getReadyList, getDispatchList,
                getExpirationTimeList, getInExecutionList, getWakeUpList, getBlockList,
                getBlockedList, getOutputList), name);
        add(tabbedPanePartitions, BorderLayout.CENTER);
        add(addButtonExit(listener), BorderLayout.SOUTH);
    }

    public void addGeneralTab(ArrayList<Object[]> datas, String name, String description){
        tabbedPanePartitions.add(dataTable("Lista de " + description, datas), name);
    }

    public JPanel dataTable(String title, ArrayList<Object[]> list){
        JPanel panel = new JPanel();

        DefaultTableModel element = new DefaultTableModel();

        JLabel nameProcess = new JLabel(title);
        nameProcess.setBorder(new EmptyBorder(0, 40, 0, 40));
        panel.setBackground(Color.WHITE);
        panel.add(nameProcess);

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        element = new DefaultTableModel();
        element.setColumnIdentifiers(Constant.HEADER_lIST_PARTITIONS);
        jtElement = new JTable();
        jtElement.setModel(element);
        jtElement.setFont(Constant.FONT_NUNITO_TABLE);
        jtElement.getTableHeader().setFont(Constant.FONT_NUNITO_TABLE);
        jtElement.getTableHeader().setBackground(Constant.COLOR_BLUE_2);
        scroll = new JScrollPane(jtElement);
        scroll.getViewport().setBackground(Color.WHITE);
        panel.add(scroll, BorderLayout.PAGE_END);
        addRows(list, element);

        return panel;
    }

    public JPanel addButtonExit(ActionListener listener) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);

        JButtonInformation newTransition = new JButtonInformation(10, 10, "Nueva transici\u00f3n", Constant.COLOR_GREEN,
                Color.BLACK, Constant.FONT_BUTTONS);
        newTransition.setActionCommand(Events.NEW_TRANSITION.toString());
        newTransition.addActionListener(listener);

        JButtonInformation exit = new JButtonInformation(10, 10, "Salir", Constant.COLOR_GREEN,
                Color.BLACK, Constant.FONT_BUTTONS);
        exit.setActionCommand(Events.EXIT.toString());
        exit.addActionListener(listener);

        panel.add(newTransition);
        panel.add(exit);
        return panel;
    }

    public void addRows(ArrayList<Object[]> list, DefaultTableModel element) {
        for(Object[] data : list) {
            element.addRow(data);
        }
    }
}
