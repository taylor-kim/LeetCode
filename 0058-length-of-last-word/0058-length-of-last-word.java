class Solution {
    public int lengthOfLastWord(String s) {
		int length = 0;
		int result = 0;

		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) != ' ') {
				length++;
				result = length;
			} else {
				length = 0;

                if (result > 0) {
                    return result;
                }
			}
		}

		return result;
	}
}