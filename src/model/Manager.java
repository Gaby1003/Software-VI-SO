package model;

import com.sun.xml.internal.ws.wsdl.writer.document.Part;
import exception.RepeatedPartition;
import exception.RepeatedProcess;

import java.util.ArrayList;
import java.util.Collections;

public class Manager {

    private static final long EXECUTION_TIME = 5;
    private ArrayList<Partition> partitionList;
    private ArrayList<Partition> partitionHistoryList;
    private ArrayList<Process> readyList; //Listos
    private ArrayList<Process> dispatchList; //Despachar
    private ArrayList<Process> expirationTimeList; //Expiraci�n de tiempo
    private ArrayList<Process> inExecutionList; //En ejecuci�n
    private ArrayList<Process> wakeUpList; //Despertar
    private ArrayList<Process> blockList; //Bloquear
    private ArrayList<Process> blockedList; //Bloqueado
    private ArrayList<Process> outputList; //Salida
    private ArrayList<Partition> newPartition; //Salida
    private ArrayList<Process> timeList; //Listos

    public Manager() {
        initLists();
    }

    public void initLists() {
        timeList = new ArrayList<Process>();
        this.partitionList = new ArrayList<Partition>();
        readyList = new ArrayList<Process>();
        dispatchList = new ArrayList<Process>();
        expirationTimeList = new ArrayList<Process>();
        inExecutionList = new ArrayList<Process>();
        wakeUpList = new ArrayList<Process>();
        blockedList = new ArrayList<Process>();
        blockList = new ArrayList<Process>();
        outputList = new ArrayList<Process>();
        newPartition = new ArrayList<Partition>();
        partitionHistoryList = new ArrayList<Partition>();
    }

    public void addNewPartition(String name, long size, long time) throws RepeatedPartition {
        if (validateNamePartition(name)) {
            partitionList.add(new Partition(name, size, time));
            partitionHistoryList.add(new Partition(name, size, time));
        } else {
            throw new RepeatedPartition();
        }
    }

    public void deleteProcess(String name){
        for (int i = 0; i < readyList.size(); i++) {
            if(readyList.get(i).getName().equals(name)){
                readyList.remove(i);
            }
        }
    }

    public void editProcess(String name, long time, long size, boolean blocked){
        for (int i = 0; i < readyList.size(); i++) {
            if(readyList.get(i).getName().equals(name)){
                readyList.get(i).setName(name);
                readyList.get(i).setTime(time);
                readyList.get(i).setSize(size);
                readyList.get(i).setBlocked(blocked);
            }
        }
    }

    public void addNewProcess(String name, long time, long size, boolean blocked) throws RepeatedProcess {
        if (validateNameProcess(name)) {
            readyList.add(new Process(name, time, size, blocked, null));
            timeList.add(new Process(name, time, size, blocked, null));
        } else {
            throw new RepeatedProcess();
        }
    }

    private int findPartition(String partition) {
        for (int i = 0; i < partitionList.size(); i++) {
            if (partition != null && partition.equals(partitionList.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Process> getListByPartition(String namePartition, ArrayList<Process> array){
        ArrayList<Process> arrayList = new ArrayList<Process>();
        for (int i = 0; i < array.size(); i++) {
            if(array.get(i).getNamePartition() != null &&
                    array.get(i).getNamePartition().equals(namePartition)){
                arrayList.add(array.get(i));
            }
        }
        return arrayList;
    }

    private boolean validateNamePartition(String name) {
        for (int i = 0; i < partitionList.size(); i++) {
            if (partitionList.get(i).getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    private boolean validateNameProcess(String name) {
        for (int i = 0; i < readyList.size(); i++) {
            for (int j = 0; j < readyList.size(); j++) {
                if (readyList.get(j).getName().equals(name)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void addPartitions() throws RepeatedPartition {
        for (int i = 0; i < readyList.size(); i++) {
            Partition partition = new Partition("PAR"+(i+1), readyList.get(i).getSize(),
                    readyList.get(i).getTime());
            addNewPartition(partition.getName(), partition.getSize(), partition.getTime());
            readyList.get(i).setNamePartition(partition.getName());
        }
    }

    public void makeTransition() throws RepeatedPartition {
        addPartitions();
        for (int i = 0; i < readyList.size(); i++) {
            Process process = new Process(readyList.get(i).getName(),
                    readyList.get(i).getTime(), readyList.get(i).getSize(),
                    readyList.get(i).isBlocked(), readyList.get(i).getNamePartition());
            validateProcess(process, i);
        }
        makeCondensation();
    }

    private void makeCondensation() throws RepeatedPartition {
        while (partitionList.size() > 1){
            for (int i = 0; i < partitionList.size(); i++) {
                evaluatePartitions(partitionList.get(i).getName());
            }
        }
    }

    public void validateProcess(Process process, int i) throws RepeatedPartition {
        process.setTime(process.getTime() - EXECUTION_TIME < 0 ?
                0 : process.getTime() - EXECUTION_TIME);
        partitionList.get(findPartition(process.getNamePartition())).setTime(process.getTime());
        dispatchList.add(readyList.get(i));
        inExecutionList.add(process);
        isExpirationTime(process);
    }

    private void isExpirationTime(Process process) throws RepeatedPartition {
        if(process.getTime() > 0) {
            isBlocked(process);
            readyList.add(process);
        }else {
            evaluatePartitions(process.getNamePartition());
            outputList.add(process);
        }
    }

    private void evaluatePartitions(String name) throws RepeatedPartition {
        int position = findPartition(name);
        if(position != 0){
            if(partitionList.get(position - 1).getTime() <= 0 ){
                replacePartitions(position, (position - 1));
            }
        }else if(position < partitionList.size()){
            if(partitionList.get(position + 1).getTime() <= 0 ){
                replacePartitions(position, position + 1);
            }
        }
    }

    private void replacePartitions(int position, int position2) throws RepeatedPartition {
        Partition partition = partitionList.get(position);
        Partition partition2 = partitionList.get(position2);
        addNewPartition(partition.getName() + " - " + partition2.getName(),
                partition.getSize() + partition2.getSize(), Long.parseLong("0"));
        newPartition.add(new Partition(partition.getName() + " - " + partition2.getName(),
                partition.getSize() + partition2.getSize(), Long.parseLong("0")));
        if(position > position2 ){
            partitionList.remove(position);
            partitionList.remove(position2);
        } else{
            partitionList.remove(position2);
            partitionList.remove(position);
        }
    }

    private void isBlocked(Process process) {
        if(process.isBlocked()) {
            blockList.add(process);
            blockedList.add(process);
            wakeUpList.add(process);
        }else {
            expirationTimeList.add(process);
        }
    }

    public ArrayList<Object[]> returnList(ArrayList<Process> processes){
        ArrayList<Object[]> listObject = new ArrayList<>();
        for (Process process: processes) {
            listObject.add(process.toObjectVector());
        }
        return listObject;
    }

    private int getPartitionPosition(int partitionPosition) {
        if(partitionPosition + 1 < partitionList.size()){
            partitionPosition++;
        }else{
            partitionPosition = 0;
        }
        return partitionPosition;
    }


    public void printList(ArrayList<Process> list) {
        System.out.println();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName() + " - "  + " - Time: "
                    + list.get(i).getTime() + " - " + list.get(i).getNamePartition());
        }
    }

    public void printPartitionList(ArrayList<Partition> list) {
        System.out.println();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName() + " - "  + " - Time: "
                    + list.get(i).getTime() + " - " + list.get(i).getSize());
        }
    }

    public void calculateTotalTime(){
        int quantity=0;
        for (int i = 0; i < partitionList.size(); i++) {
            for (int j = 0; j < timeList.size(); j++){
                if (timeList.get(j).getNamePartition().equals(partitionList.get(i).getName())
                        && timeList.get(j).getNamePartition() != null) {
                    quantity += timeList.get(i).getTime();
                }
            }
            partitionList.get(i).setTime(quantity);
        }
    }

    public ArrayList<Object[]> getProcessList(){
        ArrayList<Object[]> datas = new ArrayList<Object[]>();
        //Collections.sort(outputList, new ProcessTImeComparator());
        datas.addAll(getProcessNameList());
        return datas;
    }

    public ArrayList<Object[]> getProcessNameList(){
        ArrayList<Object[]> datas = new ArrayList<Object[]>();
        for (int i = 0; i < outputList.size(); i++) {
            datas.add(outputList.get(i).toObjectVector());
        }
        return datas;
    }

    public ArrayList<Object[]> getPartitionNameList() {
        ArrayList<Object[]> datas = new ArrayList<Object[]>();
        for (int i = 0; i < partitionList.size(); i++) {
            datas.add(partitionList.get(i).toObjectVector());
        }
        return datas;
    }

    public ArrayList<Partition> getPartitionList() {
        return partitionList;
    }

    public void setPartitionList(ArrayList<Partition> partitionList) {
        this.partitionList = partitionList;
    }


    public ArrayList<Process> getReadyList() {
        return readyList;
    }

    public void setReadyList(ArrayList<Process> readyList) {
        this.readyList = readyList;
    }

    public ArrayList<Process> getDispatchList() {
        return dispatchList;
    }

    public void setDispatchList(ArrayList<Process> dispatchList) {
        this.dispatchList = dispatchList;
    }

    public ArrayList<Process> getExpirationTimeList() {
        return expirationTimeList;
    }

    public void setExpirationTimeList(ArrayList<Process> expirationTimeList) {
        this.expirationTimeList = expirationTimeList;
    }

    public ArrayList<Process> getInExecutionList() {
        return inExecutionList;
    }

    public void setInExecutionList(ArrayList<Process> inExecutionList) {
        this.inExecutionList = inExecutionList;
    }

    public ArrayList<Process> getWakeUpList() {
        return wakeUpList;
    }

    public void setWakeUpList(ArrayList<Process> wakeUpList) {
        this.wakeUpList = wakeUpList;
    }

    public ArrayList<Process> getBlockList() {
        return blockList;
    }

    public void setBlockList(ArrayList<Process> blockList) {
        this.blockList = blockList;
    }

    public ArrayList<Process> getBlockedList() {
        return blockedList;
    }

    public void setBlockedList(ArrayList<Process> blockedList) {
        this.blockedList = blockedList;
    }

    public ArrayList<Process> getOutputList() {
        return outputList;
    }

    public void setOutputList(ArrayList<Process> outputList) {
        this.outputList = outputList;
    }

    public ArrayList<Partition> getPartitionHistoryList() {
        return partitionHistoryList;
    }

    public void setPartitionHistoryList(ArrayList<Partition> partitionHistoryList) {
        this.partitionHistoryList = partitionHistoryList;
    }

    public ArrayList<Partition> getNewPartition() {
        return newPartition;
    }

    public void setNewPartition(ArrayList<Partition> newPartition) {
        this.newPartition = newPartition;
    }
}
