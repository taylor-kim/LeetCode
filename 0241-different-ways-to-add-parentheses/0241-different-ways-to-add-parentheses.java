class Solution {
    public List<Integer> diffWaysToCompute(String expression) {
        return mySol2(expression);
    }

    public List<Integer> mySol2(String s) {
        List<Integer> ans = new ArrayList();

        int n = s.length();
        int index = 0;

        while (index < n) {
            while (index < n && !isOp(s.charAt(index))) {
                index++;
            }
            // if (!isOp(s.charAt(index))) {
            //     index++;
            //     continue;
            // }

            if (index >= n) break;

            List<Integer> partA = mySol2(s.substring(0, index));

            char op = s.charAt(index++);

            List<Integer> partB = mySol2(s.substring(index));

            for (int a : partA) {
                for (int b : partB) {
                    ans.add(calc(a, b, op));
                }
            }
        }

        if (ans.size() == 0) {
            ans.add(Integer.parseInt(s));
        }

        return ans;
    }

    public List<Integer> topdown2(String s) {
        if (s == null || "".equals(s)) return new ArrayList();

        if (s.length() == 1 || s.length() == 2 && !isOp(s.charAt(1))) {
            List<Integer> ans = new ArrayList();

            System.out.println(s);

            ans.add(Integer.parseInt(s));

            return ans;
        } else {
            return mySol2(s);
        }
    }

    public List<Integer> mySol_fail(String s) {
        if (s == null || s.length() == 0) return new ArrayList();

        if (s.length() == 1 || s.length() == 2 && !isOp(s.charAt(1))) {
            return Arrays.asList(Integer.parseInt(s));
        }

        return topdown(0, s);
    }

    public List<Integer> topdown(int index, String s) {
        int n = s.length();

        if (index >= n - 1) return new ArrayList();

        // if (index >= n) return new ArrayList();

        int prevNum = 0;
        char prevSign = '+';
        int i = index;

        List<Integer> ans = new ArrayList();

        while (i < n) {
            int num = 0;

            while (i < n && !isOp(s.charAt(i))) {
                num = num * 10 + (int)(s.charAt(i) - '0');
                i++;
            }

            num = calc(prevNum, num, prevSign);

            // System.out.println(num);

            if (i == n) {
                ans.add(num);
                break;
            }

            char sign = s.charAt(i++);
            List<Integer> subList = mySol_fail(s.substring(index));

            // System.out.println(String.format("num:%d, sign:%c, sub:%s", num, sign, subList));

            for (int sub : subList) {
                ans.add(calc(num, sub, sign));
            }

            prevNum = num;
            prevSign = sign;
        }

        System.out.println(String.format("index:%d, ans:%s", index, ans));

        return ans;
    }

    private boolean isOp(char c) {
        return c == '+' || c == '-' || c == '*';
    }

    private int calc(int a, int b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else {
            return a * b;
        }
    }
}