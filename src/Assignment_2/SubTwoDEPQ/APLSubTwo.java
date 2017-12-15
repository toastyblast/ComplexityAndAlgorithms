package Assignment_2.SubTwoDEPQ;

public class APLSubTwo {
    //Variables...

    public static void main(String[] args) {
        new APLSubTwo().run();
    }

    private void run() {
        DEPQ depq = new DEPQ();

        depq.put(5, 400);
        depq.put(10, 401);
        System.out.println(depq.simpleArrayToString());
        depq.put(45, 402);
        System.out.println(depq.simpleArrayToString());
        depq.put(25, 403);
        System.out.println(depq.simpleArrayToString());
        depq.put(8, 405);
        System.out.println(depq.simpleArrayToString());
        depq.put(40, 406);
        System.out.println(depq.simpleArrayToString());
        depq.put(20, 407);
        System.out.println(depq.simpleArrayToString());
        depq.put(15, 408);
        System.out.println(depq.simpleArrayToString());
        depq.put(9, 409);
        System.out.println(depq.simpleArrayToString());
        depq.put(30, 410);
        System.out.println(depq.simpleArrayToString());
        depq.put(19, 411);

        System.out.println(depq.getMin());
        System.out.println(depq.getMax());
        System.out.println(depq.simpleArrayToString());

        //Code here...
    }
}
