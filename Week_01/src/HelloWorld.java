
public class HelloWorld {

    static {
        String test = "Hello My World";
        String t2 = new String("Hello My World");
        HelloWorld helloWorld2 = new HelloWorld();
    }

    public static void main(String[] args) {

        int a = 1;
        int a1 = 3;
        short s = 4;
        long b = 2L;
        byte be = 127;

        float c = 1.0f;
        double d = 2.0d;

        char ch = 'o';
        boolean flag = true;

        int sum = a + a1;
        if (flag) {
            System.out.println(be);
        }
        for (int i = 0; i < a1; i++) {
            System.out.println(i);
        }

        HelloWorld helloWorld = new HelloWorld();


    }

}
