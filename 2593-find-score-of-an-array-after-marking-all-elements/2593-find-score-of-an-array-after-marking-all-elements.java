class Solution {
    public long findScore(int[] nums) {
        return mySol(nums);
    }

    public long mySol2(int[] nums) {
        int max = 0;

        for (int num : nums) max = Math.max(max, num);

        int[][] freq = new int[max + 1][2];
        
        for (int i = 0; i < nums.length; i++) {
            // freq[nums[i]][0]
        }

        long sum = 0;

        return sum;
    }

    public long mySol(int[] nums) {
        List<int[]> list = new ArrayList();
        
        for (int i = 0; i < nums.length; i++) {
            list.add(new int[] {nums[i], i});
        }

        Collections.sort(list, (a, b) -> {
            return a[0] - b[0];
        });

        long sum = 0;

        Set<Integer> marked = new HashSet();

        while (list.size() > 0) {
            int[] pick = list.remove(0);

            if (marked.add(pick[1])) {
                sum += pick[0];
                
                marked.add(Math.max(pick[1] - 1, 0));
                marked.add(Math.min(pick[1] + 1, nums.length - 1));
            }
        }

        return sum;
    }
}