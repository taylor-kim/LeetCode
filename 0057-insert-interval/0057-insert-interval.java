class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        return mySol(intervals, newInterval);
    }

    public int[][] official_binarysearch(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        if (n == 0) return new int[][] {newInterval};

        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int[] midItem = intervals[mid];

            if (midItem[0] < newInterval[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        List<int[]> list = new ArrayList();

        for (int i = 0; i < left; i++) {
            list.add(intervals[i]);
        }

        list.add(newInterval);

        for (int i = left; i < n; i++) {
            list.add(intervals[i]);
        }

        List<int[]> merged = new ArrayList();

        for (int[] item : list) {
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < item[0]) {
                merged.add(item);
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], item[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public int[][] official_linear(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        if (n == 0) return new int[][] {newInterval};

        int i = 0;
        List<int[]> ans = new ArrayList();

        while (i < n && intervals[i][1] < newInterval[0]) {
            ans.add(intervals[i++]);
        }

        while (i < n && newInterval[1] >= intervals[i][0]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        ans.add(newInterval);

        while (i < n) {
            ans.add(intervals[i]);
            i++;
        }

        return ans.toArray(new int[ans.size()][]);
    }

    public int[][] mySol(int[][] intervals, int[] newInterval) {
        int n = intervals.length;

        if (n == 0) return new int[][] {newInterval};

        List<int[]> list = new ArrayList();
        list.add(intervals[0]);

        int processed = 1;

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