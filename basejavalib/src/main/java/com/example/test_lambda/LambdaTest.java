package com.example.test_lambda;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * author: moon
 * created on: 17/11/30 下午6:23
 * description: 学习 lambda 表达式的若干形式
 */
public class LambdaTest {

    public static void main(String[] args) {
        LambdaTest t = new LambdaTest();

//        t.printIterable();

        // TODO ???????
//        t.printPredicate();

//        t.printMap();

//        t.printReduce();

//        t.printFilter();

//        t.printMatch();

//        t.printAnyStream();

//        t.testCalculateCount();

        t.printFlatMap();
    }


    /**
     * 测试 lambda 的迭代器 (报错误也能执行么。。。)
     */
    private void printIterable() {
        List<String> list = Arrays.asList("a", "b", "dddc");

        list.forEach(a -> System.out.println(a));

        list.forEach(System.out::println); //这是啥意思
    }

    /**
     * 测试 function 包下的 predicate
     */
    private void printPredicate() {
        System.out.println("printPredicate");

        List<String> list = Arrays.asList("a", "b", "dddc");

        list.stream().filter((a)-> a.startsWith("d")).forEach(
                (x)-> System.out.println(x)
        );
    }

    private void filter(List list, Predicate predicate) {
        list.stream().filter((a) -> (predicate.test(a))).forEach((a) -> {
            System.out.println(a);
        });
    }

    /**
     * 测试 map
     */
    private void printMap() {
        List<String> list = Arrays.asList("ljt", "moon", "sweet");
        list.stream().map((a) -> new StringBuffer().append(a)).forEach(b -> System.out.println(b));
    }

    /**
     * 测试 reduce
     */
    private void printReduce() {
        List<Integer> list = Arrays.asList(10, 33, 80);
        Optional<Integer> total = list.stream().map((a) -> a + 2).reduce((sum, a) -> sum + a);

        int result = total.get();

        System.out.println("加法合计 = " + result);
    }

    /**
     * 测试 过滤 filter  map  distinct
     */
    private void printFilter() {

//        Arrays.asList(10, 33, 80,3,55,15).stream()
//                .filter( x -> x>=10)   // 过滤
//                .collect(Collectors.toList())
//                .forEach(a -> System.out.println(a));
//
//        System.out.println("---------------");
//
//        Arrays.asList(10, 33, 80,3,55,15).stream()
//                .map( x -> x+1)  //转换
//                .collect(Collectors.toList())
//                .forEach(a -> System.out.println(a));
//
//        Arrays.asList(10, 33, 80,3,55,15).stream().map( x -> x+1)
//                .distinct()  // 去重
//                .forEach(a -> System.out.println(a));

        Arrays.asList(10, 33, 80,3,55,15).stream()
                .map(y -> y * 2)  //犯了个智商上的错误  y的平方应该是y*y,  之前写成了y ^ 2, 这是异或的意思
                .map(y -> y +2)
                .map(y -> new StringBuffer().append(y))
                .filter(y -> y.toString().startsWith("2"))
                .forEach(a -> System.out.println(a));
    }

    /**
     *  anymatch  allMatch  noneMatch
     */
    private void printMatch(){
        boolean result = Arrays.asList(10, 33, 80,3,55,15).stream()
//        .anyMatch(x -> x>5)
//                .allMatch( x-> x>=3)
        .noneMatch( x -> x>81)
                ;
        System.out.println(result);
    }

    /**
     *  anymatch  allMatch  noneMatch
     */
    private void printAnyStream(){
              int result =   Arrays.asList(10, 33, 80,3,55,15).stream()
                .mapToInt( (value) -> value.intValue())
                .min().getAsInt()
                ;

        System.out.println(result);
    }

    /**
     * 测试 推导参数类型
     */
    private void 推导参数类型 (){

//        new LambdaTest().test1(new Function<Long, Long>() {
//            @Override
//            public Long apply(Long aLong) {
//                return null;
//            }
//
//            @Override
//            public Long apply(Long aLong, Long aLong2) {
//                return null;
//            }
//        });

        new LambdaTest().test1( (x,y) -> x +1);
    }

    private void test1(Function<Long,Long> function){

    }

    /**
     * 学习 flatmap 功能
     * 我理解是个合并的过程，
     */
    private void printFlatMap(){
        Arrays.asList(10, 33, 80,3,55,15,3,55,15)
            .stream()
        ;

        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());


        outputStream.forEach( x -> System.out.print(x +","));

    }


    /**
     * 练习  从一个int数组集合中 得到大于50的元素的个数
     */
    private void testCalculateCount(){
        List<Integer> list = Arrays.asList(10, 33, 80,3,55,15,3,55,15);

        long count = list.stream().filter(x -> x>50).count();

        System.out.println("计算集合长度 = "+ count);

    }


}
