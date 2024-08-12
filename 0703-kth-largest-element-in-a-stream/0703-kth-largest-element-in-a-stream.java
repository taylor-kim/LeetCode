class KthLargest {
    List<Integer> list = new ArrayList();
    int k;

    public KthLargest(int k, int[] nums) {
        for (int num : nums) {
            list.add(num);
        }

        Collections.sort(list);

        this.k = k;
    }
    
    public int add(int val) {
        int index = Collections.binarySearch(list, val);

        if (index < 0) {
            index = -(index + 1);
        }

        list.add(index, val);

        return list.get(list.size() - k);
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */