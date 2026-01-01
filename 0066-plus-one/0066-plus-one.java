class Solution {
    public int[] plusOne(int[] digits) {
		for (int i = digits.length - 1; i >= 0; i--) {
			int added = digits[i] + 1;

			int carry = added / 10;
			digits[i] = added % 10;

			if (carry == 0) {
				return digits;
			}
		}

		int[] result = new int[digits.length + 1];

		result[0] = 1;

		return result;
	}
}