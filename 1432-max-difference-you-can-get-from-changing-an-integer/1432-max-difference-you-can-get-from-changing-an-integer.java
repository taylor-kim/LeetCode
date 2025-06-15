class Solution {
    public int maxDiff(int num) {
        return mySol(num);
    }

    public int mySol(int num) {
        String max = String.valueOf(num);
        String min = String.valueOf(num);

        for (int i = 0; i < max.length(); i++) {
            char c = max.charAt(i);

            if (c == '9') continue;

            max = max.replace(c, '9');

            break;
        }

        char first = min.charAt(0);

        if (first != '1') {
            min = min.replace(first, '1');
        } else {
            for (int i = 1; i < min.length(); i++) {
                char c = min.charAt(i);
                if (c != first) {
                    min = min.replace(c, '0');
                    break;
                }
            }
        }

        return Integer.parseInt(max) - Integer.parseInt(min);
    }
}