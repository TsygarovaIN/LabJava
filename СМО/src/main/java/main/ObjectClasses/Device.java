package main.ObjectClasses;

import main.Main;

//прибор
public class Device {
    private static int countRequest = 0;  //количество заявок обработанных всеми приборами
    private static double allTime;       //время обработки всех заявок всеми приборами
    private static final double lambda = Main.lambda;

    private Source.Request request;     //заявка, которая находится на приборе
    private int number;                 //номер прибора
    private double timeEmpty;           //время простоя (сумма timeAdd текущей заявки - timeOut предыдущей заявки
    private double timeAdd;             //время поступения заявки
    private double timeOut;             //время ухода из прибора
    private double timeInDevice;        //время обработки заявок этим прибором
    private double timeToTreatment;     //время, которое нужно на обработку
    private int numberSource;

    public Device(int number){
        this.number = number;
       // System.out.println("Создали ПРИБОР " + this);
    }

    @Override
    public String toString() {
        return "Device{" +
                "request=" + request +
                ", number=" + number +
                ", timeEmpty=" + timeEmpty +
                ", timeAdd=" + timeAdd +
                ", timeOut=" + timeOut +
                ", timeInDevice=" + timeInDevice +
                ", timeToTreatment=" + timeToTreatment +
                ", numberSource=" + numberSource +
                '}';
    }

    public void add(Source.Request request, int numberSource){
        this.request = request;
        this.numberSource =numberSource;
        request.setInDevice(true);
        timeAdd = Main.systemTime;
        timeEmpty = timeAdd - timeOut;
        System.out.println("Заявка " + numberSource + "." + request.getNumber() + " в приборе №" + number);
        treatment();
    }

    public void delete(){
        System.out.println("Заявка " + numberSource + "." + request.getNumber() + " вышла из прибора №" + number);
        timeOut = Main.systemTime;
        timeInDevice = timeOut - timeAdd;
        allTime += timeInDevice;
        countRequest++;
        request.setInDevice(false);
        request = null;
        numberSource = 0;
    }

    public boolean isEmpty(){
        if(request == null)
            return true;
        else {
            double timeToEnd = timeAdd + timeToTreatment;       //предполагаемое время завершения
            if (timeToEnd <= Main.systemTime) {
                delete();
                return true;
            }
            else
                return false;
        }
    }

    /* Обработка заявки: вычисляем время, когда прибор должен закончить обработку
    и на каждом шаге в основном цикле программы проверяем, время обработки + время поступления <= системному времени
    если да, то освобождаем прибор методом delete(), если нет, то продолжаем работу дальше */

    public void treatment(){
        timeToTreatment = (- (1.0 - lambda)) * Math.log(Math.random());
    }

    public double getTimeToTreatment() {
        return timeToTreatment;
    }

    public void setTimeToTreatment(double timeToTreatment) {
        this.timeToTreatment = timeToTreatment;
    }

    public static int getCountRequest() {
        return countRequest;
    }

    public static void setCountRequest(int countRequest) {
        Device.countRequest = countRequest;
    }

    public static double getAllTime() {
        return allTime;
    }

    public static void setAllTime(double allTime) {
        Device.allTime = allTime;
    }

    public Source.Request getRequest() {
        return request;
    }

    public void setRequest(Source.Request request) {
        this.request = request;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getTimeEmpty() {
        return timeEmpty;
    }

    public void setTimeEmpty(double timeEmpty) {
        this.timeEmpty = timeEmpty;
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

    public double getTimeInDevice() {
        return timeInDevice;
    }

    public void setTimeInDevice(double timeInDevice) {
        this.timeInDevice = timeInDevice;
    }
}
