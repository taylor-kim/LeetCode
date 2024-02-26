class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        return official_bucket(arr, k);
    }
    
    public int improve_myNSol(int[] arr, int k) {
        int n = arr.length;
        
        if (n == k) return 0;
        
        int minCount = n;
        int maxCount = 0;
        Map<Integer, Integer> counter = new HashMap();
        
        for (int num : arr) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        
        for (int num : counter.keySet()) {
            int count = counter.get(num);
            
            minCount = Math.min(minCount, count);
            maxCount = Math.max(maxCount, count);
        }
        
        int[] bucket = new int[maxCount - minCount + 1];
        
        for (int num : counter.keySet()) {
            int count = counter.get(num);
            int index = count - minCount;
            
            bucket[index]++;
        }
        
        int limit = maxCount - minCount;
        int totalRemove = 0;
        
        for (int i = 0; i <= limit; i++) {
            if (bucket[i] == 0) continue;
            
            int needKForOneEl = i + minCount;
            
            int countCanRemove = k / needKForOneEl;
            int actualRemove = Math.min(bucket[i], countCanRemove);

            k -= (actualRemove * needKForOneEl);

            totalRemove += actualRemove;
            
            if (needKForOneEl > k) {
                return counter.size() - totalRemove;
            }
        }
        
        return 0;
    }
    
    public int official_bucket(int[] arr, int k) {
        Map<Integer, Integer> counter = new HashMap();
        
        for (int num : arr) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        
        int[] bucket = new int[arr.length + 1];
        
        for (int count : counter.values()) {
            bucket[count]++;
        }
        
        int removed = 0;
        
        for (int i = 1; i <= arr.length; i++) {
            if (bucket[i] == 0) continue;
            
            int canRemove = k / i;
            int actualRemove = Math.min(bucket[i], canRemove);
            
            k -= i * actualRemove;
            
            removed += actualRemove;
            
            if (k <= i) {
                return counter.size() - removed;
            }
        }
        
        return 0;
    }
    
    public int official_pq(int[] arr, int k) {
        Map<Integer, Integer> counter = new HashMap();
        
        for (int num : arr) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        
        Queue<Integer> pq = new PriorityQueue(counter.values());
        
        int removed = 0;
        
        while (pq.size() > 0) {
            int count = pq.peek();
            
            removed += count;
            
            if (removed > k) {
                return pq.size();
            }
            
            pq.poll();
        }
        
        return 0;
    }
    
    public int official_sort(int[] arr, int k) {
        Map<Integer, Integer> counter = new HashMap();
        
        for (int num : arr) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        
        List<Integer> freq = new ArrayList(counter.values());
        
        Collections.sort(freq);
        
        int removed = 0;
        
        for (int i = 0; i < freq.size(); i++) {
            int count = freq.get(i);
            
            removed += count;
            
            if (removed > k) {
                return freq.size() - i;
            }
        }
        
        return 0;
    }
    
    public int myNSol(int[] arr, int k) {
        int n = arr.length;
        
        if (n == k) return 0;
        
        int minCount = n;
        int maxCount = 0;
        Map<Integer, Integer> counter = new HashMap();
        
        for (int num : arr) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        
        for (int num : counter.keySet()) {
            int count = counter.get(num);
            
            minCount = Math.min(minCount, count);
            maxCount = Math.max(maxCount, count);
        }
        
        int[] bucket = new int[maxCount - minCount + 1];
        
        for (int num : counter.keySet()) {
            int count = counter.get(num);
            int index = count - minCount;
            
            bucket[index]++;
        }
        
        int i = 0;
        int limit = maxCount - minCount;
        
        while (i <= limit) {
            while (i <= limit && bucket[i] == 0) i++;
            
            if (i > limit) break;
            
            int needKForOneEl = i + minCount;
            
            if (needKForOneEl > k) {
                break;
            } else {
                int countCanRemove = k / needKForOneEl;
                int actualRemove = Math.min(bucket[i], countCanRemove);
                
                k -= (actualRemove * needKForOneEl);
                
                bucket[i] -= actualRemove;
            }
            
            if (k == 0) break;
        }
        
        int ans = 0;
        
        while (i <= limit) {
            ans += bucket[i++];
        }
        
        return ans;
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