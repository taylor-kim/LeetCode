class Solution {
    public String getHappyString(int n, int k) {
        return official_combinatoric(n, k);
    }

    public String official_combinatoric(int n, int k) {
        int total = 3 * (int)Math.pow(2, n - 1);

        if (k > total) return "";

        Map<Character, Character> nextSmallest = Map.of('a', 'b', 'b', 'a', 'c', 'a');
        Map<Character, Character> nextGreatest = Map.of('a', 'c', 'b', 'c', 'c', 'b');

        StringBuilder sb = new StringBuilder();

        int sizeOfGroup = (int)Math.pow(2, n - 1);

        int aGroup = sizeOfGroup;
        int bGroup = 2 * sizeOfGroup;
        int cGroup = 3 * sizeOfGroup;

        if (k <= sizeOfGroup) {
            sb.append('a');
        } else if (k <= 2 * sizeOfGroup) {
            k -= sizeOfGroup;
            sb.append('b');
        } else {
            k -= 2 * sizeOfGroup;
            sb.append('c');
        }

        topdown(sb, k, n, nextSmallest, nextGreatest);

        return sb.toString();
    }

    private void topdown(StringBuilder sb, int k, int n,
        Map<Character, Character> nextSmallest, Map<Character, Character> nextGreatest) {

        // n == 2, k == 5
        // ab, ac, ba, bc, ca, cb
        // groupSize = 2
        // 1~2, 3~4, 5~6 => k belongs to 3rd group
        // sb.append('c'), k -= (2 * groupSize), k = 1

        if (sb.length() == n) return;

        int total = (int)Math.pow(2, n - sb.length());
        
        int sizeOfGroup = (int)Math.pow(2, n - (sb.length() + 1));

        char prev = sb.charAt(sb.length() - 1);

        if (k <= sizeOfGroup) {
            sb.append(nextSmallest.get(prev));
        } else {
            k -= sizeOfGroup;
            sb.append(nextGreatest.get(prev));
        }

        topdown(sb, k, n, nextSmallest, nextGreatest);
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