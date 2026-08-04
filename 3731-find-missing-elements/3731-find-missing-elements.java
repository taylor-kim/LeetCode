class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        return mySol(nums);
    }

    public List<Integer> mySol(int[] nums) {
        List<Integer> ans = new ArrayList();

        int[] counter = new int[101];

        int min = 101;
        int max = 0;

        for (int num : nums) {
            counter[num]++;
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int num = min;

        while (num <= max) {
            if (counter[num] == 0) {
                ans.add(num);
            }
            num++;
        }

        return ans;
    }
}