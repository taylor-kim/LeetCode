class Solution {
    public List<String> generateParenthesis(int n) {
        return mySol(n);
    }

    public List<String> mySol(int n) {
        List<String> list = new ArrayList();

        topdown(list, n, 0, 0, new StringBuilder());

        return list;
    }

    private void topdown(List<String> list, int n, int open, int close, StringBuilder sb) {
        if (open < close || open > n || close > n) return;

        if (open == n && close == n) {
            list.add(sb.toString());
            return;
        }

        sb.append("(");
        topdown(list, n, open + 1, close, sb);
        sb.setLength(sb.length() - 1);

        sb.append(")");
        topdown(list, n, open, close + 1, sb);
        sb.setLength(sb.length() - 1);
    }
}