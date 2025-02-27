class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        return mySol(arr);
    }

    public int mySol(int[] arr) {
        return backtrack(arr, 0, new ArrayList());
    }

    private int backtrack(int[] arr, int index, List<Integer> list) {
        if (index >= arr.length) {
            return list.size() <= 2 ? 0 : list.size();
        }

        int include = 0;

        if (list.size() < 2) {
            list.add(arr[index]);

            include = backtrack(arr, index + 1, list);

            list.remove(list.size() - 1);
        } else {
            int size = list.size();
            int sum = list.get(size - 2) + list.get(size - 1);

            if (sum == arr[index]) {
                list.add(arr[index]);

                include = backtrack(arr, index + 1, list);

                list.remove(list.size() - 1);
            } else if (sum > arr[index]) {
                include = backtrack(arr, index + 1, list);
            } else {
                include = list.size() <= 2 ? 0 : list.size();
            }
        }

        int exclude = backtrack(arr, index + 1, list);

        return Math.max(include, exclude);
    }
}