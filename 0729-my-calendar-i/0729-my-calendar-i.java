class MyCalendar {
    private List<int[]> schedules = new ArrayList();

    private Comparator<int[]> comparator = (a, b) -> {
        return a[0] - b[0];
    };

    public MyCalendar() {
        
    }
    
    public boolean book(int start, int end) {
        int[] d = {start, end};
        int index = Collections.binarySearch(schedules, d, comparator);

        // System.out.println(String.format("size:%d, index:%d", schedules.size(), index));

        // StringBuilder sb = new StringBuilder();
        // sb.append("list: ");

        // for (int[] each : schedules) {
        //     sb.append(Arrays.toString(each)).append(", ");
        // }

        // sb.append("d: ").append(Arrays.toString(d));

        // System.out.println(sb.toString());

        if (index >= 0) return false;

        index = -(index + 1);

        if (index < schedules.size()) {
            int[] next = schedules.get(index);

            if (d[1] > next[0]) return false;
        }

        if (index - 1 >= 0) {
            int[] prev = schedules.get(index - 1);

            if (prev[1] > d[0]) {
                return false;
            }
        }

        if (schedules.size() == index) {
            schedules.add(d);
            // System.out.println("add last");
            return true;
        } else {
            schedules.add(index, d);
        }

        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */