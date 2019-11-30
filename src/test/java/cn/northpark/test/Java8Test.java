//package cn.northpark.test;
//
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @author w_zhangyang
// * java 8 表达式练习
// */
//public class Java8Test {
//	
//	public static void main(String[] args) {
//		
//		
//		//===================================筛选======================================================
//		
//		//筛选出年龄大于20的人。
//		
//		//List<Person> list = peoples.stream().filter(person -> person.getAge()>20).collect(toList());
//		
//		//distinct，这个方法可以帮助我们去重
////		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
////		numbers.stream()
////		//过滤2的倍数的
////		.filter(i -> i % 2 == 0)
////		//去重
////		.distinct()
////		.forEach(System.out::println);//rs:24
//		
//		
//		//limit，这个方法可以让我们只取stream中的前几个，
//		//值得注意的是当我们用Set集合来存储元素时，因为Set是无序的，所以每次我们取到的前几个元素也会是无序的。
////		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
////		numbers.stream().limit(3).forEach(System.out::println);//rs:121
//		
//		//skip，这个方法可以让我们跳过元素，跳过多少个元素由我们指定。
////		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
////		numbers.stream().skip(3).forEach(System.out::println);//rs:3324
//		
//		
//		
//		//===================================筛选======================================================
//		
//		
//		
//		//===================================映射======================================================
//		
//		//map，这个方法接受一个Lambda表达式，它会遍历整个流把这个函数作用到每个元素上，然后把输出的结果放到一个新流中。
//
//		// 获取每个元素的name属性放入一个新流中，然后把这个流转为List类型。
////		List<Person> names = peoples.stream()
////				.map(Person::getName)
////				.collect(toList());
//		
//		// 获取每个元素的字符串长度放入新流中，然后转为List类型。
////		List<String> words = Arrays.asList("AA", "B", "CCC", "DDDDDD");
////		List<Integer> wordLengths = words.stream()
////				.map(String::length)
////				.collect(toList());
//		
////		flatmap，这个方法比较特殊，它的作用是把多个相同类型的流连成一个流，
////		我们看下面的代码，目的是为了把集合中的字符串都拆成单个字符然后放到一个集合，
////		但是如果直接用map方法的话返回的List是String[]类型的，这是因为word.split("")返回的结果就是这个类型的。
//
////		List<String> words = Arrays.asList("Hello", "world");
////		List<String[]> list = words.stream().map(word -> word.split("")).collect(toList());
//		
//		
////		在这里要提到Arrays.stream()这个方法，它的作用是接受一个数组，
////		然后把这个数组里的元素转为一个流，所以我们可以尝试使用这个方法来改进下上面的代码。
////		但是我们发现还是有问题，返回集合类型是Stream<String>，
////		因为map(Arrays::stream)把数组里的每个元素都弄成了一个流。
//		
//		
//
////		List<String> words = Arrays.asList("Hello", "world");
////		List<Stream<String>> list = words.stream().map(word -> word.split(""))
////		    .map(Arrays::stream).collect(toList());
//		
//		
////		现在flatmap的作用就出来了，我们把代码改进下，改成使用flatmap接受Arrays::stream，
////		它的作用正是在上面map(Arrays::stream)的基础上把集合里的流里面的元素合并成一个流，所以返回的List类型就是String类型的。
//
////		List<String> words = Arrays.asList("Hello", "world");
////		List<String> list = words.stream().map(word -> word.split(""))
////		    .flatmap(Arrays::stream).collect(toList());
//		
//		//===================================映射======================================================
//		
//		//===================================匹配======================================================
//		
////		匹配：
////		stream同样也提供了很多方法来检查集合中是否包含了某个指定的值。注意，这些方法都属于 终端操作 ，也就是说调用了这些方法就会关闭流。
//
//		//allMatch
//
////		List<String> numbers = Arrays.asList("Hello", "World");
////		boolean flag = numbers.stream().allMatch(string -> string.contains("e"));
////		System.out.println(flag);
//
//		
//		
//		//anyMatch，这个方法会检查流中是否至少有一个元素匹配给定的值，返回一个boolean值。
//
////		List<String> numbers = Arrays.asList("Hello", "World");
////		boolean flag = numbers.stream().anyMatch(string -> string.contains("e"));
////		System.out.println(flag);
//		
//		
//		//noneMatch，这个方法则是和allMatch方法做相反的操作。
//
////		List<String> numbers = Arrays.asList("Hello", "World");
////		boolean flag = numbers.stream().noneMatch(string -> string.contains("z"));
////		System.out.println(flag);
//
//		//===================================匹配======================================================
//		
//		
//		//===================================查找======================================================
//		
////		查找：
////		对于集合的操作最重要的就是从中查找符合条件的数据了，我们来看下面的方法。
//		
//		
//
//		//		findAny，这个方法需要配合filter方法使用，返回把筛选出来的第一个元素。
//		//		注意，这里返回的是Optional类型的对象，这个对象是Java 8新增的专门为了防止返回数据的时候遇到null的情况，
//		 //		后续再作详细了解，目前只需要知道它有个isPresent方法来判断元素是否为空，get方法用来取值。
//
////		List<String> numbers = Arrays.asList("Hello", "World");
////		Optional<String> optional= numbers.stream().filter(string -> string.contains("l")).findAny();
////		if(optional.isPresent()){
////			System.out.println(optional.get());
////		}
//
//		//		findFirst，这个方法是用来取流中第一个元素的，目前看来好像没什么用，但是有时候我们可能会对流进行复杂的筛选，再选取筛选后的流中第一个元素。
//
////		List<String> numbers = Arrays.asList("Hello", "World");
////		Optional<String> optional= numbers.stream().findFirst();
////		System.out.println(optional.toString());
//
//		//===================================查找======================================================
//
//		
//		//===================================加减乘除======================================================
////		归约：
////		归约就是把整个流归约成一个值的操作，比如求集合中最大的元素、所有元素值的和之类的操作。
////			* 
////		reduce，这个方法就是用来对元素的值进行操作的，我们这里做加法运算。它接受两个参数，第一个是初始值，就是开始计算前就已经有一个数值了。第二个参数是一个Lambda表达式，用来对各个元素做计算。
//
////		List<Integer> list = Arrays.asList(1,2,3,4,5);
////		int sum = list.stream().reduce(0,(a,b) -> a + b);
////		int reduce = list.stream().reduce(0,(a,b) -> a - b);
////		int cheng = list.stream().reduce(1,(a,b) -> a * b);
////		int divide = list.stream().reduce(5,(a,b) -> a / b);
////		System.out.println(reduce);
////		System.out.println(sum);
////		System.out.println(cheng);
////		System.out.println(divide);
//		
////		在Java 8中Integer中新增了一个sum方法，它的作用和上面的Lambda表达效果一样，所以我们可以使用这个方法的方法引用来简化代码。
//
//		List<Integer> list = Arrays.asList(1,2,3,4,5);
//		int sum = list.stream().reduce(0,Integer::sum);
//		System.out.println(sum);
////		我们还可以用这个方法来求最大值和最小值，在Integer中还新增了min、max方法，等同于(x, y) -> x < y ? x : y、(x, y) -> x > y ? x : y，这样我们就可以求出流中的最大值和最小值了。
//
////		Optional<Integer> min = numbers.stream().reduce(Integer::min);
////		Optional<Integer> min = numbers.stream().reduce(Integer::max);
//		//==================================加减乘除=======================================================
//
//		
//		
//		
////		//找出2011年所有的订单，按商品数量从低到高排序
////		public static void findOne(){
////		    orders.stream()
////		    //先筛选年份
////		    .filter(trader -> trader.getYear()==2011)
////		    //再排序商品数量
////		    .sorted(Comparator.comparing(Order::getTotal))
////		    .collect(Collectors.toList())
////		    .forEach(System.out::println);
////		}
////
////		//获取所有订单的里所有商品的单价 
////		public static void findTwo() {
////		    orders.stream()
////		    //先查出所有订单的里的商品价格
////		    .map(order -> order.getGoods().getPrice())
////		    //去重后转化为List类型
////		    //.distinct()
////		    //.collect(Collectors.toList())
////		    //或者直接转化为Set类型,自动去重
////		    .collect(Collectors.toSet())
////		    .forEach(System.out::println);
////		}
////
////		//查找订单中所有单价为12的商品
////		public static void findThree() {
////		    orders.stream()
////		    //获取商品的流
////		    .map(order -> order.getGoods())
////		    //价格等于12的
////		    .filter(goods -> goods.getPrice().equals(12.0))
////		    //去重
////		    .distinct()
////		    .collect(Collectors.toList())
////		    .forEach(System.out::println);
////		}
////
////		//查所有商品的名字,拼接成一个符串
////		public static void findFour() {
////		    String string = orders.stream()
////		    //获取所有商品的名字
////		    .map(order -> order.getGoods().getName())
////		    //去重
////		    .distinct()
////		    //使用reduce方法自行拼接
////		    //.reduce("",(str1,str2) -> str1 + str2);
////		    //或者使用joining方法自动拼接
////		    .collect(Collectors.joining());
////		    System.out.println(string);
////		}
////
////		//判断所有订单中是否有价格为20的商品
////		public static void findFive() {
////		    boolean flag = orders.stream()
////		    //查询是否有符合条件的元素
////		    .anyMatch(order -> order.getGoods().getPrice().equals(20.0));
////		    System.out.println(flag);
////		}
////
////		//所有订单中商品价格12的商品累计数量
////		public static void findSix() {
////		    int num = orders.stream()
////		    //筛选出价格12的商品
////		    .filter(order -> order.getGoods().getPrice().equals(12.0))
////		    //获取价格为12的商品的金额组成的流
////		    .map(Order::getTotal)
////		    //累加所有的金额
////		    .reduce(0, Integer::sum);
////		    System.out.println(num);
////		}
////
////		//找出所有订单中最大的数量
////		public static void findSeven() {
////		    int max = orders.stream()
////		    //获取所有订单的数量
////		    .map(Order::getTotal)
////		    //找到最大的
////		    .reduce(0, Integer::max);
////		    System.out.println(max);
////		}
////
////		//找出所有订单中商品数量最小的
////		public static void findEight() {
////		    orders.stream()
////		    //获取所有订单的数量
////		    .map(Order::getTotal)
////		    //找到最大的
////		    .reduce(Integer::min)
////		    //如果返回的结果不是空就打印
////		    .ifPresent(System.out::println);
////		}
//	}
//
//}
//
