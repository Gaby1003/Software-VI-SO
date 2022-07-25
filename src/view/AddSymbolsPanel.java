package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddSymbolsPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel element;
    private JTable jtElement;    
	private JScrollPane scroll;

    public AddSymbolsPanel(ActionListener listener, String title){
    	setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(null,title,  1, 1,
				Constant.FONT_NUNITO_TEXT));
        initComponents();
	}

    private void initComponents() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		element = new DefaultTableModel();
		element.setColumnIdentifiers(Constant.HEADER);
		jtElement = new JTable();
		jtElement.setModel(element);
		jtElement.setFont(Constant.FONT_NUNITO_TABLE);
		jtElement.getTableHeader().setFont(Constant.FONT_NUNITO_TABLE);
		jtElement.getTableHeader().setBackground(Constant.COLOR_BLUE_2);
		scroll = new JScrollPane(jtElement);
		scroll.getViewport().setBackground(Color.WHITE);
		this.add(scroll, BorderLayout.CENTER);
	}
	
	public void addRowToTable(Object[] data) {
		data[3] = (data[3].equals(true) || data[3].equals("Si")) ? "Bloqueado" : "Sin bloqueo";
		element.addRow(data);
	}
	
	public void addRows(ArrayList<Object[]> list) {
		for(Object[] data : list) {
			addRowToTable(data);
		}
	}
}
