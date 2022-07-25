package controller;

import exception.*;
import model.*;
import model.Process;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private MainFrame mainFrame;
    private CreateProcess createProcess;
    private DialogException dialogException;
    private EditProcess editProcess;
    private DeleteProcess deleteProcess;
    private Manager manager;

    public  Controller(){
        mainFrame = new MainFrame(this);
        manager = new Manager();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Events.valueOf(e.getActionCommand())){
            case CREATE_PROCESS:
                showCreateProcess();
                break;
            case ADD_PROCESS:
                addProcess();
                break;
            case SHOW_DELETE_PROCESS:
                showDeleteProcess();
                break;
            case DELETE_PROCESS:
                deleteProcess();
                break;
            case SHOW_EDIT_PROCESS:
                showEditProcess();
                break;
            case EDIT_PROCESS:
                editProcess();
                break;
            case START_SIMULATION:
                addPartitionTab();
                break;
            case NEW_TRANSITION:
                newTransition();
                break;
            case EXIT_PROCESS:
                createProcess.dispose();
                break;
            case EXIT_DIALOG_EXCEPTION:
                dialogException.dispose();
                break;
            case EXIT_EDIT_PROCESS:
                editProcess.dispose();
                break;
            case EXIT_DElETE_PROCESS:
                deleteProcess.dispose();
                break;
            case EXIT:
                System.exit(0);
                break;
        }
    }
    private void showCreateProcess() {
        createProcess = new CreateProcess(this);
    }
    private void showEditProcess(){
        editProcess = new EditProcess(this, manager.returnList(manager.getReadyList()));
    }

    private void showDeleteProcess(){
        deleteProcess = new DeleteProcess(this, manager.returnList(manager.getReadyList()));
    }

    private void addProcess() {
        try {
            String[] datas =  createProcess.getInfo();
            manager.addNewProcess(datas[0], Long.parseLong(datas[1]),
                    Long.parseLong(datas[2]), datas[3].equals("Si") ? true : false);
            createProcess.dispose();
            mainFrame.addRowToTable(datas);
        } catch (RepeatedProcess | EmptyTextFieldException | PossitiveValues | TimeInNumber e) {
            exception(e.getMessage());
        }
    }

  private void addPartitionTab(){
      try {
          manager.makeTransition();
          mainFrame.cleanPanel();
          for (int i = 0; i < manager.getPartitionHistoryList().size(); i++) {
              mainFrame.initSimulationPanel(this, manager.getPartitionList(),
                      manager.returnList(manager.getListByPartition(manager.getPartitionHistoryList().get(i).getName(),
                              manager.getReadyList())),
                      manager.returnList(manager.getListByPartition(manager.getPartitionHistoryList().get(i).getName(),
                              manager.getDispatchList())),
                      manager.returnList(manager.getListByPartition(manager.getPartitionHistoryList().get(i).getName(),
                              manager.getExpirationTimeList())),
                      manager.returnList(manager.getListByPartition(manager.getPartitionHistoryList().get(i).getName(),
                              manager.getInExecutionList())),
                      manager.returnList(manager.getListByPartition(manager.getPartitionHistoryList().get(i).getName(),
                              manager.getWakeUpList())),
                      manager.returnList(manager.getListByPartition(manager.getPartitionHistoryList().get(i).getName(),
                              manager.getBlockList())),
                      manager.returnList(manager.getListByPartition(manager.getPartitionHistoryList().get(i).getName(),
                              manager.getBlockedList())),
                      manager.returnList(manager.getListByPartition(manager.getPartitionHistoryList().get(i).getName(),
                              manager.getOutputList())), manager.getPartitionHistoryList().get(i).getName());
          }
         /* mainFrame.addGeneralTab(mainFrame.addTransitionsTable("Lista de Particiones",
                  manager.returnPartitionList(manager.getPartitionList())), "Particiones");*/
          mainFrame.addGeneralTab((manager.returnList(manager.getReadyList())), "Listos",
                  "Particiones en listos");
          mainFrame.addGeneralTab((manager.returnPartitionList(manager.getPartitionHistoryList())), "Lista Historico",
                  "Historico de particiones");
          mainFrame.addGeneralTab((manager.returnPartitionList(manager.getNewPartition())), "Lista Nuevas Particiones",
                  "Nuevas particiones");
      } catch (RepeatedPartition e) {
          exception(e.getMessage());
      }

    }

    private void editProcess() {
        try {
            Object[] datas = editProcess.getProcessData();
            manager.editProcess((String)datas[0], (String)datas[1],Integer.valueOf((String) datas[2]),
                    Integer.valueOf((String) datas[3]),datas[4].equals("Si") ? true : false);
        } catch (NumberFormatException | EmptyTextFieldException | TimeInNumber | PossitiveValues e) {
            exception(e.getMessage());
        }
        editProcess.dispose();
        mainFrame.addToTable(manager.returnList(manager.getReadyList()), this);
    }

    private void deleteProcess(){
        manager.deleteProcess(deleteProcess.getNameState());
        manager.printList(manager.getReadyList());
        System.out.println(deleteProcess.getNameState());
        deleteProcess.dispose();
        mainFrame.addToTable(manager.returnList(manager.getReadyList()), this);
    }

    private void newTransition() {
        manager.initLists();
        mainFrame.initAddProcessPanel(this);
    }

    private void exception(String text) {
        dialogException = new DialogException(this, text);
        dialogException.setVisible(true);
    }
}
