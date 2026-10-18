public class PackageAVL {
    private class Node {

        PackageItem item;
        int height;
        Node left;
        Node right;

        Node(PackageItem item) {
            this.item = item;
            this.height = 1;
        }
    }

    private Node root;


    private int height(Node node) {

        if (node == null) {
            return 0;
        }

        return node.height;
    }



    private int getBalance(Node node) {

        if (node == null) {
            return 0;
        }

        return height(node.left) - height(node.right);
    }



    private Node rightRotate(Node y) {

        Node x = y.left;
        Node temp = x.right;

        x.right = y;
        y.left = temp;

        y.height =
                1 + Math.max(
                        height(y.left),
                        height(y.right)
                );

        x.height =
                1 + Math.max(
                        height(x.left),
                        height(x.right)
                );

        return x;
    }


    private Node leftRotate(Node x) {

        Node y = x.right;
        Node temp = y.left;

        y.left = x;
        x.right = temp;

        x.height =
                1 + Math.max(
                        height(x.left),
                        height(x.right)
                );

        y.height =
                1 + Math.max(
                        height(y.left),
                        height(y.right)
                );

        return y;
    }



    public void insert(PackageItem item) {

        root = insertRec(root, item);
    }

    private Node insertRec(
            Node node,
            PackageItem item) {

        // Normal BST insertion
        if (node == null) {
            return new Node(item);
        }

        if (item.getTrackingId()
                < node.item.getTrackingId()) {

            node.left =
                    insertRec(node.left, item);

        } else if (
                item.getTrackingId()
                        > node.item.getTrackingId()) {

            node.right =
                    insertRec(node.right, item);

        } else {


            return node;
        }

        // Update height
        node.height =
                1 + Math.max(
                        height(node.left),
                        height(node.right)
                );


        int balance = getBalance(node);



        if (balance > 1 &&
                item.getTrackingId()
                        < node.left.item.getTrackingId()) {

            return rightRotate(node);
        }


        if (balance < -1 &&
                item.getTrackingId()
                        > node.right.item.getTrackingId()) {

            return leftRotate(node);
        }



        if (balance > 1 &&
                item.getTrackingId()
                        > node.left.item.getTrackingId()) {

            node.left =
                    leftRotate(node.left);

            return rightRotate(node);
        }



        if (balance < -1 &&
                item.getTrackingId()
                        < node.right.item.getTrackingId()) {

            node.right =
                    rightRotate(node.right);

            return leftRotate(node);
        }

        return node;
    }


    public PackageItem search(int trackingId) {

        Node result =
                searchRec(root, trackingId);

        if (result == null) {
            return null;
        }

        return result.item;
    }

    private Node searchRec(
            Node node,
            int trackingId) {

        if (node == null) {
            return null;
        }

        if (trackingId ==
                node.item.getTrackingId()) {

            return node;
        }

        if (trackingId <
                node.item.getTrackingId()) {

            return searchRec(
                    node.left,
                    trackingId
            );
        }

        return searchRec(
                node.right,
                trackingId
        );
    }



    public boolean delete(int trackingId) {

        if (search(trackingId) == null) {
            return false;
        }

        root =
                deleteRec(
                        root,
                        trackingId
                );

        return true;
    }

    private Node deleteRec(
            Node node,
            int trackingId) {


        if (node == null) {
            return null;
        }

        if (trackingId <
                node.item.getTrackingId()) {

            node.left =
                    deleteRec(
                            node.left,
                            trackingId
                    );

        } else if (
                trackingId >
                        node.item.getTrackingId()) {

            node.right =
                    deleteRec(
                            node.right,
                            trackingId
                    );

        } else {


            if (node.left == null &&
                    node.right == null) {

                return null;
            }


            if (node.left == null) {

                return node.right;
            }


            if (node.right == null) {

                return node.left;
            }


            Node successor =
                    getMinimumNode(node.right);

            node.item = successor.item;

            node.right =
                    deleteRec(
                            node.right,
                            successor.item
                                    .getTrackingId()
                    );
        }


        node.height =
                1 + Math.max(
                        height(node.left),
                        height(node.right)
                );


        int balance =
                getBalance(node);



        if (balance > 1 &&
                getBalance(node.left) >= 0) {

            return rightRotate(node);
        }



        if (balance > 1 &&
                getBalance(node.left) < 0) {

            node.left =
                    leftRotate(node.left);

            return rightRotate(node);
        }



        if (balance < -1 &&
                getBalance(node.right) <= 0) {

            return leftRotate(node);
        }



        if (balance < -1 &&
                getBalance(node.right) > 0) {

            node.right =
                    rightRotate(node.right);

            return leftRotate(node);
        }

        return node;
    }



    private Node getMinimumNode(Node node) {

        Node current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }



    public void inorder() {

        System.out.println(
                "\n--- AVL Tree Inorder Traversal ---"
        );

        inorderRec(root);
    }

    private void inorderRec(Node node) {

        if (node != null) {

            inorderRec(node.left);

            System.out.println(node.item);

            inorderRec(node.right);
        }
    }



    public void preorder() {

        System.out.println(
                "\n--- AVL Tree Preorder Traversal ---"
        );

        preorderRec(root);
    }

    private void preorderRec(Node node) {

        if (node != null) {

            System.out.println(node.item);

            preorderRec(node.left);

            preorderRec(node.right);
        }
    }



    public void postorder() {

        System.out.println(
                "\n--- AVL Tree Postorder Traversal ---"
        );

        postorderRec(root);
    }

    private void postorderRec(Node node) {

        if (node != null) {

            postorderRec(node.left);

            postorderRec(node.right);

            System.out.println(node.item);
        }
    }
}
