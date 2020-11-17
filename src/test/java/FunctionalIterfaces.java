import org.junit.Test;
import pl.lessons.java.Customer;
import pl.lessons.java.Movable;
import pl.lessons.java.Order;
import pl.lessons.java.OrderItem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class FunctionalIterfaces {

	@Test
	public void functionalInterfaceTest() {

		//TODO Functional Interface - interface with ONLY ONE abstract method
		//TODO Helpful for sequential data manipulation
		//TODO can have default methods

		Runnable r = new Runnable() {
			@Override
			public void run() {

			}
		};

		Comparable c = new Comparable() {
			@Override
			public int compareTo(Object o) {

				return 0;
			}
		};

		Movable movable = new Movable() {
			@Override
			public int move(String direction) {

				return 0;
			}
		};
	}

	@Test
	public void lambdas() {

		//TODO Lambdas - anonymous functions (have logic and argument list, doesn't have name)
		//TODO Lambdas can replace any functional interface
		//TODO Can be passed as method arguments, can be used like objects

		Consumer<String> stringConsumer = (String a) -> System.out.println(a);
	}

	@Test
	public void predicate() {

		//TODO Predicate gets one argument and returns a boolean

		List<String> testList = List.of("1", "12", "123", "1234", "12345", "123456");

		Predicate<String> stringLengthPredicate = s -> s.length() > 3;
		Predicate<String> lastCharPredicate = s -> s.endsWith("5");

		List<String> result = testList.stream()
				.filter(stringLengthPredicate)
				.filter(lastCharPredicate)
				.collect(Collectors.toList());

		System.out.println(result);
	}

	@Test
	public void consumer() {

		//TODO Consumer gets one argument and returns nothing -> changes argument

		List<Integer> testList = List.of(1, 2, 3, 4, 5, 6);

		Consumer<Integer> print = System.out::println;

		testList.forEach(print);
	}

	@Test
	public void supplier() {

		//TODO Supplier gets no arguments and returns object of T type

		Supplier<String> stringSupplier = () -> "TEST";

		System.out.println(stringSupplier.get());

	}

	@Test
	public void function() {

		//TODO Function takse one object type and returns other object type

		Function<String, Integer> stringLengthMultiplyByTwo = (baseString) -> baseString.length() * 2;

		System.out.println(stringLengthMultiplyByTwo.apply("TEST"));
	}

	@Test
	public void biFunction() {

		BiFunction<String, String, Integer> sumLengths = (x, y) -> x.length() + y.length();

		System.out.println(sumLengths.apply("1", "123"));
	}

	@Test
	public void primitiveFunctionalInterface() {

		//TODO useful for primitive types

		IntPredicate predicate = new IntPredicate() {
			@Override
			public boolean test(int i) {

				return false;
			}
		};

		ToIntFunction toIntFunction = new ToIntFunction() {
			@Override
			public int applyAsInt(Object o) {

				return 0;
			}
		};

	}

	@Test
	public void optional() {

		//TODO Wrapper for object. Wrapped object CAN BE null

		String nonNullString = "I'M NOT NULL, HURRAY!";
		String maybeNullString = null;

		Optional<String> nonEmptyStringOptional = Optional.of(nonNullString);
		Optional<String> nullOptional = Optional.ofNullable(maybeNullString);

		Supplier<String> emptySupplier = () -> "U FCKED UP BRO!";

		String alwaysString = Optional.ofNullable(maybeNullString).orElseGet(emptySupplier);
		//		        String throwingGrenade = Optional.ofNullable(maybeNullString).orElseThrow(() -> new IllegalStateException("WOHOO"));

		System.out.println(nonEmptyStringOptional.get());
		System.out.println(alwaysString);
		//        System.out.println(nullOptional.get());

		nullOptional.ifPresent(System.out::println);

		nullOptional.ifPresentOrElse(
				(value) -> System.out.println("Value is present, its: " + value),
				() -> System.out.println("Value is empty"));

		if (nonEmptyStringOptional.isPresent()) {
			System.out.println("SUPER ADVANCED LOGIC HERE");
		}

		nonEmptyStringOptional
				.filter(it -> it.endsWith("!"))
				.ifPresent(System.out::println);
	}

	@Test
	public void generateStreams() {

		List<Integer> testList = List.of(1, 2, 3, 4, 5, 6);

		testList.stream()
				.forEach(System.out::println);

		System.out.println("________________________________");

		Stream.of("1", "2", "3", "4", "5")
				.forEach(System.out::println);

		System.out.println("________________________________");

		Stream.generate(Math::random)
				.limit(10)
				.forEach(System.out::println);

		System.out.println("________________________________");

		Stream.iterate(0, i -> i + 2)
				.limit(10)
				.forEach(System.out::println);

		System.out.println("________________________________");

		IntStream.rangeClosed(1, 100)
				.filter(i -> i % 2 == 0)
				.forEach(System.out::println);

	}

	@Test
	public void streamOperations() {

		//TODO intermediate operations:
		//TODO stream.filter gets predicate
		//TODO stream.map gets function

		List<Integer> testList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		testList.stream()
				.filter(it -> it % 2 == 0)
				.map(it -> "prefix " + it.toString() + " postfix")
				.map(String::toUpperCase)
				.forEach(System.out::println);

		List<Customer> customers = List.of(new Customer("Customer1", 55), new Customer("Customer2", 12), new Customer("Customer3", 55));

		customers.stream()
				.map(Customer::getTitle)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(String::toUpperCase)
				.forEach(System.out::println);

		//TODO terminal operations:
		//TODO findFirst, anyMatch, allMatch

		Optional<Customer> first = customers.stream()
				.filter(it -> it.getAge() > 22)
				.findFirst();

		Optional<Optional<String>> first1 = customers.stream()
				.map(Customer::getTitle)
				.findFirst();

		customers.stream()
				.map(Customer::getTitle)
				.findFirst()
				.ifPresent(System.out::println);

		boolean anyMatch = testList.stream().anyMatch(it -> it % 2 == 0);
		boolean allMatch = testList.stream().allMatch(it -> it % 2 == 0);
		boolean noneMatch = testList.stream().noneMatch(it -> it % 2 == 0);

		//TODO reduce

		Integer sum = testList.stream().reduce(0, Integer::sum);
		System.out.println(sum);

		String concat = testList.stream().map(Object::toString).reduce("", (x, y) -> x + y);
		System.out.println(concat);

		Optional<Integer> maxAge = customers.stream()
				.map(Customer::getAge)
				.reduce((x, y) -> x > y
						? x
						: y);

		Optional<Integer> maxAgeEasier = customers.stream()
				.map(Customer::getAge)
				.reduce(Integer::max);

		maxAge.ifPresent(System.out::println);
		maxAgeEasier.ifPresent(System.out::println);

		//TODO Collectors

		String ageString = customers.stream()
				.map(Customer::getAge)
				.map(Object::toString)
				.collect(Collectors.joining(", "));

		System.out.println(ageString);

		Map<Integer, List<Customer>> customerByAge = customers.stream()
				.collect(Collectors.groupingBy(Customer::getAge));

		Map<Integer, Set<Customer>> customerByAgeSet = customers.stream()
				.collect(Collectors.groupingBy(Customer::getAge, toSet()));

		Map<Optional<String>, Integer> customerByTittleSumAge = customers.stream()
				.collect(Collectors.groupingBy(Customer::getTitle, summingInt(Customer::getAge)));

		Map<Integer, Long> collect = customers.stream()
				.collect(groupingBy(Customer::getAge, filtering(val -> val.getAge() > 20, counting()))); // TODO Java 9

		customerByAge.forEach((key, value) -> {
			System.out.println(key);
			System.out.println(value.get(0).getTitle());
			System.out.println("_____");
		});

		//TODO skip / distinct / sorted

		List<Integer> skipped = testList.stream()
				.skip(2)
				.collect(Collectors.toList());

		List<Integer> distinct = testList.stream()
				.distinct()
				.collect(Collectors.toList());

		List<Integer> reverse = testList.stream()
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());

		System.out.println(reverse);

		// TODO peek

		testList.stream()
				.peek(it -> System.out.println("Before: " + it))
				.filter(it -> it > 3)
				.forEach(it -> System.out.println("After filtering: " + it));

		OrderItem o1 = new OrderItem(BigDecimal.TEN);
		OrderItem o2 = new OrderItem(BigDecimal.TEN);

		OrderItem o3 = new OrderItem(BigDecimal.TEN);
		OrderItem o4 = new OrderItem(BigDecimal.TEN);

		List<OrderItem> orderItems1 = List.of(o1, o2);
		List<OrderItem> orderItems2 = List.of(o3, o4);

		Order order1 = new Order(LocalDate.now(), orderItems1);
		Order order2 = new Order(LocalDate.now(), orderItems2);

		List<Order> orderList = List.of(order1, order2);

		// TODO flatMap

		List<BigDecimal> bigDecimals = orderList.stream()
				.flatMap(it -> it.getItems().stream())
				.map(OrderItem::getPrice)
				.filter(it -> it.compareTo(BigDecimal.ZERO) > 0)
				.collect(toList());

		//TODO parrallel risks example
	}

	@Test
	public void exceptions() {

		//TODO Top production exceptions

		//TODO 1 NullPointerException

		String s = "FOOBAR";
		int i = Integer.parseInt(s);//TODO number format exception

		setAge(0); //TODO IllegalArgumentException

		long[] l = new long[Integer.MAX_VALUE]; //TODO Out of memory error

		oome(); //TODO OutOfMemoryError

		soe(5); // ToDo StackOverflowError

	}

	public void setAge(int age) {

		if (age < 1) {
			throw new IllegalArgumentException("Baby is not born yet!");
		}
	}

	public int soe(int x) {

		return soe(x + 1); //TODO StackOverflowError
	}

	public void oome() {

		List<BigDecimal> bigDecimals = new ArrayList<>();

		while (1 > 0) {
			bigDecimals.add(new BigDecimal(99));
		}
	}
}