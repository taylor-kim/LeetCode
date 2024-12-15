class Solution {
    public long continuousSubarrays(int[] nums) {
        return official_treemap(nums);
    }

    public long official_treemap(int[] nums) {
        long ans = 0;
        int left = 0;

        TreeMap<Integer, Integer> freq = new TreeMap();

        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];

            freq.put(num, freq.getOrDefault(num, 0) + 1);

            while (freq.lastKey() - freq.firstKey() > 2) {
                int ln = nums[left++];
                freq.put(ln, freq.get(ln) - 1);

                if (freq.get(ln) == 0) freq.remove(ln);
            }

            ans += (right - left) + 1;
        }

        return ans;
    }

    public long mySol(int[] nums) {
        long ans = 0;
        int left = 0;

        Map<Integer, Integer> freq = new HashMap();

        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];

            freq.put(num, freq.getOrDefault(num, 0) + 1);

            int[] minMax = getMinMax3(freq);

            while (Math.abs(num - minMax[0]) > 2 || Math.abs(num - minMax[1]) > 2) {
                int ln = nums[left++];
                freq.put(ln, freq.get(ln) - 1);

                if (freq.get(ln) == 0) freq.remove(ln);

                minMax = getMinMax3(freq);
            }

            ans += (right - left) + 1;
        }

        return ans;
    }

    private int[] getMinMax3(Map<Integer, Integer> map) {
        int[] minMax = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        
        for (Integer key : map.keySet()) {
            minMax[0] = Math.min(minMax[0], key);
            minMax[1] = Math.max(minMax[1], key);
        }

        return minMax;
    }

    private int[] getMinMax2(int[] freq, int[] prevMinMax) {
        int[] minMax = {-1, -1};

        System.out.println(String.format("prevMinMax:%s", Arrays.toString(prevMinMax)));

        for (int i = prevMinMax[0]; i <= prevMinMax[1]; i++) {
            if (freq[i] > 0) {
                minMax[0] = i;
                break;
            }
        }

        for (int i = prevMinMax[1]; i >= prevMinMax[0]; i--) {
            if (freq[i] > 0) {
                minMax[1] = i;
                break;
            }
        }

        return minMax;
    }

    private int[] getMinMax(int[] nums, int left, int right) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = left; i <= right; i++) {
            int num = nums[i];
            min = Math.min(num, min);
            max = Math.max(num, max);
        }

        return new int[] {min, max};
    }
}