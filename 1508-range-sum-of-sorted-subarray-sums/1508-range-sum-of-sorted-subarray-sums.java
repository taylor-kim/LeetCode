class Solution {
    public int rangeSum(int[] nums, int n, int left, int right) {
        return mySol_bruteForce(nums, n, left, right);
    }

    public int mySol_bruteForce(int[] nums, int n, int left, int right) {
        List<Integer> list = subarray(nums);

        Collections.sort(list);

        System.out.println(list);

        long sum = 0;

        for (int i = left - 1; i < right; i++) {
            sum += list.get(i);
        }

        int mod = (int)1e9 + 7;

        return (int)(sum % mod);
    }

    public List<Integer> subarray(int[] nums) {
        List<Integer> list = new ArrayList();

        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            list.add(sum);

            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                list.add(sum);
            }
        }

        return list;
    }
}