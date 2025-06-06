class Solution {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        return mySol2(s1, s2, baseStr);
    }

    public String mySol2(String s1, String s2, String baseStr) {
        Map<Integer, Set<Integer>> graph = new HashMap();

        for (int i = 0; i < s1.length(); i++) {
            int c1 = s1.charAt(i) - 'a';
            int c2 = s2.charAt(i) - 'a';

            // if (c1 < c2) {
            //     c1 += c2;
            //     c2 = c1 - c2;
            //     c1 = c1 - c2;
            // }

            graph.computeIfAbsent(c1, k -> new HashSet()).add(c2);
            graph.computeIfAbsent(c2, k -> new HashSet()).add(c1);
        }

        print(graph);

        int[] shortest = new int[26];

        // Arrays.fill(shortest, Integer.MAX_VALUE);

        for (int i = 0; i < 26; i++) {
            shortest[i] = i;
        }

        StringBuilder sb = new StringBuilder();

        boolean[] visit = new boolean[26];

        int[] cache = new int[26];

        Arrays.fill(cache, -1);

        for (char c : baseStr.toCharArray()) {
            int node = c - 'a';

            if (cache[node] == -1) {
                dfs(node, node, graph, shortest, visit);

                cache[node] = shortest[node];
            }

            sb.append((char)(cache[node] + 'a'));
        }

        // print(shortest);

        return sb.toString();
    }

    private void dfs(int start, int node, Map<Integer, Set<Integer>> graph, int[] shortest, boolean[] visit) {
        if (visit[node]) return;

        visit[node] = true;

        if (shortest[start] > node) {
            shortest[start] = node;
        } else if (shortest[node] > start) {
            shortest[node] = start;
        }

        for (int next : graph.getOrDefault(node, new HashSet<>())) {
            dfs(start, next, graph, shortest, visit);
        }

        visit[node] = false;
    }

    private void print(int[] shortest) {
        Map<Character, Character> map = new HashMap();

        for (int i = 0; i < shortest.length; i++) {
            map.put((char)(i + 'a'), (char)(shortest[i] + 'a'));
        }

        System.out.println(map);
    }

    private void print(Map<Integer, Set<Integer>> graph) {
        Map<Character, Set<Character>> g1 = new HashMap();

        for (int key : graph.keySet()) {
            char cKey = (char)(key + 'a');
            
            Set<Character> set = new HashSet();

            for (int value : graph.get(key)) {
                char cValue = (char)(value + 'a');

                set.add(cValue);
            }

            g1.put(cKey, set);
        }

        System.out.println(g1);
    }

    public String mySol_fail(String s1, String s2, String baseStr) {
        MyList[] root = new MyList[26];

        for (int i = 0; i < 26; i++) {
            // root[i] = new MyList();
        }

        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            if (root[c1 - 'a'] == null) {
                root[c1 - 'a'] = new MyList();
            }

            if (root[c2 - 'a'] == null) {
                root[c2 - 'a'] = new MyList();
            }

            fill(root[c1 - 'a'], c2);
            fill(root[c2 - 'a'], c1);
        }

        StringBuilder sb = new StringBuilder();

        Character[] memo = new Character[26];

        for (int i = 0; i < baseStr.length(); i++) {
            sb.append(findSmall(root, baseStr.charAt(i), memo));
        }

        return sb.toString();
    }

    private char findSmall(MyList[] root, char c, Character[] memo) {
        System.out.println(c);

        if (root[c - 'a'] == null) return 'z';

        // if (c == 'a') return c;

        if (memo[c - 'a'] != null) {
            return memo[c - 'a'];
        }

        char small = c;

        for (int i = 0; i < c - 'a'; i++) {
            char sub = findSmall(root, (char)(i + 'a'), memo);

            if (c <= sub) continue;

            small = min(small, sub);
        }

        return memo[c - 'a'] = min(c, small);
    }

    private char min(char c1, char c2) {
        return c1 < c2 ? c1 : c2;
    }

    private void fill(MyList root, char c) {
        if (root.list[c - 'a'] == null) {
            root.list[c - 'a'] = new MyList();
        }
    } 

    public class MyList {
        private MyList[] list = new MyList[26];
    }
}