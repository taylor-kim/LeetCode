class Solution {
    public List<String> generateParenthesis(int n) {
        return mySol(n);
    }

    public List<String> mySol(int n) {
        List<String> list = new ArrayList();

        backtrack(n, 0, 0, new StringBuilder(), list);

        return list;
    }

    private void backtrack(int n, int open, int close, StringBuilder sb, List<String> list) {
        if (open < close || open > n || close > n) return;

        if (open == n && close == n) {
            list.add(sb.toString());
            return;
        }

        backtrack(n, open + 1, close, sb.append("("), list);
        sb.setLength(sb.length() - 1);
        backtrack(n, open, close + 1, sb.append(")"), list);
        sb.setLength(sb.length() - 1);
    }
}
