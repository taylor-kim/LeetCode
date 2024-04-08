class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        return mySol(students, sandwiches);
    }

    public int mySol(int[] students, int[] sandwiches) {
        int stack = 0;
        Queue<Integer> queue = new LinkedList();

        for (int s : students) queue.add(s);

        while (!queue.isEmpty()) {
            int size = queue.size();

            Queue<Integer> next = new LinkedList();

            while (!queue.isEmpty()) {
                int s = queue.poll();

                if (s != sandwiches[stack]) {
                    next.add(s);
                } else {
                    stack++;
                }
            }

            if (size == next.size()) return size;

            queue = next;
        }

        return 0;
    }
}