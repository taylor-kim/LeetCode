class Solution {
    public int[] minOperations(String boxes) {
        return official_onepass_and_space_opt(boxes);
    }

    public int[] official_onepass_and_space_opt(String boxes) {
        int toRightBallCount = 0;
        int movesToRight = 0;
        int toLeftBallCount = 0;
        int movesToLeft = 0;

        int n = boxes.length();
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            ans[i] += movesToRight;

            toRightBallCount += boxes.charAt(i) - '0';
            movesToRight += toRightBallCount;

            int j = n - i - 1;

            ans[j] += movesToLeft;

            toLeftBallCount += boxes.charAt(j) - '0';
            movesToLeft += toLeftBallCount;
        }

        return ans;
    }

    public int[] official_bruteforce(String boxes) {
        int n = boxes.length();
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            if (boxes.charAt(i) == '1') {
                for (int j = 0; j < n; j++) {
                    ans[j] += Math.abs(j - i);
                }
            }
        }

        return ans;
    }

    public int[] mySol(String boxes) {
        int n = boxes.length();
        int[] ans = new int[n];

        char[] arr = boxes.toCharArray();

        int[][] toRight = new int[n][2];
        int[][] toLeft = new int[n][2];

        toRight[0][0] = arr[0] - '0';
        toLeft[n - 1][0] = arr[n - 1] - '0';

        for (int i = 1; i < n; i++) {
            toRight[i][0] = toRight[i - 1][0] + (arr[i] - '0');
            toRight[i][1] = toRight[i - 1][0] + toRight[i - 1][1];

            toLeft[n - i - 1][0] = toLeft[n - i][0] + (arr[n - i - 1] - '0');
            toLeft[n - i - 1][1] = toLeft[n - i][0] + toLeft[n - i][1];
        }

        for (int i = 0; i < n; i++) {
            ans[i] = toRight[i][1] + toLeft[i][1];
        }

        return ans;
    }
}