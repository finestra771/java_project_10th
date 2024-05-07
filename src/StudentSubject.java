import java.util.Arrays;

public class StudentSubject {
    private Subject[] subjects;
    private Score[][] scoreList;


    public StudentSubject() {
        this.subjects = new Subject[5]; // 과목 최소 5개
        this.scoreList = new Score[5][10]; // 과목 최소 5개 , 시험 10회
    }

    public StudentSubject(int subjectlength) { // 커스텀 가능하다
        this.subjects = new Subject[subjectlength]; // 과목 최대 9개 과목 index 의 길이만큼
        this.scoreList = new Score[subjectlength][10]; // 과목 최대 9개 과목 index 의 길이만큼, 시험 10회
    }

    //수강생의 과목별 시험 회차 및  점수 등록 (->자동으로 등급저장)
    // 이해한 내용 메모 - 메인클래스에서 수행하는 과정에 전혀 관계가 없다.
    // 이해한 내용 메모 - 오로지 딱, 주어진 역할만 수행하도록 코드를 만들면 된다.
    // 이해한 내용 메모 - 다른 클래스의 필드값 가져오기 예시 Score score=getScore()
    // 이해한 내용 메모 - 다른 클래스의 메서드 가져오기 예시 Score.scoreScale()
    public void createSubjectRoundScore(Score[][] score){
        for (int i=0; i<this.subjects.length; i++ ) {
            for (int j=0; j<10; j++) {
                scoreList[i][j] = score[i][j];
            }
        }
    }

    //수강생의 과목별 회차 점수 수정
    public void updateSubjectRoundScore(int number, int subjectNum, int round, int score) {
        // 수강생 고유번호로 Get
        // 과목 고유번호로 Get
        // Score[][] 배열에서 수정
    }

    //수강생의 과목별 평균 등급
    public double inquireSubjectAverageScore() {
        // 수강생 고유번호로 Get
        // 과목 고유번호로 Get
        // Score[][]호출
        // 평균 연산

    }

    //수강생의 특정 과목 회차별 등급 조회
    public void inquireSubjectRoundScore(String scoreScale) {
        // 수강생 고유번호로 Get
        // 과목 고유번호로 Get
        // Score[][] 조회


    }



}
