class Solution {
    public boolean canReach(int[] arr, int start) {
        return mySol(arr, start);
    }

    public boolean mySol(int[] arr, int start) {
        return topdown(arr, start, new HashSet());
    }

    public boolean topdown(int[] arr, int index, Set<Integer> visit) {
        if (index >= arr.length || index < 0) return false;

        if (arr[index] == 0) return true;

        if (!visit.add(index)) return false;

        if (topdown(arr, index + arr[index], visit)) return true;
        if (topdown(arr, index - arr[index], visit)) return true;

        return false;
    }
}