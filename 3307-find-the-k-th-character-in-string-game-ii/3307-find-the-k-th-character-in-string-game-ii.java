class Solution {
    public char kthCharacter(long k, int[] operations) {
        return byVersionOneSol(k, operations);
    }

    public char byVersionOneSol(long k, int[] operations) {
        int ans = 0;

        while (k != 1) {
            int t = 63 - Long.numberOfLeadingZeros(k);

            if ((1L << t) == k) {
                t--;
            }

            k = k - (1L << t);

            ans += operations[t];
        }

        return (char)('a' + (ans % 26));
    }

    public char mySol_tle(long k, int[] operations) {
        String s = "a";

        int index = 0;
        
        while (s.length() < k) {
            if (operations[index++] == 0) {
                s += s;
            } else {
                s += next(s);
            }
        }

        return s.charAt((int)k - 1);
    }

    private String next(String s) {
		StringBuilder sb = new StringBuilder();

		for (char c : s.toCharArray()) {
			sb.append(c == 'z' ? 'a' : (char)(c + 1));
		}

		return sb.toString();
	}
}