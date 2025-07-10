class Solution {
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        return try_20250711(eventTime, startTime, endTime);
    }

    public int try_20250711(int eventTime, int[] startTime, int[] endTime) {
        int n = startTime.length;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        for (int i = 0; i < n; i++) {
            int leftFree = startTime[i] - (i == 0 ? 0 : endTime[i - 1]);

            leftMax[i] = Math.max(leftFree, leftMax[Math.max(i - 1, 0)]);

            int j = n - i - 1;
            int rightFree = (j == n - 1 ? eventTime : startTime[j + 1]) - endTime[j];

            rightMax[j] = Math.max(rightFree, rightMax[Math.min(j + 1, n - 1)]);
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            int left = i == 0 ? 0 : endTime[i - 1];
            int right = i == n - 1 ? eventTime : startTime[i + 1];

            int meetingTime = endTime[i] - startTime[i];

            ans = Math.max(ans, right - left - meetingTime);

            if (i > 0 && meetingTime <= leftMax[i - 1]) {
                ans = Math.max(ans, right - left);
            }

            if (i + 1 < n && meetingTime <= rightMax[i + 1]) {
                ans = Math.max(ans, right - left);
            }
        }

        return ans;
    }

    public int mySol2(int eventTime, int[] startTime, int[] endTime) {
        int n = startTime.length;

        if (n == 1) {
            return eventTime - (endTime[0] - startTime[0]);
        }
        
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        int prevEnd = 0;
        int nextStart = eventTime;

        for (int i = 0; i < n; i++) {
            int leftFree = startTime[i] - prevEnd;
            
            leftMax[i] = Math.max(leftFree, leftMax[Math.max(i - 1, 0)]);
            prevEnd = endTime[i];

            int j = n - i - 1;

            int rightFree = nextStart - endTime[j];
            rightMax[j] = Math.max(rightFree, rightMax[Math.min(j + 1, n - 1)]);
            nextStart = startTime[j];
        }

        // System.out.println(Arrays.toString(leftMax));
        // System.out.println(Arrays.toString(rightMax));

        int ans = 0;

        for (int i = 0; i < n; i++) {
            int duration = endTime[i] - startTime[i];
            int max = 0;

            prevEnd = 0;

            if (i > 0) {
                prevEnd = endTime[i - 1];
            }

            nextStart = eventTime;

            if (i < n - 1) {
                nextStart = startTime[i + 1];
            }

            // System.out.println(String.format("i:%d, prevEnd:%d, nextStart:%d", i, prevEnd, nextStart));

            if ((i - 1 >= 0 && leftMax[i - 1] >= duration) || (i + 1 < n && rightMax[i + 1] >= duration)) {
                max = nextStart - prevEnd;
            } else {
                max = nextStart - prevEnd - duration;
            }

            ans = Math.max(ans, max);
        }

        return ans;

        /*
        i -> duration = 3;
        if leftMax[i - 1] >= 3 || rightMax[i + 1] >= 3
            then max = nextStart - prevEnd
        else
            then max = nextStart - prevEnd - duration
        */
    }

    public int mySol_fail(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;

        List<Integer> frees = new ArrayList();

        int totalMeetingTime = 0;
        int prevEnd = 0;

        for (int i = 0; i < n; i++) {
            frees.add(startTime[i] - prevEnd);
            prevEnd = endTime[i];

            totalMeetingTime += endTime[i] - startTime[i];
        }

        frees.add(eventTime - endTime[n - 1]);

        if (k == n) {
            return eventTime - totalMeetingTime;
        }

        Collections.sort(frees);

        int index = frees.size() - 1;

        int ans = frees.get(index--);

        while (k-- > 0 && index >= 0) {
            ans += frees.get(index--);
        }

        return ans;

        /*
        if (k == n) -> ans = eventTime - totalMeetingTime
        frees : frees[i] -> freeTimeFor [i - 1, i] th meeting
        sort frees
        get sum of first k frees.
        ans = last free + sum
        */
    }
}