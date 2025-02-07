class Solution {
    public int tupleSameProduct(int[] nums) {
        return mySol2(nums);
    }

    public int mySol2(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int multi = nums[i] * nums[j];

                map.put(multi, map.getOrDefault(multi, 0) + 1);
            }
        }

        int ans = 0;
        Integer[] memo = new Integer[500];

        // System.out.println(map);

        for (int count : map.values()) {
            if (count > 1) {
                // ans += fact(count, memo);
                // ans += fact(count, memo) * 4;

                // n 개에서 2개 조합
                // n! / 2 * (n - 2)! => n * (n - 1) / 2
                int pairsOfEqualProduct = (count - 1) * count / 2;

                // 1개의 조합당 8개의 tuple
                ans += pairsOfEqualProduct * 8;
            }
        }

        return ans;
    }

    private int fact(int number, Integer[] memo) {
        if (memo[number] != null) return memo[number];

        int fact = 1;
        
        for (int i = number; i > 0; i--) {
            fact *= i;
        }

        return memo[number] = fact;
    }

    public int mySol_tle(int[] nums) {
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            ans += topdown(nums, i, 0, new int[3]);
        }

        return ans;
    }

    public int topdown(int[] nums, int index, int count, int[] datas) {
        // System.out.println(String.format("index:%d, count:%d", index, count));

        if (count == 3) {
            return datas[0] * datas[1] == datas[2] * nums[index] ? 1 : 0;
        }

        datas[count] = nums[index];
        nums[index] = -nums[index];

        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) continue;
            
            ans += topdown(nums, i, count + 1, datas);
        }

        nums[index] = -nums[index];

        return ans;
    }
}