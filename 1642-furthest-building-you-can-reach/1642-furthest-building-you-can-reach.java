class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        return others_binarySearch(heights, bricks, ladders);
    }
    
    public int others_binarySearch(int[] h, int b, int l) {
        int left = 0;
        int right = h.length;
        // left <= mid < right
        
        while (true) {
            if (left + 1 == right) {
                break;
            }
            int mid = left + (right - left) / 2;
            boolean canMid = checkRange(h, mid, b, l);
            if (canMid) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    private boolean checkRange(int[] h, int limit, int b, int l) {        
        List<Integer> list = new ArrayList();
        for (int i = 1; i <= limit; i++) {
            int need = h[i] - h[i-1];
            if (need > 0) list.add(need);
        }
        
        if (list.size() <= l) {
            return true;
        }
        
        Collections.sort(list);
        
        int sizeForBricks = list.size() - l;
        // l-1 / l ~ list.size() list.size() - l
        
        for (int i = 0; i < sizeForBricks; i++) {
            b -= list.get(i);
            if (b < 0) return false;
        }
        
        return true;
    }
}