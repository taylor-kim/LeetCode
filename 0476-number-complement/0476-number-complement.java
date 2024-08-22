class Solution {
    public int findComplement(int num) {
        return others_good2(num);
    }

    public int others_good2(int num) {
        int mask = ~0;

        while ((num & mask) != 0) mask <<= 1;

        /***
        num          = 00000101
        mask         = 11111000
        ~mask & ~num = 00000010
         */

        return ~mask & ~num;
    }

    public int others_good(int num) {
        int sum = 0;
        int pos = 0;

        while (sum < num) {
            sum += Math.pow(2, pos++);
        }

        // a + a's complement => 111111
        // 111111 - a = a's complement

        return sum - num;
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