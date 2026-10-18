📮 Postal Service Management System

A console-based Postal Service Management System developed in Java as a group project for the Data Structures and Algorithms (DSA) module at SLTC Research University.

📌 Table of Contents

- Project Overview
- Objectives
- Features
- Data Structures
- Algorithms
- Technologies Used
- Project Structure
- How to Run
- Academic Context
- Learning Outcomes
- Limitations and Future Improvements
- Repository
- 
📖 Project Overview

The Postal Service Management System is a menu-driven Java application that models basic postal package operations and demonstrates the use of multiple data structures and algorithms.
The application allows users to add, view, search, update, and delete package records; manage a dispatch queue; look up parcel tracking information; sort packages by tracking ID; represent connections between postal offices; and explore data structures through a DSA demonstration menu.
This project was developed collaboratively as part of our university coursework. We used Java and IntelliJ IDEA to implement and test the application.

🎯 Objectives

- Apply Data Structures and Algorithms concepts to a practical scenario.
- Manage package records using Java.
- Compare different searching and sorting approaches.
- Demonstrate linear data structures, trees, hashing, sets, and graphs.
- Improve programming logic, problem-solving, and teamwork skills.
  
🚀 Features

1. Package Management
- Add a package with a tracking ID, recipient name, weight, and destination.
- Display all packages.
- Search for a package by tracking ID.
- Update recipient name, package weight, and destination.
- Delete a package.
- Display the total package count.
- Reject duplicate tracking IDs when adding a package.

  
2. Dispatch Queue
- Add an existing package to the dispatch queue.
- Process the next package in the queue.
- View the next package without removing it.
- Display the queue and its size.
- Demonstrates the First In, First Out (FIFO) principle.

  
3. Parcel Tracking
- Search for tracking information using a tracking ID.
- Retrieve the stored recipient and destination summary through the hash table.

  
4. Searching
The search menu provides:
- Linear Search: searches package records in the linked list.
- Hash Table Search: looks up a tracking ID using a hash table.
- A BST search option is displayed in the menu, but its integration is marked as incomplete in the current source code.

  
5. Sorting and Performance
Packages can be sorted by tracking ID using:
- Bubble Sort
- Selection Sort
- Insertion Sort
- Merge Sort
- Quick Sort
The application also includes an option to compare the sorting algorithms. The sorting implementations operate on an array of PackageItem objects.


6. Postal Network
The graph component supports:
- Adding postal offices.
- Connecting two existing offices.
- Displaying the postal network.
- Breadth-First Search (BFS).
- Depth-First Search (DFS).
Connections are stored in an adjacency-list representation and are added in both directions.


7. Undo Operation History
A stack records actions such as adding, deleting, and updating packages and adding packages to the dispatch queue. The current undo menu supports restoring a deleted package to the main data structures. Undoing package addition, package updates, and queue operations is not fully implemented yet.


9. Postal Service Types
Displays the service types defined in the program:
- Normal
- Express
- Registered
- International
- Same Day

  
10. DSA Demonstration
A separate menu demonstrates the current state of:
- Linked List
- Queue
- Stack
- Hash Table
- Set ADT
- Binary Search Tree (BST)
- AVL Tree
The BST and AVL tree demonstrations include inorder, preorder, and postorder traversals.


🧠 Data Structures

Data Structure	How it is used in the project
Array	Stores the available postal service types and holds package data for sorting.
Singly Linked List	Stores package records and supports display, search, update, deletion, and counting.
Queue	Manages packages awaiting dispatch using FIFO order.
Stack	Stores a history of operations for undo-related functionality.
Hash Table	Maps tracking IDs to recipient/destination summary information. Uses separate chaining to handle collisions.
Set ADT	Stores unique tracking IDs and rejects duplicate values.
Binary Search Tree (BST)	Supports insertion, searching, deletion, and tree traversals by tracking ID.
AVL Tree	Implements a self-balancing search tree with rotations, searching, insertion, deletion, and traversals.
Graph	Represents postal offices and connections using an adjacency list.


⚡ Algorithms
Searching
- Linear Search: checks linked-list package records one by one.
- Hash Table Lookup: calculates a bucket index from the tracking ID and searches that bucket.
- BST Search: implemented in the PackageBST class.
- AVL Search: implemented in the PackageAVL class.
Sorting
- Bubble Sort: repeatedly compares adjacent tracking IDs and swaps them when needed.
- Selection Sort: selects the smallest remaining tracking ID for each position.
- Insertion Sort: inserts each package into its correct position in the sorted section.
- Merge Sort: divides the array into smaller sections, sorts them, and merges them.
- Quick Sort: partitions the array around a pivot tracking ID and recursively sorts the partitions.
Graph Traversal
- Breadth-First Search (BFS): visits connected offices level by level using a queue.
- Depth-First Search (DFS): explores a path before backtracking, using recursion.
Tree Operations
- BST operations: insertion, search, deletion, and inorder/preorder/postorder traversal.
- AVL operations: self-balancing insertion and deletion using rotations, searching, and tree traversals.

  
🛠️ Technologies Used

- Programming Language: Java
- IDE: IntelliJ IDEA
- Version Control / Hosting: Git and GitHub
- Application Type: Console-based Java application
The current source files use standard Java classes and do not require a third-party library for the described core functionality.


📁 Project Structure


Postal-Service-Management-System/
├── src/
│   ├── Main.java
│   ├── PostalServiceSystem.java
│   ├── PackageItem.java
│   ├── PackageLinkedList.java
│   ├── DispatchQueue.java
│   ├── UndoStack.java
│   ├── PostalHashTable.java
│   ├── PostalCodeSet.java
│   ├── PackageBST.java
│   ├── PackageAVL.java
│   ├── PostalGraph.java
│   ├── BubbleSort.java
│   ├── SelectionSort.java
│   ├── InsertionSort.java
│   ├── MergeSort.java
│   └── QuickSort.java
├── .gitignore
├── postelservice.iml
└── README.md

Source File Descriptions
File	Responsibility
Main.java	Application entry point.
PostalServiceSystem.java	Main menu and coordination of postal service operations.
PackageItem.java	Represents a package and its details.
PackageLinkedList.java	Linked-list storage and package operations.
DispatchQueue.java	Queue operations for dispatch.
UndoStack.java	Stores operation history.
PostalHashTable.java	Hash-based lookup by tracking ID.
PostalCodeSet.java	Stores unique tracking ID values in a set-like structure.
PackageBST.java	Binary search tree implementation.
PackageAVL.java	AVL self-balancing tree implementation.
PostalGraph.java	Postal office graph, BFS, and DFS.
BubbleSort.java	Bubble sort implementation.
SelectionSort.java	Selection sort implementation.
InsertionSort.java	Insertion sort implementation.
MergeSort.java	Merge sort implementation.
QuickSort.java	Quick sort implementation.


💻 How to Run

Prerequisites
- Java Development Kit (JDK) installed.
- IntelliJ IDEA installed.
Option 1: Clone from GitHub
git clone https://github.com/hasan10631/Postal-Service-Management-System.git
1. Open IntelliJ IDEA.
2. Select File → Open and choose the cloned project folder.
3. Configure a JDK in the project settings if prompted.
4. Open src/Main.java.
5. Run the main() method.
6. Use the numbered console menus to explore the application.
Option 2: Open an Existing Project
If you already have the project folder, open it directly in IntelliJ IDEA, locate Main.java, and run it.


🎓 Academic Context

- Project: Postal Service Management System
- Module: Data Structures and Algorithms (DSA)
- Institution: SLTC Research University
- Project Type: Academic group project
- Language: Java
- IDE: IntelliJ IDEA

  
📚 Learning Outcomes

This project provided practical experience with:
- Implementing data structures in Java.
- Managing records using a linked list.
- Applying FIFO queue and LIFO stack concepts.
- Implementing a hash table with separate chaining.
- Working with BST and AVL trees.
- Implementing sorting algorithms and comparing their behaviour.
- Representing connections using graphs and traversing them with BFS and DFS.
- Organizing code into separate classes.
- Collaborating on a university group assignment.

  
🔧 Limitations and Future Improvements

Possible improvements include:
- Complete the unfinished BST search integration in the main search menu.
- Implement complete undo behaviour for package addition, package updates, and dispatch queue operations.
- Keep all data structures synchronized after every package update and deletion.
- Add input validation for invalid weights and other package fields.
- Add persistent storage using a database or file storage.
- Add a graphical user interface.
- Improve sorting comparison output with clearly measured execution times.
- Add automated unit tests and clearer error messages.
These items are suggested improvements, not features claimed to be complete in the current version.

🔗 Repository

GitHub: Postal Service Management System
Developed collaboratively as an academic group project for the Data Structures and Algorithms module at SLTC Research University.
Tags
Java DataStructures Algorithms IntelliJIDEA GitHub SoftwareDevelopment GroupProject SLTC
