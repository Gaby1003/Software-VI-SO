package view;

import java.awt.*;
import java.awt.event.ActionListener;

import exception.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.Events;

public class AddDataProcess extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField idProcess, timProcess;
	private JComboBox statProcess;

	public AddDataProcess(ActionListener listener) {
		UIManager.put("ComboBox.background",new javax.swing.plaf.ColorUIResource(Color.WHITE));
		repaint();
		setOpaque(false);
		initComponents(listener);
		setVisible(true);
	}

	private void initComponents(ActionListener listener) {
		setBorder(new EmptyBorder(180, 40, 0, 40));
		add(addDataProcess());
        add(addButtonNewProcess(listener));
	}
    
	public JPanel addDataProcess() {
        JPanel panel = new JPanel(new GridLayout(3,1, 20, 20));
		panel.setOpaque(false);

        JLabel nameProcess = new JLabel("Nombre del proceso");
		nameProcess.setBorder(new EmptyBorder(0, 40, 0, 40));
        JLabel timeProcess = new JLabel("Tiempo del proceso");
		timeProcess.setBorder(new EmptyBorder(0, 40, 0, 40));
        JLabel stateProcess = new JLabel("Estado del proceso");
		stateProcess.setBorder(new EmptyBorder(0, 40, 0, 40));

		idProcess = new JTextField();
		idProcess.setSize(10,10);

		System.out.print(idProcess.getText());
        timProcess = new JTextField();
        
		statProcess = new JComboBox<String>();
		statProcess.addItem("Bloqueado");
		statProcess.addItem("Sin bloqueo");

		panel.add(nameProcess);
		panel.add(idProcess);
        panel.add(timeProcess);
        panel.add(timProcess);
        panel.add(stateProcess);
        panel.add(statProcess);
		return panel;
	}

	public JPanel addButtonNewProcess(ActionListener listener) {
		JPanel panel = new JPanel(new GridLayout(3,1, 20, 20));

		panel.setOpaque(false);
		panel.setBorder(new EmptyBorder(40, 40, 0, 40));

		JButtonInformation addProcess = new JButtonInformation(10, 10, "Agregar proceso", Constant.COLOR_GREEN,
				Color.white, Constant.FONT_BUTTONS);

        addProcess.setActionCommand(Events.ADD_PROCESS.toString());
		addProcess.addActionListener(listener);

		JButtonInformation start = new JButtonInformation(10, 10, "Iniciar simulaci\u00f3n", Constant.COLOR_GREEN,
        Color.white, Constant.FONT_BUTTONS);
		start.setActionCommand(Events.START_SIMULATION.toString());
		start.addActionListener(listener);

		JButtonInformation editProcess = new JButtonInformation(10, 10, "Editar proceso", Constant.COLOR_GREEN,
        Color.white, Constant.FONT_BUTTONS);
		editProcess.setActionCommand(Events.EDIT_PROCESS.toString());
		editProcess.addActionListener(listener);

		panel.add(addProcess);
		panel.add(editProcess);
		panel.add(start);
		return panel;
	}
	
	public String[] getProcessData() throws EmptyTextFieldException, PossitiveValues, TimeInNumber  {
		String[] datas = {getNameProcess(), getTimeProcess(), statProcess.getSelectedItem().toString()};
		idProcess.setText("");
		timProcess.setText("");
		return datas;
	}
	
    public String getNameProcess() throws EmptyTextFieldException {
        if(idProcess.getText().isEmpty()){
            throw new EmptyTextFieldException();
        }else{
            return idProcess.getText();
        }
    }

	public String getTimeProcess() throws EmptyTextFieldException, TimeInNumber, PossitiveValues{
        if(timProcess.getText().isEmpty()){
            throw new EmptyTextFieldException();
		}else if((boolean)timProcess.getText().matches("-?\\d+") !=true){
			throw new TimeInNumber();
		}else if(Integer.parseInt(timProcess.getText()) <= 0){
			throw new PossitiveValues();
		}
		else{
            return timProcess.getText();
        }
    }

}