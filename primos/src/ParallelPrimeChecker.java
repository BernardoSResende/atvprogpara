import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelPrimeChecker implements PrimeChecker {
    private final int numThreads;

    public ParallelPrimeChecker(int numThreads) {
        this.numThreads = numThreads;
    }

    private boolean isPrime(int n) {
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;
        if (n % 2 == 0 || n % 3 == 0)
            return false;

        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Integer> checkPrimes(List<Integer> numbers) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<Boolean>> futures = new ArrayList<>();
        List<Integer> primes = new ArrayList<>();

        for (int num : numbers) {
            futures.add(executor.submit(() -> isPrime(num)));
        }

        for (int i = 0; i < futures.size(); i++) {
            if (futures.get(i).get()) {
                primes.add(numbers.get(i));
            }
        }

        executor.shutdown();
        return primes;
    }
}