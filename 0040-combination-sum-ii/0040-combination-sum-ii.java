class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        return mySol(candidates, target);
    }

    public List<List<Integer>> mySol(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList();

        Arrays.sort(candidates);

        backtrack(candidates, 0, target, result, new ArrayList(), new boolean[candidates.length]);

        return result;
    }

    public void backtrack(int[] candidates, int index, int target, List<List<Integer>> result, List<Integer> list, boolean[] visit) {
        if (target == 0) {
            result.add(new ArrayList(list));
            return;
        }

        if (index >= candidates.length || target < 0) {
            return;
        }

        if (index == 0 || candidates[index - 1] != candidates[index] || visit[index - 1]) {
            int num = candidates[index];

            visit[index] = true;

            list.add(num);
            backtrack(candidates, index + 1, target - num, result, list, visit);
            list.remove(list.size() - 1);

            visit[index] = false;
        }

        backtrack(candidates, index + 1, target, result, list, visit);
    }
}