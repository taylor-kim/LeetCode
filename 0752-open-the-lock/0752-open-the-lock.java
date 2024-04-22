class Solution {
    public int openLock(String[] deadends, String target) {
        return official_bfs(deadends, target);
    }

    public int official_bfs(String[] deadends, String target) {
        Set<String> visit = new HashSet(Arrays.asList(deadends));
        Queue<String> queue = new LinkedList();

        if (visit.contains("0000")) return -1;

        queue.add("0000");
        visit.add("0000");

        int ans = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                String s = queue.poll();

                if (s.equals(target)) return ans;

                for (int pos = 0; pos < 4; pos++) {
                    StringBuilder sb = new StringBuilder(s);
                    char c = sb.charAt(pos);
                    
                    sb.setCharAt(pos, getNext(c));

                    if (!visit.contains(sb.toString())) {
                        queue.add(sb.toString());
                        visit.add(sb.toString());
                    }

                    sb.setCharAt(pos, getPrev(c));

                    if (!visit.contains(sb.toString())) {
                        queue.add(sb.toString());
                        visit.add(sb.toString());
                    }
                }
            }

            ans++;
        }

        return -1;
    }

    private char getNext(char c) {
        if (c == '9') return '0';

        return (char)(c + 1);
    }

    private char getPrev(char c) {
        if (c == '0') return '9';

        return (char)(c - 1);
    }

    public int mySol_rec_fail(String[] deadends, String target) {
        Set<String> set = new HashSet(Arrays.asList(deadends));

        char[] initial = {'0', '0', '0', '0'};

        return mySol_rec(set, target.toCharArray(), initial, 0);
    }

    public int mySol_rec(Set<String> deadends, char[] target, char[] current, int index) {
        if (isEquals(target, current)) return 0;

        if (index >= target.length) return -1;

        int seq = 0;

        char origin = current[index];
        char c = current[index];

        int ans = Integer.MAX_VALUE;

        while (seq++ < 10) {
            if (c == target[index] && !deadends.contains(String.valueOf(current))) {
                int sub = mySol_rec(deadends, target, current, index + 1);

                if (sub != -1) {
                    ans = Math.min(ans, sub);
                }
            }

            c = getNext(c, target[index]);

            current[index] = c;
            
            int sub = mySol_rec(deadends, target, current, index);
        }

        current[index] = origin;

        return ans == Integer.MAX_VALUE ? -1 : ans + 1;
    }

    private boolean isEquals(char[] a, char[] b) {
        if (a.length != b.length) return false;

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }

        return true;
    }

    private char getNext(char c, char target) {
        int diff = target - c;
        if (diff >= 0 && diff < 5) {
            return (char)(c + 1);
        } else {
            return (char)(c - 1);
        }
    }
}