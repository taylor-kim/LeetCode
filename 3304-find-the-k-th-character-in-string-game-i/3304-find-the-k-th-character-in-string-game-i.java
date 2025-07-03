class Solution {
    public char kthCharacter(int k) {
        return mySol(k);
    }

    public char mySol(int k) {
		String s = "a";

		while (s.length() < k) {
			s = s + next(s);
		}

		return s.charAt(k - 1);
	}

	private String next(String s) {
		StringBuilder sb = new StringBuilder();

		for (char c : s.toCharArray()) {
			sb.append(c == 'z' ? 'a' : (char)(c + 1));
		}

		return sb.toString();
	}
}