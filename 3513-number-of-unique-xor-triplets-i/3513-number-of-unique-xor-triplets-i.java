class Solution {
    public int uniqueXorTriplets(int[] nums) {
        return hint(nums);
    }

    public int hint(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }

        int max = nums.length;

        int mostSignificantBit = 0;

        for (int i = 31; i >= 0; i--) {
            if ((max & (1 << i)) != 0) {
                mostSignificantBit = i;
                break;
            }
        }

        int maxXor = (int)Math.pow(2, mostSignificantBit + 1) - 1;

        return maxXor + 1;
    }

    public int mySol(int[] nums) {
        Set<Integer> set = new HashSet();

        int max = 0;

        for (int num : nums) {
            max = Math.max(max, num);
        }

        int mostSignificantBit = 0;

        for (int i = 31; i >= 0; i--) {
            if ((max & (1 << i)) != 0) {
                mostSignificantBit = i;
                break;
            }
        }

        mostSignificantBit = mostSignificantBit << 1;

        int maxXor = 1 << (mostSignificantBit + 1);
        
        topdown(nums, 0, 3, 0, set, new boolean[nums.length][4][maxXor]);

        return set.size();
    }

    private void topdown(int[] nums, int index, int count, int xor, Set<Integer> set, boolean[][][] visit) {
        if (count == 0) {
            // set.add(xor);
            return;
        }

        if (index >= nums.length) return;

        // System.out.println(String.format("index:%d, count:%d, xor:%d", index, count, xor));

        if (visit[index][count][xor]) return;

        visit[index][count][xor] = true;

        topdown(nums, index, count - 1, xor ^ nums[index], set, visit);
        topdown(nums, index + 1, count - 1, xor ^ nums[index], set, visit);
        topdown(nums, index + 1, count, xor, set, visit);
    }
}