class Solution {
    public boolean canTraverseAllPairs(int[] nums) {
        return try_20250809(nums);
    }

    public boolean try_20250809(int[] nums) {
        Set<Integer> set = new HashSet();
        for (int num : nums) set.add(num);

        if (set.size() == 1) return nums.length == 1 ? true : nums[nums.length - 1] != 1;

        List<Integer> list = new ArrayList(set);
        Collections.sort(list);

        Set<Integer> added = new HashSet();

        Map<Integer, Set<Integer>> graph = new HashMap();

        for (int i = 0; i < list.size(); i++) {
            int a = list.get(i);
            graph.computeIfAbsent(a, k -> new HashSet());
            for (int j = i + 1; j < list.size(); j++) {
                int b = list.get(j);

                if (gcd(a, b) > 1) {
                    graph.get(a).add(b);
                    graph.computeIfAbsent(b, k -> new HashSet()).add(a);
                }
            }
        }

        Set<Integer> visit = new HashSet();

        topdown(list.get(0), graph, visit, list.size());

        return visit.size() == set.size();
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;

        if (a < b) {
            a += b;
            b = a - b;
            a = a - b;
        }

        return gcd(b, a % b);
    }

    private boolean topdown(int node, Map<Integer, Set<Integer>> graph, Set<Integer> visit, int size) {
        if (visit.size() == size) return true;

        if (!visit.add(node)) return false;

        for (int next : graph.get(node)) {
            if (topdown(next, graph, visit, size)) return true;
        }

        return false;
    }
}