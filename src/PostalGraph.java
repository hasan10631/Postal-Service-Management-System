import java.util.*;

public class PostalGraph {



    private Map<String, List<String>> adjacencyList;

    public PostalGraph() {

        adjacencyList =
                new LinkedHashMap<>();
    }


    public void addOffice(String office) {

        if (!adjacencyList.containsKey(office)) {

            adjacencyList.put(
                    office,
                    new ArrayList<>()
            );

            System.out.println(
                    "Postal office " +
                            office +
                            " added successfully."
            );

        } else {

            System.out.println(
                    "Postal office already exists."
            );
        }
    }



    public void addConnection(
            String office1,
            String office2) {

        if (!adjacencyList.containsKey(office1) ||
                !adjacencyList.containsKey(office2)) {

            System.out.println(
                    "Both postal offices must exist first."
            );

            return;
        }

        if (!adjacencyList.get(office1)
                .contains(office2)) {

            adjacencyList
                    .get(office1)
                    .add(office2);
        }

        if (!adjacencyList.get(office2)
                .contains(office1)) {

            adjacencyList
                    .get(office2)
                    .add(office1);
        }

        System.out.println(
                "Connection added between " +
                        office1 +
                        " and " +
                        office2
        );
    }



    public void displayGraph() {

        System.out.println(
                "\n========== POSTAL NETWORK =========="
        );

        if (adjacencyList.isEmpty()) {

            System.out.println(
                    "No postal offices available."
            );

            return;
        }

        for (String office :
                adjacencyList.keySet()) {

            System.out.print(
                    office + " -> "
            );

            List<String> connections =
                    adjacencyList.get(office);

            for (int i = 0;
                 i < connections.size();
                 i++) {

                System.out.print(
                        connections.get(i)
                );

                if (i < connections.size() - 1) {
                    System.out.print(", ");
                }
            }

            System.out.println();
        }
    }



    public void bfs(String startOffice) {

        if (!adjacencyList.containsKey(startOffice)) {

            System.out.println(
                    "Postal office not found."
            );

            return;
        }

        Set<String> visited =
                new HashSet<>();

        Queue<String> queue =
                new LinkedList<>();

        visited.add(startOffice);

        queue.add(startOffice);

        System.out.println(
                "\n--- BFS Traversal ---"
        );

        while (!queue.isEmpty()) {

            String current =
                    queue.poll();

            System.out.print(
                    current + " "
            );

            for (String neighbour :
                    adjacencyList.get(current)) {

                if (!visited.contains(neighbour)) {

                    visited.add(neighbour);

                    queue.add(neighbour);
                }
            }
        }

        System.out.println();
    }



    public void dfs(String startOffice) {

        if (!adjacencyList.containsKey(startOffice)) {

            System.out.println(
                    "Postal office not found."
            );

            return;
        }

        Set<String> visited =
                new HashSet<>();

        System.out.println(
                "\n--- DFS Traversal ---"
        );

        dfsRecursive(
                startOffice,
                visited
        );

        System.out.println();
    }

    private void dfsRecursive(
            String office,
            Set<String> visited) {

        visited.add(office);

        System.out.print(
                office + " "
        );

        for (String neighbour :
                adjacencyList.get(office)) {

            if (!visited.contains(neighbour)) {

                dfsRecursive(
                        neighbour,
                        visited
                );
            }
        }
    }
}