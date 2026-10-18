public class BubbleSort {
    public static void sortByTrackingId(PackageItem[] packages) {

        int n = packages.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {

            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {

                if (packages[j].getTrackingId()
                        > packages[j + 1].getTrackingId()) {

                    PackageItem temp = packages[j];

                    packages[j] = packages[j + 1];

                    packages[j + 1] = temp;

                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }
}
