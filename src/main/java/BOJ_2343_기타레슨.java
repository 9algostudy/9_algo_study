import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2343_기타레슨 {
	static int[] lecture;
	static int[] sum;
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		lecture = new int[N];
		sum = new int[M+1];
		
		input = br.readLine().split(" ");
		
		for(int i = 0; i < N; i++) {
			lecture[i] = Integer.parseInt(input[i]);
		}
	}
	static void findMinSize(int depth) {
		if(depth == M+1) {
			return;
		}
		
		
		for(int i = depth; i <= N-M+1; i++) {
			sum[depth] = sum[depth] + i;
			findMinSize(depth+1);
		}
	}
}
