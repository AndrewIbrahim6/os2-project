package diningphilosophers;

public interface Dining_Table {
    // called by a philosopher when he wants to eat
    public void Hold_ChopSticks(int Philosopher_Number);

    // called by a philosopher when he is finished eatign
    public void Release_ChopSticks(int Philosopher_Number);
}
