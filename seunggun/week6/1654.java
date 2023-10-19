package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1654번
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] lines = new int[K];
        int max = -1;

        for (int i = 0; i < lines.length; ++i) {
            lines[i] = Integer.parseInt(br.readLine());
            if (lines[i] > max) {
                max = lines[i];
            }
        }

        long start = 1;
        long end = max;

        long result = 0;
        while (start <= end) {
            long mid = start + end / 2;

            int count = 0;
            for (int line : lines) {
                count += line / mid;
            }
            if (count < N) {
                end = mid - 1;
            } else {
                if (result < mid)
                    result = mid;
                start = mid + 1;
            }
        }
        System.out.println(result);
    }
}
