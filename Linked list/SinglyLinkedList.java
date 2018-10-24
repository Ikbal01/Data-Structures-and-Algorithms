package DataStructures;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {

    private Node head;
    private int size;

    public SinglyLinkedList(){
        head = null;
        size = 0;
    }

    /**
     * Appends the specified element to the end of this list
     *
     * @param data
     */
    public void add(T data){
        if (head != null){
            Node current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new Node(data));
        }else {
            head = new Node(data);
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

        if (index != 0){
            Node current = head;
            Node temp = new Node(data);
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            temp.setNext(current.getNext());
            current.setNext(temp);
            size++;

        }else {
            addFirst(data);
        }
    }

    /**
     * Inserts the specified element at the beginning of this list
     *
     * @param data
     */
    public void addFirst(T data){
        Node current = new Node(data);
        current.setNext(head);
        head = current;
        size++;
    }

    /**
     * Inserts the specified element to the end of this list
     *
     * @param data
     */
    public void addLast(T data){
        add(data);
    }

    /**
     * Removes and returns the element at the specified position in this list, if it is present
     *
     * @param index
     * @return
     */
    public T remove(int index){
        checkPositionIndex(index);

        Node output;

        if (index != 0){
            Node current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            output = current.getNext();
            current.setNext(current.getNext().getNext());

        }else {
            output = head;
            head = head.getNext();
        }
        size--;
        return output.data;
    }

    /**
     * Removes and returns the first element from this list
     *
     * @return
     */
    public T removeFirst(){
        headNullCheck();

        Node output = head;
        head = head.getNext();
        size--;
        return output.getData();
    }

    /**
     * Removes and returns the last element from this list
     *
     * @return
     */
    public T removeLast(){
        headNullCheck();

        Node current = head;
        Node output = current.getNext();

        while (output.getNext() != null){
            output = output.getNext();
            current = current.getNext();
        }
        current.setNext(null);
        size--;
        return output.getData();
    }

    /**
     * Returns the element at the specified position in this list
     *
     * @param index
     * @return
     */
    public T get(int index){
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
    public T getFirst(){
        headNullCheck();

        return head.getData();
    }

    /**
     * Returns the last element in this list
     *
     * @return
     */
    public T getLast(){
        headNullCheck();

        Node current = head;
        while (current.getNext() != null){
            current = current.getNext();
        }
        return current.getData();
    }

    /**
     * Returns the number of elements in this list
     *
     * @return
     */
    public int size(){
        return size;
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
     * Returns true if this list contains the specified element
     *
     * @param data
     * @return
     */
    public boolean contains(T data){
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
     * Removes all of the elements from this list
     */
    public void clear(){
        head = null;
        size = 0;
    }

    /**
     *     Prints the elements of the list
     */
    public void print(){
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

    private class Node{
        // Reference to the next node in the chain, or null if there isn't one.
        private Node next;

        // Data carried by this node.
        private T data;

        public Node(T data){
            next = null;
            this.data = data;
        }

        public Node getNext(){
            return next;
        }

        public void setNext(Node next){
            this.next = next;
        }

        public T getData(){
            return data;
        }

        public void setData(T data){
            this.data = data;
        }
    }
}
