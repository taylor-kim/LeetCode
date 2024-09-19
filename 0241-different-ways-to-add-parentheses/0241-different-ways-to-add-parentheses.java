class Solution {
    public List<Integer> diffWaysToCompute(String expression) {
        return othersWithMemo(expression);
    }

    public List<Integer> othersWithMemo(String s) {
        return othersWithMemo(s, new HashMap());
    }

    public List<Integer> othersWithMemo(String s, Map<String, List<Integer>> memo) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        List<Integer> result = new ArrayList();

        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                List<Integer> sub1 = othersWithMemo(s.substring(0, i), memo);
                List<Integer> sub2 = othersWithMemo(s.substring(i + 1), memo);

                for (int a : sub1) {
                    for (int b : sub2) {
                        switch (s.charAt(i)) {
                            case '+':
                                result.add(a + b);
                                break;
                            case '-':
                                result.add(a - b);
                                break;
                            case '*':
                                result.add(a * b);
                                break;
                        }
                    }
                }
            }
        }

        if (result.size() == 0) {
            result.add(Integer.parseInt(s));
        }

        memo.put(s, result);

        return result;
    }

    public List<Integer> others(String s) {
        List<Integer> result = new ArrayList();

        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                List<Integer> sub1 = others(s.substring(0, i));
                List<Integer> sub2 = others(s.substring(i + 1));

                for (int a : sub1) {
                    for (int b : sub2) {
                        switch (s.charAt(i)) {
                            case '+':
                                result.add(a + b);
                                break;
                            case '-':
                                result.add(a - b);
                                break;
                            case '*':
                                result.add(a * b);
                                break;
                        }
                    }
                }
            }
        }

        if (result.size() == 0) {
            result.add(Integer.parseInt(s));
        }

        return result;
    }
}