package view;

import controller.Events;
import view.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.*;
import javax.swing.border.EmptyBorder;

public class AddListProcessPanel extends JPanel{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String PROCESS_LIST_PANEL_TITLE = "Resultado de la transici\u00f3n de procesos";
	private JTable jtElement;
	private JScrollPane scroll;
	private JTabbedPane tabbedPane;

	public AddListProcessPanel(ActionListener listener, ArrayList<Object[]> getReadyList,
							   ArrayList<Object[]> getDispatchList,	ArrayList<Object[]> getExpirationTimeList,
							   ArrayList<Object[]> getInExecutionList,	ArrayList<Object[]> getWakeUpList,
							   ArrayList<Object[]> getBlockList, ArrayList<Object[]> getBlockedList,
							   ArrayList<Object[]> getOutputList){
		setBackground(Color.WHITE);
		tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		tabbedPane.add(dataTable("Lista de listos", getReadyList), "Listos");
		tabbedPane.add(dataTable("Lista de despachados", getDispatchList), "Despachados");
		tabbedPane.add(dataTable("Lista de expirados por tiempo", getExpirationTimeList), "Expirados");
		tabbedPane.add(dataTable("Lista de  en ejecuci\u00f3n", getInExecutionList), "En ejecuci\u00f3n");
		tabbedPane.add(dataTable("Lista de despertados", getWakeUpList), "Despertados");
		tabbedPane.add(dataTable("Lista de A bloquear", getBlockList), "A bloquear");
		tabbedPane.add(dataTable("Lista de bloqueados", getBlockedList), "bloqueados");
		tabbedPane.add(dataTable("Lista de finalizados", getOutputList), "Finalizados");
		add(tabbedPane);
	}



	public void createScroll(JPanel panel, String name) {
		JScrollPane scroll = new JScrollPane();
		scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
		scroll.setViewportView( panel );
		scroll.getViewport().setBackground(Color.WHITE);
		add(scroll, name);
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
		element.setColumnIdentifiers(Constant.HEADER);
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

	public void addRows(ArrayList<Object[]> list, DefaultTableModel element) {
		for(Object[] data : list) {
			data[3] = (data[3].equals(true)) ? "Bloqueado" : "Sin bloqueo";
			element.addRow(data);
		}
	}
}
