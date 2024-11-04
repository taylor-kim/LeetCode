class Solution {
    public String compressedString(String word) {
        return mySol(word);
    }

    public String mySol(String word) {
        int n = word.length();

        if (n == 0) return "";

        StringBuilder sb = new StringBuilder();

        char prev = word.charAt(0);
        int count = 1;

        for (int i = 1; i < n; i++) {
            char c = word.charAt(i);

            if (prev == c) {
                count++;

                if (count == 10) {
                    sb.append(9).append(c);
                    count = 1;
                }
            } else {
                sb.append(count).append(prev);
                prev = c;
                count = 1;
            }
        }

        sb.append(count).append(prev);

        return sb.toString();
    }
}