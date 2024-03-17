class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        return mySol(intervals, newInterval);
    }

    public int[][] mySol(int[][] intervals, int[] newInterval) {
        int n = intervals.length;

        if (n == 0) return new int[][] {newInterval};

        if (n == 1) {
            if (hasIntersect(intervals[0], newInterval)) {
                return new int[][] {merge(intervals[0], newInterval)};
            } else {
                if (intervals[0][0] < newInterval[0]) {
                    return new int[][] {intervals[0], newInterval};
                } else {
                    return new int[][] {newInterval, intervals[0]};
                }
            }
        }

        List<int[]> list = new ArrayList();
        list.add(intervals[0]);

        int processed = 0;

        boolean added = false;

        while (processed < n) {
            int[] prev = list.get(list.size() - 1);

            if (!added && hasIntersect(prev, newInterval)) {
                prev = merge(prev, newInterval);
                list.set(list.size() - 1, prev);
                added = true;
            }

            int[] current = intervals[processed++];

            if (!added && hasIntersect(current, newInterval)) {
                current = merge(current, newInterval);
                added = true;
            }

            if (hasIntersect(prev, current)) {
                list.set(list.size() - 1, merge(prev, current));
            } else {
                list.add(current);
            }
        }

        if (!added) {
            if (newInterval[1] < list.get(0)[0]) {
                list.add(0, newInterval);
            } else if (list.get(list.size() - 1)[1] < newInterval[0]) {
                list.add(newInterval);
            } else {
                for (int i = 0; i < list.size(); i++) {
                    int[] current = list.get(i);

                    if (newInterval[1] < current[0]) {
                        list.add(i, newInterval);
                        break;
                    }
                }
            }
        }

        int[][] ans = new int[list.size()][2];

        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    int[] merge(int[] a, int[] b) {
        return new int[] {Math.min(a[0], b[0]), Math.max(a[1], b[1])};
    }

    boolean hasIntersect(int[] a, int[] b) {
        if (a[0] > b[0]) {
            int[] temp = a;
            a = b;
            b = temp;
        }

        return a[1] >= b[0];
    }
}