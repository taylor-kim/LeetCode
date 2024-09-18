class Solution {
    public String largestNumber(int[] nums) {
        Integer[] arr = new Integer[nums.length];

        for (int i = 0; i < nums.length; i++) {
            arr[i] = nums[i];
        }

        Arrays.sort(arr, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                int paddingFor2 = 10;
                int paddingFor1 = 10;

                while (paddingFor1 <= i2) {
                    paddingFor1 *= 10;
                }

                while (paddingFor2 <= i1) {
                    paddingFor2 *= 10;
                }

                return ((i2 * paddingFor2) + i1) - ((i1 * paddingFor1) + i2);
            }
        });

        if (arr[0] == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        for (Integer num : arr) {
            sb.append(num);
        }

        return sb.toString();
    }
}