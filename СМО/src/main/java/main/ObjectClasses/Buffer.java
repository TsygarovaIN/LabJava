package main.ObjectClasses;

import main.Main;

//буфер
public class Buffer {
    private static double allTime;         //время нахождения всех заявок во всех буферах

    private Source.Request request;       //заявка в буфере
    private int numberSource;             //номер источника из которого появилась заявка (нужно для приоритета)
    private double timeAdd;               //время заявки в момент поступления;
    private double timeOut;              //воемя выхода заявки из буфера;
    private int number;                  //номер буфера

    public Buffer(int number){
        this.number = number;
        //System.out.println("Создали БУФЕР " + this);
    }

    @Override
    public String toString() {
        return "Buffer{" +
                "request=" + request +
                ", numberSource=" + numberSource +
                ", timeAdd=" + timeAdd +
                ", timeOut=" + timeOut +
                ", number=" + number +
                '}';
    }

    public void add(Source.Request request, int numberSource){
        this.request = request;
        this.numberSource = numberSource;
        timeAdd = Main.systemTime;
        request.setInBuffer(true);
        System.out.println("Заявка " + numberSource + "." + request.getNumber() + " в буфере №" + number);
    }

    public void delete(){
        System.out.println("Заявка " + numberSource + "." + request.getNumber() + " удалена из буфера №" + number);
        request.setInBuffer(false);
        timeOut = Main.systemTime;
        allTime += timeOut - timeAdd;
        request = null;
    }

    public boolean isEmpty(){
        return request == null;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void clear(){
        request = null;
        numberSource = 0;
        timeAdd = 0;
        timeOut = 0;
        number = 0;
    }

    public static double getAllTime() {
        return allTime;
    }

    public static void setAllTime(double allTime) {
        Buffer.allTime = allTime;
    }

    public Source.Request getRequest() {
        return request;
    }

    public void setRequest(Source.Request request) {
        this.request = request;
    }

    public int getNumberSource() {
        return numberSource;
    }

    public void setNumberSource(int numberSource) {
        this.numberSource = numberSource;
    }

    public double getTimeAdd() {
        return timeAdd;
    }

    public void setTimeAdd(double timeAdd) {
        this.timeAdd = timeAdd;
    }

    public double getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(double timeOut) {
        this.timeOut = timeOut;
    }
}
