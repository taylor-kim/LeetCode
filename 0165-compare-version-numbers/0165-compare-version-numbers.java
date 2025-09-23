class Solution {
    public int compareVersion(String version1, String version2) {
        return mySol(version1, version2);
    }

    public int mySol(String version1, String version2) {
        int i = 0;
        int j = 0;

        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        while (i < v1.length && j < v2.length) {
            int c = compare(v1[i], v2[j]);

            if (c < 0) return -1;
            if (c > 0) return 1;

            i++;
            j++;
        }

        while (i < v1.length) {
            int a = Integer.parseInt(v1[i++]);

            if (a != 0) return 1;
        }

        while (j < v2.length) {
            int a = Integer.parseInt(v2[j++]);

            if (a != 0) return -1;
        }

        return 0;
    }

    private int compare(String v1, String v2) {
        int a = Integer.parseInt(v1);
        int b = Integer.parseInt(v2);

        return a - b;
    }

    private String removeLeadingZero(String s) {
        int i = 0;

        while (i < s.length()) {
            if (s.charAt(i) == '0') {
                i++;
                continue;
            }
        }

        return s.substring(i);
    }
}