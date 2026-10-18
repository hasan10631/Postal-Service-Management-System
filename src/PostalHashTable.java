public class PostalHashTable {

    private static class HashNode {
        int key;
        String officeName;
        HashNode next;

        HashNode(int key, String officeName) {
            this.key = key;
            this.officeName = officeName;
            this.next = null;
        }
    }

    private HashNode[] table;
    private int capacity;

    public PostalHashTable(int capacity) {
        this.capacity = capacity;
        this.table = new HashNode[capacity];
    }

    // ==========================================
    // HASH FUNCTION
    // ==========================================

    private int hashFunction(int key) {
        return Math.abs(key) % capacity;
    }

    // ==========================================
    // INSERT / UPDATE
    // ==========================================

    public void put(int key, String officeName) {

        int index = hashFunction(key);

        HashNode current = table[index];

        while (current != null) {

            if (current.key == key) {

                current.officeName = officeName;

                return;
            }

            current = current.next;
        }

        HashNode newNode =
                new HashNode(key, officeName);

        newNode.next = table[index];

        table[index] = newNode;

        System.out.println(
                "Hash Table: Added ID " + key
        );
    }

    // ==========================================
    // SEARCH
    // ==========================================

    public String get(int key) {

        int index = hashFunction(key);

        HashNode current = table[index];

        while (current != null) {

            if (current.key == key) {
                return current.officeName;
            }

            current = current.next;
        }

        return null;
    }

    // ==========================================
    // DELETE
    // ==========================================

    public boolean remove(int key) {

        int index = hashFunction(key);

        HashNode current = table[index];
        HashNode previous = null;

        while (current != null) {

            if (current.key == key) {

                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                return true;
            }

            previous = current;
            current = current.next;
        }

        return false;
    }

    // ==========================================
    // DISPLAY HASH TABLE
    // ==========================================

    public void displayTable() {

        System.out.println(
                "\n========== HASH TABLE =========="
        );

        for (int i = 0; i < capacity; i++) {

            System.out.print(i + " : ");

            HashNode current = table[i];

            if (current == null) {

                System.out.println("empty");

                continue;
            }

            while (current != null) {

                System.out.print(
                        "[" +
                                current.key +
                                " -> " +
                                current.officeName +
                                "]"
                );

                if (current.next != null) {
                    System.out.print(" -> ");
                }

                current = current.next;
            }

            System.out.println();
        }
    }
}