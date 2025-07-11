class Solution {
    public int mostBooked(int n, int[][] meetings) {
        return gpt(n, meetings);
    }

    public int gpt(int n, int[][] meetings) {
        Queue<Integer> rooms = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            rooms.add(i);
        }

        Queue<int[]> usedRooms = new PriorityQueue<>((a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });

        Arrays.sort(meetings, (a, b) -> {
            return a[0] - b[0];
        });

        // for (int[] meeting : meetings) {
        //     System.out.print(Arrays.toString(meeting));
        // }

        // System.out.println("\n");

        Map<Integer, Integer> counter = new HashMap();

        for (int[] meeting : meetings) {
            while (!usedRooms.isEmpty() && usedRooms.peek()[0] <= meeting[0]) {
                rooms.add(usedRooms.poll()[1]);
            }

            if (!rooms.isEmpty()) {
                int room = rooms.poll();

                usedRooms.add(new int[] {meeting[1], room});

                counter.put(room, counter.getOrDefault(room, 0) + 1);
            } else {
                int delay = usedRooms.peek()[0];
                int room = usedRooms.poll()[1];

                usedRooms.add(new int[] {delay + (meeting[1] - meeting[0]), room});
                counter.put(room, counter.getOrDefault(room, 0) + 1);
            }
        }

        int ans = 0;
        int max = 0;

        // System.out.println(counter);

        for (int room : counter.keySet()) {
            int count = counter.get(room);

            if (count > max) {
                max = count;
                ans = room;
            } else if (count == max) {
                ans = Math.min(ans, room);
            }
        }

        return ans;
    }

    public int try_20250711_3_gpt_help_fail(int n, int[][] meetings) {
        Queue<Integer> rooms = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            rooms.add(i);
        }

        Queue<int[]> usedRooms = new PriorityQueue<>((a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });

        Arrays.sort(meetings, (a, b) -> {
            return a[0] - b[0];
        });

        // for (int[] meeting : meetings) {
        //     System.out.print(Arrays.toString(meeting));
        // }

        // System.out.println("\n");

        Map<Integer, Integer> counter = new HashMap();

        for (int time = meetings[0][0], i = 0; i < meetings.length; time++) {
            while (!usedRooms.isEmpty() && usedRooms.peek()[0] <= time) {
                rooms.add(usedRooms.poll()[1]);
            }

            while (!rooms.isEmpty() && i < meetings.length && meetings[i][0] <= time) {
                int[] meeting = meetings[i];

                int delay = Math.max(time - meeting[0], 0);
                int room = rooms.poll();

                // System.out.println(String.format("time:%d, delay:%d, room:%d, end:%d, meeting:%s"
                // , time, delay, room, meeting[1] + delay, Arrays.toString(meeting)));

                usedRooms.add(new int[] {meeting[1] + delay, room});
                counter.put(room, counter.getOrDefault(room, 0) + 1);
                i++;
            }
        }

        int ans = 0;
        int max = 0;

        // System.out.println(counter);

        for (int room : counter.keySet()) {
            int count = counter.get(room);

            if (count > max) {
                max = count;
                ans = room;
            } else if (count == max) {
                ans = Math.min(ans, room);
            }
        }

        return ans;
    }

    public int try_20250711_2_fail(int n, int[][] meetings) {
        Queue<Integer> rooms = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            rooms.add(i);
        }

        Queue<int[]> usedRooms = new PriorityQueue<>((a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });

        Arrays.sort(meetings, (a, b) -> {
            return a[0] - b[0];
        });

        for (int[] meeting : meetings) {
            System.out.print(Arrays.toString(meeting));
        }

        System.out.println("\n");

        Map<Integer, Integer> counter = new HashMap();

        for (int time = meetings[0][0], i = 0; i < meetings.length; time++) {
            int[] meeting = meetings[i];

            while (!usedRooms.isEmpty() && usedRooms.peek()[0] <= time) {
                rooms.add(usedRooms.poll()[1]);
            }

            if (time < meeting[0]) continue;

            if (!rooms.isEmpty()) {
                int delay = Math.max(time - meeting[0], 0);
                int room = rooms.poll();

                System.out.println(String.format("time:%d, delay:%d, room:%d, end:%d, meeting:%s"
                , time, delay, room, meeting[1] + delay, Arrays.toString(meeting)));

                usedRooms.add(new int[] {meeting[1] + delay, room});
                counter.put(room, counter.getOrDefault(room, 0) + 1);
                i++;
            }
        }

        int ans = 0;
        int max = 0;

        System.out.println(counter);

        for (int room : counter.keySet()) {
            int count = counter.get(room);

            if (count > max) {
                max = count;
                ans = room;
            } else if (count == max) {
                ans = Math.min(ans, room);
            }
        }

        return ans;
    }

    public int try_20250711_fail(int n, int[][] meetings) {
        Queue<Integer> rooms = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            rooms.add(i);
        }

        Queue<int[]> usedRooms = new PriorityQueue<>((a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });

        Arrays.sort(meetings, (a, b) -> {
            return a[0] - b[0];
        });

        Map<Integer, Integer> counter = new HashMap();

        for (int[] meeting : meetings) {
            int delay = 0;

            if (rooms.isEmpty()) {
                int newStart = usedRooms.peek()[0];
                int room = usedRooms.poll()[1];

                delay = Math.max(newStart - meeting[0], 0);

                rooms.add(room);
            }

            int room = rooms.poll();

            usedRooms.add(new int[] {meeting[1] + delay, room});

            counter.put(room, counter.getOrDefault(room, 0) + 1);
        }

        int ans = 0;
        int max = 0;

        // System.out.println(counter);

        for (int room : counter.keySet()) {
            int count = counter.get(room);

            if (count > max) {
                max = count;
                ans = room;
            } else if (count == max) {
                ans = Math.min(ans, room);
            }
        }

        return ans;
    }











    
    public int official_sort_and_loop(int n, int[][] meetings) {
        int[] meetingCount = new int[n];
        long[] nextTime = new long[n];
        
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));
        
        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];
            
            long minNextTime = Long.MAX_VALUE;
            int nextRoom = 0;
            
            boolean found = false;
            
            for (int i = 0; i < n; i++) {
                if (nextTime[i] <= start) {
                    nextTime[i] = end;
                    meetingCount[i]++;
                    found = true;
                    break;
                }
                
                if (minNextTime > nextTime[i]) {
                    minNextTime = nextTime[i];
                    nextRoom = i;
                }
            }
            
            if (!found) {
                nextTime[nextRoom] += end - start;
                meetingCount[nextRoom]++;
            }
        }
        
        int max = 0;
        int room = 0;
        
        for (int i = 0; i < n; i++) {
            if (max < meetingCount[i]) {
                max = meetingCount[i];
                room = i;
            }
        }
        
        return room;
    }
    
    public int official_pq(int n, int[][] meetings) {
        int[] meetingCount = new int[n];
        
        Queue<int[]> used = new PriorityQueue<>((a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
            // return a[0] - b[0];
        });
        
        Queue<Integer> rooms = new PriorityQueue();
        
        // Arrays.sort(meetings, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));
        
        for (int i = 0; i < n; i++) rooms.add(i);
        
        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];
            
            while (!used.isEmpty() && used.peek()[0] <= start) {
                rooms.add(used.poll()[1]);
            }
            
            if (!rooms.isEmpty()) {
                int room = rooms.poll();
                used.add(new int[] {end, room});
                meetingCount[room]++;
            } else {
                int nextStart = used.peek()[0];
                int room = used.poll()[1];
                
                used.add(new int[] {nextStart + end - start, room});
                
                meetingCount[room]++;
            }
        }
        
        int max = 0;
        int room = 0;
        
        for (int i = 0; i < n; i++) {
            if (max < meetingCount[i]) {
                max = meetingCount[i];
                room = i;
            }
        }
        
        return room;
    }
    
    public int mySol_fail(int n, int[][] meetings) {
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
           return a[0] - b[0];
        });
        
        Queue<Integer> rooms = new PriorityQueue();
        Queue<int[]> used = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        
        for (int[] meeting : meetings) {
            queue.add(meeting);
        }
        
        for (int i = 0; i < n; i++) rooms.add(i);
        
        int time = queue.peek()[0];
        
        int[] record = new int[n];
        
        while (!queue.isEmpty()) {
            while (!used.isEmpty() && used.peek()[1] + used.peek()[2] <= time) {
                // System.out.println("getback room : " + used.peek()[2] + " time : " + time + " meeting : " + Arrays.toString(used.peek()));
                rooms.add(used.poll()[3]);
            }
            
            if (rooms.isEmpty()) {                
                time++;
                continue;
            }
            
            if (queue.peek()[0] > time) {
                time++;
                continue;
            }
            
            int[] meeting = queue.poll();
            
            int room = rooms.poll();
            
            // System.out.println("use room : " + room + " time : " + time + " meeting : " + Arrays.toString(meeting));
            
            used.add(new int[] {meeting[0], meeting[1], time - meeting[0], room});
            
            record[room]++;
            time++;
        }
        
        int max = 0;
        int ans = -1;
        
        // System.out.println(Arrays.toString(record) + "\n");
        
        for (int i = 0; i < n; i++) {
            if (max < record[i]) {
                max = record[i];
                ans = i;
            }
        }
        
        return ans;
    }
}