class MyCalendarTwo {
    TreeMap<Integer, Integer> map = new TreeMap();
    int allow = 2;

    public MyCalendarTwo() {
        
    }
    
    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);

        int over = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            over += entry.getValue();

            if (over > allow) {
                map.put(start, map.getOrDefault(start, 0) - 1);
                map.put(end, map.getOrDefault(end, 0) + 1);

                if (map.get(start) == 0) {
                    map.remove(start);
                }

                return false;
            }
        }

        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */