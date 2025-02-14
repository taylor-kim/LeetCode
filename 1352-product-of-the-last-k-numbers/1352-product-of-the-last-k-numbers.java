class ProductOfNumbers {
    private LinkedList<Integer> list = new LinkedList();

    public ProductOfNumbers() {
        list.add(1);
    }
    
    public void add(int num) {
        if (num == 0) {
            list.clear();
            list.add(1);
        } else {
            list.add(list.get(list.size() - 1) * num);
        }
    }
    
    public int getProduct(int k) {
        if (k > list.size() - 1) {
            return 0;
        }

        int start = list.size() - k;

        // return list.get(list.size() - 1) / list.get(Math.max(start - 1, 0));
        return list.get(list.size() - 1) / list.get(start - 1);
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */