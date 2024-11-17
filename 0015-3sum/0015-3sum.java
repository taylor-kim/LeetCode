class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums != null && nums.length >= 3) {
            Arrays.sort(nums);
            
            return threeSumWithDoubleIndex(nums);
        } else {
            return Collections.emptyList();
        }
    }

    private List<List<Integer>> threeSumWithBruteForce(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);
        Set<String> addedTriplets = new HashSet<>();

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    String key = "" + nums[i] + nums[j] + nums[k];

                    if (nums[i] + nums[j] + nums[k] == 0 && !addedTriplets.contains(key)) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        addedTriplets.add(key);
                    }
                }
            }
        }

        return result;
    }
    
    private List<List<Integer>> threeSumWithDoubleIndex(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int leftIndex = i + 1;
                int rightIndex = nums.length - 1;

                while (leftIndex < rightIndex) {
                    if (leftIndex > i + 1 && nums[leftIndex] == nums[leftIndex - 1]) {
                        leftIndex++;
                        continue;
                    }

                    if (rightIndex < nums.length -1 && nums[rightIndex] == nums[rightIndex + 1]) {
                        rightIndex--;
                        continue;
                    }

                    int sum = nums[i] + nums[leftIndex] + nums[rightIndex];

                    if (sum == 0) {
                        result.add(Arrays.asList(nums[i], nums[leftIndex], nums[rightIndex]));
                        leftIndex++;
                        rightIndex--;
                    } else {
                        if (sum < 0) {
                            leftIndex++;
                        } else {
                            rightIndex--;
                        }
                    }
                }
            }
        }

        return result;
    }
}