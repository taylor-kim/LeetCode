class AllOne {
    Map<String, Integer> counter = new HashMap();
    TreeMap<Integer, TreeSet<String>> map = new TreeMap();

    public AllOne() {
        
    }
    
    public void inc(String key) {
        int count = counter.getOrDefault(key, 0);

        counter.put(key, count + 1);
        map.computeIfAbsent(count + 1, k -> new TreeSet()).add(key);

        remove(count, key);
    }
    
    public void dec(String key) {
        int count = counter.get(key);

        counter.put(key, count - 1);

        if (counter.get(key) == 0) counter.remove(key);

        remove(count, key);

        if (count - 1 > 0) {
            map.computeIfAbsent(count - 1, k -> new TreeSet()).add(key);
        }
    }

    private void remove(int count, String value) {
        if (count > 0) {
            map.get(count).remove(value);

            if (map.get(count).size() == 0) {
                map.remove(count);
            }
        } else {
            map.remove(count);
        }
    }
    
    public String getMaxKey() {
        if (map.isEmpty()) return "";

        // int key = map.lastKey();

        // return map.get(key).last();

        return map.lastEntry().getValue().first();
    }
    
    public String getMinKey() {
        if (map.isEmpty()) return "";

        // int key = map.firstKey();

        // return map.get(key).first();

        return map.firstEntry().getValue().first();
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */