class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        return mySol(intervals);
    }

    public int[] mySol(List<List<Integer>> intervals) {
        for (int i = 0; i < intervals.size(); i++) {
            intervals.get(i).add(i);
        }

        Collections.sort(intervals, (a, b) -> {
            return a.get(0) - b.get(0);
        });

        Data d = topdown(intervals, 0, 0, new Data[intervals.size()][4]);

        return d.indices.stream().mapToInt(i -> i).toArray();
    }

    private Data topdown(List<List<Integer>> intervals, int index, int size, Data[][] memo) {
        if (index >= intervals.size() || size >= 4) {
            return new Data();
        }

        if (memo[index][size] != null) {
            return memo[index][size];
        }

        Data exclude = topdown(intervals, index + 1, size, memo);

        List<Integer> current = intervals.get(index);

        int point = current.get(2);
        int nextStart = current.get(1) + 1;
        int originIndex = current.get(3);

        // System.out.println("sum:%d, ".formatted(sum) + list);

        int lo = index;
        int hi = intervals.size();

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (intervals.get(mid).get(0) < nextStart) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        Data include = topdown(intervals, lo, size + 1, memo);
        Data added = new Data();
        added.sum = include.sum + point;
        added.indices = new ArrayList(include.indices);

        int insertedIndex = Collections.binarySearch(added.indices, originIndex);

        if (insertedIndex < 0) {
            insertedIndex = -(insertedIndex + 1);
        }

        added.indices.add(insertedIndex, originIndex);

        return memo[index][size] = compare(exclude, added);
    }

    private Data compare(Data d1, Data d2) {
        if (d1.sum == d2.sum) {
            int compare = 0;

            for (int i = 0; i < d1.indices.size() && i < d2.indices.size() && compare == 0; i++) {
                if (d1.indices.get(i) > d2.indices.get(i)) {
                    compare = 1;
                } else if (d1.indices.get(i) < d2.indices.get(i)) {
                    compare = -1;
                }
            }

            if (compare == 0) {
                return d1.indices.size() < d2.indices.size() ? d1 : d2;
            }

            return compare == -1 ? d1 : d2;
        } else {
            return d1.sum > d2.sum ? d1 : d2;
        }
    }

    class Data {
        long sum;
        List<Integer> indices = new ArrayList();
    }
}