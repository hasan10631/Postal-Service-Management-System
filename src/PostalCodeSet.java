import java.util.ArrayList;

public class PostalCodeSet {

    private ArrayList<Integer> setList;

    public PostalCodeSet() {
        setList = new ArrayList<>();
    }

    // ==========================================
    // ADD
    // ==========================================

    public boolean add(int postalCode) {

        if (setList.contains(postalCode)) {

            System.out.println(
                    "Duplicate Postal Code " +
                            postalCode +
                            " rejected!"
            );

            return false;
        }

        setList.add(postalCode);

        System.out.println(
                "Postal Code " +
                        postalCode +
                        " added to Set."
        );

        return true;
    }

    // ==========================================
    // REMOVE
    // ==========================================

    public boolean remove(int postalCode) {

        if (setList.remove(Integer.valueOf(postalCode))) {

            System.out.println(
                    "Postal Code " +
                            postalCode +
                            " removed."
            );

            return true;
        }

        return false;
    }

    // ==========================================
    // CONTAINS
    // ==========================================

    public boolean contains(int postalCode) {

        return setList.contains(postalCode);
    }

    // ==========================================
    // DISPLAY
    // ==========================================

    public void displaySet() {

        System.out.println(
                "\n--- Unique Service Postal Codes ---"
        );

        System.out.println(setList);
    }

    // ==========================================
    // SIZE
    // ==========================================

    public int size() {
        return setList.size();
    }
}