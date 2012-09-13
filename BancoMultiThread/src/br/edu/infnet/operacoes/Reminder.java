package br.edu.infnet.operacoes;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Simple demo that uses java.util.Timer to schedule a task 
 * to execute once 5 seconds have passed.
 */

public class Reminder {
    Timer timer;

    public Reminder(int seconds) {
        timer = new Timer();
      
 
        timer.schedule(new RemindTask(), new Date(), seconds*1000);
        
       
    }

    class RemindTask extends TimerTask {
        public void run() {
            System.out.println("Time's up!");
           
        }
    }

    public static void main(String args[]) {
        new Reminder(5);
        System.out.println("Task scheduled.");
        System.out.println("Outra Operação 1");
        System.out.println("Outra Operação 2");
        System.out.println("Outra Operação 3");
        System.out.println("Outra Operação 4");
        System.out.println("Outra Operação 5");
    }
}




