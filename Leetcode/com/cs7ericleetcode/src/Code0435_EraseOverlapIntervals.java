package Leetcode.Leetcode.com.cs7ericleetcode.src;

import java.util.Arrays;

/**
 * @author C77eric
 * @version 1.0
 */
public class Code0435_EraseOverlapIntervals {

    public int eraseOverlapIntervals1(int[][] intervals) {
        if(intervals.length == 0)   return 0;

        Arrays.sort(intervals,(a, b) ->(a[1] - b[1]));

        int n = intervals.length;
        int[] f = new int[n];
        Arrays.fill(f,1);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (intervals[j][1] <= intervals[i][0]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }
        return n - Arrays.stream(f).max().getAsInt();
    }

    public int eraseOverlapIntervals2(int[][] intervals) {
        if(intervals.length == 0)   return 0;

        Arrays.sort(intervals,(a,b) ->(a[1] - b[1]));

        int n = intervals.length;
        int right = intervals[0][1];
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            if (intervals[i][0] >= right) {
                ++ans;
                right = intervals[i][1];
            }
        }
        return n - ans;
    }
}
