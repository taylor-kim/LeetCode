class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        return others_binarySearch(heights, bricks, ladders);
    }
    
    public int others_binarySearch(int[] h, int b, int l) {
        int n = h.length;
        int left = 0;
        int right = n - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // if (mid == n - 1) return mid;
            
            boolean canMid = checkRange(h, mid, b, l);
            boolean canNextMid = checkRange(h, mid + 1, b, l);
            
            if (canMid && !canNextMid) {
                return mid;
            } else if (canNextMid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return left;
    }
    
    private boolean checkRange(int[] h, int limit, int b, int l) {    
        if (limit >= h.length) return false;
        List<Integer> list = new ArrayList();
        
        for (int i = 0; i < limit; i++) {
            int need = h[i + 1] - h[i];
            if (need > 0) list.add(need);
        }
        
        if (list.size() <= l) {
            return true;
        }
        
        Collections.sort(list);
        
        int sizeForBricks = list.size() - l;
        
        for (int i = 0; i < sizeForBricks; i++) {
            b -= list.get(i);
            if (b < 0) return false;
        }
        
        return b >= 0;
    }
    
    public int hint_from_thunder_simplified(int[] h, int b, int l) {
        int n = h.length;
        
        Queue<Integer> pq = new PriorityQueue();
        
        for (int i = 1; i < n; i++) {
            int need = h[i] - h[i - 1];
            
            if (need <= 0) continue;
            
            pq.add(need);
            
            if (pq.size() > l) {
                b -= pq.poll();
            }
            
            if (b < 0) {
                return i - 1;
            }
        }
        
        return n - 1;
    }
    
    public int hint_from_thunder(int[] h, int b, int l) {
        int n = h.length;
        
        int ans = 0;
        
        Queue<Integer> pq = new PriorityQueue();
        
        for (int i = 1; i < n; i++) {
            int need = h[i] - h[i - 1];
            
            if (need <= 0) {
                ans = i;
                continue;
            }
            
            if (l > 0) {
                l--;
                pq.add(need);
            } else if (pq.size() > 0 && pq.peek() < need && b >= pq.peek()) {
                int last = pq.poll();
                b -= last;
                pq.add(need);
            } else if (b >= need) {
                b -= need;
            } else {
                break;
            }
            
            ans = i;
        }
        
        return ans;
    }
    
    public int try_otherway2(int[] h, int b, int l) {
        int n = h.length;
        Integer[] needs = new Integer[n];
        
        for (int i = 1; i < n; i++) {
            if (h[i - 1] < h[i]) {
                needs[i] = h[i] - h[i - 1];
            }
        }
        
        Arrays.sort(needs, (o1, o2) -> {
            return o2 - o1;
        });
        
        int used = 0;
        
        int ans = 0;
        
        for (int i = 1; i < n; i++) {
            int need = h[i] - h[i - 1];
            
            if (need <= 0) {
                ans = i;
            } else {
                if (needs[used] == need && l > 0) {
                    l--;
                    used++;
                    ans = i;
                } else if (b >= need) {
                    b -= need;
                    ans = i;
                } else {
                    break;
                }
            }
        }
        
        return ans;
    }
    
    public int try_otherway_fail(int[] h, int b, int l) {
        int n = h.length;
        int[] sup = new int[n];
        int[][] sorted = new int[n][2];
        
        for (int i = 1; i < n; i++) {
            if (h[i - 1] < h[i]) {
                sup[i] = h[i] - h[i - 1];
            }
        }
        
        System.out.println(Arrays.toString(sup));
        
        for (int i = 0; i < n; i++) {
            sorted[i][0] = i;
            sorted[i][1] = h[i];
        }
        
        Arrays.sort(sorted, (o1, o2) -> {
            return sup[o2[0]] != sup[o1[0]] ? sup[o2[0]] - sup[o1[0]] : o1[0] - o2[0];
        });
        
        List<Integer> list = new ArrayList();
        
        int ans = -1;
        
        for (int start = 0; start < n; start++) {
            ans = Math.max(ans, check(start, sorted, sup, b, l, list));
            
            list.clear();
        }
        
        return ans;
    }
    
    private int check(int start, int[][] sorted, int[] sup, int b, int l, List<Integer> list) {
        for (int i = start; i < sorted.length; i++) {
            int index = sorted[i][0];
            int need = sup[index];
            
            System.out.print(need + ", ");
            
            if (need == 0) {
                list.add(index);
            } else if (l > 0) {
                l--;
                list.add(index);
            } else if (b >= need) {
                b -= need;
                list.add(index);
            }
        }
        
        System.out.println("\n" + list + "\n");
        
        Collections.sort(list);
        
        int ans = 0;
        
        for (int i = 0; i < list.size(); i++) {
            if (i == list.get(i)) {
                ans = i;
            } else {
                break;
            }
        }
        
        return ans;
    }
    
    public int mySol_topdown_fail(int[] h, int b, int l) {
        Integer[][] memo = new Integer[b + 1][l + 1];
        return mySol_topdown(h, 0, b, l, memo);
    }
    
    public int mySol_topdown(int[] h, int index, int b, int l, Integer[][] memo) {
        if (index >= h.length) return -1;
        
        if (index == h.length - 1) return index;
        
        if (memo[b][l] != null) {
            return memo[b][l];
        }
        
        int ans = index;
        
        int current = h[index];
        int next = h[index + 1];
        
        if (current >= next) return mySol_topdown(h, index + 1, b, l, memo);
        
        if (b >= next - current) {
            ans = Math.max(ans, mySol_topdown(h, index + 1, b - (next - current), l, memo));
        }
        
        if (l > 0) {
            ans = Math.max(ans, mySol_topdown(h, index + 1, b, l - 1, memo));
        }
        
        return memo[b][l] = ans;
    }
}