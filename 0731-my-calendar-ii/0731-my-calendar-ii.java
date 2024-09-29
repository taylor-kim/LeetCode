class MyCalendarTwo {
    List<int[]> bookings = new ArrayList();
    List<int[]> overlapBookings = new ArrayList();

    public MyCalendarTwo() {
        
    }
    
    public boolean book(int start, int end) {
        for (int[] d : overlapBookings) {
            if (isOverlap(start, end, d[0], d[1])) {
                return false;
            }
        }

        for (int[] d : bookings) {
            if (isOverlap(start, end, d[0], d[1])) {
                overlapBookings.add(getOverlap(start, end, d[0], d[1]));
            }
        }

        bookings.add(new int[] {start, end});

        return true;
    }

    private boolean isOverlap(int s1, int e1, int s2, int e2) {
        return Math.max(s1, s2) < Math.min(e1, e2);
    }

    private int[] getOverlap(int s1, int e1, int s2, int e2) {
        return new int[] {
            Math.max(s1, s2), Math.min(e1, e2)
        };
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */