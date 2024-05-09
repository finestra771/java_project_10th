import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        //수강생 정보 조회
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("수강생 관리 메뉴는 1, 조회 메뉴는 2를 입력하세요");
            switch (sc.nextInt()) {
                case 1:
                    System.out.println("수강생 추가는 1, 수강생 삭제는 2, 수강생 점수 세팅은 3, 특정 수강생 정보 수정은 4를 입력하세요");
                    switch (sc.nextInt()) {
                        case 1:
                            client.addStudentInfo();
                            break;
                        case 2:
                            client.deleteStudent();
                            break;
                        case 3:
                            client.setStudentScore();
                            break;
                        case 4:
                            System.out.println("정보를 변경하고 싶은 싶은 학생의 번호를 입력하세요 : ");
                            client.modifyStudentInfo(sc.nextInt());
                            break;
                    }
                    break;
                case 2:
                    System.out.println("전체 수강생 목록 조회는 1, 상태별 수강생 목록 조회는 2, 특정 수강생의 전체 성적 조회는 3, 수강생들의 필수 과목 평균 등급 조회는 4, 특정 상태 수강생들의 필수 과목 평균 등급을 조회는 5를 입력하세요");
                    switch (sc.nextInt()) {
                        case 1:
                            client.printAllStudentInfo();
                            System.out.print("조회하고 싶은 학생의 번호를 입력하세요 : ");
                            client.inquireStudentInfo(sc.nextInt());
                            break;
                        case 2:
                            client.inquireStudentInfoByStatus();
                            break;
                        case 3:
                            client.getScores();
                            break;
                        case 4:
                            client.inquireStudentAvgScore();
                            break;
                        case 5:
                            client.inquireStudentAvgByStatus();
                    }
            }
        }
    }

}