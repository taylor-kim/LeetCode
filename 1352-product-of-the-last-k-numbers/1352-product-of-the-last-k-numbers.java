class ProductOfNumbers {
    int prod = 1;
    Deque<int[]> list = new LinkedList();

    public ProductOfNumbers() {
        
    }
    
    public void add(int num) {
        // while (num * prod < 0) {
        //     int[] left = list.peekFirst();
        //     prod /= left[0];

        //     if (--left[1] == 0) {
        //         list.removeFirst();
        //     }
        // }
        prod *= num;

        if (list.isEmpty() || list.peekLast()[0] != num) {
            list.add(new int[] {num, 1});
        } else {
            list.peekLast()[1]++;
        }
    }
    
    public int getProduct(int k) {
        int ret = 1;

        Iterator<int[]> descIt = list.descendingIterator();

        while (k > 0 && ret > 0 && descIt.hasNext()) {
            int[] data = descIt.next();
            int count = Math.min(k, data[1]);
            k -= count;
            ret *= Math.pow(data[0], count);
        }

        return ret;
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */