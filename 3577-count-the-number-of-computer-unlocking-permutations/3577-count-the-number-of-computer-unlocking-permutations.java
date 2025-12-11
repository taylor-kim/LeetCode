class Solution {
    public int countPermutations(int[] complexity) {
        return mySol2(complexity);
    }

    public int mySol2(int[] arr) {
        int n = arr.length;
        int min = arr[0];

        for (int i = 1; i < n; i++) {
            if (min >= arr[i]) return 0;
        }

        int mod = (int)1e9 + 7;
        long fact = 1;

        for (int i = 1; i < n; i++) {
            fact = (fact * i) % mod;
        }

        return (int)fact;
    }

    public int trigger(int[] arr) {
        int ans = 0;
        int mod = (int)1e9 + 7;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                ans = (ans + topdown2(arr, i)) % mod;
            }
        }

        return ans;
    }

    public int topdown2(int[] arr, int index) {
        for (int j = 0; j < index; j++) {

        }

        return 0;
    }

    public int mySol_fail(int[] complexity) {
        boolean[] openned = new boolean[complexity.length];
        openned[0] = true;

        int ans = 0;
        int mod = (int)1e9 + 7;

        for (int i = 1; i < complexity.length; i++) {
            ans = (ans + topdown(complexity, 1, i, openned)) % mod;
        }

        return ans;
    }

    public int topdown(int[] arr, int index, int count, boolean[] openned) {
        if (count == arr.length) return 1;
        if (index >= arr.length) return 0;

        int ans = 0;
        int mod = (int)1e9 + 7;

        // System.out.println("index:%d, count:%d".formatted(index, count));

        for (int j = 0; j < index; j++) {
            if ((j != index && openned[j] && arr[j] < arr[index])) {
                openned[index] = true;

                ans = (ans + topdown(arr, index + 1, count + 1, openned)) % mod;

                openned[index] = false;
            }
        }

        // ans = (ans + topdown(arr, index + 1, count, openned)) % mod;

        return ans;
    }
}