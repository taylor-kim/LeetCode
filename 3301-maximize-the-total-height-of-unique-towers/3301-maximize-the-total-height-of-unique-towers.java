class Solution {
    public long maximumTotalSum(int[] maximumHeight) {
        int n = maximumHeight.length;

        Arrays.sort(maximumHeight);

        long ans = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (i + 1 < n) {
                if (maximumHeight[i] >= maximumHeight[i + 1]) {
                    maximumHeight[i] = maximumHeight[i + 1] - 1;
                }
            }
            
            if (maximumHeight[i] <= 0) return -1;

            ans += maximumHeight[i];
        }

        return ans;
    }

    public long bucket_fail(int[] maximumHeight) {
        int max = 0;
        int min = (int)1e9;

        for (int h : maximumHeight) {
            max = Math.max(max, h);
            min = Math.min(min, h);
        }

        Map<Integer, Integer> bucket = new HashMap();

        for (int h : maximumHeight) {
            bucket.put(h - min, bucket.getOrDefault(h - min, 0) + 1);
        }

        int index = max - min;
        int h = index;

        int ans = 0;

        while (index >= 0) {
            while (bucket.getOrDefault(index, 0) > 0) {
                h = Math.min(index, h);

                if (h + min <= 0) return -1;

                ans += h + min;
                h--;

                bucket.put(index, bucket.getOrDefault(index, 0) - 1);
            }

            index--;
        }

        return ans;
    }
}