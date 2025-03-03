class Solution {
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        return official_dfs(edges, bob, amount);
    }

    public int official_dfs(int[][] edges, int bob, int[] amount) {
        Map<Integer, List<Integer>> graph = new HashMap();
        int n = edges.length + 1;

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList()).add(edge[0]);
        }

        return findMax(graph, 0, 0, 0, bob, new int[n], amount);
    }

    private int findMax(Map<Integer, List<Integer>> graph, int source, int parent, int time
                        , int bob, int[] distanceFromBob, int[] amount) {
        if (source == bob) {
            distanceFromBob[source] = 0;
        } else {
            distanceFromBob[source] = distanceFromBob.length;
        }

        int score = 0;
        int sub = Integer.MIN_VALUE;

        for (int next : graph.getOrDefault(source, new ArrayList<>())) {
            if (next != parent) {
                sub = Math.max(sub, findMax(graph, next, source, time + 1, bob, distanceFromBob, amount));

                distanceFromBob[source] = Math.min(distanceFromBob[source], distanceFromBob[next] + 1);
            }
        }

        if (distanceFromBob[source] > time) {
            score += amount[source];
        } else if (distanceFromBob[source] == time) {
            score += amount[source] / 2;
        }

        return score + (sub == Integer.MIN_VALUE ? 0 : sub);
    }

    public int mySol2(int[][] edges, int bob, int[] amount) {
        Map<Integer, List<Integer>> graph = new HashMap();
        int n = edges.length + 1;

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList()).add(edge[0]);
        }

        List<List<Integer>> bobsPaths = new ArrayList();

        findBobs(bob, graph, new boolean[n], bobsPaths, new ArrayList());

        if (bobsPaths.size() != 1) {
            throw new RuntimeException(String.format("bobsPaths.size() is not 1, actual:%d", bobsPaths.size()));
        }

        int ans = Integer.MIN_VALUE;

        // System.out.println(bobsPaths);

        for (List<Integer> bobsPath : bobsPaths) {
            ans = Math.max(ans, dfs(graph, amount, 0, bobsPath, 0, new boolean[n], new boolean[n]));
        }

        return ans;
    }

    private void findBobs(int bob, Map<Integer, List<Integer>> graph, boolean[] visit, List<List<Integer>> paths, List<Integer> path) {
        visit[bob] = true;
        path.add(bob);

        if (bob == 0) {
            paths.add(new ArrayList(path));
        } else if (graph.containsKey(bob)) {
            for (int next : graph.get(bob)) {
                if (visit[next]) continue;
                findBobs(next, graph, visit, paths, path);
            }
        }

        path.remove(path.size() - 1);
        visit[bob] = false;
    }

    private int dfs(Map<Integer, List<Integer>> graph, int[] amount, int alice, List<Integer> bobsPath, int bobIndex, boolean[] aVisit, boolean[] opened) {
        int bob = bobIndex < bobsPath.size() ? bobsPath.get(bobIndex) : -1;
        int factor = 1;

        if (alice == bob) factor = 2;

        int ap = opened[alice] ? 0 : amount[alice] / factor;

        opened[alice] = true;
        aVisit[alice] = true;

        if (bob >= 0) {
            opened[bob] = true;
        }

        int ans = Integer.MIN_VALUE;

        for (int next : graph.getOrDefault(alice, new ArrayList<>())) {
            if (aVisit[next]) continue;

            ans = Math.max(ans, dfs(graph, amount, next, bobsPath, bobIndex + 1, aVisit, opened));
        }

        opened[alice] = false;

        if (bob >= 0) {
            opened[bob] = false;
        }

        aVisit[alice] = false;

        return ap + (ans == Integer.MIN_VALUE ? 0 : ans);
    }

    public int mySol_fail(int[][] edges, int bob, int[] amount) {
        Map<Integer, List<Integer>> graph = new HashMap();
        int n = edges.length + 1;

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList()).add(edge[0]);
        }

        // System.out.println(graph);

        return dfs(graph, amount, 0, bob, new boolean[n], new boolean[n], new boolean[n]);
    }

    private int dfs(Map<Integer, List<Integer>> graph, int[] amount, int alice, int bob, boolean[] opened, boolean[] av, boolean[] bv) {
        av[alice] = true;
        bv[bob] = true;

        int factor = 1;

        if (alice == bob) factor = 2;

        int aIncome = opened[alice] ? 0 : amount[alice] / factor;
        int bIncome = opened[bob] ? 0 : amount[bob] / factor;

        opened[alice] = true;
        opened[bob] = true;

        int ans = Integer.MIN_VALUE;

        for (int aNext : graph.getOrDefault(alice, new ArrayList<>())) {
            if (av[aNext]) continue;

            int calls = 0;

            for (int bNext : graph.getOrDefault(bob, new ArrayList<>())) {
                if (bv[bNext]) continue;

                ans = Math.max(ans, dfs(graph, amount, aNext, bNext, opened, av, bv));

                calls++;
            }

            if (calls == 0) {
                ans = Math.max(ans, dfs(graph, amount, aNext, bob, opened, av, bv));
            }
        }

        opened[alice] = false;
        opened[bob] = false;

        av[alice] = false;
        bv[bob] = false;

        System.out.println(String.format("alice:%d, income:%d", alice, aIncome));

        return aIncome + (ans == Integer.MIN_VALUE ? 0 : ans);
    }
}