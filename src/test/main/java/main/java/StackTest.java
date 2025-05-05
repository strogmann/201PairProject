import main.java.Stack;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void pushAndPeek() {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        assertEquals(10, stack.peek());
        assertEquals(1, stack.size());
    }

    @Test
    void pushAndPop() {
        Stack<String> stack = new Stack<>();
        stack.push("hello");
        stack.push("world");
        assertEquals("world", stack.pop());
        assertEquals("hello", stack.pop());
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void popFromEmptyStack() {
        Stack<Double> stack = new Stack<>();
        assertThrows(IllegalStateException.class, stack::pop);
    }

    @Test
    void peekAtEmptyStack() {
        Stack<Character> stack = new Stack<>();
        assertThrows(IllegalStateException.class, stack::peek);
    }

    @Test
    void isEmptyOnNewStack() {
        Stack<Object> stack = new Stack<>();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void isEmptyAfterPushAndPop() {
        Stack<Boolean> stack = new Stack<>();
        stack.push(true);
        stack.pop();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void sizeAfterMultiplePushAndPop() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.size());
        stack.pop();
        assertEquals(1, stack.size());
        stack.push(3);
        assertEquals(2, stack.size());
    }

    @Test
    void getCapacityOnInitialization() {
        Stack<Integer> stack = new Stack<>(5);
        assertEquals(5, stack.getCapacity());
        Stack<String> defaultStack = new Stack<>();
        assertEquals(10, defaultStack.getCapacity()); // Assuming default capacity is 10
    }

    @Test
    void clearEmptyStack() {
        Stack<Integer> stack = new Stack<>();
        stack.clear();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void clearNonEmptyStack() {
        Stack<String> stack = new Stack<>();
        stack.push("one");
        stack.push("two");
        stack.clear();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void pushBeyondCapacity() {
        Stack<Integer> stack = new Stack<>(2);
        stack.push(1);
        stack.push(2);
        assertThrows(IllegalStateException.class, () -> stack.push(3));
    }
}