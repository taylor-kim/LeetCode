class Solution {
    public int[] sortedSquares(int[] nums) {
        return simple_bucket_sort(nums);
    }
    
    public int[] simple_bucket_sort(int[] nums) {
        int n = nums.length;
        
        int max = 0;
        
        for (int i = 0; i < n; i++) {
            max = Math.max(max, Math.abs(nums[i]));
        }
        
        int[] bucket = new int[max + 1];
        
        for (int num : nums) {
            bucket[Math.abs(num)]++;
        }
        
        int[] ans = new int[n];
        int index = 0;
        
        for (int num = 0; num <= max; num++) {
            if (bucket[num] == 0) continue;
            
            int count = bucket[num];
            
            while (count-- > 0) {
                ans[index++] = num * num;
            }
        }
        
        return ans;
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