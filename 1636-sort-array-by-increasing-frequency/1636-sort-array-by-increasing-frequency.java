class Solution {
    public int[] frequencySort(int[] nums) {
        return mySol(nums);
    }

    public int[] mySol(int[] nums) {
        int[] freq = new int[201];
        Integer[] ans = new Integer[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            freq[num + 100]++;
            ans[i] = num;
        }
        
        Arrays.sort(ans, (a, b) -> {
            return freq[a + 100] != freq[b + 100] ? freq[a + 100] - freq[b + 100] : b - a;
        });

        for (int i = 0; i < nums.length; i++) {
            nums[i] = ans[i];
        }

        return nums;
    }

    public int[] mySol_try_later(int[] nums) {
        int[] freq = new int[201];

        for (int num : nums) {
            freq[num]++;
        }

        int[] bucket = new int[nums.length + 1];

        // Map<Integer, List<Integer>> bucket = new HashMap();

        for (int i = 0; i < freq.length; i++) {
            bucket[freq[i]] = i;
            // bucket.computeIfAbsent(freq[i], k -> new ArrayList()).add(i);
        }

        int index = 0;

        for (int count = 1; index < nums.length; count++) {
            
            for (int i = 0; i < count; i++) {
                nums[index] = bucket[count];
            }
        }

        return null;
    }
}