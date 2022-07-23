package model;

import java.util.ArrayList;

public class Partition {
    private String name;
    private long partitionSize;
    private ArrayList<Process> readyList; //Listos
    private ArrayList<Process> dispatchList; //Despachar
    private ArrayList<Process> expirationTimeList; //Expiraci�n de tiempo
    private ArrayList<Process> inExecutionList; //En ejecuci�n
    private ArrayList<Process> wakeUpList; //Despertar
    private ArrayList<Process> blockList; //Bloquear
    private ArrayList<Process> blockedList; //Bloqueado
    private ArrayList<Process> outputList; //Salida
    private ArrayList<Process> noReadyList; //Salida
    private long time;

    public Partition(String name, long partitionSize, long time) {
        super();
        this.name = name;
        this.partitionSize = partitionSize;
        readyList = new ArrayList<Process>();
        dispatchList = new ArrayList<Process>();
        expirationTimeList = new ArrayList<Process>();
        inExecutionList = new ArrayList<Process>();
        wakeUpList = new ArrayList<Process>();
        blockedList = new ArrayList<Process>();
        blockList = new ArrayList<Process>();
        outputList = new ArrayList<Process>();
        noReadyList = new ArrayList<Process>();
        this.time = time;
    }

    public ArrayList<Object[]> returnList(ArrayList<Process> processes){
        ArrayList<Object[]> listObject = new ArrayList<>();
        for (Process process: processes) {
            listObject.add(process.toObjectVector());
        }
        return listObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return partitionSize;
    }

    public void setSize(long size) {
        this.partitionSize = partitionSize;
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

    public ArrayList<Process> getNoReadyList() {
        return noReadyList;
    }

    public void setNoReadyList(ArrayList<Process> noReadyList) {
        this.noReadyList = noReadyList;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Object[] toObjectVector() {
        return new Object[] {name, partitionSize};
    }
}
