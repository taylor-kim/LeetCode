class Solution {
    public int[] sortedSquares(int[] nums) {
        return mySol(nums);
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