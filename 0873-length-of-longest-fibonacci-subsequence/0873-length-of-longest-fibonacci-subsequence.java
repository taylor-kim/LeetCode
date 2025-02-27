class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        return bf(arr);
    }

    public int bf(int[] arr) {
        int n = arr.length;
        int ans = 0;

        Set<Integer> set = new HashSet();

        for (int num : arr) set.add(num);

        for (int i = 0; i < n; i++) {
            int a = arr[i];

            for (int j = i + 1; j < n; j++) {
                int b = arr[j];

                ans = Math.max(ans, findLength(a, b, set));
            }
        }

        return ans;
    }

    private int findLength(int a, int b, Set<Integer> set) {
        int length = 2;

        while (set.contains(a + b)) {
            int sum = a + b;
            a = b;
            b = sum;
            length++;
        }

        return length == 2 ? 0 : length;
    }

    public int mySol_tle(int[] arr) {
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