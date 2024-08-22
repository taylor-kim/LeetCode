class Solution {
    public int findComplement(int num) {
        return mySol(num);
    }

    public int mySol(int num) {
        int ans = 0;
        int pos = 0;

        while (num > 0) {
            int complement = (num & 1) == 0 ? 1 : 0;

            ans |= complement << pos++;

            num >>= 1;
        }

        return ans;
    }
}