class Solution {
    public int maxUniqueSplit(String s) {
        return official_topdown_pruning(s);
    }

    public int official_topdown_pruning(String s) {
        Set<String> set = new HashSet();

        return official_topdown_pruning(s, 0, 0, set);
    }

    public int official_topdown_pruning(String s, int index, int count, Set<String> set) {
        if (count + (s.length() - index) <= set.size()) return set.size();

        if (index >= s.length()) return set.size();

        int ans = 0;

        for (int i = index; i < s.length(); i++) {
            String sub = s.substring(index, i + 1);

            if (set.add(sub)) {
                ans = Math.max(ans, official_topdown_pruning(s, i + 1, count + 1, set));

                set.remove(sub);
            }
        }

        return ans;
    }

    public int try_iter_fail(String s) {
        Set<String> set = new HashSet();
        Stack<String> stack = new Stack();

        int ans = 0;

        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            String sub = s.substring(left, right + 1);

            System.out.println(sub);

            if (set.add(sub)) {
                ans++;
                left = right + 1;

                stack.add(sub);
            } else if (right + 1 == s.length()) {
                System.out.println(String.format("need to back. sub:%s", sub));

                // while (set.contains(stack.peek() + sub)) {
                //     String pop = stack.pop();
                //     sub = pop + sub;
                //     set.remove(pop);
                // }

                String pop = stack.pop();

                set.remove(pop);
                set.add(pop + sub);

                stack.add(pop + sub);

                System.out.println(String.format("pop:%s, pop + sub:%s", pop, pop + sub));

                left = right + 1;
            }
        }

        System.out.println(set);
        System.out.println(stack);

        return set.size();
    }

    public int topdown(String s) {
        Set<String> set = new HashSet();

        return topdown(s, 0, set);
    }

    public int topdown(String s, int index, Set<String> set) {
        if (index >= s.length()) return set.size();

        int ans = 0;

        for (int i = index; i < s.length(); i++) {
            String sub = s.substring(index, i + 1);

            if (set.add(sub)) {
                ans = Math.max(ans, topdown(s, i + 1, set));

                set.remove(sub);
            }
        }

        return ans;
    }
}