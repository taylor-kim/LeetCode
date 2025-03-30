class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int[] pre = new int[nums.length+1];
        for(int i=0;i<queries.length;i++)
        {
            pre[queries[i][0]]++;
            pre[queries[i][1]+1]--;
        }
        if(pre[0]<nums[0])
            return false;
        for(int i=1;i<nums.length;i++)
        {
            pre[i]+=pre[i-1];
            if(pre[i]<nums[i])
                return false;
        }
        return true;
    }
}