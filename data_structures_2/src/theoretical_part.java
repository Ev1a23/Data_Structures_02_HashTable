import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Random;

public class theoretical_part {
    public static void main(String[] args) throws IHashTable.KeyDoesntExistException, IHashTable.KeyAlreadyExistsException, IHashTable.TableIsFullException {
        Set<Long> Q1 = new HashSet<Long>();
        Set<Long> Q2 = new HashSet<Long>();
        long q = 6571;
        for (int i = 0; i < q; i++) {
            Q1.add((long)Math.pow(i, 2) % q);
            Q2.add(((long)Math.pow(-1, i)*(long)Math.pow(i, 2))%q);
        }
        System.out.println("###### Q3a ######");
        System.out.println(String.format("Size of Q1 is: %s",Q1.size()));
        System.out.println(String.format("Size of Q2 is: %s",Q2.size()));

        System.out.println("\n###### Q3b ######");
        System.out.println("## QPHashTable ##");
        Random random = new Random();
        int i = 0;
        int j = 0;
        int failCnt = 0;
        long Ai, Bi;
        for (j = 0; j < 100; j++) {
            try {
                QPHashTable qpht = new QPHashTable(6571, 1000000007);
                for (i = 0; i < 6571; i++) {
                    Bi = random.nextInt(100);
                    Ai = 100 * i + Bi;
                    qpht.Insert(new HashTableElement(Ai, 0));
                }
            }
            catch (IHashTable.TableIsFullException e){
                failCnt++;
            }
        }
        System.out.println(String.format("TableIsFullException occurred %s times out of 100 experiments.", failCnt));

        System.out.println("\n## AQPHashTable ##");
        try {
            for (j = 0; j < 100; j++) {
                AQPHashTable aqpht = new AQPHashTable(6571, 1000000007);
                for (i = 0; i < 6571; i++) {
                    Bi = random.nextInt(100);
                    Ai = 100 * i + Bi;
                    aqpht.Insert(new HashTableElement(Ai, 0));
                }
            }
            System.out.println("No Exceptions");
        }
        catch (IHashTable.TableIsFullException e) {
            e.printStackTrace(System.out);
            System.out.println(String.format("The exception was thrown from iteration number: %s", i));
        }
        catch (IHashTable.KeyAlreadyExistsException e) {
            e.printStackTrace(System.out);
            System.out.println(String.format("The exception was thrown from iteration number: %s", i));
        }

        System.out.println("\n###### Q4a ######");

        LPHashTable lpht;
        QPHashTable qpht;
        AQPHashTable aqpht;
        DoubleHashTable dht;

        int n = (int) Math.floor(10000019/2);
        double startTime, endTime, lphtTime = 0, qphtTime = 0, aqphtTime = 0, dhtTime = 0;
        for (j = 0; j < 500; j++) {
            lpht = new LPHashTable(10000019, 1000000007);
            qpht = new QPHashTable(10000019, 1000000007);
            aqpht = new AQPHashTable(10000019, 1000000007);
            dht = new DoubleHashTable(10000019, 1000000007);
            for (i = 0; i < n; i++) {
                Bi = random.nextInt(100);
                Ai = 100 * i + Bi;

                startTime = System.nanoTime();
                lpht.Insert(new HashTableElement(Ai, 0));
                endTime = System.nanoTime();
                lphtTime += endTime - startTime;

                startTime = System.nanoTime();
                qpht.Insert(new HashTableElement(Ai, 0));
                endTime = System.nanoTime();
                qphtTime += endTime - startTime;

                startTime = System.nanoTime();
                aqpht.Insert(new HashTableElement(Ai, 0));
                endTime = System.nanoTime();
                aqphtTime += endTime - startTime;

                startTime = System.nanoTime();
                dht.Insert(new HashTableElement(Ai, 0));
                endTime = System.nanoTime();
                dhtTime += endTime - startTime;
            }
        }

        System.out.println(String.format("LPHashTable total runtime (avg over 500 experiments) in seconds: %s", (lphtTime/500)/Math.pow(10, 9)));
        System.out.println(String.format("QPHashTable total runtime (avg over 500 experiments) in seconds: %s", (qphtTime/500)/Math.pow(10, 9)));
        System.out.println(String.format("AQPHashTable total runtime (avg over 500 experiments) in seconds: %s", (aqphtTime/500)/Math.pow(10, 9)));
        System.out.println(String.format("DoubleHashTable total runtime (avg over 500 experiments) in seconds: %s", (dhtTime/500)/Math.pow(10, 9)));


        System.out.println("\n###### Q4b ######");

        n = (int) Math.floor((19*10000019)/20);
        lphtTime = 0;
        aqphtTime = 0;
        dhtTime = 0;
        for (j = 0; j < 200; j++) {
            lpht = new LPHashTable(10000019, 1000000007);
            aqpht = new AQPHashTable(10000019, 1000000007);
            dht = new DoubleHashTable(10000019, 1000000007);
            for (i = 0; i < n; i++) {
                Bi = random.nextInt(100);
                Ai = 100 * i + Bi;

                startTime = System.nanoTime();
                lpht.Insert(new HashTableElement(Ai, 0));
                endTime = System.nanoTime();
                lphtTime += endTime - startTime;

                startTime = System.nanoTime();
                aqpht.Insert(new HashTableElement(Ai, 0));
                endTime = System.nanoTime();
                aqphtTime += endTime - startTime;

                startTime = System.nanoTime();
                dht.Insert(new HashTableElement(Ai, 0));
                endTime = System.nanoTime();
                dhtTime += endTime - startTime;
            }
        }

        System.out.println(String.format("LPHashTable total runtime (avg over 200 experiments) in seconds: %s", (lphtTime/200)/Math.pow(10, 9)));
        System.out.println(String.format("AQPHashTable total runtime (avg over 200 experiments) in seconds: %s", (aqphtTime/200)/Math.pow(10, 9)));
        System.out.println(String.format("DoubleHashTable total runtime (avg over 200 experiments) in seconds: %s", (dhtTime/200)/Math.pow(10, 9)));

        System.out.println("\n###### Q5 ######");

        n = (int) Math.floor(10000019/2);
        double firstThreeIterTime = 0, lastThreeIterTime = 0;
        double accTime = 0;
        for (int k = 0; k < 5; k++) {
            dht = new DoubleHashTable(10000019, 1000000007);
            for (j = 0; j < 6; j++) {
                if (j == 3) {
                    firstThreeIterTime += accTime;
                    accTime = 0;
                }

                // Generating the sequence A, new sequence per iteration
                List<Long> A = new ArrayList<Long>();
                for (i = 0; i < n; i++) {
                    Bi = random.nextInt(100);
                    Ai = 100 * i + Bi;
                    A.add(Ai);
                }

                // Inserting every a in A to dht
                startTime = System.nanoTime();
                for (long a: A) {
                    dht.Insert(new HashTableElement(a, 0));
                }

                // Deleting every a in A from dht
                for (long a: A) {
                    dht.Delete(a);
                }
                endTime = System.nanoTime();
                accTime += endTime - startTime;
            }
            lastThreeIterTime += accTime;
            accTime = 0;
        }

        System.out.println(String.format("First three iterations total runtime (avg over 5 experiments) in seconds: %s", (firstThreeIterTime/5)/Math.pow(10, 9)));
        System.out.println(String.format("Last three iterations total runtime (avg over 5 experiments) in seconds: %s", (lastThreeIterTime/5)/Math.pow(10, 9)));

    }
}
