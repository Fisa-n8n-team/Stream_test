import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelStreamTest {

    public static void main(String[] args) {

        // ===== Ignoring Parallel Stream Overhead =====
        List<Integer> smallNumbers = Arrays.asList(1, 2, 3, 4, 5);

        long startTime1 = System.nanoTime();

        smallNumbers.parallelStream()
                .map(n -> {
                    System.out.println(
                            Thread.currentThread().getName() + " processing: " + n
                    );
                    return n * n;
                })
                .forEach(System.out::println);

        long endTime1 = System.nanoTime();

        System.out.println("[비효율 코드 실행 시간(ns)] : " + (endTime1 - startTime1));
        System.out.println("--------------------------------------------------");


        // ===== :Solution =====
        List<Integer> largeNumbers = IntStream.rangeClosed(1, 1_000)
                .boxed()
                .collect(Collectors.toList());

        long startTime2 = System.nanoTime();

        List<Integer> squaredNumbers = largeNumbers.parallelStream()
                .map(n -> n * n)
                .collect(Collectors.toList());

        long endTime2 = System.nanoTime();

        System.out.println("First 10 squared numbers: "
                + squaredNumbers.subList(0, 10));
        System.out.println("[개선 코드 실행 시간(ns)] : " + (endTime2 - startTime2));
        
    }
}
