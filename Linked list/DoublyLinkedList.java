package DataStructures;

import java.util.NoSuchElementException;

public class DoublyLinkedList<T> {

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Appends the specified element to the end of this list
     * @param data
     */
    public void add(T data){
        Node newNode = new Node(data);

        if (head != null){
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        }else {
            head = new Node(data);
            tail = head;
        }
        size++;
    }

    /**
     * Inserts the specified element at the specified position in this list
     *
     * @param index
     * @param data
     */
    public void add(int index, T data){
        checkElementIndex(index);

        if (index != 0 && index != size){
            Node newNode = new Node(data);
            Node current = head;

            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            Node previous = current.getPrevious();

            newNode.setPrevious(previous);
            previous.setNext(newNode);
            newNode.setNext(current);
            current.setPrevious(newNode);

            size++;

        }else if (index == 0){
            addFirst(data);

        }else {
            addLast(data);
        }
    }

    /**
     * Inserts the specified element at the beginning of this list
     *
     * @param data
     */
    public void addFirst(T data) {
        if (head != null){
            Node newNode = new Node(data);
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }else {
            head = new Node(data);
            tail = head;
        }
        size++;
    }

    /**
     * Inserts the specified element to the end of this list
     *
     * @param data
     */
    public void addLast(T data) {
        add(data);
    }

    /**
     * Removes and returns the element at the specified position in this list, if it is present
     *
     * @param index
     * @return
     */
    public T remove(int index) {
        checkPositionIndex(index);

        if (index != 0 && index != size - 1){
            Node current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            Node output = current.getNext();

            current.setNext(current.getNext().getNext());
            Node currentNext = current.getNext();
            currentNext.setPrevious(current);

            size--;
            return output.getData();
        }else if(index == 0) {
            return removeFirst();
        }else {
            return removeLast();
        }
    }

    /**
     * Removes and returns the first element from this list
     *
     * @return
     */
    public T removeFirst() {
        headNullCheck();

        Node first = head;
        head = head.getNext();
        head.setPrevious(null);
        size--;

        return first.getData();
    }

    /**
     * Removes and returns the last element from this list
     *
     * @return
     */
    public T removeLast() {
        tailNullCheck();

        Node last = tail;
        tail = tail.getPrevious();
        tail.setNext(null);
        size--;

        return last.getData();
    }

    /**
     * Returns the element at the specified position in this list
     *
     * @param index
     * @return
     */
    public T get(int index) {
        checkPositionIndex(index);

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    /**
     * Returns the first element (head) in this list
     *
     * @return
     */
    public T getFirst() {
        headNullCheck();

        return head.getData();
    }

    /**
     * Returns the last element in this list
     *
     * @return
     */
    public T getLast() {
        tailNullCheck();

        return tail.getData();
    }

    /**
     * Removes all of the elements from this list
     *
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Determines if the list is empty or not
     *
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Returns the number of elements in this list
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this list contains the specified element
     *
     * @param data
     * @return
     */
    public boolean contains(T data) {
        Node current = head;

        while (current != null){
            if (current.data.equals(data)){
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Prints the elements of the list
     */
    public void print() {
        StringBuilder output = new StringBuilder();

        if (head != null){
            Node current = head;

            while (current != null){
                output.append("[");
                output.append(current.getData());
                output.append("] ");
                current = current.getNext();
            }
            System.out.println(output.toString());
            return;
        }
        System.out.println("No elements");
    }

    public void printReverse(){
        StringBuilder output = new StringBuilder();

        if (tail != null){
            Node current = tail;
            while (current != null){
                output.append("[");
                output.append(current.getData());
                output.append("] ");
                current = current.getPrevious();
            }
            System.out.println(output.toString());
            return;
        }
        System.out.println("No elements");
    }

    private void checkElementIndex(int index){
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkPositionIndex(int index){
        if (index < 0 || index >= size()){
            throw new IndexOutOfBoundsException();
        }
    }

    private void headNullCheck(){
        if (head == null){
            throw new NoSuchElementException();
        }
    }

    private void tailNullCheck(){
        if (tail == null){
            throw new NoSuchElementException();
        }
    }

    private class Node{
        private Node next;
        private Node previous;
        private T data;

        public Node(T data){

            // Reference to the next node in the chain, or null if there isn't one.
            next = null;

            // Reference to the previous node in the chain, or null if there isn't one.
            previous = null;

            // Data carried by this node.
            this.data = data;
        }

        public Node getNext(){
            return next;
        }

        public void setNext(Node next){
            this.next = next;
        }

        public Node getPrevious(){
            return previous;
        }

        public void setPrevious(Node previous){
            this.previous = previous;
        }

        public T getData(){
            return data;
        }

        public void setData(T data){
            this.data = data;
        }
    }
}
