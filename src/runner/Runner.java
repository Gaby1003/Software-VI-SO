package runner;

import controller.Controller;
import exception.RepeatedPartition;
import exception.RepeatedProcess;
import model.Manager;
import view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Runner {
	
	public static void main(String[] args) throws RepeatedProcess, RepeatedPartition {
		new Controller();

		/*Manager manager = new Manager();
		manager.addNewProcess("P1",20,20,true);
		manager.addNewProcess("P2",30,30,true);
		manager.addNewProcess("P3",40,40,true);
		manager.addNewProcess("P4",50,50,true);
		manager.addNewProcess("P5",20,20,true);
		manager.addNewProcess("P6",30,30,true);
		manager.addNewProcess("P7",40,40,true);
		manager.addNewProcess("P8",50,50,true);

		manager.makeTransition();

		manager.printPartitionList(manager.getPartitionList());
		System.out.println("-------------------");
		manager.printList(manager.getReadyList());
		System.out.println("-------------------");
		manager.printPartitionList(manager.getPartitionHistoryList());
		System.out.println("-------------------");
		manager.printPartitionList(manager.getNewPartition());*/
	}

}

