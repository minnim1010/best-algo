INSERT INTO member
VALUES (1, '조재혁', '1234'),
       (2, '김문기', '1234'),
       (3, '조현아', '1234'),
       (4, '최광민', '1234');

INSERT INTO problem
VALUES (1, '실습004_SWEA_1208_Flatten', '20240202'),
       (2, '실습011_JES_12891_DNA비밀번호', '20240202'),
       (3, '실습014_SWEA_1225_암호생성기', '20240202');

INSERT INTO code (id, member_id, problem_id, content, type, is_deleted, created_time, last_modified_time)
VALUES (1, 1, 1, 'public class 실습004_SWEA_1208_Flatten {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder(); // StringBuilder 사용

        int T = 10; // test case 10으로 고정

        for (int test_case = 1; test_case <= T; ++test_case) { // 10번 동안
            PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder()); // 최대 큐
            PriorityQueue<Integer> minQ = new PriorityQueue<>(); // 최소 큐

            int N = Integer.parseInt(br.readLine()); // N 입력

            StringTokenizer st = new StringTokenizer(br.readLine()); // Token
            for (int i = 0; i < 100; i++) { // 100번 동안
                int n = Integer.parseInt(st.nextToken()); // 입력
                minQ.add(n); // 최소 순서와
                maxQ.add(n); // 최대 순서에 동시에 저장
            }

            while (N-- > 0) { // N의 개수만큼 반복
                int a = minQ.poll(); // 가장 작은거 a
                int b = maxQ.poll(); // 가장 큰거 b

                if (b == a) break; // 같으면 전체가 평평해졌다는 뜻

                minQ.add(a + 1); // 최소에 하나 커진 걸 넣고
                maxQ.add(b - 1); // 최대에 하나 작아진 걸 넣으면 이동을 한 효과가 남
            }

            sb.append("#").append(test_case).append(" ").append(maxQ.peek() - minQ.peek()).append("\n"); // 결과 추가
        }

        bw.write(sb.toString()); // 출력
        bw.flush(); // 비우기
        bw.close(); // 닫기
    }
}', 'GOOD', 0, '2024-02-02T13:00:00', '2024-02-02T13:00:00'),
       (2, 2, 2, 'public class 실습011_JES_12891_DNA비밀번호 {
    /**
     * sb : StringBuilder
     * S : length of DNA string
     * P : length of password
     * _cnt : # of valid passwords
     * start, end : idx to use it as sliding-window technique
     * dna[] : dna string as char[] type
     * condition[] : minimum condition password should satisfy
     * current[] : dna[start+1:end]''s char composition
     */
    public static StringBuilder sb = new StringBuilder();
    public static int S, P, _cnt, start, end;
    public static char[] dna;
    public static int[] condition, current;
    public static final String DNA = "ACGT";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        dna = br.readLine().toCharArray();
        condition = new int[4];
        current = new int[4];
        st = new StringTokenizer(br.readLine());

        // [0] : A, [1] : C, [2]: G, [3]: T
        for (int i = 0; i < 4; i++) {
            condition[i] = Integer.parseInt(st.nextToken());
        }

        // initialize current array with the first window
        for (int idx = 0; idx < P; idx++) {
            current[DNA.indexOf(dna[idx])]++;
        }

        // window of size P slides from 0 to S-P
        // subtract the pop-element''s value add the put-element''s value
        for (start = 0, end = start + P; end <= S; start++, end++) {
            // check if string is valid
            isValid: {
                for (int i = 0; i < 4; i++) {
                    if (current[i] < condition[i])
                        break isValid;
                }
                _cnt++; // increase the cnt if four condition has satisfied
            }
            if (end == S) // need to break out of for loop before dna[end] runs to avoid index out of range error
                break;
            current[DNA.indexOf(dna[start])]--; // decrease DNA''s value of corresponding index of the char of start
            current[DNA.indexOf(dna[end])]++;   // increase DNA''s value of corresponding index of the char of end
        }

        sb.append(_cnt);
        System.out.println(sb);
    }

}', 'GOOD', 0, '2024-02-02T13:00:00', '2024-02-02T13:00:00'),
       (3, 3, 3, 'package 조현아;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 실습014_SWEA_1225_암호생성기 {
    static final int TEST_CASE_CNT = 10; // 테스트케이스 수
    static final int NUMS_CNT = 8; // 한번 입력할 때의 숫자 총 개수
    static StringBuilder sb = new StringBuilder(); // 결과
    static Deque<Integer> dq = new ArrayDeque<>(); // 암호 숫자 넣을 덱

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int t=0;t<TEST_CASE_CNT;t++) { // 테스트케이스 수만큼 반복
            int testCaseNum = Integer.parseInt(br.readLine()); // 테스트케이스 번호 입력받기

            // 숫자 입력받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<NUMS_CNT;i++) {
                dq.add(Integer.parseInt(st.nextToken()));
            }

            makePassword(); // 암호 생성

            // 결과 생성
            sb.append("#").append(testCaseNum).append(" ");
            for(int i=0;i<NUMS_CNT;i++) {
                sb.append(dq.poll()).append(" ");
            }
            sb.append("\n");
        }
        // 결과 출력
        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }

    public static void makePassword() { // 암호 생성
        int minusValue = 0; // 빼야하는 값

        while(true) {
            minusValue += 1; // 뺴야하는 값 증가
            int changedValue = dq.poll() - minusValue; // 변경된 값

            if(changedValue <= 0) { // 변경된 값이 0이하인 경우
                dq.add(0); // 0 넣어주고
                return; // 종료
            }

            dq.add(changedValue); // 변경된 값 덱에 넣어주기
            minusValue %= 5; // 1사이클 끝나면 다시 처음부터
        }
    }
}', 'GOOD', 0, '2024-02-02T13:00:00', '2024-02-02T13:00:00'),
       (4, 4, 3, 'package 최광민;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 실습014_SWEA_1225_암호생성기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // br로 입력 받는다.
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // bw로 출력한다.
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder(); // 스트링 빌더 선언
    static Deque<Integer> dq = new ArrayDeque<>(); // deque 선언

    public static void main(String[] args) throws IOException {
        for (int testCase = 1; testCase <= 10; testCase++) { // testCase 반복
            br.readLine(); // 첫번째 입력 넘기기

            st = new StringTokenizer(br.readLine()); // 이번 입력줄 토크나이저
            for (int i = 0 ; i < 8; i++) { // 8 만큼 반복
                dq.addLast(Integer.parseInt(st.nextToken())); // dq 마지막에 토큰 값 넣기
            }
            int cnt = 1; // cnt 는 1로 먼저 설정
            while(true) { // 무한 반복
                int tmpInt = dq.pollFirst(); // tmpInt에 첫번째 값 임시 저장
                if (tmpInt <= cnt) { // 만약 cnt 보다 작거나 같으면 0보다 작아지므로
                    dq.addLast(0); // 마지막에 0 추가 후
                    break; // 그냥 끝내기
                }
                tmpInt -= cnt++; // cnt 만큼 빼고 cnt는 +1

                dq.addLast(tmpInt); // dq 마지막에 임시 값 넣기

                if (cnt == 6) { // 한바퀴 다 돌면
                    cnt = 1; // 1로 돌아가기
                }
            }
            sb.append("#"+testCase+" "); // 테스트 케이스 넣기
            for (int i = 0; i < 8; i++) { // 8번 반복
                sb.append(dq.pollFirst() + " "); // 출력
            }
            sb.append("\n"); // 한줄 바꾸기
        }
        bw.write(sb.toString()); // bw에 sb 넣기
        bw.flush(); // 남은 bw 출력
        bw.close(); // bw 끝내기
    }
}', 'GOOD', 0, '2024-02-02T13:00:00', '2024-02-02T13:00:00');

