package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main7 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] times = new int[N];
        for (int i = 0; i < times.length; ++i)
            times[i] = Integer.parseInt(br.readLine());

        long min = Long.MAX_VALUE;
        for (int t : times) {
            if (t < min) {
                min = t;
            }
        }

        long maxTime = min * M;
        long start = 0;
        long end = maxTime;

        while (start <= end) {
            long mid = (start + end) / 2;
            long sum = 0;
            for (int time : times) sum += mid / time;

            if (M <= sum) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start);
    }
}
