package view;

import controller.Events;
import exception.EmptyTextFieldException;
import exception.PossitiveValues;
import exception.TimeInNumber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditProcess extends JDialog {

    private JTextField newNameProcess, jSize, jTime;
    private JComboBox  blockJProcess, idProcessC;
    private JPanel  panel;

    public EditProcess(ActionListener listener, ArrayList<Object[]>datas){
        setSize(700, 500);
        setLocationRelativeTo(null);
        setUndecorated(true);
        ((JPanel)getContentPane()).setBorder(BorderFactory.createLineBorder(Color.BLACK));
        initComponents(listener, datas);
        setVisible(true);
    }

    private void initComponents(ActionListener listener, ArrayList<Object[]>datas){
        panel = new JPanel();
        panel.setBackground(Constant.COLOR_GREEN);
        initialPanel(listener, datas);
        panel.setBorder(new EmptyBorder(0,20,0,20));
        add(panel, BorderLayout.CENTER);
    }

    public void initialPanel(ActionListener listener, ArrayList<Object[]> datas) {
        panel.removeAll();
        optionProcess(listener, datas);
        panel.setBackground(Color.WHITE);
        panel.repaint();
        panel.updateUI();
        repaint();
    }

    public void optionProcess(ActionListener listener, ArrayList<Object[]>datas){
        panel.removeAll();

        JPanel panelAux = new JPanel(new GridLayout(4, 1, 20, 20));
        panelAux.setOpaque(false);
        panelAux.setBorder(new EmptyBorder(150, 0, 0, 0));

        JLabel nameProcess = new JLabel("Nombre del proceso");
        nameProcess.setBorder(new EmptyBorder(0, 40, 0, 40));

        idProcessC = new JComboBox<String>();
        for (Object[] objects : datas) {
            idProcessC.addItem(objects[0]);
        }

        idProcessC.setBorder(new EmptyBorder(0, 40, 0, 40));

        UIManager.put("ComboBox.background",new javax.swing.plaf.ColorUIResource(Color.WHITE));

        panelAux.add(nameProcess);
        panelAux.add(idProcessC);
        panelAux.add(addButtonEditProcess(listener));
        panel.add(panelAux);

        panel.repaint();
        panel.updateUI();
        repaint();
    }

    public JPanel addButtonEditProcess(ActionListener listener) {
        JPanel panel = new JPanel();

        panel.setOpaque(false);

        JButtonInformation selectProcess = new JButtonInformation(10, 10, "Seleccionar", Constant.COLOR_GREEN,
                Color.white, Constant.FONT_BUTTONS);

        selectProcess.setActionCommand(Events.ENTER.toString());
        selectProcess.addActionListener(listener);

        JButtonInformation exit = new JButtonInformation(10, 10, "Salir", Constant.COLOR_GREEN, Color.white,
                Constant.FONT_BUTTONS);
        exit.setActionCommand(Events.EXIT_EDIT_PROCESS.toString());
        exit.addActionListener(listener);

        panel.add(selectProcess);
        panel.add(exit);
        return panel;
    }

    public void addDataProcess(ActionListener listener, ArrayList<Object[]> datas,
                               Object[] infoProcess) {
        panel.removeAll();

        JPanel panel2 = new JPanel(new GridLayout(6, 2, 40, 10));
        panel2.setOpaque(false);
        panel2.setBorder(new EmptyBorder(50, 10, 0, 10));


        idProcessC = new JComboBox<String>();
        for (Object[] objects : datas) {
            idProcessC.addItem(objects[0]);
        }

        JLabel nameNewProcess = new JLabel("Nuevo nombre del proceso");
        nameNewProcess.setBorder(new EmptyBorder(10, 5, 10, 5));
        nameNewProcess.setFont(Constant.FONT_NUNITO_TEXT);
        newNameProcess = new JTextField();
        newNameProcess.setText(String.valueOf(infoProcess[0]));


        JLabel sizeProcess = new JLabel("Tama\u00f1o del proceso");
        sizeProcess.setBorder(new EmptyBorder(10, 5, 10, 5));
        sizeProcess.setFont(Constant.FONT_NUNITO_TEXT);
        jSize = new JTextField();
        jSize.setText(String.valueOf(infoProcess[2]));

        JLabel timeProcess = new JLabel("Tiempo del proceso");
        timeProcess.setBorder(new EmptyBorder(10, 5, 10, 5));
        timeProcess.setFont(Constant.FONT_NUNITO_TEXT);
        jTime = new JTextField();
        jTime.setText(String.valueOf(infoProcess[1]));


        JLabel newBlockProcess = new JLabel("Bloqueado");
        newBlockProcess.setFont(Constant.FONT_NUNITO_TEXT);
        newBlockProcess.setBorder(new EmptyBorder(0, 5, 0, 5));

        blockJProcess = new JComboBox<String>();
        blockJProcess.addItem("No");
        blockJProcess.addItem("Si");
        blockJProcess.setSelectedItem(infoProcess[3].equals(true) ? "Si" : "No");
        UIManager.put("ComboBox.background",new javax.swing.plaf.ColorUIResource(Color.WHITE));

        panel2.add(nameNewProcess);
        panel2.add(newNameProcess);
        panel2.add(sizeProcess);
        panel2.add(jSize);
        panel2.add(timeProcess);
        panel2.add(jTime);
        panel2.add(newBlockProcess);
        panel2.add(blockJProcess);

        panel.add(panel2);
        panel.add(addButtonNewProcess(listener));

        panel.repaint();
        panel.updateUI();
        repaint();
    }

    public JPanel addButtonNewProcess(ActionListener listener) {
        JPanel panel2 = new JPanel();

        panel2.setOpaque(false);
        panel2.setBorder(new EmptyBorder(0, 10, 10, 10));

        JButtonInformation addChanges = new JButtonInformation(10, 10, "Agregar", Constant.COLOR_GREEN,
                Color.white, Constant.FONT_BUTTONS);

        addChanges.setActionCommand(Events.EDIT_PROCESS.toString());
        addChanges.addActionListener(listener);

        JButtonInformation exit = new JButtonInformation(10, 10, "Regresar", Constant.COLOR_GREEN, Color.white,
                Constant.FONT_BUTTONS);
        exit.setActionCommand(Events.EXIT_EDIT_PROCESS.name().toString());
        exit.addActionListener(listener);

        panel2.add(addChanges);
        panel2.add(exit);
        return panel2;
    }

    public String[] getInfo() throws EmptyTextFieldException, PossitiveValues, TimeInNumber {
        String[] datas = {getNameProcess(), getTimeData(), getSizeData(),
                blockJProcess.getSelectedItem().toString()};
        jSize.setText("");
        jTime.setText("");
        return  datas;
    }

    public String getNameProcess() throws EmptyTextFieldException {
        if(newNameProcess.getText().isEmpty()){
            throw new EmptyTextFieldException();
        }else{
            return newNameProcess.getText();
        }
    }

    public String getNameInfo(){
        return idProcessC.getSelectedItem().toString();
    }


    public String getSizeData() throws EmptyTextFieldException, TimeInNumber, PossitiveValues{
        if(jSize.getText().isEmpty()){
            throw new EmptyTextFieldException();
        }else if((boolean)jSize.getText().matches("-?\\d+") !=true){
            throw new TimeInNumber();
        }else if(Integer.parseInt(jSize.getText()) <= 0){
            throw new PossitiveValues();
        }
        else{
            return jSize.getText();
        }
    }

    public String getTimeData() throws EmptyTextFieldException, TimeInNumber, PossitiveValues{
        if(jTime.getText().isEmpty()){
            throw new EmptyTextFieldException();
        }else if((boolean)jTime.getText().matches("-?\\d+") !=true){
            throw new TimeInNumber();
        }else if(Integer.parseInt(jTime.getText()) <= 0){
            throw new PossitiveValues();
        }
        else{
            return jTime.getText();
        }
    }

    public String getState() {
        return blockJProcess.getSelectedItem().toString();
    }
    public String[] getProcessData() throws EmptyTextFieldException, PossitiveValues, TimeInNumber  {
        String[] datas = { idProcessC.getSelectedItem().toString(), getNameProcess() ,
                getTimeData(), getSizeData(), blockJProcess.getSelectedItem().toString()};
        return datas;
    }
}
