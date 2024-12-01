class Solution {
    public boolean checkIfExist(int[] arr) {
        return mySol(arr);
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