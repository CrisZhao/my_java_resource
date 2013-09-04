package pongo;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: IT_Zhaoqince
 * Date: 13-8-5
 * Time: 下午3:25
 * To change this template use File | Settings | File Templates.
 */
public class Lucky {


    public boolean isLucky(int a, List<Integer> primes) {
        int sum = 0;
        int sum2 = 0;
        while (a > 0) {
            int m = a % 10;
            sum += m;
            sum2 += m * m;
            a = a / 10;
        }
        return isPrime(sum, primes) && isPrime(sum2, primes);
    }


    public List<Integer> searchPrimes(int limit) {
        List<Integer> primes = new ArrayList<Integer>();
        for (int i = 2; i < limit; i++) {
            boolean prime = true;
            for (int prime1 : primes) {
                if (i % prime1 == 0) {
                    prime = false;
                    break;
                }
            }

            if (prime) {
                primes.add(i);
            }
        }
        return primes;
    }

    public boolean isPrime(int a, List<Integer> primes) {
        return primes.contains(a);
    }

    public int lucky(int x, int y) {
        List<Integer> primes = searchPrimes(730);

        ExecutorService exec = Executors.newFixedThreadPool(10);
        List<Future<Integer>> results = new ArrayList<Future<Integer>>();
        int l = (y - x) / 10;
        for (int i = 0; i < 9; i++) {
            results.add(exec.submit(new LuckyCalculator(primes, x + i * l, x + (i + 1) * l)));
        }
        results.add(exec.submit(new LuckyCalculator(primes, x + l * 9, y + 1)));
        int count = 0;
        try {

            for (Future<Integer> result : results) {
                count += result.get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            exec.shutdown();
        }
        return count;
    }


    public class LuckyCalculator implements Callable<Integer> {

        private List<Integer> primes;
        private int start;
        private int end;

        public LuckyCalculator(List<Integer> primes, int start, int end) {
            this.primes = primes;
            this.start = start;
            this.end = end;
        }

        @Override
        public Integer call() {
            int count = 0;
            for (int i = start; i < end; i++) {
                if (isLucky(i, primes)) {
                    count++;
                }
            }
            return count;
        }
    }


    @Test
    public void test() {
        int x = 1;
        int y = 10000000;
        System.out.println(lucky(x, y));
    }
}
