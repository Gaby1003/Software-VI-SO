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

public class DeleteProcess extends JDialog {

    private JComboBox  idProcessC;
    private JPanel  panel;

    public DeleteProcess(ActionListener listener, ArrayList<Object[]>datas){
        setSize(700, 500);
        setLocationRelativeTo(null);
        setUndecorated(true);
        ((JPanel)getContentPane()).setBorder(BorderFactory.createLineBorder(Color.BLACK));
        initComponents(listener, datas);
        setVisible(true);
    }

    private void initComponents(ActionListener listener, ArrayList<Object[]>datas){
        panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(0,20,0,20));
        initialPanel(listener, datas);
        add(panel, BorderLayout.CENTER);
        panel.repaint();
        panel.updateUI();
        repaint();
    }

    public void initialPanel(ActionListener listener, ArrayList<Object[]>datas) {
        panel.removeAll();
        addDataProcess(listener, datas);
        panel.setBackground(Color.WHITE);
        panel.repaint();
        panel.updateUI();
        repaint();
    }

    public void addDataProcess(ActionListener listener, ArrayList<Object[]> datas) {
        panel.removeAll();

        JPanel panel2 = new JPanel(new GridLayout(6, 2, 20, 5));
        panel2.setOpaque(false);
        panel2.setBorder(new EmptyBorder(50, 10, 0, 10));

        JLabel nameProcess = new JLabel("Nombre del proceso");
        nameProcess.setBorder(new EmptyBorder(10, 5, 10, 5));
        nameProcess.setFont(Constant.FONT_NUNITO_TEXT);

        idProcessC = new JComboBox<String>();
        idProcessC.setMaximumRowCount(10);
        for (Object[] objects : datas) {
            idProcessC.addItem(objects[0]);
        }

        panel2.add(nameProcess);
        panel2.add(idProcessC);

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

        JButtonInformation addChanges = new JButtonInformation(10, 10, "Eliminar", Constant.COLOR_GREEN,
                Color.white, Constant.FONT_BUTTONS);

        addChanges.setActionCommand(Events.DELETE_PROCESS.toString());
        addChanges.addActionListener(listener);

        JButtonInformation exit = new JButtonInformation(10, 10, "Regresar", Constant.COLOR_GREEN, Color.white,
                Constant.FONT_BUTTONS);
        exit.setActionCommand(Events.EXIT_DElETE_PROCESS.name().toString());
        exit.addActionListener(listener);

        panel2.add(addChanges);
        panel2.add(exit);
        return panel2;
    }
    public String getNameState() {
        return idProcessC.getSelectedItem().toString() ;
    }
}
