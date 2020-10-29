package main.ObjectClasses;

import main.Main;

//источник
public class Source {
    private static int countAllRequest;         //количество всех сгенерированных заявок

    private int number;                        //номер источника
    private static final double alpha = Main.alpha;   // для равномерного закона распределения при генерации заявки
    private static final double beta = Main.beta;
    private double prevTime;             //время генерации предыдущей заявки (-1 если заявки ещё не генерировались)
    private int countRequest = 0;          //количество заявок сгенерированных этим источником
    private int countRefusal = 0;          //количество заявок в отказе

    public Source(int number){
        prevTime = -1;
        this.number = number;
    }

    public Request generate() {
        if(prevTime == -1)
            prevTime = 0.0;

        double timeGener = 0.0;

        if(Main.systemTime == 0.0)
            timeGener = 0.0;
        else
            timeGener = prevTime + (beta - alpha) * Math.random() + alpha;

        countRequest++;
        countAllRequest++;
        Request request = new Request(timeGener, countRequest);
        prevTime = timeGener;

        System.out.println("Заявка " + getNumber() + "." + request.getNumber() + " сгенерирована");
        return request;
    }

    //если предположительное время генерации следующей заявки меньше, чем системное, то можем снова генерировать
    // т.к. источник бесконечны с равномерным законом распределения
    public boolean isReady(){
        return ((prevTime == -1)) || ((prevTime + alpha) < Main.systemTime);
    }

    public void clear(){
        prevTime = -1;
        number = 0;
    }

    public static int getCountAllRequest() {
        return countAllRequest;
    }

    public int getNumber() {
        return number;
    }

    public double getPrevTime() {
        return prevTime;
    }

    public void setPrevTime(double prevTime) {
        this.prevTime = prevTime;
    }

    public int getCountRequest() {
        return countRequest;
    }

    public int getCountRefusal() {
        return countRefusal;
    }

    public void setCountRefusal() {
        countRefusal++;
    }

    public static class Request {             //внутренний класс для заявки
        private static int countRefusal = 0;  //количество заявок, ушедших в отказ со всех источников

        private boolean inBuffer = false;     //сначал считаем, что все заявки попадают сначала в буфер, а потом на прибор
        private boolean inDevice = false;     //находится ли заявка на приборе
        private boolean inRefusal = false;    //ушла ли заявка в отказ

        private double tGiner;                //время гинерации заявки
        private static int count = 0;         //количество всех заявок из этого источника
        private int number;                   //номер заявки

        public Request(double tGiner, int number){
            this.tGiner = tGiner;
            this.number = number;
            count++;
        }

        public void setInRefusal(boolean inRefusal, int numberSource) {
            System.out.println("Заявка " + numberSource + "." + number + " ушла в отказ");
            this.inRefusal = inRefusal;
        }

        public static int getCountRefusal() {
            return countRefusal;
        }

        public static void setCountRefusal() {
            Request.countRefusal++;
        }

        public static int getCount() {
            return count;
        }

        public static void setCount(int count) {
            Request.count = count;
        }

        public boolean isInBuffer() {
            return inBuffer;
        }

        public void setInBuffer(boolean inBuffer) {
            this.inBuffer = inBuffer;
        }

        public boolean isInDevice() {
            return inDevice;
        }

        public void setInDevice(boolean inDevice) {
            this.inDevice = inDevice;
        }

        public boolean isInRefusal() {
            return inRefusal;
        }

        public double gettGiner() {
            return tGiner;
        }

        public void settGiner(double tGiner) {
            this.tGiner = tGiner;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }
}
