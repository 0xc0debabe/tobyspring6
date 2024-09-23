package tobyspring.hellospring;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sort {

    public static void main(String[] args) {
        List<String> scores = Arrays.asList("aa1b","2ab","1vs","aa","a","aaaa");
        scores.sort((x, y) -> x.length() - y.length());
        scores.forEach(System.out::println);
    }

}
