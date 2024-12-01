class Solution {
    public boolean checkIfExist(int[] arr) {
        return official_set(arr);
    }

    public boolean official_set(int[] arr) {
        Set<Integer> set = new HashSet();

        for (int num : arr) {
            if (set.contains(num * 2) || num % 2 == 0 && set.contains(num / 2)) {
                return true;
            } else {
                set.add(num);
            }
        }

        return false;
    }

    public boolean mySol(int[] arr) {
        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            int index = Arrays.binarySearch(arr, arr[i] * 2);

            if (index >= 0 && index != i) return true;
        }

        return false;
    }
}