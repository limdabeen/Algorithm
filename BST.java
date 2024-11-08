import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // Node 클래스: 이진 탐색 트리의 각 노드를 표현하는 클래스
    static class Node {
        int num; // 노드에 저장된 값
        Node left, right; // 왼쪽 및 오른쪽 자식 노드를 가리키는 참조 변수

        // 노드의 값을 초기화하는 생성자
        Node(int num) {
            this.num = num;
        }

        // 노드의 값과 자식 노드를 함께 초기화하는 생성자
        Node(int num, Node left, Node right) {
            this.num = num;
            this.left = left;
            this.right = right;
        }

        // 새로운 값을 이진 탐색 트리에 삽입하는 메서드
        void insert(int n) {
            // 새 값이 현재 노드 값보다 작으면 왼쪽에 삽입
            if (n < this.num) {
                if (this.left == null)
                    this.left = new Node(n); // 왼쪽 자식이 없으면 새 노드 생성
                else
                    this.left.insert(n); // 왼쪽 하위 트리에 재귀적으로 삽입
            } else {
                // 새 값이 현재 노드 값보다 크면 오른쪽에 삽입
                if (this.right == null)
                    this.right = new Node(n); // 오른쪽 자식이 없으면 새 노드 생성
                else
                    this.right.insert(n); // 오른쪽 하위 트리에 재귀적으로 삽입
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 입력 값을 루트 노드로 설정
        Node root = new Node(Integer.parseInt(br.readLine()));
        String input;
        while (true) {
            input = br.readLine();
            if (input == null || input.equals("")) // 입력이 없거나 빈 줄이면 종료
                break;
            root.insert(Integer.parseInt(input)); // 입력 값을 트리에 삽입
        }

        postOrder(root); // 후위 순회 방식으로 트리 출력
    }

    // 후위 순회 메서드: 왼쪽 -> 오른쪽 -> 현재 노드 순서로 방문
    static void postOrder(Node node) {
        if (node == null)
            return;

        postOrder(node.left); // 왼쪽 자식 방문
        postOrder(node.right); // 오른쪽 자식 방문
        System.out.println(node.num); // 현재 노드 값 출력
    }
}