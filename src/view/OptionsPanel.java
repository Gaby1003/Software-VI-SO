package view;

import controller.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class OptionsPanel extends JPanel {

    public OptionsPanel(ActionListener listener) {
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setVisible(true);
        initComponents(listener);
    }

    private void initComponents(ActionListener listener) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1,15,20));
        panel.setBackground(Color.white);

        panel.add(createButton(listener, "Crear proceso", Events.CREATE_PROCESS.toString()));
        panel.add(createButton(listener, "Eliminar proceso", Events.SHOW_DELETE_PROCESS.toString()));
        panel.add(createButton(listener, "Editar proceso", Events.SHOW_EDIT_PROCESS.toString()));
        panel.add(createButton(listener, "Iniciar simulación", Events.START_SIMULATION.toString()));
        panel.setBorder(new EmptyBorder(100,30,200,30));
        add(panel, BorderLayout.CENTER);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.WHITE);
        panel1.add(createButton(listener, "Salir", Events.EXIT.toString()), BorderLayout.SOUTH);
        panel1.setBorder(new EmptyBorder(50,30,10,30));
        add(panel1, BorderLayout.SOUTH);
    }

    private JButtonInformation createButton(ActionListener listener, String text, String command) {
        JButtonInformation addChanges = new JButtonInformation(10, 10, text,
                Constant.COLOR_GREEN, Color.white, Constant.FONT_BUTTONS);
        addChanges.setBorder(new EmptyBorder(15,15,15,15));
        addChanges.setActionCommand(command);
        addChanges.addActionListener(listener);
        return addChanges;
    }
}