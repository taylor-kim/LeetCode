class Solution {
    public int minLength(String s) {
        return official_inplace(s);
    }

    public int official_inplace(String s) {
        int writeIndex = 0;

        char[] arr = s.toCharArray();

        for (int readIndex = 0; readIndex < arr.length; readIndex++) {
            arr[writeIndex] = arr[readIndex];

            if (writeIndex > 0 && 
                (
                    (arr[writeIndex - 1] == 'A' || arr[writeIndex - 1] == 'C')
                    && arr[writeIndex] == arr[writeIndex - 1] + 1
                )) {
                    writeIndex--;
            } else {
                writeIndex++;
            }
        }

        return writeIndex;
    }

    public int mySol_stack(String s) {
        Stack<Character> stack = new Stack();

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty()
                 && ((stack.peek() == 'A' && c == 'B')
                 || (stack.peek() == 'C' && c == 'D'))) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        return stack.size();
    }

    public int mySol(String s) {
        if (s.indexOf("AB") >= 0 || s.indexOf("CD") >= 0 ) {
            s = s.replaceAll("AB", "").replaceAll("CD", "");

            return mySol(s);
        }

        return s.length();
    }
}