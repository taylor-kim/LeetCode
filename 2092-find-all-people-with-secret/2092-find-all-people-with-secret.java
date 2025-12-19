class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        return mySol(n, meetings, firstPerson);
    }

    public List<Integer> mySol(int n, int[][] meetings, int firstPerson) {
        Set<Integer> ans = new HashSet();

        ans.add(0);
        ans.add(firstPerson);

        Map<Integer, List<int[]>> map = new TreeMap();

        for (int[] meeting : meetings) {
            map.computeIfAbsent(meeting[2], k -> new ArrayList()).add(meeting);
        }

        for (List<int[]> meetingsAt : map.values()) {
            Queue<Integer> queue = new LinkedList();
            Set<Integer> visit = new HashSet();
            Map<Integer, List<Integer>> graph = new HashMap();

            for (int[] meetingAt : meetingsAt) {
                graph.computeIfAbsent(meetingAt[0], k -> new ArrayList()).add(meetingAt[1]);
                graph.computeIfAbsent(meetingAt[1], k -> new ArrayList()).add(meetingAt[0]);

                for (int i = 0; i < 2; i++) {
                    if (ans.contains(meetingAt[i])) {
                        if (visit.add(meetingAt[i])) {
                            queue.add(meetingAt[i]);
                        }
                    }
                }
            }

            while (!queue.isEmpty()) {
                int person = queue.poll();

                for (int next : graph.getOrDefault(person, new ArrayList<>())) {
                    if (ans.add(next) && visit.add(next)) {
                        queue.add(next);
                    }
                }
            }
        }

        return new ArrayList(ans);
    }
}