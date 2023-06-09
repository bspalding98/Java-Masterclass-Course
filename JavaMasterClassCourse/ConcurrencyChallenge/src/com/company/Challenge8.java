package com.company;

// Deadlock problem:
// when solving, lock for:
    // Is a set of locks being obtained in a different order by multiple threads (that's the case here). If so, can we force all threads to obtain the locks in teh same order?
    // Are we over-synchronizing the code?
    // Can we rewrite the code to break any circular call patterns?
    // Would using ReentrantLock objects help?

// I just used .join()

// Could also use wait() notify()

// THIS IS also over synchronised
public class Challenge8 {

    public static void main(String[] args) {
        Tutor tutor = new Tutor();
        Student student = new Student(tutor);
        tutor.setStudent(student);

        Thread tutorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                tutor.studyTime();
            }
        });

        Thread studentThread = new Thread(new Runnable() {
            @Override
            public void run() {
                student.handInAssignment();
            }
        });

        tutorThread.start();

        // Just did this to ensure that tutorThread will finish executing before the next Thread starts
        // Could also do a tryLock() and have a timeout value to ensure it will always release and not deadlock
        try {
            tutorThread.join();
        } catch (InterruptedException e) {
            System.out.println("Tutor Thread was interrupted");
        }

        studentThread.start();
    }
}

class Tutor {
    private Student student;

    public synchronized void setStudent(Student student) {
        this.student = student;
    }

    public synchronized void studyTime() {
        System.out.println("Tutor has arrived");
        try {
            // wait for student to arrive and hand in assignment
            Thread.sleep(300);
        }
        catch (InterruptedException e) {

        }
        student.startStudy();
        System.out.println("Tutor is studying with student");
    }

    public synchronized void getProgressReport() {
        // get progress report
        System.out.println("Tutor gave progress report");
    }
}

class Student {

    private Tutor tutor;

    Student(Tutor tutor) {
        this.tutor = tutor;
    }

    public synchronized void startStudy() {
        // study
        System.out.println("Student is studying");
    }

    public synchronized void handInAssignment() {
        tutor.getProgressReport();
        System.out.println("Student handed in assignment");
    }
}