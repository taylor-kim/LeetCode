class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        return myNlogNSol(arr, k);
    }
    
    public int myNlogNSol(int[] arr, int k) {
        int n = arr.length;
        
        if (n == k) return 0;
        
        Map<Integer, Integer> counter = new HashMap();
        Integer[] arr2 = new Integer[n];
        
        for (int i = 0; i < n; i++) {
            int num = arr[i];
            counter.put(num, counter.getOrDefault(num, 0) + 1);
            arr2[i] = num;
        }
        
        Arrays.sort(arr2, (a, b) -> {
            return counter.get(a) != counter.get(b) ? counter.get(a) - counter.get(b) : a - b;
        });
        
        Set<Integer> ans = new HashSet();
        
        for (int i = 0; i < n; i++) {
            if (k-- > 0) {
                continue;
            } else {
                ans.add(arr2[i]);
            }
        }
        
        return ans.size();
    }
}