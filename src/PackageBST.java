public class PackageBST {
    private static class Node {

        PackageItem item;
        Node left;
        Node right;

        Node(PackageItem item) {
            this.item = item;
        }
    }

    private Node root;

    public PackageBST() {
        root = null;
    }

    // ==========================================
    // INSERT
    // ==========================================

    public void insert(PackageItem item) {

        root = insertRec(root, item);
    }

    private Node insertRec(
            Node root,
            PackageItem item) {

        if (root == null) {
            return new Node(item);
        }

        if (item.getTrackingId()
                < root.item.getTrackingId()) {

            root.left =
                    insertRec(root.left, item);

        } else if (
                item.getTrackingId()
                        > root.item.getTrackingId()) {

            root.right =
                    insertRec(root.right, item);
        }

        return root;
    }

    // ==========================================
    // SEARCH
    // ==========================================

    public PackageItem search(int trackingId) {

        Node result =
                searchRec(root, trackingId);

        return result == null
                ? null
                : result.item;
    }

    private Node searchRec(
            Node root,
            int trackingId) {

        if (root == null ||
                root.item.getTrackingId()
                        == trackingId) {

            return root;
        }

        if (trackingId <
                root.item.getTrackingId()) {

            return searchRec(
                    root.left,
                    trackingId
            );
        }

        return searchRec(
                root.right,
                trackingId
        );
    }

    // ==========================================
    // DELETE
    // ==========================================

    public boolean delete(int trackingId) {

        if (search(trackingId) == null) {
            return false;
        }

        root =
                deleteRec(root, trackingId);

        return true;
    }

    private Node deleteRec(
            Node root,
            int trackingId) {

        if (root == null) {
            return null;
        }

        if (trackingId <
                root.item.getTrackingId()) {

            root.left =
                    deleteRec(
                            root.left,
                            trackingId
                    );

        } else if (
                trackingId >
                        root.item.getTrackingId()) {

            root.right =
                    deleteRec(
                            root.right,
                            trackingId
                    );

        } else {

            // No left child
            if (root.left == null) {
                return root.right;
            }

            // No right child
            if (root.right == null) {
                return root.left;
            }

            // Two children
            Node successor =
                    findMin(root.right);

            root.item = successor.item;

            root.right =
                    deleteRec(
                            root.right,
                            successor.item
                                    .getTrackingId()
                    );
        }

        return root;
    }

    private Node findMin(Node root) {

        Node current = root;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    // ==========================================
    // INORDER
    // ==========================================

    public void inorderTraversal() {

        System.out.println(
                "\n--- BST Inorder Traversal ---"
        );

        inorderRec(root);
    }

    private void inorderRec(Node root) {

        if (root != null) {

            inorderRec(root.left);

            System.out.println(root.item);

            inorderRec(root.right);
        }
    }

    // ==========================================
    // PREORDER
    // ==========================================

    public void preorderTraversal() {

        System.out.println(
                "\n--- BST Preorder Traversal ---"
        );

        preorderRec(root);
    }

    private void preorderRec(Node root) {

        if (root != null) {

            System.out.println(root.item);

            preorderRec(root.left);

            preorderRec(root.right);
        }
    }

    // ==========================================
    // POSTORDER
    // ==========================================

    public void postorderTraversal() {

        System.out.println(
                "\n--- BST Postorder Traversal ---"
        );

        postorderRec(root);
    }

    private void postorderRec(Node root) {

        if (root != null) {

            postorderRec(root.left);

            postorderRec(root.right);

            System.out.println(root.item);
        }
    }
}
