class ProductOfNumbers {
    List<Integer> list = new ArrayList();
    int index = 0;

    public ProductOfNumbers() {
        list.add(1);
    }
    
    public void add(int num) {
        if (num == 0) {
            list.clear();
            list.add(1);
            index = 0;
        } else {
            list.add(list.get(index++) * num);
        }
    }
    
    public int getProduct(int k) {
        if (k > index) return 0;

        return list.get(index) / list.get(index - k);
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */