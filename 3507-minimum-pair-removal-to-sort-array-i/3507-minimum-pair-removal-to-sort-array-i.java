class Solution {
    public int minimumPairRemoval(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList();

        for (int num : nums) list.add(num);
        
        int ans = 0;

        while (!isSorted(list)) {
            int minIndex = -1;
            int minSum = Integer.MAX_VALUE;

            for (int i = 0; i < list.size() - 1; i++) {
                int a = list.get(i);
                int b = list.get(i + 1);

                if (minSum > a + b) {
                    minSum = a + b;
                    minIndex = i;
                }
            }

            list.remove(minIndex);
            list.remove(minIndex);
            list.add(minIndex, minSum);

            ans++;
        }

        return ans;
    }

    private boolean isSorted(List<Integer> list) {
        if (list.isEmpty()) return true;

        int prev = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            int num = list.get(i);

            if (prev > num) return false;

            prev = num;
        }

        return true;
    }
}