class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        return editorial_copied(numberOfUsers, events);
    }

    public int[] editorial_copied(int numberOfUsers, List<List<String>> events) {
        events.sort((a, b) -> {
            int timeA = Integer.parseInt(a.get(1));
            int timeB = Integer.parseInt(b.get(1));
            if (timeA != timeB) {
                return Integer.compare(timeA, timeB);
            }
            boolean aIsMessage = a.get(0).equals("MESSAGE");
            boolean bIsMessage = b.get(0).equals("MESSAGE");
            return Boolean.compare(aIsMessage, bIsMessage);
        });

        int[] count = new int[numberOfUsers];
        int[] nextOnlineTime = new int[numberOfUsers];

        for (List<String> event : events) {
            int curTime = Integer.parseInt(event.get(1));
            String type = event.get(0);

            if (type.equals("MESSAGE")) {
                String target = event.get(2);
                if (target.equals("ALL")) {
                    for (int i = 0; i < numberOfUsers; i++) {
                        count[i]++;
                    }
                } else if (target.equals("HERE")) {
                    for (int i = 0; i < numberOfUsers; i++) {
                        if (nextOnlineTime[i] <= curTime) {
                            count[i]++;
                        }
                    }
                } else {
                    String[] users = target.split(" ");
                    for (String user : users) {
                        int idx = Integer.parseInt(user.substring(2));
                        count[idx]++;
                    }
                }
            } else {
                int idx = Integer.parseInt(event.get(2));
                nextOnlineTime[idx] = curTime + 60;
            }
        }

        return count;
    }

    public int[] editorial(int numberOfUsers, List<List<String>> events) {
        int[] nextOnlineTime = new int[numberOfUsers];

        Collections.sort(events, (a, b) -> {
            int numA = Integer.parseInt(a.get(1));
            int numB = Integer.parseInt(b.get(1));

            return numA != numB ? numA - numB : a.get(0).equals("OFFLINE") ? -1 : 1;
        });

        int all = 0;
        int[] ans = new int[numberOfUsers];

        for (List<String> event : events) {
            int timestamp = Integer.parseInt(event.get(1));

            String type = event.get(0);

            if (type.equals("MESSAGE")) {
                String data = event.get(2);

                if (data.equals("ALL")) {
                    all++;
                } else if (data.equals("HERE")) {
                    for (int i = 0; i < numberOfUsers; i++) {
                        if (nextOnlineTime[i] <= timestamp) {
                            ans[i]++;
                        }
                    }
                } else {
                    int[] ids = getIds(data);

                    for (int id : ids) ans[id]++;
                }
            } else {
                nextOnlineTime[Integer.parseInt(event.get(2))] = timestamp + 60;
            }
        }

        for (int i = 0; i < ans.length; i++) {
            ans[i] += all;
        }

        return ans;
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

            return numA != numB ? numA - numB : a.get(0).equals("OFFLINE") ? -1 : 1;
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