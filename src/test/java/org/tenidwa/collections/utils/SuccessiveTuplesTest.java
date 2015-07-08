package org.tenidwa.collections.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Test;

public final class SuccessiveTuplesTest {
    @Test
    public void iteratesOverPairs() throws Exception {
        List<String> list = new ArrayList<>();
        SuccessiveTuples.forEach(
            Arrays.asList(1, 2, 3, 4, 5, 6),
            (a, b) -> list.add(String.valueOf(a) + " " + String.valueOf(b))
        );
        Assert.assertEquals(
            Arrays.asList(
                "1 2",
                "2 3",
                "3 4",
                "4 5",
                "5 6"
            ),
            list
        );
    }

    @Test(expected = NoSuchElementException.class)
    public void rejectsEmptyIterable() throws Exception {
        SuccessiveTuples.forEach(
            Collections.emptyList(),
            (a, b) -> System.out.println("nopping")
        );
    }

    @Test(expected = NoSuchElementException.class)
    public void rejectsSingleElementIterable() throws Exception {
        SuccessiveTuples.forEach(
            Collections.singletonList(1),
            (a, b) -> System.out.println("nopping")
        );
    }

    @Test
    public void acceptsTwoElementIterable() throws Exception {
        List<String> list = new ArrayList<>();
        SuccessiveTuples.forEach(
            Arrays.asList(1, 2),
            (a, b) -> list.add(String.valueOf(a) + " " + String.valueOf(b))
        );
        Assert.assertEquals(
            Collections.singletonList(
                "1 2"
            ),
            list
        );
    }

    @Test
    public void loopsOverPairs() throws Exception {
        List<String> list = new ArrayList<>();
        SuccessiveTuples.forEachLooped(
            Arrays.asList(1, 2, 3, 4, 5, 6),
            (a, b) -> list.add(String.valueOf(a) + " " + String.valueOf(b))
        );
        Assert.assertEquals(
            Arrays.asList(
                "1 2",
                "2 3",
                "3 4",
                "4 5",
                "5 6",
                "6 1"
            ),
            list
        );
    }


    @Test(expected = NoSuchElementException.class)
    public void loopingOverEmptyIterableFails() throws Exception {
        SuccessiveTuples.forEach(
            Collections.emptyList(),
            (a, b) -> System.out.println("nopping")
        );
    }

    @Test(expected = NoSuchElementException.class)
    public void loopingOverSingleElementIterableFails() throws Exception {
        SuccessiveTuples.forEach(
            Collections.singletonList("Anything"),
            (a, b) -> System.out.println("nopping")
        );
    }

    @Test
    public void loopsOverTriplets() throws Exception {
        List<String> list = new ArrayList<>();
        SuccessiveTuples.forEachLooped(
            Arrays.asList(1, 2, 3, 4, 5, 6),
            (a, b, c) -> list.add(
                String.format(
                    "%s %s %s",
                    String.valueOf(a),
                    String.valueOf(b),
                    String.valueOf(c)
                )
            )
        );
        Assert.assertEquals(
            Arrays.asList(
                "1 2 3",
                "2 3 4",
                "3 4 5",
                "4 5 6",
                "5 6 1",
                "6 1 2"
            ),
            list
        );
    }

    @Test(expected = NoSuchElementException.class)
    public void loopingOverEmptyTripletsIterableFails() throws Exception {
        SuccessiveTuples.forEachLooped(
            Collections.emptyList(),
            (a, b, c) -> System.out.println("nopping")
        );
    }

    @Test(expected = NoSuchElementException.class)
    public void loopingOverSingleElementTripletsIterableFails()
        throws Exception {
        SuccessiveTuples.forEachLooped(
            Collections.singletonList("Anything"),
            (a, b, c) -> System.out.println("nopping")
        );
    }
}
