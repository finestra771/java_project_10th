import java.util.Arrays;

public class StudentSubject {
    private Subject[] subjects;
    private Score[][] scoreList;


    public StudentSubject() {
        this.subjects = new Subject[5]; // 과목 최소 5개
        this.scoreList = new Score[5][10]; // 과목 최소 5개 , 시험 10회
    }

    public StudentSubject(int subjectlength) {
        this.subjects = new Subject[subjectlength]; // 과목 최대 9개 과목 index 의 길이만큼
        this.scoreList = new Score[subjectlength][10]; // 과목 최대 9개 과목 index 의 길이만큼, 시험 10회
    }

    //수강생의 과목별 시험 회차 및  점수 등록 (->자동으로 등급저장)
    public void createSubjectRoundScore(){
        int number = getNumber(); // 수강생클래스 고유번호로 Get
        int subjectNum = getSubjectNum(); // 과목클래스 고유번호로 Get
        int score = getScore(); // 점수클래스 점수로 Get
        // 여기에 score 를 scoreScale 로 등급수정 메서드 Get
        for (int i=0; i<9; i++ ) {// Score[][] 배열에 저장
            for (int j=0; j<10; j++) {
                scoreList[i][j] = scoreList[subjectNum][];
            }
        }
    }

    //수강생의 과목별 회차 점수 수정
    public void updateSubjectRoundScore() {
        int number = getNumber(); // 수강생 고유번호로 Get
        int subjectNum = getSubjectNum(); // 과목 고유번호로 Get
        // Score[][] 배열에서 수정
    }

    //수강생의 과목별 평균 등급
    public double inquireSubjectAverageScore() {
        int number = getNumber(); // 수강생 고유번호로 Get
        int subjectNum = getSubjectNum(); // 과목 고유번호로 Get
        // Score[][]호출
        // 평균 연산

    }

    //수강생의 특정 과목 회차별 등급 조회
    public void inquireSubjectRoundScore(String input) {
        int number = getNumber(); // 수강생 고유번호로 Get
        int subjectNum = getSubjectNum(); // 과목 고유번호로 Get
        // Score[][] 조회
        for (int i = 0; i < scoreList.length; i++) {

        }

    }



}
