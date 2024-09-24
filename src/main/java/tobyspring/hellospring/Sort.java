package tobyspring.hellospring;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sort {

    public List<String> sortByLength(List<String> list) {
        list.sort(((o1, o2) -> o1.length() - o2.length()));
        return list;
    }

}
