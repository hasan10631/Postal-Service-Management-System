public class SelectionSort {

    public static void sortByTrackingId(PackageItem[] packages) {

        int n = packages.length;

        for (int i = 0; i < n - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < n; j++) {

                if (packages[j].getTrackingId()
                        < packages[minIndex].getTrackingId()) {

                    minIndex = j;
                }
            }

            PackageItem temp = packages[minIndex];

            packages[minIndex] = packages[i];

            packages[i] = temp;
        }
    }
}
