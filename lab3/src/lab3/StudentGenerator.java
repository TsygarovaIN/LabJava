package lab3;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

import static lab3.RobotClass.semaphore;

public class StudentGenerator extends Thread {
    private ConcurrentLinkedQueue<StudentClass> studentQueue = new ConcurrentLinkedQueue<StudentClass>();

    private static final String[] subjects = {"Mathematics", "OOP", "Physics"};
    private static final int[] labs = {10, 20, 100};

    void setResource(ConcurrentLinkedQueue<StudentClass> studentQueue) {
        this.studentQueue = studentQueue;
    }

@Override
    synchronized public void run() {
    while (true) {
            if (studentQueue.size() < 10) {
                try {
                    semaphore.acquire();

                    int sub = new Random().nextInt(subjects.length);
                    int count = new Random().nextInt(labs.length);
                    StudentClass student = new StudentClass(subjects[sub],labs[count]);

                    studentQueue.add(student);
                    System.out.println("Generated " + student.subjectName + ", with " + student.labsCount + " labs");
                    semaphore.release();
                }

                catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
           }
        }
    }
}

