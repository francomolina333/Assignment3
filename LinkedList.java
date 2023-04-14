import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LinkedList {
    private Node head;
    private Node tail;

    public void add(int value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            Node current = head;
            Node previous = null;
            while (current != null && current.getValue() < value) {
                previous = current;
                current = current.getNext();
            }
            if (previous == null) {
                head = node;
            } else {
                previous.setNext(node);
            }
            node.setNext(current);
            if (current == null) {
                tail = node;
            }
        }
    }

    public synchronized int removeFirst() {
        if (head == null) {
            throw new IllegalStateException("Linked list is empty");
        }
        int value = head.getValue();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        System.out.println("Thank you " + value);
        return value;
    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.getValue() + " ");
            current = current.getNext();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Set<Integer> usedNumbers = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    while (usedNumbers.size() < 500000) {
                        while (list.head != null) {
                            list.removeFirst();
                        }
                        int number = random.nextInt(500000) + 1;
                        if (!usedNumbers.contains(number)) {
                            list.add(number);
                            usedNumbers.add(number);
                        }
                    }
                    while (list.head != null) {
                        list.removeFirst();
                    }
                }
            });
            thread.start();
        }

    }

    private static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
