public class DispatchQueue {
    private static class Node {

        PackageItem item;
        Node next;

        Node(PackageItem item) {
            this.item = item;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    public DispatchQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    public void enqueue(PackageItem item) {

        Node newNode = new Node(item);

        if (rear == null) {

            front = newNode;
            rear = newNode;

        } else {

            rear.next = newNode;
            rear = newNode;
        }

        size++;

        System.out.println(
                "Package ID " +
                        item.getTrackingId() +
                        " added to dispatch queue."
        );
    }


    public PackageItem dequeue() {

        if (isEmpty()) {

            System.out.println(
                    "Dispatch Queue is empty."
            );

            return null;
        }

        PackageItem item = front.item;

        front = front.next;

        if (front == null) {
            rear = null;
        }

        size--;

        System.out.println(
                "Package ID " +
                        item.getTrackingId() +
                        " removed from dispatch queue."
        );

        return item;
    }



    public PackageItem peek() {

        if (isEmpty()) {
            return null;
        }

        return front.item;
    }



    public void displayQueue() {

        if (isEmpty()) {

            System.out.println(
                    "Dispatch Queue is empty."
            );

            return;
        }

        Node current = front;

        System.out.println(
                "\n--- Dispatch Queue (FIFO) ---"
        );

        while (current != null) {

            System.out.println(
                    current.item
            );

            current = current.next;
        }

        System.out.println(
                "Queue Size: " + size
        );
    }


    public boolean isEmpty() {

        return front == null;
    }



    public int getSize() {

        return size;
    }
}
