package test.java;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PriorityQueueTest{
    static Stream<Arguments> streamProvider() {
        return Stream.of(
            Arguments.of(new int[] {5, 4, 3, 2}, new int[] {2, 3, 4, 5}),
            Arguments.of(new int[] {-3, 1, -100, 7}, new int[] {-100, -3, 1, 7}),
            Arguments.of(new int[] {1, 1, 2, 1}, new int[] {1, 1, 1, 2}),
            Arguments.of(new int[] {0, -1, -2, -3, -4}, new int[] {-4, -3, -2, -1, 0}),
            Arguments.of(new int[] {1, 2, 3, 4, 5, 6}, new int[] {1, 2, 3, 4, 5, 6})
        );
    }

    @ParameterizedTest(name="#{index} - Test with Argument={0},{1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array){
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        int index = 0; // for cnt index
        Integer s; // for PriorityQueue.poll()
        int[] result = new int[random_array.length];

        // random_array add to PriorityQueue
        for(int i=0; i<random_array.length; i++){
            test.add(random_array[i]);
        }

        // get PriorityQueue result
        for(int i=0; i<random_array.length; i++) {
            s = test.poll();
            result[i] = s;
        }

        assertArrayEquals(correct_array, result);

    }

    @Test
    public void whenExceptionThrow_thenInitialCapacityNotGreaterThanOne(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<Integer>(0); // initial Capacity should be greater than 1
        });

        String expectedMessage = null;
        String actualMessage = exception.getMessage(); // the exception message is null
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void whenExceptionThrow_thenElementAreNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<Integer>();
            test.add(null);
        });

        String expectedMessage = null;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void whenExceptionThrow_thenRemoveEmpty() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<Integer>();
            test.remove();
        });

        String expectedMessage = null;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}