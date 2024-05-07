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
    public void createSubjectRoundScore(Score[][] score){
        for (int i=0; i<this.subjects.length; i++ ) {
            for (int j = 0; j < 10; j++) {
                this.scoreList[i][j] = score[i][j];
            }
        }
    }

    //수강생의 과목별 회차 점수 수정
    public void updateSubjectRoundScore(Score[][] score, int subjectRound, int round) {
        for (int i=0; i<this.subjects.length; i++ ) {
            for (int j=0; j<10; j++) {
                if (subjectRound == round) {
                    this.scoreList[i][j] = score[i][j];
                }
            }
        }
    }

    //수강생의 과목별 평균 등급
    public double inquireSubjectAverageScore(Score[][] score) {
        int scoreSum = 0;
        for (int i=0; i<this.subjects.length; i++ ) {
            for (int j = 0; j < 10; j++) {
                this.scoreList[i][j] = score[i][j];
                scoreSum += Integer.parseInt(this.scoreList[i][j].toString());
            }
        } return scoreSum / this.subjects.length ;
    }


    //수강생의 특정 과목 회차별 등급 조회
    public void inquireSubjectRoundScore(String scoreScale) {
        Arrays.deepToString(this.scoreList);
    }



}
