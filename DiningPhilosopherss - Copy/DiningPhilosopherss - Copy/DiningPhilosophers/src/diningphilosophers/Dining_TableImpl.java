package diningphilosophers;

import java.util.concurrent.locks.ReentrantLock;

public class Dining_TableImpl implements Dining_Table {

    private static ReentrantLock LOCK;

    // different philosopher states
    enum State {
        THINKING, Wants_to_Eat, Is_Eating
    };

    // number of philosophers on the table
    public static final int NUM_OF_PHILOSOPHERS = 5;

    // array to record each philosopher's state 
    private volatile State[] state;

    public Dining_TableImpl() {
        LOCK = new ReentrantLock();

        this.state = new State[NUM_OF_PHILOSOPHERS];

        //  intialize  all philosophers to thinking state
        for (int i = 0; i < NUM_OF_PHILOSOPHERS; i++) {
            this.state[i] = State.THINKING;
        }
    }

    // called by a philosopher when they want to eat
    @Override
    public void Hold_ChopSticks(int PHILOSOPHER_num) {
        LOCK.lock();
        try {
            this.state[PHILOSOPHER_num] = State.Wants_to_Eat;

            // checking to see if forks are available
            test(PHILOSOPHER_num);
            if (this.state[PHILOSOPHER_num] != State.Is_Eating) {
                // wait for chopsticks to be available
                SleepUtilities.nap();
            }

        } finally {
            LOCK.unlock();
        }
    }

    // called by a philosopher when they are finished eating
    @Override
    public void Release_ChopSticks(int pnum) {

        LOCK.lock();
        try {
            this.state[pnum] = State.THINKING;

            // letting neighbours know that forks are on the table to use
            test((pnum + 1) % NUM_OF_PHILOSOPHERS);
            test((pnum + 4) % NUM_OF_PHILOSOPHERS);
        } finally {
            LOCK.unlock();
        }
    }

    // test if the right and left forks is available
    private void test(int pnum) {

        if ((this.state[pnum] == State.Wants_to_Eat) &&
                (this.state[(pnum + 1) % NUM_OF_PHILOSOPHERS] != State.Is_Eating) &&
                (this.state[(pnum + 4) % NUM_OF_PHILOSOPHERS] != State.Is_Eating)) {
            this.state[pnum] = State.Is_Eating;
        }
    }
}
