class Solution {
    public String getHappyString(int n, int k) {
        return try_20260314(n, k);
    }

    public String try_20260314(int n, int k) {
        Map<Character, List<Character>> map = Map.of(
            ' ', List.of('a', 'b', 'c'),
            'a', List.of('b', 'c'),
            'b', List.of('a', 'c'),
            'c', List.of('a', 'b')
        );

        return topdown(n, k, new int[1], new StringBuilder(), map);
    }

    private String topdown(int n, int k, int[] counter, StringBuilder sb, Map<Character, List<Character>> map) {
        if (sb.length() == n) {
            return ++counter[0] == k ? sb.toString() : "";
        }

        char prev = sb.length() == 0 ? ' ' : sb.charAt(sb.length() - 1);

        for (char next : map.get(prev)) {
            sb.append(next);

            String s = topdown(n, k, counter, sb, map);

            if (s.length() > 0) return s;

            sb.setLength(sb.length() - 1);
        }

        return "";
    }

    public String mySol(int n, int k) {
        Map<Character, List<Character>> map = new HashMap();
        map.put('s', Arrays.asList('a', 'b', 'c'));
        map.put('a', Arrays.asList('b', 'c'));
        map.put('b', Arrays.asList('a', 'c'));
        map.put('c', Arrays.asList('a', 'b'));

        StringBuilder sb = new StringBuilder();
        sb.append('s');
        List<String> list = new ArrayList();

        topdown(n, map, sb, list);

        return list.size() < k ? "" : list.get(k - 1).substring(1);
    }

    private void topdown(int n, Map<Character, List<Character>> map, StringBuilder sb, List<String> list) {
        if (sb.length() >= n + 1) {
            list.add(sb.toString());
            return;
        }

        char c = sb.charAt(sb.length() - 1);

        for (char next : map.get(c)) {
            sb.append(next);
            topdown(n, map, sb, list);
            sb.setLength(sb.length() - 1);
        }
    }
}