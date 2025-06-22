class Solution {
    public String[] divideString(String s, int k, char fill) {
        return mySol(s, k, fill);
    }

    public String[] mySol(String s, int k, char fill) {
        int n = s.length();

        int odd = n % k;
        String[] ans = new String[(n + k - 1) / k];

        int index = 0;

        while (index < n) {
            ans[index / k] = s.substring(index, Math.min(index + k, n));
            index += k;
        }

        if (ans[ans.length - 1].length() < k) {
            ans[ans.length - 1] = ans[ans.length - 1] + String.valueOf(fill).repeat(k - ans[ans.length - 1].length());
        }

        // System.out.println(Arrays.toString(ans));

        return ans;
    }
}