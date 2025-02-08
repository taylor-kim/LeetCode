class Solution {
    public int maxDistance(String s, int k) {
        return bf(s, k);
    }

    Map<Character, Integer> deltaMap = Map.of('N', 1, 'S', -1, 'W', -1, 'E', 1);
    Set<Character> vertical = Set.of('N', 'S');
    Set<Character> dirs = Set.of('N', 'S', 'W', 'E');

    public int bf(String s, int k) {
        return topdown(s, 0, k, 0, 0);
    }

    private int topdown(String s, int index, int k, int v, int h) {
        if (index >= s.length()) return Math.abs(v) + Math.abs(h);

        char c = s.charAt(index);

        // System.out.println(String.format("index:%d, c:%c, k:%d, v:%d, h:%d", index, c, k, v, h));

        int ans = Math.abs(v) + Math.abs(h);

        boolean isV = vertical.contains(c);
        int delta = deltaMap.get(c);
        ans = Math.max(ans, topdown(s, index + 1, k, v + (isV ? delta : 0), h + (isV ? 0 : delta)));

        if (k > 0) {
            for (char d : dirs) {
                if (c == d) continue;

                isV = vertical.contains(d);
                delta = deltaMap.get(d);
                ans = Math.max(ans, topdown(s, index + 1, k - 1, v + (isV ? delta : 0), h + (isV ? 0 : delta)));
            }
        }

        return ans;
    }

    public int mySol3(String s, int k) {
        int y = 0;
        int x = 0;
        Map<Character, Integer> delta = Map.of('N', 1, 'S', -1, 'W', -1, 'E', 1);

        Set<Character> vertical = Set.of('N', 'S');

        int left = 0;
        int ans = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            if (vertical.contains(c)) {
                y += delta.get(c);
            } else {
                x += delta.get(c);
            }

            ans = Math.max(ans, Math.abs(y) + Math.abs(x));
        }

        return ans;
    }

    public int mySol2(String s, int k) {
        Map<Character, Integer> map = new HashMap();
        map.put('N', 0);
        map.put('S', 0);
        map.put('E', 0);
        map.put('W', 0);

        int left = 0;
        int ans = 0;
        int v = 0;
        int h = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map.put(c, map.get(c) + 1);

            int canChange = k;

            if (map.get('N') > 1 && map.get('S') > 1) {
                int vMax = Math.max(map.get('N'), map.get('S'));
                int vMin = Math.min(map.get('N'), map.get('S'));
            }

            
        }

        return 0;
    }

    public int mySol_fail(String s, int k) {
        Map<Character, Integer> map = new HashMap();
        map.put('N', 0);
        map.put('S', 0);
        map.put('E', 0);
        map.put('W', 0);

        for (char c : s.toCharArray()) {
            map.put(c, map.get(c) + 1);
        }

        // nnnnnssss

        System.out.println(map);

        int vMax = Math.max(map.get('N'), map.get('S'));
        int vMin = Math.min(map.get('N'), map.get('S'));
        int vChange = Math.min(vMin, k);
        k -= vChange;

        int v = vMax + vChange - (vMin - vChange);
        System.out.println(v);

        int hMax = Math.max(map.get('W'), map.get('E'));
        int hMin = Math.min(map.get('W'), map.get('E'));
        int hChange = Math.min(hMin, k);

        int h = hMax + hChange - (hMin - hChange);
        System.out.println(h);

        return Math.abs(v) + Math.abs(h);
    }
}