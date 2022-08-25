import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class question4 {

    private static int m = 10000019;
    private static long p = 1000000007;

    public static void main(String[] args) throws Exception {
        System.out.println("### question 4a ####");
        question4.partA(100);
        System.out.println("### question 4b ####");
        question4.partB(100);
    }

    private static void partA(int times) throws Exception {
        int n = (int) Math.floor(m / 2);
        question4.makeExperiment(times, n, true);
    }

    private static void partB(int times) throws Exception {
        int n = (int) Math.floor((m * 19) / 20);
        question4.makeExperiment(times, n, false);
    }

    private static void makeExperiment(int times, int n, boolean QP) throws Exception {
        double LPTime = 0;
        double QPTime = 0;
        double AQPTime = 0;
        double DHTime = 0;
        for (int i = 0; i < times; i++) {
            LPHashTable LPtable = new LPHashTable(m, p);
            AQPHashTable AQPtable = new AQPHashTable(m, p);
            DoubleHashTable DHtable = new DoubleHashTable(m, p);
            List<HashTableElement> randSeq = question4.getRandomSeq(n);
            DHTime += question4.measureTime(randSeq, DHtable);
            LPTime += question4.measureTime(randSeq, LPtable);
            if (QP) {
                QPHashTable QPtable = new QPHashTable(m, p);
                QPTime += question4.measureTime(randSeq, QPtable);
            }
            AQPTime += question4.measureTime(randSeq, AQPtable);

        }

        System.out.println("LPHashTable avg runtime over " + times + " experiments in seconds: "
                + (LPTime / times) / Math.pow(10, 9));

        if (QP) {
            System.out.println("QPHashTable avg runtime over " + times + " experiments in seconds: "
                    + (QPTime / times) / Math.pow(10, 9));
        }

        System.out.println("AQPHashTable avg runtime over " + times + " experiments in seconds: "
                + (AQPTime / times) / Math.pow(10, 9));

        System.out.println("DoubleHashTable avg runtime over " + times + " experiments in seconds: "
                + (DHTime / times) / Math.pow(10, 9));

    }

    private static double measureTime(List<HashTableElement> randSeq, IHashTable table) throws Exception {
        double startTime = System.nanoTime();
        for (HashTableElement elem : randSeq) {
            table.Insert(elem);
        }
        double endTime = System.nanoTime();
        double time = endTime - startTime;
        return time;
    }

    private static List<HashTableElement> getRandomSeq(int n) {
        List<HashTableElement> randSeq = new ArrayList<>();
        long Bi;
        Long Ai;
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            Bi = random.nextInt(100);
            Ai = 100 * i + Bi;
            HashTableElement hte = new HashTableElement(Ai, 0);
            randSeq.add(hte);
        }
        return randSeq;
    }
}
