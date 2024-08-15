class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        return mySol2(candidates, target);
    }

    public List<List<Integer>> mySol2(int[] arr, int target) {
        Arrays.sort(arr);

        List<List<Integer>> result = new ArrayList();

        mySol2(arr, target, 0, new boolean[arr.length], result, new ArrayList());

        return result;
    }

    public void mySol2(int[] arr, int target, int index, boolean[] visit, List<List<Integer>> result, List<Integer> list) {
        if (target == 0) {
            result.add(new ArrayList(list));
            return;
        }

        if (target < 0 || index >= arr.length || visit[index]) return;

        for (int i = index; i < arr.length; i++) {
            if (i == index || arr[i - 1] < arr[i] || visit[i - 1]) {
                visit[i] = true;
                list.add(arr[i]);

                mySol2(arr, target - arr[i], i + 1, visit, result, list);

                list.remove(list.size() - 1);
                visit[i] = false;
            }
        }
    }
}