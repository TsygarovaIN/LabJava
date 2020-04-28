package lab3;

import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class RobotClass extends Thread {
    private ConcurrentLinkedQueue<StudentClass> studentQueue = new ConcurrentLinkedQueue<StudentClass>();
    private String subject;
    private boolean active = true;

    static Semaphore semaphore;

    public RobotClass(String subject, ConcurrentLinkedQueue<StudentClass> studentQueue, Semaphore semaphore) {
        this.subject = subject;
        this.studentQueue = studentQueue;
        RobotClass.semaphore = semaphore;
    }

@Override
    public void run() {
        while (true) {
            try {
                if (!studentQueue.isEmpty() && isInterrupted()) {
                    RobotClass newRobot = new RobotClass(subject, studentQueue, semaphore);
                    newRobot.start();
                }

                if (!isInterrupted() && studentQueue.element().subjectName.equals(subject) && active) {
                    semaphore.acquire();
                    System.out.println("Check " + studentQueue.remove().toString());

                    semaphore.release();
                    System.out.println("Labs in " + subject + " checked")
                    active = false;
                }
            }

            catch (InterruptedException exception) {
            exception.printStackTrace();
            }

            catch (NoSuchElementException exception) {
                interrupt();
            }
        }
    }
}
