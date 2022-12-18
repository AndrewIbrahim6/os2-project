package diningphilosophers;

public class DiningPhilosophers {

    public static void main(String[] args) {
        
      

        Dining_Table Table = new Dining_TableImpl();

        Philosopher[] Philosopher_Array = new Philosopher[Dining_TableImpl.NUM_OF_PHILOSOPHERS];
        //create thread for each philosopher
        // assign each philosopher to the Table
        for (int i = 0; i < Dining_TableImpl.NUM_OF_PHILOSOPHERS; i++) {
            Philosopher_Array[i] = new Philosopher(Table, i);
        }
        //  each philosopher's Thread starts
        for (int i = 0; i < Dining_TableImpl.NUM_OF_PHILOSOPHERS; i++) {
            new Thread(Philosopher_Array[i]).start();
        }
    }

}
