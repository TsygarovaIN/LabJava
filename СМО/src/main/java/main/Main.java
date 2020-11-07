package main;

import main.ObjectClasses.Buffer;
import main.ObjectClasses.Device;
import main.ObjectClasses.Manager;
import main.ObjectClasses.Source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//Тут всё происходит
public class Main {
    public static double systemTime = 0.0;      //системное время моделирования
    public static double alpha = 1.0;                 //параметр alpha для времени генерации заявки
    public static double beta = 1.1;                  //параметр beta для времени генерации заявки
    public static double lambda = 0.275;                //параметр lambda для экспоненциального закона распределения обработки прибора
    public static int countSources;             //количество источников в системе
    public static int countBuffers;             //количество буферов в системе
    public static int countDevices;             //количество приборов в системе
    public static int countRequests;            //количество моделируемых заявок
    public static boolean generateIsReady = false;      //Флаг окончания основного цикла моделирования

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество источников: ");
        countSources = scanner.nextInt();
        System.out.print("Введите количество буферов: ");
        countBuffers = scanner.nextInt();
        System.out.print("Введите количество приборов: ");
        countDevices = scanner.nextInt();
        System.out.print("Введите количество моделируемых заявок: ");
        countRequests = scanner.nextInt();

        ArrayList<Source> sources = new ArrayList<Source>(countSources);
        for (int i = 0; i < countSources; i++) {
            sources.add(new Source(i + 1));
        }

        ArrayList<Buffer> buffers = new ArrayList<>(countBuffers);
        for (int i = 0; i < countBuffers; i++) {
            buffers.add(new Buffer(i + 1));
        }

        ArrayList<Device> devices = new ArrayList<>(countDevices);
        for (int i = 0; i < countDevices; i++) {
            devices.add(new Device(i + 1));
        }

        ArrayList<Source.Request> requestList = new ArrayList<>();
        for(Source s : sources)
            requestList.add(s.generate());

        Manager manager = new Manager();         //Менеджер занимается постановкой заявок на прибор из буферов

        //Главный цикл программы
        while(Source.getCountAllRequest() != countRequests) {

            //Находим заявку с минимальным временем генерации и берём её из массива заявок
            //На место этой заявки встанет новая заявка из этого же источника
            double minTime = requestList.get(0).gettGiner();
            Source.Request newRequest = requestList.get(0);  //Новая заявка, с которой работаем
            int numberSourse = requestList.get(0).getSourceNumber();   // номер источника, у которого сгенерировалась заявка
            int position = 0;

            for(int i = 0; i < requestList.size(); i++){
                if(requestList.get(i).gettGiner() < minTime) {
                    minTime = requestList.get(i).gettGiner();
                    newRequest = requestList.get(i);
                    numberSourse = requestList.get(i).getSourceNumber();
                    position = i;
                }
            }

            systemTime = newRequest.gettGiner();  //Системное вреям фиксирует событие - Генерация заявки

            requestList.remove(position);                  //Удаляем самую раннюю сгенерировшаюся заявку
            requestList.add(sources.get(numberSourse - 1).generate());  //Добавляем новую заявку из того же источника

            for (Buffer b : buffers) {          //Находим пустой буфер
                if (b.isEmpty()) {              //Если нашли, то добавляем туда новую заявку
                    b.add(newRequest, numberSourse);
                    systemTime = b.getTimeAdd();  //Системное вреям фиксирует событие - Добавлени в буфер
                    break;
                }
            }

            if (!newRequest.isInBuffer()) {    //Если заявка не была добавлена в буфер (все заняты)
                Source.Request req = buffers.get(countBuffers - 1).getRequest();       //Достаём последнюю заявку
                int numberSourceReq = buffers.get(countBuffers - 1).getNumberSource(); //и номер её источника

                req.setInRefusal(true, numberSourceReq);                  //Ставим статус "В отказ"
                sources.get(numberSourceReq - 1).setCountRefusal();
                Source.Request.setCountRefusal();                                //Увеличиваем счётчик заявок в отказе

                buffers.get(countBuffers - 1).delete();                          //Удаляем последнюю заявку в буфере
                //Добавляем информацию о нахождении заявки из нужного источника
                sources.get(numberSourceReq - 1).settBP(buffers.get(countBuffers - 1).gettForSource());

                systemTime = buffers.get(countBuffers - 1).getTimeOut();         //Системное вреям фиксирует событие - Уход заявки в отказ
                buffers.get(countBuffers - 1).add(newRequest, numberSourse);     //Ставим на её место новую заявку
                systemTime = buffers.get(countBuffers - 1).getTimeAdd();         //Системное вреям фиксирует событие - Добавлени в буфер
            }

            manager.toDevice(buffers, devices, requestList, sources);      //внутри происходит постановка заявки на прибор из буфера, если есть свободный прибор
        }

        generateIsReady = true;

        //Дорабатываем все заявки, которые есть в буферах и приборах
        boolean isAllDevicesEmpty = false;   //Проверка на пустоту всех приборов, дорабатываем, пока не будут все пустые
        while(!isAllDevicesEmpty) {
            int count = 0;
            for (Device d : devices) {
                if (d.isEmpty())
                    count++;
            }
            if (count == devices.size())
                isAllDevicesEmpty = true;
            else
                manager.toDevice(buffers, devices, requestList, sources);
        }

        //Вывод результатов
        System.out.println("\nРЕЗУЛЬТАТЫ");
        System.out.println("\nИнформация о работе каждого источника");
        for(Source s : sources) {
            System.out.println("Источник №" + s.getNumber() + " сгенерировал " + s.getCountRequest());
            System.out.println("Вероятность отказа: " + (double)s.getCountRefusal()/s.getCountRequest());
            double srTOb = s.gettObc() / s.getCountRequest();
            double srTBP = s.gettBP() / s.getCountRequest();
            System.out.println("Среднее время обслуживания заявки: " + srTOb);
            System.out.println("Среднее время ожидания заявки: " + srTBP);
            System.out.println("Среднее время прибывания заявки в системе: " + (srTOb + srTBP) + "\n");
        }
        System.out.println("\nВсего заявок в отказе " + Source.Request.getCountRefusal());

        System.out.println("\nКоэффициенты использования приборов");
        for (Device d : devices){
            System.out.println("Прибор №" + d.getNumber() + " коэффициент " + d.getTimeInDevice()/systemTime);
        }
    }
}
