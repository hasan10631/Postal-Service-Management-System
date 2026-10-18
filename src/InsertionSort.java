public class InsertionSort {
    public static void sortByTrackingId(PackageItem[] packages) {

        int n = packages.length;

        for (int i = 1; i < n; i++) {

            PackageItem key = packages[i];

            int j = i - 1;

            while (j >= 0 &&
                    packages[j].getTrackingId()
                            > key.getTrackingId()) {

                packages[j + 1] = packages[j];

                j--;
            }

            packages[j + 1] = key;
        }
    }
}
