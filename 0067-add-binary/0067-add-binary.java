class Solution {
    public String addBinary(String a, String b) {
        return mySol(a, b);
    }

    public String mySol(String a, String b) {
        StringBuilder sb = new StringBuilder();

        int i = a.length();
        int j = b.length();

        int carry = 0;

        while (i > 0 || j > 0) {
            i--;
            j--;
            int c1 = i >= 0 ? a.charAt(i) - '0' : 0;
            int c2 = j >= 0 ? b.charAt(j) - '0' : 0;

            int next = c1 + c2 + carry;

            sb.append(next % 2);

            carry = next >> 1;
        }

        if (carry > 0) {
            sb.append(carry);
        }

        return sb.reverse().toString();
    }
}