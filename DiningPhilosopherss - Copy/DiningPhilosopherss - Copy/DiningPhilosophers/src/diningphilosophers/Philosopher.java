
package diningphilosophers;


public class Philosopher implements Runnable{
     private Dining_Table Table;
    private int philosopher_NUM;

    public Philosopher(Dining_Table Table, int philosopher_NUM) {
        this.Table = Table;
        this.philosopher_NUM = philosopher_NUM;
    }

    private void Think() {
        SleepUtilities.nap();
    }

    private void Eat() {
        SleepUtilities.nap();
    }

    // philosophers Alternate between Thinking and Eating States
    public void run() {
        while (true) {
            // thinking
            System.out.println("Philosopher's " + philosopher_NUM + " State is thinking.");
            Think();

            // wants to eat
            System.out.println("Philosopher " + philosopher_NUM + " Wants to Eat");
            Table.Hold_ChopSticks(philosopher_NUM);

            // Starts Eating
            System.out.println("philosopher " + philosopher_NUM + " Starts Eating");
            Eat();

            // finished Eating
            System.out.println("philosopher " + philosopher_NUM + " is Finished.");
            Table.Release_ChopSticks(philosopher_NUM);
        }
    }
}
