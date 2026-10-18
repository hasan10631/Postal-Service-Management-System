public class QuickSort {

    public static void sort(
            PackageItem[] packages,
            int low,
            int high) {

        if (low < high) {

            int pi =
                    partition(
                            packages,
                            low,
                            high
                    );

            sort(
                    packages,
                    low,
                    pi - 1
            );

            sort(
                    packages,
                    pi + 1,
                    high
            );
        }
    }

    private static int partition(
            PackageItem[] packages,
            int low,
            int high) {

        int pivot =
                packages[high].getTrackingId();

        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (packages[j].getTrackingId()
                    <= pivot) {

                i++;

                PackageItem temp =
                        packages[i];

                packages[i] =
                        packages[j];

                packages[j] =
                        temp;
            }
        }

        PackageItem temp =
                packages[i + 1];

        packages[i + 1] =
                packages[high];

        packages[high] =
                temp;

        return i + 1;
    }
}