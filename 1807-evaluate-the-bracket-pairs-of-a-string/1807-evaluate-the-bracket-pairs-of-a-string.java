class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        return mySol(s, knowledge);
    }

    public String mySol(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap();

        for (List<String> each : knowledge) {
            map.put(each.get(0), each.get(1));
        }

        StringBuilder sb = new StringBuilder();

        for (int i = -1, j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (c == '(') {
                i = j;
            } else if (c == ')') {
                String key = s.substring(i + 1, j);

                sb.append(map.getOrDefault(key, "?"));
                i = -1;
            } else if (i < 0) {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}