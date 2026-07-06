public class HelloGoodbye {
    public static void main(String[] args) {
        if (args.length < 2) {
            return;
        }
        String firstWord = args[0];
        String secondWord = args[1];
        System.out.printf("Hello %s and %s.%n", firstWord, secondWord);
        System.out.printf("Goodbye %s and %s.%n", secondWord, firstWord);
    }
}