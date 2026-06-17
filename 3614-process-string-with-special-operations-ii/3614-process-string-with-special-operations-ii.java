class Solution {
    public char processStr(String s, long k) {
        return official(s, k);
    }

    public char official(String s, long k) {
        long length = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '*') {
                if (length > 0) length--;
            } else if (c == '#') {
                length *= 2;
            } else if (c == '%') {
                //nothing
            } else {
                length++;
            }
        }

        if (k + 1 > length) return '.';

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if (c == '*') {
                length++;
            } else if (c == '#') {
                long half = length / 2;
                if (k + 1 > half) {
                    k = k - half;
                }
                length /= 2;
            } else if (c == '%') {
                k = length - k - 1;
            } else {
                if (k + 1 == length) return c;
                
                length--;
            }
        }

        return '.';
    }
}