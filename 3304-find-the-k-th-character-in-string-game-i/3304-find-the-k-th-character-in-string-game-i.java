class Solution {
    public char kthCharacter(int k) {
        return official(k);
    }

    public char official(int k) {
        int ans = 0;

        while (k > 1) {
            int t = 31 - Integer.numberOfLeadingZeros(k);

            if ((1 << t) == k) {
                t--;
            }

            k -= (1 << t);

            ans++;
        }

        return (char)('a' + (ans % 26));
    }
}