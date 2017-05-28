public class Program {

    public static void main(String[] args){

        try {
            System.out.println("Press any key to exit...");
            System.in.read();
        }
        catch (Throwable ex) {

            System.out.println("Program finished with error: " + ex.toString());
        }
    }
}
