class Solution {
    public int compareVersion(String version1, String version2) {
        return othersIterative2(version1, version2);
    }

    public int othersIterative2(String version1, String version2) {
        int n1 = 0;
        int n2 = 0;

        char[] v1 = version1.toCharArray();
        char[] v2 = version2.toCharArray();

        for (int i = 0, j = 0; i < v1.length || j < v2.length; i++, j++) {
            while (i < v1.length && v1[i] != '.') {
                n1 = n1 * 10 + (v1[i++] - '0');
            }

            while (j < v2.length && v2[j] != '.') {
                n2 = n2 * 10 + (v2[j++] - '0');
            }

            if (n1 != n2) {
                return n1 > n2 ? 1 : -1;
            }

            n1 = 0;
            n2 = 0;
        }

        return 0;
    }

    public int othersIterative(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        for (int i = 0; i < Math.max(v1.length, v2.length); i++) {
            int n1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int n2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;

            if (n1 != n2) {
                return n1 > n2 ? 1 : -1;
            }
        }
        
        return 0;
    }

    public int mySol(String version1, String version2) {
        int ans = 0;

        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        return mySol(v1, v2, 0);
    }

    public int mySol(String[] v1, String[] v2, int index) {
        if (v1.length == v2.length && index >= v1.length) return 0;
        if (index >= v1.length && index >= v2.length) return 0;

        int iv1 = index >= v1.length ? 0 : Integer.parseInt((v1[index]));
        int iv2 = index >= v2.length ? 0 : Integer.parseInt((v2[index]));

        if (iv1 != iv2) {
            return iv1 > iv2 ? 1 : -1;
        } else {
            return mySol(v1, v2, index + 1);
        }
    }

    private String trimLeadingZero(String s) {
        int i = 0;

        while (i < s.length() && s.charAt(i) == '0') {
            i++;
        }

        return i < s.length() ? s.substring(i) : "0";
    }
}