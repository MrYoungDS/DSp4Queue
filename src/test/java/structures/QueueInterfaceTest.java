package structures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import config.Configuration;

public class QueueInterfaceTest {

    private QueueInterface<String> strQueue;
    private QueueInterface<Integer> intQueue;

    @BeforeEach
    public void setup() {
        strQueue = Configuration.getQueueImplementation();
        intQueue = Configuration.getQueueImplementation();
        assertNotNull(strQueue,
                "You have not set your queue in the configuration class.");
        QueueInterface<String> queue2 = Configuration.getQueueImplementation();
        assertNotEquals(strQueue, queue2,
                "The getQueueImplementation method must return a NEW queue");
    }

    @Test
    public void testEnqueueSizeString() {
        strQueue.enqueue("One");
        assertEquals(1, strQueue.size());
        strQueue.enqueue("Two");
        assertEquals(2, strQueue.size());
        strQueue.enqueue("Three");
        assertEquals(3, strQueue.size());
        strQueue.enqueue("Four");
        assertEquals(4, strQueue.size());
    }

    @Test
    public void testEnqueueSizeInteger() {
        intQueue.enqueue(1);
        assertEquals(1, intQueue.size());
        intQueue.enqueue(2);
        assertEquals(2, intQueue.size());
        intQueue.enqueue(3);
        assertEquals(3, intQueue.size());
        intQueue.enqueue(4);
        assertEquals(4, intQueue.size());
    }

    @Test
    public void testEnqueuePeekString() {
        strQueue.enqueue("One");
        assertEquals("One", strQueue.peek());
        strQueue.enqueue("Two");
        assertEquals("One", strQueue.peek());
        strQueue.enqueue("Three").enqueue("Four");
        assertEquals("One", strQueue.peek());
    }

    @Test
    public void testEnqueuePeekInteger() {
        intQueue.enqueue(1);
        assertEquals(1, intQueue.peek());
        intQueue.enqueue(2);
        assertEquals(1, intQueue.peek());
        intQueue.enqueue(3).enqueue(4);
        assertEquals(1, intQueue.peek());
    }

    @Test
    public void testEnqueueSizeLarge() {
        //int max = 10;
        int max = 1000000;
        for(int i = 0; i < max; i ++){
            assertEquals(i, intQueue.size());
            intQueue.enqueue(i);
        }
    }

    @Test
    public void testEnqueueDequeueSize() {
        strQueue.enqueue("One");
        assertEquals(1, strQueue.size());
        strQueue.enqueue("Two");
        assertEquals(2, strQueue.size());
        strQueue.enqueue("Three");
        assertEquals(3, strQueue.size());
        strQueue.enqueue("Four");
        assertEquals(4, strQueue.size());
        assertEquals("One", strQueue.dequeue());
        assertEquals(3, strQueue.size());
        assertEquals("Two", strQueue.dequeue());
        assertEquals(2, strQueue.size());
        assertEquals("Three", strQueue.dequeue());
        assertEquals(1, strQueue.size());
        assertEquals("Four", strQueue.dequeue());
        assertEquals(0, strQueue.size());
    }

    @Test
    public void testEnqueueDequeueSize2() {
        //int max = 10;
        int max = 1000000;
        for(int i = 0; i < max; i++) {
            assertEquals(i, intQueue.size());
            intQueue.enqueue(i);
        }

        for(int i = max - 1; i >= 0; i--) {
            assertEquals(i + 1, intQueue.size());
            Integer r = intQueue.dequeue();
            assertEquals((max-1) - i, r.intValue());
        }
    }

    @Test
    public void testEnqueueDequeuePeekString() {
        strQueue.enqueue("One");
        assertEquals("One", strQueue.peek());
        strQueue.enqueue("Two");
        assertEquals("One", strQueue.peek());
        assertEquals("One", strQueue.dequeue());
        assertEquals("Two", strQueue.peek());
        strQueue.enqueue("Three").enqueue("Four");
        assertEquals("Two", strQueue.dequeue());
        assertEquals("Three", strQueue.dequeue());
        assertEquals("Four", strQueue.peek());
    }

    @Test
    public void testEnqueueDequeuePeekInteger() {
        intQueue.enqueue(1);
        assertEquals(1, intQueue.peek());
        intQueue.enqueue(2);
        assertEquals(1, intQueue.peek());
        assertEquals(1, intQueue.dequeue());
        assertEquals(2, intQueue.peek());
        intQueue.enqueue(3).enqueue(4);
        assertEquals(2, intQueue.dequeue());
        assertEquals(3, intQueue.dequeue());
        assertEquals(4, intQueue.peek());
    }

    @Test
    public void testEnqueueIsEmptyDequeue() {
        assertTrue(strQueue.isEmpty());

        assertEquals(strQueue, strQueue.enqueue("hello"));
        assertFalse(strQueue.isEmpty());

        assertEquals("hello", strQueue.dequeue());
        assertTrue(strQueue.isEmpty());

        assertEquals(strQueue, strQueue.enqueue("hello"));
        assertFalse(strQueue.isEmpty());

        assertEquals(strQueue, strQueue.enqueue("there"));
        assertFalse(strQueue.isEmpty());

        assertEquals(strQueue, strQueue.enqueue("world"));
        assertFalse(strQueue.isEmpty());

        assertEquals("hello", strQueue.dequeue());
        assertFalse(strQueue.isEmpty());

        assertEquals("there", strQueue.dequeue());
        assertFalse(strQueue.isEmpty());

        assertEquals("world", strQueue.dequeue());
        assertTrue(strQueue.isEmpty());
    }

    @Test
    public void testEnqueueToString() {
        assertEquals("[]", strQueue.toString());

        strQueue.enqueue("Hello");
        assertEquals("[Hello]", strQueue.toString());

        QueueInterface<Integer> queue2 = Configuration.getQueueImplementation();
        queue2.enqueue(1).enqueue(2).enqueue(3);
        assertEquals("[1, 2, 3]", queue2.toString());

        strQueue.enqueue("World");
        assertEquals("[Hello, World]", strQueue.toString());
    }

    @Test
    public void testNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> strQueue.enqueue(null),
                "Trying to enqueue a null value should throw null pointer.");
    }

    @Test
    public void testIllegalStateException1() {
        assertThrows(IllegalStateException.class,
                () -> strQueue.dequeue(),
                "Trying to dequeue an empty list should throw illegal state.");
    }

    @Test
    public void testIllegalStateException2() {
        strQueue.enqueue("One").enqueue("Two").enqueue("Three");
        strQueue.dequeue();
        strQueue.dequeue();
        strQueue.dequeue();
        assertThrows(IllegalStateException.class,
                () -> strQueue.dequeue(),
                "Trying to dequeue more than you enqueue should throw illegal state.");
    }
}
