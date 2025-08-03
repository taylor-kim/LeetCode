class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        return mySol2(fruits, startPos, k);
    }

    public int mySol2(int[][] fruits, int startPos, int k) {
        int maxPos = startPos;
        
        for (int[] fruit : fruits) {
            maxPos = Math.max(maxPos, fruit[0]);
        }

        int[] sumToLeft = new int[maxPos + 1];
        int[] sumToRight = new int[maxPos + 1];

        for (int[] fruit : fruits) {
            sumToLeft[fruit[0]] = fruit[1];
            sumToRight[fruit[0]] = fruit[1];
        }

        for (int i = 0; i <= maxPos; i++) {
            sumToRight[i] += i > 0 ? sumToRight[i - 1] : 0;

            int j = maxPos - i;

            sumToLeft[j] += j < maxPos ? sumToLeft[j + 1] : 0;
        }

        // System.out.println(Arrays.toString(sumToRight));
        // System.out.println(Arrays.toString(sumToLeft));

        int ans = 0;

        for (int left = 0; left * 2 <= k; left++) {
            int getFromLeft = sumToLeft[Math.max(0, startPos - left)] - sumToLeft[startPos];

            int getFromRight = sumToRight[Math.min(maxPos, startPos + k - (left * 2))] - sumToRight[startPos];

            int getFromCurrent = sumToRight[startPos] - (startPos > 0 ? sumToRight[startPos - 1] : 0);

            ans = Math.max(ans, getFromLeft + getFromRight + getFromCurrent);

            // System.out.println(String.format("left:%d, getFromLeft:%d, getFromRight:%d, getFromCurrent:%d"
            //                     , left, getFromLeft, getFromRight, getFromCurrent));
        }

        for (int right = 0; right * 2 <= k; right++) {
            int getFromLeft = sumToLeft[Math.max(0, startPos - (k - (right * 2)))] - sumToLeft[startPos];

            int getFromRight = sumToRight[Math.min(maxPos, right + startPos)] - sumToRight[startPos];

            int getFromCurrent = sumToRight[startPos] - (startPos > 0 ? sumToRight[startPos - 1] : 0);

            ans = Math.max(ans, getFromLeft + getFromRight + getFromCurrent);
        }

        return ans;
    }

    public int mySol_hold(int[][] fruits, int startPos, int k) {
        int index = findIndex(fruits, startPos);
        int ans = 0;

        if (index >= 0) {
            int base = fruits[index][1];

            int left = goOneSide(fruits, startPos, index - 1, k, -1);
            int right = goOneSide(fruits, startPos, index + 1, k, 1);

            ans = Math.max(left, right) + base;
        } else {
            index = -(index + 1);

            int left = goOneSide(fruits, startPos, index - 1, k, -1);
            int right = goOneSide(fruits, startPos, index, k, 1);

            ans = Math.max(left, right);
        }

        return ans;
    }

    private int goOneSide(int[][] fruits, int prevPos, int index, int k, int direction) {
        int get = 0;
        

        while (k > 0 && index >= 0 && index < fruits.length) {
            int[] fruit = fruits[index];

            if (direction > 0) {
                k -= fruit[0] - prevPos;
            } else {
                k -= prevPos - fruit[0];
            }
            prevPos = fruit[0];
            index += direction;

            if (k >= 0) {
                get += fruit[1];
            }
        }

        return get;
    }

    private int goBothSideWithEachSteps(int[][] fruits, int index, int startPos, int k, int leftStep, int rightStep) {
        int totalMove = 0;

        while (totalMove <= k) {
            int[] fruit = fruits[index];
        }

        return 1;
    }

    private int findIndex(int[][] fruits, int pos) {
        int lo = 0;
        int hi = fruits.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int midPos = fruits[mid][0];

            if (pos == midPos) {
                return mid;
            } else if (midPos < pos) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return -(lo + 1);
    }
}