class Solution {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        return official_simple(differences, lower, upper);
    }

    public int official_simple(int[] differences, int lower, int upper) {
        int current = 1, max = 1, min = 1;

        for (int diff : differences) {
            current += diff;

            max = Math.max(max, current);
            min = Math.min(min, current);

            if (max - min > upper - lower) return 0;
        }

        return (upper - lower) - (max - min) + 1;
    }

    public int try_simple(int[] differences, int lower, int upper) {
        long prev = lower;
        long min = lower;
        long max = lower;

        for (int i = 0; i < differences.length; i++) {
            long next = differences[i] + prev;

            min = Math.min(min, next);
            max = Math.max(max, next);

            // System.out.println(String.format("min:%d, max:%d, next:%d", min, max, next));

            prev = next;
        }

        if (max > upper) {
            min -= (max - upper);
            max = upper;
        }
        
        if (min < lower) {
            max += (lower - min);
            min = lower;
        }

        if (max > upper || min < lower) return 0;

        return (int)(upper - max + min - lower + 1);
    }

    public int mySol2(int[] differences, int lower, int upper) {
        int lo = lower;
        int hi = upper;

        Integer start = null;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            int ret = isSequence(mid, differences, lower, upper);

            if (ret == 1) {
                hi = mid - 1;
            } else if (ret == -1) {
                lo = mid + 1;
            } else {
                start = mid;
                break;
            }
        }

        if (start == null) return 0;

        int[] arr = new int[differences.length + 1];

        arr[0] = start;

        int min = arr[0];
        int max = arr[0];

        for (int i = 0; i < differences.length; i++) {
            arr[i + 1] = differences[i] + arr[i];

            min = Math.min(min, arr[i + 1]);
            max = Math.max(max, arr[i + 1]);
        }

        // System.out.println(String.format("start:%d, min:%d, max:%d", start, min, max));

        return upper - max + min - lower + 1;
    }

    private int isSequence(int start, int[] differences, int lower, int upper) {
        int prev = start;

        for (int diff : differences) {
            int next = diff + prev;

            // System.out.println(String.format("prev:%d, next:%d, lower:%d, upper:%d", prev, next, lower, upper));

            if (next < lower) return -1;

            if (next > upper) return 1;

            prev = next;
        }

        // System.out.println(String.format("ok! start:%d", start));

        return 0;
    }

    public int mySol_fail(int[] differences, int lower, int upper) {
        int min = lower;
        int max = upper;

        for (int diff : differences) {
            if (diff >= 0) {
                max = Math.min(upper - diff, max);
            } else {
                min = Math.max(lower - diff, min);
            }
        }

        System.out.println(String.format("min:%d, max:%d", min, max));

        if (min < lower || max > upper) return 0;

        return max - min;
    }
}