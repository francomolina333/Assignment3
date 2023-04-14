import java.util.*;

public class TempReport {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < 60; i++) {
                        generateTemp();
                    }
                }
            });
            thread.start();
        }
        for (int i = 0; i < 60; i++) {
            int number = random.nextInt(171) - 100; // generates a random temperature from -100 to 70
            numbers.add(number);
            System.out.println(number);
            Thread.sleep(1000); // takes a reading every second
        }

        System.out.println("Generated numbers: " + numbers);

        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        sortedNumbers.sort(Integer::compareTo);
        List<Integer> highestNumbers = sortedNumbers.subList(sortedNumbers.size() - 5, sortedNumbers.size());
        List<Integer> lowestNumbers = sortedNumbers.subList(0, 5);

        System.out.println("Highest numbers: " + highestNumbers);
        System.out.println("Lowest numbers: " + lowestNumbers);

        int highestInterval = 0;
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 10; j < numbers.size(); j++) {
                if (Math.abs(numbers.get(i) - numbers.get(j)) > highestInterval) {
                    highestInterval = Math.abs(numbers.get(i) - numbers.get(j));
                }
            }
        }

        System.out.println("Highest interval between two numbers that are 10 generations apart: " + highestInterval);
    }

    public synchronized static void generateTemp() {
        int number = random.nextInt(171) - 100; // generates a random temperature from -100 to 70
        numbers.add(number);
        System.out.println(number);
        Thread.sleep(1000); // takes a reading every second
    }
}
