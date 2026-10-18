public class MergeSort {
    public static void sort(
            PackageItem[] packages,
            int l,
            int r) {

        if (l < r) {

            int m =
                    l + (r - l) / 2;

            sort(packages, l, m);

            sort(packages, m + 1, r);

            merge(packages, l, m, r);
        }
    }

    private static void merge(
            PackageItem[] packages,
            int l,
            int m,
            int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        PackageItem[] L =
                new PackageItem[n1];

        PackageItem[] R =
                new PackageItem[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = packages[l + i];
        }

        for (int j = 0; j < n2; j++) {
            R[j] = packages[m + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = l;

        while (i < n1 && j < n2) {

            if (L[i].getTrackingId()
                    <= R[j].getTrackingId()) {

                packages[k] = L[i];
                i++;

            } else {

                packages[k] = R[j];
                j++;
            }

            k++;
        }

        while (i < n1) {

            packages[k] = L[i];

            i++;
            k++;
        }

        while (j < n2) {

            packages[k] = R[j];

            j++;
            k++;
        }
    }

}
