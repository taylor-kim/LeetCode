class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        return do_understood(intervals);
    }

    public int do_understood(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            return a[1] != b[1] ? a[1] - b[1] : b[0] - a[0];
        });

        int ans = 0;
        int[] prev = {-1, -1};

        for (int[] interval : intervals) {
            if (prev[1] < interval[0]) {
                ans += 2;
                prev[0] = interval[1] - 1;
                prev[1] = interval[1];
            } else if (prev[0] < interval[0]) {
                prev[0] = prev[1];
                prev[1] = interval[1];
                ans++;
            }
        }

        return ans;
    }

    public int others(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> 
            a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]
        );

        int ans = 0;
        int a = -1, b = -1;

        for (int[] it : intervals) {
            int l = it[0], r = it[1];

            if (l > b) {
                a = r - 1;
                b = r;
                ans += 2;
            } else if (l > a) {
                a = b;
                b = r;
                ans += 1;
            }
        }

        return ans;
    }

    /**
    1, 2, 3, 4, 5
    2, 1, 1,-1,-1,-2
    2, 3, 4, 3, 2, 0
     */

    public int mySol_fail(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });

        for (int[] interval : intervals) {
            System.out.print(Arrays.toString(interval) + ", ");
        }

        System.out.println("");

        int ans = 2;
        int num = intervals[0][0] + 1;

        // 1, 2
        // 2, 3

        for (int i = 1; i < intervals.length; i++) {
            //prev[1] >= cur[0]
            if (intervals[i - 1][1] < intervals[i][0]) {

                ans += 2;
            }
        }

        return ans;
    }
}