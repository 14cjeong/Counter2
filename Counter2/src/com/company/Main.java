package com.company;

//Demonstrating a race condition
//and also showing an example of synchronization
//KEEP CODE THAT IS SYNCHRONIZED TO AN ABSOLUTE MINIMUM
public class Main {

    public static void main(String[] args) {
      /*  Countdown countdown = new Countdown();

        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");
        CountdownThread t2 = new CountdownThread(countdown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();*/

        //Creating two objects, so we can have one object
        //per thread
        //because in line 10 and 12, we're using the same
        //object, "CountdownThread"
        //So do the following:

       /* Countdown countdown1 = new Countdown();
        Countdown countown2 = new Countdown();

        CountdownThread t1 = new CountdownThread(countdown1);
        t1.setName("Thread 1");
        CountdownThread t2 = new CountdownThread(countown2);
        t2.setName("Thread 2");

        t1.start();
        t2.start();*/

        //Now, there is no interference!
        //But unfortunatley, this solution won't work in
        //real world applications
        //Here's an example:
        //Imagine if the objects represented here
        //were bank accounts or employee records
        //we can't provide each thread with a different object
        //it wouldn't make sense to do that
        //Bank accounts or employee records would be stored
        //in the same object to maintain integrity of the data
        //Now we can have multiple threads to act on the same
        //object, such as a thread for depositing, one for
        //withdrawing, etc, for all of the bank account data

        //The process of controlling when threads execute
        //code and therefore when they can access the heap
        //is called
        //SYNCHRONIZATION
        //So we can "synchronize" methods and statements
        //When a method is synchronized, only one thread
        //can execute that at a time
        //In other words, any other thread that wants to
        //execute that method will be suspended.
        //Now, if there are three synchronized methods
        //only one synchronized method can be run (only by
        //one thread, remember)

        //We need to synchronize all areas that threads touch
        //to avoid thread interference
        //synchronizing is really easy
        //you just have to add the synchronize keyword
        //to a method declaration
        //It's been added to line 22



        Countdown countdown = new Countdown();

        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");
        CountdownThread t2 = new CountdownThread(countdown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();

        //But, we can't synchronize constructors
        //Doesn't make sense to do that anyway
        //only one thread can construct an instance
        //and until the constructor has finished executing
        //the instants won't be available for other
        //threads to use anyway


    }
}


class Countdown {


    private int i;

    //Now what the synchronized method does is that
    //it makes the whole method run BEFORE it is run again
    //by another method
    public synchronized void doCountdown() {
        String color;

        switch (Thread.currentThread().getName()) {
            case "Thread 1":
                color = ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;
            default:
                color = ThreadColor.ANSI_GREEN;
        }

        //Now, every java object has what's called an "Intrinsic Lock"
//Others may call it a "monitor"
//We can synchronize a block of statements
//that work with an object by forcing threads
//to acquire the objects lock before they execute
//the statement

//Now, only one thread can hold the lock at a time
//so other threads that want the lock will be
//suspended until the running thread releases it
//The only one of the waiting threads can get the lock
//and continue executing

        //the only block of statement that we have to
        //synchronize is the for loop
        //just looking at the code
        //thread interference doesn't affect the rest of
        //the code since everything else is simply
        //assigning colors based on the thread name

        //We can't synchronize the counter variable
        //because it's a primitive type not an object
        //primitive types also don't have intrinsic locks

        //HOW TO SYNCHRONIZE A BLOCK OF STATMENT(S)
      /*  synchronized (color) {
        for (i = 10; i > 0; i--) {
            System.out.println(color + Thread.currentThread().getName() + ": i =" + i);
        }
    }*/
        //if you tried to synchronize the object, color,
        //you'll find that the sout will still skip
        //this is because we're using a local variable
        //Explanation:
        //Remember that local variables are stored in the
        //thread stack, and each thread will
        //create its OWN COPY of the local variable
        //and EACH COPY becomes an object that its its
        //OWN LOCK.
        //To avoid interference we need the threads to
        //COMPETE with the SAME LOCK because they are
        //accessing the same heap
        //the ONLY EXCEPTION to this rule is using
        //a String variable (or object) because it's
        //reused within the JVM
        //The JVM uses string pools for allocation of string objects
        //Simply don't use loval variables to synchronize
        //to make it easier for yourself

        //Also an important note is that
        //Object references are saved on the thread stack
        //But object values are stored in the heap
        //since the threads will create their own
        //copy of the object, so the object references
        //will then be different,
        //there wont be any interference even though
        //the object values are in the heap
        //Essentially the thread stack will only ever contain
        //primitive values and object references (and function references)

        //remember to delete the synchronized in line 101
        synchronized (this) {
            for (i = 10; i > 0; i--) {
                System.out.println(color + Thread.currentThread().getName() + ": i =" + i);
            }
        }

        //you can also synchronize static objects and static methods
        //when that happens, the lock that's used is owned
        //by the class object associated with the object's class
        //Not sure if that second prepositioinal phrase was necessary...
    }
}

class CountdownThread extends Thread {
    private Countdown threadCountdown;

    public CountdownThread(Countdown countdown) {
        this.threadCountdown = countdown;
    }

    public void run() {
        threadCountdown.doCountdown();
    }

}

//Ways to prevent a race condition:
//1) Synchronize all methods that threads touch
//2) Synchronize a block of statements rather than
//an entire method

//Now, every java object has what's called an "Intrinsic Lock"
//Others may call it a "monitor"
//We can synchronize a block of statements
//that work with an object by forcing threads
//to acquire the objects lock before they execute
//the statement

//Now, only one thread can hold the lock at a time
//so other threads that want the lock will be
//suspended until the running thread releases it
//The only one of the waiting threads can get the lock
//and continue executing

