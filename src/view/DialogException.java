package view;

import controller.Events;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class DialogException extends JDialog {

	private static final long serialVersionUID = 1L;

	public DialogException(ActionListener listener, String text) {
		setSize(500, 300);
		initComponents(listener, text);	
		setLocationRelativeTo(null);
		setBackground(Color.WHITE);
		setUndecorated(true);
		setVisible(true);
	}

	public void initComponents(ActionListener listener, String text) {
		setLayout(new BorderLayout());
		addText(listener, text);
		addButtonOptions(listener, text);
	}

	private JPanel addText(ActionListener listener, String text) {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(Color.WHITE);
		JLabel nameException = new JLabel(text);
		nameException.setFont(Constant.FONT_NUNITO_TEXT);
		nameException.setBorder(new EmptyBorder(60, 60, 20, 40));

		panel.add(nameException);
		add(panel, BorderLayout.CENTER);
		return panel;
	}

	private void addButtonOptions(ActionListener listener, String text) {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(Color.WHITE);
		
		JButtonInformation exit = new JButtonInformation(10, 10, "Salir", Constant.COLOR_GREEN, Color.white,
				Constant.FONT_BUTTONS);
		exit.setActionCommand(Events.EXIT_DIALOG_EXCEPTION.toString());
		exit.addActionListener(listener);
		exit.setBorder(new EmptyBorder(10, 50, 10, 50));

		panel.add(exit);
		add(panel, BorderLayout.SOUTH);
	}
}