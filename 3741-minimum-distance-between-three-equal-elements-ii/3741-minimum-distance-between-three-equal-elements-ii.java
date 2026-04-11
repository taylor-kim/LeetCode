class Solution {
    public int minimumDistance(int[] nums) {
        return official_index_track(nums);
    }

    public int official_index_track(int[] nums) {
        int n = nums.length;
        int[] indices = new int[n];
        Arrays.fill(indices, -1);
        Map<Integer, Integer> lastIndices = new HashMap();
        
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (lastIndices.containsKey(nums[i])) {
                indices[i] = lastIndices.get(nums[i]);
            }

            lastIndices.put(nums[i], i);
        }

        for (int i = n - 1; i >= 0; i--) {
            int prev = indices[i];
            if (prev != -1) {
                int prevOfPrev = indices[prev];

                if (prevOfPrev != -1) {
                    ans = Math.min(ans, i - prevOfPrev);
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans * 2;
    }

    /**
    n = 10
    idx : 0,1,2,3,4,5,6,7,8,9
    nums: 4,3,7,1,2,7,1,9,7,8
    
    map={8:9, 7:2, 9:7, 1:6, 2:4, 1:3, 3:1, 4:0}
    next={5:8, 2:5}
     */

    public int mySol(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList()).add(i);
        }

        int ans = Integer.MAX_VALUE;

        for (List<Integer> indices : map.values()) {
            for (int i = 0; i < indices.size() - 2; i++) {
                ans = Math.min(ans, indices.get(i + 2) - indices.get(i));
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans * 2;
    }
}