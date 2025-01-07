class Solution {
    public long calculateScore(String s) {
        return mySol(s);
    }

    public long mySol(String s) {
        List<Integer>[] mirrors = new List[26];

        for (int i = 0; i < mirrors.length; i++) {
            mirrors[i] = new ArrayList();
        }

        int n = s.length();

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            mirrors[25 - c].add(i);
        }

        boolean[] used = new boolean[n];

        long ans = 0;

        for (int i = 1; i < n; i++) {
            if (used[i]) continue;
            
            List<Integer> indices = mirrors[s.charAt(i) - 'a'];

            if (indices.size() == 0) continue;

            int index = rightMost(indices, i - 1);

            if (index >= 0) {
                int j = indices.remove(index);

                if (!used[j]) {
                    // System.out.println(String.format("i:%c, j:%c, score:%d", s.charAt(i), s.charAt(j), i - j));
                    used[i] = true;
                    used[j] = true;
    
                    ans += i - j;
                }
            }
        }

        return ans;
    }

    private int rightMost(List<Integer> list, int target) {        
        int lo = 0;
        int hi = list.size();

        int index = -1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (list.get(mid) <= target) {
                index = mid;
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        // System.out.println(String.format("search - list:%s, target:%d, ret:%d", list, target, lo - 1));

        // if (lo == list.size()) return -1;

        return lo - 1;
    }
}