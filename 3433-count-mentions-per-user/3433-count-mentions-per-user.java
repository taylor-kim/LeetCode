class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        return mySol(numberOfUsers, events);
    }

    public int[] mySol(int numberOfUsers, List<List<String>> events) {
        Set<Integer> onlines = new HashSet();
        for (int i = 0; i < numberOfUsers; i++) onlines.add(i);

        Queue<int[]> offlines = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        Collections.sort(events, (a, b) -> {
            int numA = Integer.parseInt(a.get(1));
            int numB = Integer.parseInt(b.get(1));

            return numA - numB;
        });

        Map<Integer, Integer> mentioned = new HashMap();
        int all = 0;
        int[] ans = new int[numberOfUsers];

        for (List<String> event : events) {
            int timestamp = Integer.parseInt(event.get(1));

            while (!offlines.isEmpty() && offlines.peek()[0] <= timestamp) {
                onlines.add(offlines.poll()[1]);
            }

            String type = event.get(0);

            if (type.equals("MESSAGE")) {
                String data = event.get(2);

                if (data.equals("ALL")) {
                    all++;
                } else if (data.equals("HERE")) {
                    for (int online : onlines) ans[online]++;
                } else {
                    int[] ids = getIds(data);

                    for (int id : ids) ans[id]++;
                }
            } else {
                int offId = Integer.parseInt(event.get(2));
                offlines.add(new int[] {timestamp + 60, offId});
                onlines.remove(offId);
                
            }
        }

        for (int i = 0; i < ans.length; i++) {
            ans[i] += all;
        }

        return ans;
    }

    private int[] getIds(String ids) {
        return Arrays.stream(ids.replaceAll("id", "").split(" "))
            .map(s -> Integer.parseInt(s))
            .mapToInt(i -> (int)i)
            .toArray();
    }
}