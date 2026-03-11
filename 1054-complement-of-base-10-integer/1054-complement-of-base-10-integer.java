class Solution {
    public int bitwiseComplement(int n) {
        return mySol(n);
    }

    public int mySol(int n) {
        char[] arr = Integer.toBinaryString(n).toCharArray();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] == '0' ? '1' : '0';
        }

        return Integer.parseInt(String.valueOf(arr), 2);
    }
}