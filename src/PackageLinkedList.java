public class PackageLinkedList {
    private static class Node {
        PackageItem item;
        Node next;

        Node(PackageItem item) {
            this.item = item;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public PackageLinkedList() {
        this.head = null;
        this.size = 0;
    }

    
    public void addPackage(PackageItem item) {

        Node newNode = new Node(item);

        if (head == null) {
            head = newNode;
        } else {

            Node temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = newNode;
        }

        size++;

        System.out.println(
                "Package " + item.getTrackingId()
                        + " added successfully."
        );
    }



    public PackageItem linearSearchByTrackingId(int trackingId) {

        Node current = head;

        while (current != null) {

            if (current.item.getTrackingId() == trackingId) {
                return current.item;
            }

            current = current.next;
        }

        return null;
    }



    public boolean deletePackage(int trackingId) {

        if (head == null) {
            return false;
        }

        // If first node is the package
        if (head.item.getTrackingId() == trackingId) {

            head = head.next;
            size--;

            return true;
        }

        Node current = head;

        while (current.next != null) {

            if (current.next.item.getTrackingId() == trackingId) {

                current.next = current.next.next;
                size--;

                return true;
            }

            current = current.next;
        }

        return false;
    }


    public boolean updatePackage(
            int trackingId,
            String recipientName,
            double weightKg,
            String destination) {

        PackageItem item =
                linearSearchByTrackingId(trackingId);

        if (item == null) {
            return false;
        }

        item.setRecipientName(recipientName);
        item.setWeightKg(weightKg);
        item.setDestination(destination);

        return true;
    }


    public void displayAll() {

        if (head == null) {

            System.out.println(
                    "No packages in the inventory."
            );

            return;
        }

        Node temp = head;

        System.out.println(
                "\n--- Current Package Inventory ---"
        );

        while (temp != null) {

            System.out.println(temp.item);

            temp = temp.next;
        }
    }



    public PackageItem[] getAllPackages() {

        PackageItem[] packages =
                new PackageItem[size];

        Node current = head;

        int index = 0;

        while (current != null) {

            packages[index] = current.item;

            index++;

            current = current.next;
        }

        return packages;
    }


    public int getSize() {
        return size;
    }

}
