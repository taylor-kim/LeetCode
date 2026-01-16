class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        return try_20260116(m, n, hFences, vFences);
    }

    public int try_20260116(int m, int n, int[] hFences, int[] vFences) {
        int ans = -1;

        Set<Integer> hSet = getSet(m, hFences);
        Set<Integer> vSet = getSet(n, vFences);

        int max = 0;

        for (int length : hSet) {
            if (max < length && vSet.contains(length)) {
                max = length;
                ans = max * max;
            }
        }

        return ans;
    }

    private Set<Integer> getSet(int limit, int[] fences) {
        Arrays.sort(fences);

        Set<Integer> set = new HashSet();
        set.add(limit - 1);

        for (int i = 0; i < fences.length; i++) {
            set.add(fences[i] - 1);

            for (int j = i + 1; j < fences.length; j++) {
                set.add(fences[j] - fences[i]);
            }

            set.add(limit - fences[i]);
        }

        return set;
    }

    public int past_maybe_giveup(int m, int n, int[] hFences, int[] vFences) {
        Arrays.sort(hFences);
        Arrays.sort(vFences);
        
        Set<Integer> hs = getSet(hFences, m);
        Set<Integer> vs = getSet(vFences, n);
        
        long ans = -1;
        int mod = (int)1e9 + 7;
        
        for (int h : hs) {
            if (vs.contains(h)) {
                long area = ((long)h * h);
                ans = Math.max(ans, area);
            }
        }
        
        return (int)(ans % mod);
    }
    
    private Set<Integer> getSet(int[] fences, int length) {
        Set<Integer> set = new HashSet();
        set.add(length - 1);
        
        for (int i = 0; i < fences.length; i++) {
            int one = fences[i];
            set.add(one - 1);
            set.add(length - one);
            for (int j = i + 1; j < fences.length; j++) {
                int otherOne = fences[j];;
                set.add(otherOne - one);
            }
        }
        
        return set;
    }
    
    public int maximizeSquareArea_fail2(int m, int n, int[] hFences, int[] vFences) {
        Set<Integer> hs = new HashSet();
        Set<Integer> vs = new HashSet();
        
        for (int h : hFences) {
            int top = h - 1;
            int bottom = m - h;
            hs.add(top);
            hs.add(bottom);
        }
        
        hs.add(m - 1);
        
        for (int v : vFences) {
            int left = v - 1;
            int right = n - 1;
            vs.add(left);
            vs.add(right);
        }
        
        vs.add(n - 1);
        
        int ans = -1;
        int mod = (int)1e9 + 7;
        
        for (int h : hs) {
            if (vs.contains(h)) {
                ans = Math.max(ans, (h * h) % mod);
            }
        }
        
        return ans;
    }
    
    public int fail(int m, int n, int[] hFences, int[] vFences) {
        Arrays.sort(hFences);
        Arrays.sort(vFences);
        
        int ans = -1;
        int mod = (int)1e9 + 7;
        
        int max = Math.min(m - 1, n - 1);
        
        for (int i = 0; i < hFences.length; i++) {
            int h = hFences[i];
            
            int top = h - 1;
            int bottom = m - h;
            for (int j = 0; j < vFences.length; j++) {
                int v = vFences[j];
                
                int left = v - 1;
                int right = n - v;
                
                if (top == left) {
                    ans = Math.max(ans, multi(top, left, mod));
                }
                if (top == right) {
                    ans = Math.max(ans, multi(top, right, mod));
                }
                if (bottom == left) {
                    ans = Math.max(ans, multi(bottom, left, mod));
                }
                if (bottom == right) {
                    ans = Math.max(ans, multi(bottom, right, mod));
                }
            }
        }
        
        System.out.println(ans);
        
        return ans;
    }
    
    private int multi(int a, int b, int mod) {
        return (a * b) % mod;
    }
}