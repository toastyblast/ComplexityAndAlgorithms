package Assignment_2.SubTwoDEPQ;

public class APLSubTwo {
    //Variables...

    public static void main(String[] args) {
        new APLSubTwo().run();
    }

    private void run() {
        DEPQ depq = new DEPQ();

        depq.put(2, 420);
        System.out.println(depq.getMin());
        System.out.println(depq.getMax());
        System.out.println(depq.simpleArrayToString());

        depq.put(1, 69);
        System.out.println(depq.getMin());
        System.out.println(depq.getMax());
        System.out.println(depq.simpleArrayToString());

        //Code here...
    }
}
