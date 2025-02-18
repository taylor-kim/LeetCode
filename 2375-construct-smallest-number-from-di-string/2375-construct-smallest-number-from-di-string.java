class Solution {
    public String smallestNumber(String pattern) {
        return mySol_bf(pattern);
    }

    public String mySol_bf(String pattern) {
        StringBuilder sb = new StringBuilder();

        topdown(0, pattern, sb, new boolean[10]);

        return sb.toString();
    }
    
    private boolean topdown(int index, String pattern, StringBuilder sb, boolean[] usedNumber) {
        if (index >= pattern.length()) {
            for (int num = 1; num < 10; num++) {
                if (!usedNumber[num]) {
                    int prevNum = (int)(sb.charAt(sb.length() - 1) - '0');
                    
                    if (pattern.charAt(index - 1) == 'I') {
                        if (prevNum > num) return false;
                    } else {
                        if (prevNum < num) return false;
                    }
                    
                    sb.append(num);
                    return true;
                }
            }

            return false;
        }

        List<Integer> numbers = new ArrayList();

        if (index == 0) {
            for (int number = 1; number < 10; number++) {
                if (!usedNumber[number]) {
                    numbers.add(number);
                }
            }
        } else {
            int prevNumber = sb.charAt(sb.length() - 1) - '0';
            char c = pattern.charAt(index - 1);

            if (c == 'I') {
                // smallestOneBiggerThanPrevious
                for (int number = 1; number < 10; number++) {
                    if (!usedNumber[number]) {
                        numbers.add(number);
                    }
                }
            } else if (c == 'D') {
                // asSmallAsPossibleSmallerThanPrevious
                for (int number = 1; number < prevNumber; number++) {
                    if (!usedNumber[number]) {
                        numbers.add(number);
                    }
                }
            }
        }

        for (int number : numbers) {
            if (usedNumber[number]) continue;

            usedNumber[number] = true;

            sb.append(number);

            if (topdown(index + 1, pattern, sb, usedNumber)) {
                return true;
            }

            sb.setLength(sb.length() - 1);

            usedNumber[number] = false;
        }

        return false;
    }
}