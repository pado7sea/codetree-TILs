import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int[][] egg;
    static boolean[][] visited;
    static ArrayList<Point> eggGroup = new ArrayList<>(); // 합쳐질 그룹
    static boolean isChanged = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int res = 0; // 계란 이동 횟수

        egg = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                egg[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visited[i][j] = false;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    eggGroup = new ArrayList<>();
                    bfs(i, j);
                    if(!isChanged)
                        break;
                    res++;
                }
            }
        }
        System.out.println(res);


    }

    private static void bfs(int i, int j) {
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(i,j));
        visited[i][j] = true;

        if(eggGroup.size()>1){
            isChanged = true;
        }



        int sumOfEggs= 0;
        for(int k = 0; k < eggGroup.size(); k++) {
            int x = eggGroup.get(k).x, y = eggGroup.get(k).y;
            sumOfEggs += egg[x][y];
        }

        for(int k = 0; k < eggGroup.size(); k++) {
            int x = eggGroup.get(k).x, y = eggGroup.get(k).y;
            egg[x][y] = sumOfEggs / eggGroup.size();
        }
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}