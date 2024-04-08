class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        return official_counting(students, sandwiches);
    }

    public int official_counting(int[] students, int[] sandwiches) {
        int countZero = 0;
        int countOne = 0;

        for (int s : students) {
            if (s == 0) countZero++;
            else countOne++;
        }

        for (int s : sandwiches) {
            if (s == 0 && countZero == 0) {
                return countOne;
            }

            if (s == 1 && countOne == 0) {
                return countZero;
            }

            if (s == 0) countZero--;
            else countOne--;
        }

        return countOne + countZero;
    }

    public int official_queue(int[] students, int[] sandwiches) {
        int stack = 0;
        Queue<Integer> queue = new LinkedList();

        for (int s : students) queue.add(s);

        int delayed = 0;

        while (queue.size() > 0 && delayed != queue.size()) {
            if (queue.peek() == sandwiches[stack]) {
                queue.poll();
                stack++;
                delayed = 0;
            } else {
                queue.add(queue.poll());
                delayed++;
            }
        }

        return queue.size();
    }

    public int mySol(int[] students, int[] sandwiches) {
        int stack = 0;
        Queue<Integer> queue = new LinkedList();

        for (int s : students) queue.add(s);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int iter = size;

            while (iter-- > 0) {
                int s = queue.poll();

                if (s != sandwiches[stack]) {
                    queue.add(s);
                } else {
                    stack++;
                }
            }

            if (size == queue.size()) return size;
        }

        return 0;
    }
}