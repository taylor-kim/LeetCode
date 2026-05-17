class Solution {
    public boolean canReach(int[] arr, int start) {
        return mySol(arr, start);
    }

    public boolean mySol(int[] arr, int start) {
        return topdown(arr, start, new Boolean[arr.length], new HashSet());
    }

    public boolean topdown(int[] arr, int index, Boolean[] memo, Set<Integer> visit) {
        if (index >= arr.length || index < 0) return false;

        if (arr[index] == 0) return true;

        if (memo[index] != null) return memo[index];

        if (!visit.add(index)) return false;

        boolean plus = topdown(arr, index + arr[index], memo, visit);
        boolean minus = topdown(arr, index - arr[index], memo, visit);

        return memo[index] = plus || minus;
    }
}