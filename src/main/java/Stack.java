package main.java;

public class Stack<T> {
    private T[] stackArray;
    private int top;
    private int capacity;

    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        this.capacity = capacity;
        this.stackArray = (T[]) new Object[capacity];
        this.top = -1;
    }

    public Stack() {
        this(10);
    }

    public void push(T item) {
        if (top == capacity - 1) {
            throw new IllegalStateException("Stack is full");
        }
        stackArray[++top] = item;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stackArray[top--];
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stackArray[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    public int getCapacity() {
        return capacity;
    }

    public void clear() {
        top = -1;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(stackArray, 0, newArray, 0, top + 1);
        stackArray = newArray;
        capacity = newCapacity;
    }
}