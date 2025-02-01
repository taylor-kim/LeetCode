class Solution {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        return mySol2(eventTime, k, startTime, endTime);
    }

    public int mySol2(int eventTime, int k, int[] startTime, int[] endTime) {
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

        int left = 0;
        int ans = 0;
        int sum = 0;

        for (int right = 0; right < frees.size(); right++) {
            sum += frees.get(right);

            if (right - left > k) {
                sum -= frees.get(left++);
            }

            ans = Math.max(ans, sum);
        }

        return ans;
    }

    public int mySol_fail(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;

        List<int[]> frees = new ArrayList();

        int totalMeetingTime = 0;
        int prevEnd = 0;

        for (int i = 0; i < n; i++) {
            frees.add(new int[] {startTime[i] - prevEnd, i});
            prevEnd = endTime[i];

            totalMeetingTime += endTime[i] - startTime[i];
        }

        frees.add(new int[] {eventTime - endTime[n - 1], n});

        if (k == n) {
            return eventTime - totalMeetingTime;
        }

        Collections.sort(frees, (a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });

        int index = frees.size() - 1;

        int ans = frees.get(index--)[0];

        while (k > 0 && index >= 0) {
            int[] data = frees.get(index);
            
            if (data[1] >= index + 1 - k) {
                ans += data[0];

                k--;
            }
            index--;
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