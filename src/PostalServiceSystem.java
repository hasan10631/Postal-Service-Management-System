import java.util.Scanner;

public class PostalServiceSystem {

    // ==============================
    // DSA COMPONENTS
    // ==============================

    private PackageLinkedList packageList;
    private DispatchQueue dispatchQueue;
    private UndoStack undoStack;
    private PostalHashTable hashTable;
    private PostalCodeSet postalCodeSet;
    private PackageBST bst;
    private PackageAVL avl;
    private PostalGraph postalGraph;

    // ==============================
    // ARRAY - POSTAL SERVICE TYPES
    // ==============================

    private String[] serviceTypes = {
            "Normal",
            "Express",
            "Registered",
            "International",
            "Same Day"
    };

    private Scanner scanner;

    // ==============================
    // CONSTRUCTOR
    // ==============================

    public PostalServiceSystem() {

        packageList = new PackageLinkedList();
        dispatchQueue = new DispatchQueue();
        undoStack = new UndoStack();

        // Hash table with 10 positions
        hashTable = new PostalHashTable(10);

        postalCodeSet = new PostalCodeSet();
        postalGraph = new PostalGraph();

        bst = new PackageBST();
        avl = new PackageAVL();

        scanner = new Scanner(System.in);
    }

    // ==============================
    // MAIN MENU
    // ==============================

    public void start() {

        int choice;

        do {

            displayMainMenu();

            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {

                case 1:
                    packageManagement();
                    break;

                case 2:
                    dispatchManagement();
                    break;

                case 3:
                    parcelTracking();
                    break;

                case 4:
                    searchPackages();
                    break;

                case 5:
                    sortPackages();
                    break;

                case 6:
                    postalNetworkMenu();
                    break;

                case 7:
                    undoLastOperation();
                    break;

                case 8:
                    displayServiceTypes();
                    break;

                case 9:
                    dsaDemonstration();
                    break;

                case 0:
                    System.out.println("\nThank you for using");
                    System.out.println("Postal Service Management System!");
                    break;

                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }

        } while (choice != 0);
    }

    // ==============================
    // MAIN MENU DISPLAY
    // ==============================

    private void displayMainMenu() {

        System.out.println("\n");
        System.out.println("====================================================");
        System.out.println("       POSTAL SERVICE MANAGEMENT SYSTEM");
        System.out.println("====================================================");
        System.out.println("1. Package Management");
        System.out.println("2. Dispatch Queue");
        System.out.println("3. Parcel Tracking");
        System.out.println("4. Search Packages");
        System.out.println("5. Sort Packages");
        System.out.println("6. Postal Network");
        System.out.println("7. Undo Last Operation");
        System.out.println("8. Postal Service Types");
        System.out.println("9. DSA Demonstration");
        System.out.println("0. Exit");
        System.out.println("====================================================");
    }

    // ==============================
    // PACKAGE MANAGEMENT
    // ==============================

    private void packageManagement() {

        int choice;

        do {

            System.out.println("\n----------------------------------------");
            System.out.println("         PACKAGE MANAGEMENT");
            System.out.println("----------------------------------------");
            System.out.println("1. Add Package");
            System.out.println("2. Display All Packages");
            System.out.println("3. Search Package");
            System.out.println("4. Delete Package");
            System.out.println("5. Update Package");
            System.out.println("6. Package Count");
            System.out.println("0. Back");
            System.out.println("----------------------------------------");

            System.out.print("Enter choice: ");
            choice = readInt();

            switch (choice) {

                case 1:
                    addPackage();
                    break;

                case 2:
                    packageList.displayAll();
                    break;

                case 3:
                    searchPackageById();
                    break;

                case 4:
                    deletePackage();
                    break;

                case 5:
                    updatePackage();
                    break;

                case 6:
                    displayPackageCount();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    // ==============================
    // ADD PACKAGE
    // ==============================

    private void addPackage() {

        System.out.println("\n========== ADD NEW PACKAGE ==========");

        System.out.print("Enter Tracking ID: ");
        int trackingId = readInt();

        // Check for duplicate tracking ID
        if (packageList.linearSearchByTrackingId(trackingId) != null) {

            System.out.println(
                    "Package with Tracking ID " +
                            trackingId +
                            " already exists."
            );

            System.out.println(
                    "Duplicate package rejected."
            );

            return;
        }

        System.out.print("Enter Recipient Name: ");
        String recipient = scanner.nextLine();

        System.out.print("Enter Weight (kg): ");
        double weight = readDouble();

        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();

        PackageItem newPackage =
                new PackageItem(
                        trackingId,
                        recipient,
                        weight,
                        destination
                );

        // ==========================================
        // ADD TO LINKED LIST
        // ==========================================

        packageList.addPackage(newPackage);

        // ==========================================
        // ADD TO HASH TABLE
        // ==========================================

        hashTable.put(
                trackingId,
                recipient + " - " + destination
        );

        // ==========================================
        // ADD TO SET
        // ==========================================

        postalCodeSet.add(trackingId);

        // ==========================================
        // ADD TO BST
        // ==========================================

        bst.insert(newPackage);

        // ==========================================
        // ADD TO AVL
        // ==========================================

        avl.insert(newPackage);

        // ==========================================
        // RECORD OPERATION IN STACK
        // ==========================================



        undoStack.push(
                "Added Package ID " + trackingId
        );

        System.out.println(
                "\nPackage successfully added to all DSA structures."
        );
    }

    // ==============================
    // SEARCH PACKAGE
    // ==============================

    private void searchPackageById() {

        System.out.print("\nEnter Tracking ID: ");
        int trackingId = readInt();

        PackageItem result =
                packageList.linearSearchByTrackingId(trackingId);

        if (result != null) {

            System.out.println("\nPackage Found!");
            System.out.println(result);

        } else {

            System.out.println("\nPackage not found.");
        }
    }

    // ==============================
    // DISPATCH MANAGEMENT
    // ==============================

    private void dispatchManagement() {

        int choice;

        do {

            System.out.println("\n----------------------------------------");
            System.out.println("          DISPATCH QUEUE");
            System.out.println("----------------------------------------");
            System.out.println("1. Add Package to Dispatch Queue");
            System.out.println("2. Process Next Package");
            System.out.println("3. View Next Package");
            System.out.println("4. Display Queue");
            System.out.println("5. Queue Size");
            System.out.println("0. Back");
            System.out.println("----------------------------------------");

            System.out.print("Enter choice: ");
            choice = readInt();

            switch (choice) {

                case 1:
                    addToDispatchQueue();
                    break;

                case 2:
                    dispatchQueue.dequeue();
                    break;

                case 3:
                    PackageItem next = dispatchQueue.peek();

                    if (next != null) {
                        System.out.println("Next package:");
                        System.out.println(next);
                    } else {
                        System.out.println("Dispatch queue is empty.");
                    }

                    break;

                case 4:
                    dispatchQueue.displayQueue();
                    break;

                case 5:
                    System.out.println(
                            "Queue Size: " +
                                    dispatchQueue.getSize()
                    );
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    // ==============================
    // ADD PACKAGE TO QUEUE
    // ==============================

    private void addToDispatchQueue() {

        System.out.print("\nEnter Tracking ID: ");
        int trackingId = readInt();

        PackageItem item =
                packageList.linearSearchByTrackingId(trackingId);

        if (item != null) {

            dispatchQueue.enqueue(item);

            undoStack.push(
                    "Added Package " +
                            trackingId +
                            " to dispatch queue"
            );

        } else {

            System.out.println(
                    "Package not found."
            );
        }
    }

    // ==============================
    // PARCEL TRACKING
    // ==============================

    private void parcelTracking() {

        System.out.println("\n========== PARCEL TRACKING ==========");

        System.out.print("Enter Tracking ID: ");

        int trackingId = readInt();

        String result =
                hashTable.get(trackingId);

        if (result != null) {

            System.out.println("\nTracking Information");
            System.out.println("----------------------------");
            System.out.println("Tracking ID : " + trackingId);
            System.out.println("Details     : " + result);

        } else {

            System.out.println(
                    "\nNo parcel found with Tracking ID "
                            + trackingId
            );
        }
    }

    // ==============================
    // SEARCH MENU
    // ==============================

    private void searchPackages() {

        System.out.println("\n========== SEARCH PACKAGES ==========");
        System.out.println("1. Linear Search");
        System.out.println("2. Hash Table Search");
        System.out.println("3. BST Search");
        System.out.println("0. Back");

        System.out.print("Enter choice: ");

        int choice = readInt();

        System.out.print("Enter Tracking ID: ");

        int trackingId = readInt();

        switch (choice) {

            case 1:

                PackageItem result =
                        packageList.linearSearchByTrackingId(
                                trackingId
                        );

                if (result != null) {
                    System.out.println(result);
                } else {
                    System.out.println("Package not found.");
                }

                break;

            case 2:

                String hashResult =
                        hashTable.get(trackingId);

                if (hashResult != null) {
                    System.out.println(
                            "Found: " + hashResult
                    );
                } else {
                    System.out.println("Package not found.");
                }

                break;

            case 3:

                // BST search will be connected
                // completely in the next step.
                System.out.println(
                        "BST search integration will be completed in Step 2."
                );

                break;

            case 0:
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    // ==============================
    // SORT MENU
    // ==============================

    private void sortPackages() {

        int choice;

        do {

            System.out.println("\n========================================");
            System.out.println("          SORTING & PERFORMANCE");
            System.out.println("========================================");

            System.out.println("1. Bubble Sort");
            System.out.println("2. Selection Sort");
            System.out.println("3. Insertion Sort");
            System.out.println("4. Merge Sort");
            System.out.println("5. Quick Sort");
            System.out.println("6. Compare All Algorithms");
            System.out.println("0. Back");

            System.out.println("========================================");

            System.out.print("Enter choice: ");

            choice = readInt();

            switch (choice) {

                case 1:
                    runBubbleSort();
                    break;

                case 2:
                    runSelectionSort();
                    break;

                case 3:
                    runInsertionSort();
                    break;

                case 4:
                    runMergeSort();
                    break;

                case 5:
                    runQuickSort();
                    break;

                case 6:
                    compareSortingAlgorithms();
                    break;

                case 0:
                    break;

                default:
                    System.out.println(
                            "Invalid choice."
                    );
            }

        } while (choice != 0);
    }
    // ==============================
    // UNDO
    // ==============================

    private void undoLastOperation() {

        if (undoStack.isEmpty()) {

            System.out.println(
                    "\nThere are no operations to undo."
            );

            return;
        }

        // Get the package stored at the top of the stack
        // BEFORE removing the stack node
        PackageItem packageToRestore =
                undoStack.getTopPackage();

        // Remove the action from the stack
        String action = undoStack.pop();

        System.out.println(
                "\nUndoing: " + action
        );

        // ==========================================
        // UNDO ADD PACKAGE
        // ==========================================

        if (action.startsWith("Added Package ID")) {

            System.out.println(
                    "Undo operation detected: Package addition."
            );

            System.out.println(
                    "Package addition undo is not implemented yet."
            );

        }

        // ==========================================
        // UNDO DELETE PACKAGE
        // ==========================================

        else if (action.startsWith("Deleted Package ID")) {

            System.out.println(
                    "Undo operation detected: Package deletion."
            );

            if (packageToRestore == null) {

                System.out.println(
                        "Unable to restore package: package data not found."
                );

                return;
            }

            int trackingId =
                    packageToRestore.getTrackingId();

            String recipient =
                    packageToRestore.getRecipientName();

            double weight =
                    packageToRestore.getWeightKg();

            String destination =
                    packageToRestore.getDestination();

            // ==========================================
            // RESTORE TO LINKED LIST
            // ==========================================

            packageList.addPackage(packageToRestore);

            // ==========================================
            // RESTORE TO HASH TABLE
            // ==========================================

            hashTable.put(
                    trackingId,
                    recipient + " - " + destination
            );

            // ==========================================
            // RESTORE TO SET
            // ==========================================

            postalCodeSet.add(trackingId);

            // ==========================================
            // RESTORE TO BST
            // ==========================================

            bst.insert(packageToRestore);

            // ==========================================
            // RESTORE TO AVL
            // ==========================================

            avl.insert(packageToRestore);

            System.out.println(
                    "Package " + trackingId +
                            " has been restored successfully."
            );

        }

        // ==========================================
        // UNDO UPDATE PACKAGE
        // ==========================================

        else if (action.startsWith("Updated Package ID")) {

            System.out.println(
                    "Undo operation detected: Package update."
            );

            System.out.println(
                    "Package update undo is not implemented yet."
            );

        }

        // ==========================================
        // UNDO QUEUE OPERATION
        // ==========================================

        else if (action.contains("to dispatch queue")) {

            System.out.println(
                    "Undo operation detected: Queue operation."
            );

            System.out.println(
                    "Queue operation undo is not implemented yet."
            );

        }

        // ==========================================
        // UNKNOWN OPERATION
        // ==========================================

        else {

            System.out.println(
                    "Unknown operation type."
            );
        }
    }

    // ==============================
    // ARRAY DEMONSTRATION
    // ==============================

    private void displayServiceTypes() {

        System.out.println(
                "\n========== POSTAL SERVICE TYPES =========="
        );

        for (int i = 0; i < serviceTypes.length; i++) {

            System.out.println(
                    (i + 1) + ". " + serviceTypes[i]
            );
        }
    }

    // ==============================
    // DSA DEMONSTRATION
    // ==============================

    private void dsaDemonstration() {

        int choice;

        do {

            System.out.println("\n========================================");
            System.out.println("           DSA DEMONSTRATION");
            System.out.println("========================================");

            System.out.println("1. Linked List");
            System.out.println("2. Queue");
            System.out.println("3. Stack");
            System.out.println("4. Hash Table");
            System.out.println("5. Set ADT");
            System.out.println("6. Binary Search Tree");
            System.out.println("7. AVL Tree");
            System.out.println("0. Back");

            System.out.println("========================================");

            System.out.print("Enter choice: ");

            choice = readInt();

            switch (choice) {

                case 1:

                    System.out.println(
                            "\nLinked List Size: " +
                                    packageList.getSize()
                    );

                    packageList.displayAll();

                    break;

                case 2:

                    System.out.println(
                            "\nQueue Size: " +
                                    dispatchQueue.getSize()
                    );

                    PackageItem next =
                            dispatchQueue.peek();

                    if (next != null) {

                        System.out.println(
                                "Front Package:"
                        );

                        System.out.println(next);

                    } else {

                        System.out.println(
                                "Queue is empty."
                        );
                    }

                    break;

                case 3:

                    System.out.println(
                            "\nStack Status: " +
                                    (undoStack.isEmpty()
                                            ? "Empty"
                                            : "Contains operations")
                    );

                    break;

                case 4:

                    hashTable.displayTable();

                    break;

                case 5:

                    postalCodeSet.displaySet();

                    break;

                case 6:

                    bst.inorderTraversal();

                    bst.preorderTraversal();

                    bst.postorderTraversal();

                    break;

                case 7:

                    avl.inorder();

                    avl.preorder();

                    avl.postorder();

                    break;

                case 0:
                    break;

                default:

                    System.out.println(
                            "Invalid choice."
                    );
            }

        } while (choice != 0);
    }

    // ==============================
    // SAFE INTEGER INPUT
    // ==============================

    private int readInt() {

        while (true) {

            try {

                String input = scanner.nextLine();

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.print(
                        "Please enter a valid number: "
                );
            }
        }
    }

    // ==============================
    // SAFE DOUBLE INPUT
    // ==============================

    private double readDouble() {

        while (true) {

            try {

                String input = scanner.nextLine();

                return Double.parseDouble(input);

            } catch (NumberFormatException e) {

                System.out.print(
                        "Please enter a valid weight: "
                );
            }
        }
    }
    private void deletePackage() {

        System.out.println("\n========== DELETE PACKAGE ==========");

        System.out.print("Enter Tracking ID: ");

        int trackingId = readInt();

        // Check whether package exists
        PackageItem existing =
                packageList.linearSearchByTrackingId(trackingId);

        if (existing == null) {

            System.out.println(
                    "Package with ID " +
                            trackingId +
                            " was not found."
            );

            return;
        }

        // ==========================================
        // DELETE FROM LINKED LIST
        // ==========================================

        boolean deleted =
                packageList.deletePackage(trackingId);

        if (!deleted) {

            System.out.println(
                    "Unable to delete package."
            );

            return;
        }

        // ==========================================
        // DELETE FROM HASH TABLE
        // ==========================================

        hashTable.remove(trackingId);

        // ==========================================
        // DELETE FROM SET
        // ==========================================

        postalCodeSet.remove(trackingId);

        // ==========================================
        // DELETE FROM BST
        // ==========================================

        bst.delete(trackingId);

        // ==========================================
        // DELETE FROM AVL
        // ==========================================

        avl.delete(trackingId);

        // ==========================================
        // RECORD OPERATION IN STACK
        // ==========================================

        undoStack.push(
                "Deleted Package ID " + trackingId,
                existing
        );

        System.out.println(
                "Package " + trackingId + " deleted successfully."
        );

        undoStack.push(
                "Deleted Package ID " + trackingId,
                existing
        );

        System.out.println(
                "\nPackage " +
                        trackingId +
                        " deleted successfully."
        );

        System.out.println(
                "Package removed from all DSA structures."
        );
    }
    private void updatePackage() {

        System.out.println("\n========== UPDATE PACKAGE ==========");

        System.out.print("Enter Tracking ID: ");

        int trackingId = readInt();

        PackageItem existing =
                packageList.linearSearchByTrackingId(trackingId);

        if (existing == null) {

            System.out.println(
                    "Package with ID " +
                            trackingId +
                            " was not found."
            );

            return;
        }

        System.out.println("\nCurrent Package:");
        System.out.println(existing);

        System.out.print("\nEnter New Recipient Name: ");
        String recipientName = scanner.nextLine();

        System.out.print("Enter New Weight (kg): ");
        double weightKg = readDouble();

        System.out.print("Enter New Destination: ");
        String destination = scanner.nextLine();

        // ==========================================
        // UPDATE LINKED LIST / PACKAGE OBJECT
        // ==========================================

        boolean updated =
                packageList.updatePackage(
                        trackingId,
                        recipientName,
                        weightKg,
                        destination
                );

        if (!updated) {

            System.out.println(
                    "Unable to update package."
            );

            return;
        }

        // ==========================================
        // UPDATE HASH TABLE
        // ==========================================

        hashTable.put(
                trackingId,
                recipientName + " - " + destination
        );

        // ==========================================
        // BST AND AVL
        // ==========================================
        // They contain the same PackageItem object,
        // so the updated values are automatically visible.

        // ==========================================
        // RECORD OPERATION IN STACK
        // ==========================================

        undoStack.push(
                "Updated Package ID " + trackingId
        );

        System.out.println(
                "\nPackage updated successfully."
        );

        System.out.println(
                "Updated Package:"
        );

        System.out.println(
                packageList.linearSearchByTrackingId(
                        trackingId
                )
        );
    }
    private void displayPackageCount() {

        System.out.println(
                "\nTotal Packages: " +
                        packageList.getSize()
        );
    }
    private void displaySortedPackages(
            PackageItem[] packages) {

        System.out.println(
                "\n--- Sorted Packages by Tracking ID ---"
        );

        for (PackageItem item : packages) {

            System.out.println(item);
        }
    }
    private void runBubbleSort() {

        PackageItem[] packages =
                packageList.getAllPackages();

        if (packages.length == 0) {

            System.out.println(
                    "No packages available for sorting."
            );

            return;
        }

        long startTime =
                System.nanoTime();

        BubbleSort.sortByTrackingId(packages);

        long endTime =
                System.nanoTime();

        long executionTime =
                endTime - startTime;

        displaySortedPackages(packages);

        System.out.println(
                "\nBubble Sort Execution Time: " +
                        executionTime +
                        " ns"
        );
    }
    private void runSelectionSort() {

        PackageItem[] packages =
                packageList.getAllPackages();

        if (packages.length == 0) {

            System.out.println(
                    "No packages available for sorting."
            );

            return;
        }

        long startTime =
                System.nanoTime();

        SelectionSort.sortByTrackingId(packages);

        long endTime =
                System.nanoTime();

        long executionTime =
                endTime - startTime;

        displaySortedPackages(packages);

        System.out.println(
                "\nSelection Sort Execution Time: " +
                        executionTime +
                        " ns"
        );
    }
    private void runInsertionSort() {

        PackageItem[] packages =
                packageList.getAllPackages();

        if (packages.length == 0) {

            System.out.println(
                    "No packages available for sorting."
            );

            return;
        }

        long startTime =
                System.nanoTime();

        InsertionSort.sortByTrackingId(packages);

        long endTime =
                System.nanoTime();

        long executionTime =
                endTime - startTime;

        displaySortedPackages(packages);

        System.out.println(
                "\nInsertion Sort Execution Time: " +
                        executionTime +
                        " ns"
        );
    }
    private void runMergeSort() {

        PackageItem[] packages =
                packageList.getAllPackages();

        if (packages.length == 0) {

            System.out.println(
                    "No packages available for sorting."
            );

            return;
        }

        long startTime =
                System.nanoTime();

        MergeSort.sort(
                packages,
                0,
                packages.length - 1
        );

        long endTime =
                System.nanoTime();

        long executionTime =
                endTime - startTime;

        displaySortedPackages(packages);

        System.out.println(
                "\nMerge Sort Execution Time: " +
                        executionTime +
                        " ns"
        );
    }
    private void runQuickSort() {

        PackageItem[] packages =
                packageList.getAllPackages();

        if (packages.length == 0) {

            System.out.println(
                    "No packages available for sorting."
            );

            return;
        }

        long startTime =
                System.nanoTime();

        QuickSort.sort(
                packages,
                0,
                packages.length - 1
        );

        long endTime =
                System.nanoTime();

        long executionTime =
                endTime - startTime;

        displaySortedPackages(packages);

        System.out.println(
                "\nQuick Sort Execution Time: " +
                        executionTime +
                        " ns"
        );
    }
    private void compareSortingAlgorithms() {

        PackageItem[] original =
                packageList.getAllPackages();

        if (original.length == 0) {

            System.out.println(
                    "No packages available for sorting."
            );

            return;
        }

        System.out.println(
                "\n========== SORTING PERFORMANCE =========="
        );

        // ------------------------------------------
        // Bubble Sort
        // ------------------------------------------

        PackageItem[] bubble =
                original.clone();

        long start =
                System.nanoTime();

        BubbleSort.sortByTrackingId(bubble);

        long end =
                System.nanoTime();

        long bubbleTime =
                end - start;


        // ------------------------------------------
        // Selection Sort
        // ------------------------------------------

        PackageItem[] selection =
                original.clone();

        start =
                System.nanoTime();

        SelectionSort.sortByTrackingId(selection);

        end =
                System.nanoTime();

        long selectionTime =
                end - start;


        // ------------------------------------------
        // Insertion Sort
        // ------------------------------------------

        PackageItem[] insertion =
                original.clone();

        start =
                System.nanoTime();

        InsertionSort.sortByTrackingId(insertion);

        end =
                System.nanoTime();

        long insertionTime =
                end - start;


        // ------------------------------------------
        // Merge Sort
        // ------------------------------------------

        PackageItem[] merge =
                original.clone();

        start =
                System.nanoTime();

        MergeSort.sort(
                merge,
                0,
                merge.length - 1
        );

        end =
                System.nanoTime();

        long mergeTime =
                end - start;


        // ------------------------------------------
        // Quick Sort
        // ------------------------------------------

        PackageItem[] quick =
                original.clone();

        start =
                System.nanoTime();

        QuickSort.sort(
                quick,
                0,
                quick.length - 1
        );

        end =
                System.nanoTime();

        long quickTime =
                end - start;


        // ------------------------------------------
        // DISPLAY RESULTS
        // ------------------------------------------

        System.out.println(
                "\nNumber of Packages: " +
                        original.length
        );

        System.out.println(
                "\nAlgorithm          Execution Time"
        );

        System.out.println(
                "----------------------------------------"
        );

        System.out.println(
                "Bubble Sort        " +
                        bubbleTime +
                        " ns"
        );

        System.out.println(
                "Selection Sort     " +
                        selectionTime +
                        " ns"
        );

        System.out.println(
                "Insertion Sort     " +
                        insertionTime +
                        " ns"
        );

        System.out.println(
                "Merge Sort         " +
                        mergeTime +
                        " ns"
        );

        System.out.println(
                "Quick Sort         " +
                        quickTime +
                        " ns"
        );

        System.out.println(
                "========================================"
        );
    }
    private void postalNetworkMenu() {

        int choice;

        do {

            System.out.println(
                    "\n========================================"
            );

            System.out.println(
                    "           POSTAL NETWORK"
            );

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "1. Add Postal Office"
            );

            System.out.println(
                    "2. Add Connection"
            );

            System.out.println(
                    "3. Display Network"
            );

            System.out.println(
                    "4. BFS Traversal"
            );

            System.out.println(
                    "5. DFS Traversal"
            );

            System.out.println(
                    "0. Back"
            );

            System.out.println(
                    "========================================"
            );

            System.out.print(
                    "Enter choice: "
            );

            choice = readInt();

            switch (choice) {

                case 1:

                    System.out.print(
                            "Enter Office Name: "
                    );

                    String office =
                            scanner.nextLine();

                    postalGraph.addOffice(
                            office
                    );

                    break;

                case 2:

                    System.out.print(
                            "Enter First Office: "
                    );

                    String office1 =
                            scanner.nextLine();

                    System.out.print(
                            "Enter Second Office: "
                    );

                    String office2 =
                            scanner.nextLine();

                    postalGraph.addConnection(
                            office1,
                            office2
                    );

                    break;

                case 3:

                    postalGraph.displayGraph();

                    break;

                case 4:

                    System.out.print(
                            "Enter Starting Office: "
                    );

                    String bfsStart =
                            scanner.nextLine();

                    postalGraph.bfs(
                            bfsStart
                    );

                    break;

                case 5:

                    System.out.print(
                            "Enter Starting Office: "
                    );

                    String dfsStart =
                            scanner.nextLine();

                    postalGraph.dfs(
                            dfsStart
                    );

                    break;

                case 0:
                    break;

                default:

                    System.out.println(
                            "Invalid choice."
                    );
            }

        } while (choice != 0);
    }

}