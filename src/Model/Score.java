package Model;

import Enum.SubjectCode;
import Enum.SubjectList;

public class Score {

    private String subjectNum; //과목 고유번호
    private SubjectCode subjectCode; // 과목 코드
    private int studentNum; //수강생 고유 번호
    private int round; //회차
    private int score; //점수
    private String scoreScale; //등급

    public Score(String subjectNum, int studentNum, int round, int score) {
        this.subjectNum = subjectNum;
        this.studentNum = studentNum;
        this.round = round;
        this.score = score;
        setScoreScale(score); // 객체 생성 시점 => 점수 => 등급 변경 메서드 호출
    }

    public String getSubjectName() {
        SubjectList subjectCode1 = SubjectList.getSubjectByOrder(Integer.parseInt(subjectNum));
        return subjectCode1.name();
    }

    public void setSubjectNum(int subjectNum) {
        SubjectList subject = SubjectList.getSubjectByOrder(subjectNum);
        if (subject.getSubjectCode() == SubjectCode.MANDATORY) this.subjectCode = SubjectCode.MANDATORY;
        else this.subjectCode = SubjectCode.CHOICE;
    }

    public SubjectCode getSubjectCode() {
        return subjectCode;
    }

    /**
     * 등급 변경 메서드
     *
     * @param score : 점수
     */
    public void setScoreScale(int score) {
        if (subjectCode == SubjectCode.MANDATORY) {
            if (score >= 95 && score <= 100) {
                this.scoreScale = "A";
            } else if (score >= 90 && score <= 94) {
                this.scoreScale = "B";
            } else if (score >= 80 && score <= 89) {
                this.scoreScale = "C";
            } else if (score >= 70 && score <= 79) {
                this.scoreScale = "D";
            } else if (score >= 60 && score <= 69) {
                this.scoreScale = "F";
            } else {
                this.scoreScale = "N";
            }
        } else {
            if (score >= 90 && score <= 100) {
                this.scoreScale = "A";
            } else if (score >= 80 && score <= 89) {
                this.scoreScale = "B";
            } else if (score >= 70 && score <= 79) {
                this.scoreScale = "C";
            } else if (score >= 60 && score <= 69) {
                this.scoreScale = "D";
            } else if (score >= 50 && score <= 59) {
                this.scoreScale = "F";
            } else {
                this.scoreScale = "N";
            }
        }
        // 점수 등록
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public String getScoreScale() {
        return this.scoreScale;
    }

    public String getScoretoString() {
        return Integer.toString(this.score);
    }
    //등급 세팅 메소드

    // 과목 하나당 10회 시험
    // 10회 시험 하나당 점수 및 등급
    // Java
    // 등급 1회차 D 2회차 D
    // 등급 산정 기준

    // 등급 ( 필수 과목 )
    // A : 95 ~ 100
    // B : 90 ~ 94
    // C : 80 ~ 89
    // D : 70 ~ 79
    // F : 60 ~ 69
    // N : 60점 미만

    // 등급 ( 선택 과목 )
    // A : 90 ~ 100
    // B : 80 ~ 89
    // C : 70 ~ 79
    // D : 60 ~ 69
    // F : 50 ~ 59
    // N : 50점 미만

}
