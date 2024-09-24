package tobyspring.hellospring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    Sort sort;

    @BeforeEach
    void beforeEach() {
        sort = new Sort();
    }

    @Test
    void sort() {
        List<String> list = sort.sortByLength(Arrays.asList("aa", "b"));

        Assertions.assertThat(list).isEqualTo(List.of("b", "aa"));
    }

    @Test
    void sort3Item() {
        List<String> list = sort.sortByLength(Arrays.asList("aa", "ccc", "b"));

        Assertions.assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
    }

    @Test
    void sortAlreadySorted() {
        List<String> list = sort.sortByLength(Arrays.asList("b", "aa", "ccc"));

        Assertions.assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
    }
}