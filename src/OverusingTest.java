import java.util.Arrays;
import java.util.List;

class Overusing {
	
	static void badTest() {
		List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
		
        long startTime = System.nanoTime();
        
		List<String> result = names.stream()
				.filter(name -> name.startsWith("A"))
				.filter(name -> name.length() > 3)
				.map(String::toUpperCase)
				.map(name -> name + " is a name")
				.toList();
		
        long endTime = System.nanoTime();

        long durationNs = endTime - startTime;
        double durationSec = durationNs / 1_000_000_000.0;

        System.out.println("Bad: " + result);
        System.out.println("걸린 시간(ns): " + durationNs + " ns");
        System.out.printf("걸린 시간(s): %.9f 초\n", durationSec);
		
	}
	
	static void goodTest() {
		List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

        long startTime = System.nanoTime();
        
		List<String> result = names.stream()
				.filter(name -> name.startsWith("A") && name.length() > 3)
				.map(name -> name.toUpperCase() + " is a name")
				.toList();
		
        long endTime = System.nanoTime();

        long durationNs = endTime - startTime;
        double durationSec = durationNs / 1_000_000_000.0;

        System.out.println("Good: " + result);
        System.out.println("걸린 시간(ns): " + durationNs + " ns");
        System.out.printf("걸린 시간(s): %.9f 초\n", durationSec);
	}
}

public class OverusingTest {

	public static void main(String[] args) {
		Overusing.badTest();
		Overusing.goodTest();
	}

}