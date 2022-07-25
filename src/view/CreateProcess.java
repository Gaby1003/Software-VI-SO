package view;

import controller.Events;
import exception.EmptyTextFieldException;
import exception.PossitiveValues;
import exception.TimeInNumber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreateProcess extends JDialog {

    private JTextField nameJProcess, jSize, jTime;
    private JComboBox  blockJProcess;
    private JPanel  panel;

    public CreateProcess(ActionListener listener){
        setSize(700, 500);
        setLocationRelativeTo(null);
        setUndecorated(true);
        ((JPanel)getContentPane()).setBorder(BorderFactory.createLineBorder(Color.BLACK));
        initComponents(listener);
        setVisible(true);
    }

    private void initComponents(ActionListener listener){
        panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(0,20,0,20));
        initialPanel(listener);
        add(panel, BorderLayout.CENTER);
    }

    public void initialPanel(ActionListener listener) {
        panel.removeAll();
        addDataProcess(listener);
        panel.setBackground(Color.WHITE);
        panel.repaint();
        panel.updateUI();
        repaint();
    }

    public void addDataProcess(ActionListener listener) {
        panel.removeAll();

        JPanel panel2 = new JPanel(new GridLayout(4, 1, 40, 25));
        panel2.setOpaque(false);
        panel2.setBorder(new EmptyBorder(50, 10, 100, 10));

        JLabel nameProcess = new JLabel("Nombre del proceso");
        nameProcess.setBorder(new EmptyBorder(10, 5, 10, 5));
        nameProcess.setFont(Constant.FONT_NUNITO_TEXT);
        nameJProcess = new JTextField();


        JLabel sizeProcess = new JLabel("Tama\u00f1o del proceso");
        sizeProcess.setBorder(new EmptyBorder(10, 5, 10, 5));
        sizeProcess.setFont(Constant.FONT_NUNITO_TEXT);
        jSize = new JTextField();

        JLabel timeProcess = new JLabel("Tiempo del proceso");
        timeProcess.setBorder(new EmptyBorder(10, 5, 10, 5));
        timeProcess.setFont(Constant.FONT_NUNITO_TEXT);
        jTime = new JTextField();

        JLabel newBlockProcess = new JLabel("Bloqueado");
        newBlockProcess.setFont(Constant.FONT_NUNITO_TEXT);
        newBlockProcess.setBorder(new EmptyBorder(0, 5, 0, 5));

        blockJProcess = new JComboBox<String>();
        blockJProcess.addItem("No");
        blockJProcess.addItem("Si");
        UIManager.put("ComboBox.background",new javax.swing.plaf.ColorUIResource(Color.WHITE));

        panel2.add(nameProcess);
        panel2.add(nameJProcess);
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

        addChanges.setActionCommand(Events.ADD_PROCESS.toString());
        addChanges.addActionListener(listener);

        JButtonInformation exit = new JButtonInformation(10, 10, "Regresar", Constant.COLOR_GREEN, Color.white,
                Constant.FONT_BUTTONS);
        exit.setActionCommand(Events.EXIT_PROCESS.name().toString());
        exit.addActionListener(listener);

        panel2.add(addChanges);
        panel2.add(exit);
        return panel2;
    }

    public String[] getInfo() throws EmptyTextFieldException, PossitiveValues, TimeInNumber {
        String[] datas = {getNameProcess(), getTimeData(), getSizeData(),
                blockJProcess.getSelectedItem().toString()};
        nameJProcess.setText("");
        jSize.setText("");
        jTime.setText("");
        return  datas;
    }
    public String getNameProcess() throws EmptyTextFieldException {
        if(nameJProcess.getText().isEmpty()){
            throw new EmptyTextFieldException();
        }else{
            return nameJProcess.getText();
        }
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
}
