package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import exception.*;

public class AddProcessPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    public static final String PROCESS_LIST_PANEL_TITLE = "Lista de procesos";
    private AddSymbolsPanel symbolsPanel;

    public AddProcessPanel(ActionListener listener){
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1,2,1,10));

        symbolsPanel = new AddSymbolsPanel(listener, PROCESS_LIST_PANEL_TITLE);
        add(symbolsPanel);
    }
    public void addRowToTable(Object[] data) {
        symbolsPanel.addRowToTable(data);
    }

    public void addRows(ArrayList<Object[]> datas) {
        symbolsPanel.addRows(datas);
    }
}
