class Solution {

    public String largestNumber(int[] nums) {
        // Priority queue to order numbers using the custom comparison function
        PriorityQueue<String> maxHeap = new PriorityQueue<>(
            new Comparator<String>() {
                @Override
                public int compare(String first, String second) {
                    return (second + first).compareTo(first + second);
                }
            }
        );

        // Convert integers to strings and push them into the priority queue
        for (int num : nums) {
            String strNum = Integer.toString(num);
            maxHeap.offer(strNum);
        }

        // Build the result string from the priority queue
        StringBuilder result = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            result.append(maxHeap.poll());
        }

        // Handle edge case where the result might be "000...0"
        return result.charAt(0) == '0' ? "0" : result.toString();
    }
}