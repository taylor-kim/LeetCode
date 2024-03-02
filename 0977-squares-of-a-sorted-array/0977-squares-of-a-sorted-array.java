class Solution {
    public int[] sortedSquares(int[] nums) {
        return others_two_pointer(nums);
    }
    
    public int[] others_two_pointer(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        
        int[] ans = new int[n];
        int index = n - 1;
        
        while (left <= right) {
            if (Math.abs(nums[left]) < Math.abs(nums[right])) {
                ans[index--] = nums[right] * nums[right--];
            } else {
                ans[index--] = nums[left] * nums[left++];
            }
        }
        
        return ans;
    }
    
    public int[] mySol(int[] nums) {
        int n = nums.length;
        
        Queue<Integer> pq = new PriorityQueue();
        
        for (int num : nums) {
            pq.add(num * num);
        }
        
        int[] ans = new int[n];
        int index = 0;
        
        while (!pq.isEmpty()) {
            ans[index++] = pq.poll();
        }
        
        return ans;
    }
}