class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        return mySol(arr);
    }

    public List<List<Integer>> mySol(int[] arr) {
        Arrays.sort(arr);

        List<List<Integer>> ans = new ArrayList();
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length - 1; i++) {
            int a = arr[i];
            int b = arr[i + 1];

            if (b - a < min) {
                ans.clear();
                min = b - a;
            }

            if (b - a == min) {
                ans.add(List.of(a, b));
            }
        }

        return ans;
    }
}