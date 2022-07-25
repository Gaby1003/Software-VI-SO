package view;

import model.Partition;

import view.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame  extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String TITLE = "Sistemas Operativos";
    private JPanel mainPanel;
    private OptionsPanel optionsPanel;
    private  AddListProcessPanel listProcessPanel;
    //private AddPartitionsPanel addPartitionsPanel;
    private AddProcessPanel addProcessPanel;
    private AddPartitionReports addPartitionReports;
    public MainFrame(ActionListener listener){
        setTitle(TITLE);
        setBackground(Color.pink);
        setIconImage(new ImageIcon(getClass().getResource(Constant.ICON_APP)).getImage());
        setUndecorated(true);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
       initComponents(listener);

        setVisible(true);
    }

    public void addGeneralTab(ArrayList<Object[]> datas, String name, String description){
        addPartitionReports.addGeneralTab(datas, name, description );
    }
    public void cleanPanel(){
        mainPanel.removeAll();
        addPartitionReports = new AddPartitionReports();
    }

    public void initSimulationPanel(ActionListener listener, ArrayList<Partition> partitions,
                                    ArrayList<Object[]> getReadyList,
                                    ArrayList<Object[]> getDispatchList,    ArrayList<Object[]> getExpirationTimeList,
                                    ArrayList<Object[]> getInExecutionList,    ArrayList<Object[]> getWakeUpList,
                                    ArrayList<Object[]> getBlockList, ArrayList<Object[]> getBlockedList,
                                    ArrayList<Object[]> getOutputList, String name) {
        addPartitionReports.addTab(listener, partitions, getReadyList, getDispatchList,
                getExpirationTimeList, getInExecutionList, getWakeUpList, getBlockList,getBlockedList,
                getOutputList ,name);
        mainPanel.add(addPartitionReports, BorderLayout.CENTER);

        mainPanel.repaint();
        mainPanel.updateUI();
        repaint();
    }
    private void initComponents(ActionListener listener){
        mainPanel = new JPanel();

        mainPanel.setBackground(Color.pink);
        mainPanel.setVisible(true);
        initAddProcessPanel(listener);
       this.add(mainPanel);
    }

    public void initAddProcessPanel(ActionListener listener) {
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        optionsPanel = new OptionsPanel(listener);
        optionsPanel.setBorder(BorderFactory.createTitledBorder(null,"Menú",  1, 1,
               Constant.FONT_NUNITO_TEXT));
        optionsPanel.setPreferredSize(new Dimension(300,900));
        mainPanel.add(optionsPanel, BorderLayout.WEST);

       addProcessPanel = new AddProcessPanel(listener);
        mainPanel.add(addProcessPanel, BorderLayout.CENTER);

        mainPanel.repaint();
        mainPanel.updateUI();
        repaint();
    }

    public void addToTable(ArrayList<Object[]> datas, ActionListener actionListener) {
        initAddProcessPanel(actionListener);
        addProcessPanel.addRows(datas);
    }

    public void addRowToTable(Object[] data) {
        addProcessPanel.addRowToTable(data);
    }


    public JPanel addTransitionsTable(String title, ArrayList<Object[]> list){
        return listProcessPanel.dataTable(title, list);
    }
}
