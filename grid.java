import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 마을 수
        int C = Integer.parseInt(st.nextToken()); // 트럭 용량
        int M = Integer.parseInt(br.readLine()); // 박스 정보 개수

        int[][] receivePriority = new int[M][3];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int box = Integer.parseInt(st.nextToken());

            receivePriority[i][0] = start;
            receivePriority[i][1] = end;
            receivePriority[i][2] = box;
        }

        Arrays.sort(receivePriority, ((x, y) -> {
            if (x[1] == y[1]) {
                return x[0] - y[0];
            }
            return x[1] - y[1];
        }));

        int[] capacities = new int[N + 1];
        for (int i = 1; i < N; i++) {
            capacities[i] = C;
        }

        int ans = 0;
        for (int i = 0; i < receivePriority.length; i++) {
            int start = receivePriority[i][0];
            int end = receivePriority[i][1];
            int box = receivePriority[i][2];

            int capacity = Integer.MAX_VALUE;

            for (int j = start; j < end; j++) {
                capacity = Math.min(capacity, capacities[j]);
            }

            for (int j = start; j < end; j++) {
                capacities[j] -= Math.min(capacity, box);
            }

            ans += Math.min(capacity, box);
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}