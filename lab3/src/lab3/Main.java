package lab3;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class Main {
    private static ConcurrentLinkedQueue<StudentClass> studentQueue = new ConcurrentLinkedQueue<StudentClass>();
    private static StudentGenerator studentGenerator = new StudentGenerator();

    private static Semaphore semaphore = new Semaphore(1, true);

    private static RobotClass robot1 = new RobotClass("Mathematics", studentQueue, semaphore);
    private static RobotClass robot2 = new RobotClass("OOP", studentQueue, semaphore);
    private static RobotClass robot3 = new RobotClass("Physics", studentQueue, semaphore);

    public static void main(String[] args){
        studentGenerator.start();
        studentGenerator.setQueue(studentQueue);
        robot1.start();
        robot2.start();
        robot3.start();
    }
}
