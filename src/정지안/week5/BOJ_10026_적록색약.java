package 정지안.week5;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_10026_적록색약 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static char[][] arr, arr2;// 적록색약을 위해 arr2도 만들었다.
    static boolean[][] visited, visited2;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];
        arr2 = new char[n][n];
        visited = new boolean[n][n];
        visited2 = new boolean[n][n];


        for (int i = 0; i < n; i++) {
            String A = br.readLine();
            for (int j = 0; j < n; j++) {
                char color = A.charAt(j);

                arr[i][j] = color;
                arr2[i][j] = color == 'G' ? 'R' : color; // 적록색약을 위해 녹색 => 빨간색으로 변경
            }
        }

        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }

        // 모든 배열 요소에 대해 dfs수행
        int cnt = 0, cnt2 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {// 방문하지 않은 경우에만 dfs수행
                    visited[i][j] = true;
                    dfs(i, j);
                    cnt++;
                }

                if (!visited2[i][j]) {// (적록색약)방문하지 않은 경우에만 dfs수행
                    visited2[i][j] = true;
                    dfs2(i, j);
                    cnt2++;
                }
            }
        }
        sb.append(cnt).append(" ").append(cnt2);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }//main()

    private static void dfs2(int y, int x) {
        char currentColor = arr2[y][x];
        for(int i=0; i<4; i++){
            int moveY = y + dy[i];
            int moveX = x + dx[i];
            if(isBoundary(moveY,moveX)  // 움직이려는 방향이 배열 경계를 벗어나지 않고
                    && !visited2[moveY][moveX]  //방문하지 않은 곳이며
                    && arr2[moveY][moveX] == currentColor){ // 현재 선택된 배열 요소의 색상과 같은 색상이라면

                visited2[moveY][moveX] = true; // 방문 처리
                dfs2(moveY,moveX); // dfs로 뻗어나간다.
            }
        }
    }

    private static void dfs(int y, int x) {
        char currentColor = arr[y][x];
        for(int i=0; i<4; i++){
            int moveY = y + dy[i];
            int moveX = x + dx[i];
            if(isBoundary(moveY,moveX)  // 움직이려는 방향이 배열 경계를 벗어나지 않고
                    && !visited[moveY][moveX]  //방문하지 않은 곳이며
                    && arr[moveY][moveX] == currentColor){ // 현재 선택된 배열 요소의 색상과 같은 색상이라면

                visited[moveY][moveX] = true; // 방문 처리
                dfs(moveY,moveX); // dfs로 뻗어나간다.
            }
        }
    }

    private static boolean isBoundary(int moveY, int moveX) {
        return moveY>=0 && moveY<n && moveX>=0 && moveX<n;
    }
}
