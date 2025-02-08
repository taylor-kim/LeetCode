class Solution {
    public int maxDistance(String s, int k) {
        return others_genious(s, k);
    }

    public int others_genious(String str, int k) {
        int[] map = new int[26];

        int ans = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            map[c - 'A']++;

            int n = map['N' - 'A'];
            int s = map['S' - 'A'];

            int w = map['W' - 'A'];
            int e = map['E' - 'A'];

            int diff = Math.abs(n - s) + Math.abs(w - e);

            ans = Math.max(ans, diff + Math.min(i + 1 - diff, 2 * k));
        }

        return ans;
    }

    Map<Character, Integer> deltaMap = Map.of('N', 1, 'S', -1, 'W', -1, 'E', 1);
    Set<Character> vertical = Set.of('N', 'S');
    Set<Character> dirs = Set.of('N', 'S', 'W', 'E');

    public int try_20250208(String str, int k) {
        int[] map = new int[26];

        int ans = 0;

        for (char c : str.toCharArray()) {
            map[c - 'A']++;

            int changeMax = k;

            int n = map['N' - 'A'];
            int s = map['S' - 'A'];

            int distance = 0;

            int[] data = getInfo(n, s, changeMax);

            distance = data[0];
            changeMax = data[1];

            int w = map['W' - 'A'];
            int e = map['E' - 'A'];

            int[] data2 = getInfo(w, e, changeMax);

            distance += data2[0];

            ans = Math.max(ans, distance);
        }

        return ans;
    }

    public int bf(String s, int k) {
        return topdown(s, 0, k, 0, 0, new Integer[s.length()][k + 1]);
    }

    private int topdown(String s, int index, int k, int v, int h, Integer[][] memo) {
        if (index >= s.length()) return Math.abs(v) + Math.abs(h);

        if (memo[index][k] != null) {
            return memo[index][k];
        }

        char c = s.charAt(index);

        // System.out.println(String.format("index:%d, c:%c, k:%d, v:%d, h:%d", index, c, k, v, h));

        int ans = Math.abs(v) + Math.abs(h);

        boolean isV = vertical.contains(c);
        int delta = deltaMap.get(c);
        ans = Math.max(ans, topdown(s, index + 1, k, v + (isV ? delta : 0), h + (isV ? 0 : delta), memo));

        if (k > 0) {
            for (char d : dirs) {
                if (c == d) continue;

                isV = vertical.contains(d);
                delta = deltaMap.get(d);
                ans = Math.max(ans, topdown(s, index + 1, k - 1, v + (isV ? delta : 0), h + (isV ? 0 : delta), memo));
            }
        }

        return memo[index][k] = ans;
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
            int vMax = 0;

            if (map.get('N') > 1 && map.get('S') > 1) {
                int[] info = getInfo(map.get('N'), map.get('S'), canChange);
                vMax = info[0];
                canChange -= info[1];
            } else {
                vMax = Math.abs(map.get('N') - map.get('S'));
            }

            int hMax = 0;

            if (map.get('W') > 1 && map.get('E') > 1) {
                int[] info = getInfo(map.get('W'), map.get('E'), canChange);
                hMax = info[0];
                canChange -= info[1];
            } else {
                hMax = Math.abs(map.get('W') - map.get('E'));
            }

            ans = Math.max(ans, vMax + hMax);
        }

        return ans;
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

    private int[] getInfo(int a, int b, int k) {
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        int change = Math.min(min, k);

        int move = max + change - (min - change);
        k -= change;

        return new int[] {move, k};
    }
}