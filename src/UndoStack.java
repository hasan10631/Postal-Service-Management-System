public class UndoStack {

    private static class Node {

        String actionDescription;
        PackageItem packageItem;
        Node next;

        Node(String actionDescription, PackageItem packageItem) {
            this.actionDescription = actionDescription;
            this.packageItem = packageItem;
            this.next = null;
        }
    }

    private Node top;
    private int size;

    public UndoStack() {
        this.top = null;
        this.size = 0;
    }


    public void push(String action) {

        Node newNode =
                new Node(action, null);

        newNode.next = top;
        top = newNode;

        size++;

        System.out.println(
                "Action logged: \"" +
                        action +
                        "\""
        );
    }
    public void push(String action, PackageItem packageItem) {

        Node newNode =
                new Node(action, packageItem);

        newNode.next = top;
        top = newNode;

        size++;

        System.out.println(
                "Action logged: \"" +
                        action +
                        "\""
        );
    }



    public String pop() {
        if (isEmpty()) {
            System.out.println("No actions to undo!");
            return null;
        }
        String action = top.actionDescription;
        top = top.next;
        size--;
        System.out.println("Undone action: \"" + action + "\"");
        return action;
    }

    public boolean isEmpty() {
        return top == null;
    }
    public String peek() {

        if (isEmpty()) {
            return null;
        }

        return top.actionDescription;
    }
    public PackageItem getTopPackage() {

        if (isEmpty()) {
            return null;
        }

        return top.packageItem;
    }

}
