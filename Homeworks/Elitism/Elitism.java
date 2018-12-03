import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Elitism {
    public static void main(String[] args) {
        InputReader reader = new InputReader();
        StringBuilder result = new StringBuilder();

        MinIntHeap minIntHeap = new MinIntHeap();
        MaxIntHeap maxIntHeap = new MaxIntHeap();
        double median = 0;

        int n = reader.readInt();

        for (int i = 0; i < n; i++) {
            int amount = reader.readInt();
            if (amount >= 0) {
                median = median(amount, median, minIntHeap, maxIntHeap);
                result.append(String.format("%.1f\n", median));
            }
        }
        System.out.println(result.toString());
    }

    private static double median(int newElement, double median, MinIntHeap minIntHeap, MaxIntHeap maxIntHeap) {

        if (minIntHeap.size() < maxIntHeap.size()) {
            if (newElement < median) {
                minIntHeap.add(maxIntHeap.poll());
                maxIntHeap.add(newElement);
            } else {
                minIntHeap.add(newElement);
            }
            median = (minIntHeap.peek() + maxIntHeap.peek()) / 2.0;
        } else if (minIntHeap.size() > maxIntHeap.size()) {
            if (newElement < median) {
                maxIntHeap.add(newElement);
            } else {
                maxIntHeap.add(minIntHeap.poll());
                minIntHeap.add(newElement);
            }
            median = (minIntHeap.peek() + maxIntHeap.peek()) / 2.0;
        } else {
            if (newElement < median) {
                maxIntHeap.add(newElement);
                median = maxIntHeap.peek();
            } else {
                minIntHeap.add(newElement);
                median = minIntHeap.peek();
            }
        }
        return median;
    }

    private static class MinIntHeap {
        private int capacity = 10;
        private int size = 0;

        private int[] items = new int[capacity];

        public void add(int item) {
            ensureExtraCapacity();
            items[size] = item;
            size++;
            heapifyUp(size - 1);
        }

        private void heapifyUp(int startIndex) {
            int index = startIndex;
            while (hasParent(index) && parent(index) > items[index]) {
                swap(getParentIndex(index), index);
                index = getParentIndex(index);
            }
        }

        public int poll() {
            if (size == 0) {
                throw new IllegalStateException();
            }
            int item = items[0];
            items[0] = items[size - 1];
            size--;
            heapifyDown(0);
            return item;
        }

        private void heapifyDown(int startIndex) {
            int index = startIndex;
            while (hasLeftChild(index)) {
                int smallerChildIndex = getLeftChildIndex(index);
                if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
                    smallerChildIndex = getRightChildIndex(index);
                }

                if (items[index] < items[smallerChildIndex]) {
                    break;
                } else {
                    swap(index, smallerChildIndex);
                }
                index = smallerChildIndex;
            }
        }

        public int peek() {
            if (size == 0) {
                throw  new IllegalStateException();
            }
            return items[0];
        }

        public int size() {
            return size;
        }

        private int getLeftChildIndex(int parentIndex) {
            return 2 * parentIndex + 1;
        }

        private int getRightChildIndex(int parentIndex) {
            return 2 * parentIndex + 2;
        }

        private int getParentIndex(int childIndex) {
            return (childIndex - 1) / 2;
        }

        private boolean hasLeftChild(int parentIndex) {
            return getLeftChildIndex(parentIndex) < size;
        }

        private boolean hasRightChild(int parentIndex) {
            return getRightChildIndex(parentIndex) < size;
        }

        private boolean hasParent(int childIndex) {
            return getParentIndex(childIndex) >= 0;
        }

        private int leftChild(int parentIndex) {
            return items[getLeftChildIndex(parentIndex)];
        }

        private int rightChild(int parentIndex) {
            return items[getRightChildIndex(parentIndex)];
        }

        private int parent(int childIndex) {
            return items[getParentIndex(childIndex)];
        }

        private void swap(int indexOne, int indexTwo) {
            int temp = items[indexOne];
            items[indexOne] = items[indexTwo];
            items[indexTwo] = temp;
        }

        private void ensureExtraCapacity() {
            if (size == capacity) {
                items = Arrays.copyOf(items, capacity * 2);
                capacity *= 2;
            }
        }
    }

    private static class MaxIntHeap {
        private int capacity = 10;
        private int size = 0;

        private int[] items = new int[capacity];

        public void add(int item) {
            ensureExtraCapacity();
            items[size] = item;
            size++;
            heapifyUp(size - 1);
        }

        private void heapifyUp(int startIndex) {
            int index = startIndex;
            while (hasParent(index) && parent(index) < items[index]) {
                swap(getParentIndex(index), index);
                index = getParentIndex(index);
            }
        }

        public int poll() {
            if (size == 0) {
                throw new IllegalStateException();
            }
            int item = items[0];
            items[0] = items[size - 1];
            size--;
            heapifyDown();
            return item;
        }

        private void heapifyDown() {
            int index = 0;
            while (hasLeftChild(index)) {
                int largerChildIndex = getLeftChildIndex(index);
                if (hasRightChild(index) && rightChild(index) > leftChild(index)) {
                    largerChildIndex = getRightChildIndex(index);
                }

                if (items[index] > items[largerChildIndex]) {
                    break;
                } else {
                    swap(index, largerChildIndex);
                }
                index = largerChildIndex;
            }
        }

        public int peek() {
            if (size == 0) {
                throw  new IllegalStateException();
            }
            return items[0];
        }

        public int size() {
            return size;
        }

        private int getLeftChildIndex(int parentIndex) {
            return 2 * parentIndex + 1;
        }

        private int getRightChildIndex(int parentIndex) {
            return 2 * parentIndex + 2;
        }

        private int getParentIndex(int childIndex) {
            return (childIndex - 1) / 2;
        }

        private boolean hasLeftChild(int parentIndex) {
            return getLeftChildIndex(parentIndex) < size;
        }

        private boolean hasRightChild(int parentIndex) {
            return getRightChildIndex(parentIndex) < size;
        }

        private boolean hasParent(int childIndex) {
            return getParentIndex(childIndex) >= 0;
        }

        private int leftChild(int parentIndex) {
            return items[getLeftChildIndex(parentIndex)];
        }

        private int rightChild(int parentIndex) {
            return items[getRightChildIndex(parentIndex)];
        }

        private int parent(int childIndex) {
            return items[getParentIndex(childIndex)];
        }

        private void swap(int indexOne, int indexTwo) {
            int temp = items[indexOne];
            items[indexOne] = items[indexTwo];
            items[indexTwo] = temp;
        }

        private void ensureExtraCapacity() {
            if (size == capacity) {
                items = Arrays.copyOf(items, capacity * 2);
                capacity *= 2;
            }
        }
    }

    private static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        InputReader() {
            this.stream = System.in;
        }

        int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        int readInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }


        boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }
}
