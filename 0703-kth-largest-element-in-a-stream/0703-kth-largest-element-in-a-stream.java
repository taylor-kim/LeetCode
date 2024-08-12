class KthLargest {
    int base = 10000;
    int max = base * 2;
    int[] bucket = new int[max + 1];
    int k;

    public KthLargest(int k, int[] nums) {
        for (int num : nums) {
            bucket[base + num]++;
        }

        this.k = k;
    }
    
    public int add(int val) {
        bucket[base + val]++;

        int pos = k;
        int index = max;

        while (index >= 0) {
            while (bucket[index] == 0) {
                index--;
            }

            pos -= bucket[index];

            if (pos <= 0) return index - base;

            index--;
        }

        return 0;
    }
}
