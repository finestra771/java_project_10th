import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StudentSubject {

    ArrayList<Score> scoreList=new ArrayList<>();
    private Subject[] subjectList; //수강생 수강 과목 배열
    private Score[][] scoresList;// 수강생의 과목당 점수 배열

    public StudentSubject(int subjectslength) {  //생성자
        this.subjectList = new Subject[subjectslength];
        this.scoresList = new Score[subjectslength][10];
    }

    //수강생의 과목별 시험 회차 및  점수 등록 (->자동으로 등급저장)
    public void createSubjectRoundScore(int studentID){
        Scanner sc = new Scanner(System.in);
        Student student=findStudentById(studentID);
        ArrayList<ArrayList<Score>> scoreList2=new ArrayList<>();
        for (Subject subject : student.getSubjectList()) {
            ArrayList<Score> scoreList=new ArrayList<>();
            for(int i=0;i<10;i++){
                System.out.println(subject.getSubjectName()+"의 "+(i+1)+"회차 성적을 입력해주세요 : ");
                int score=sc.nextInt();
                if(score<=100 && score>=0) scoreList.add(new Score(subject.getSubjectNum(), studentID, i, score));
                else{
                    System.out.println("잘못된 입력입니다.");
                    i--;
                }
            }
            scoreList2.add(scoreList);
        }
        Score[][] scores=new Score[scoreList2.size()][10];
        for(int i=0;i<scoreList2.size();i++){
            for(int j=0;j<scoreList2.get(i).size();j++){
                scores[i][j]=scoreList2.get(i).get(j);
            }
        }
        student.setScoresList(scores);
    }
    private static List<Student> studentList = new ArrayList<>();
    public static Student findStudentById(int studentScore) {
        for (Student student : studentList) {
            if(student.getStudentID() == studentScore)
                return student;
        }
        return null;
    }

    //수강생의 과목별 회차 점수 수정
    public void updateSubjectRoundScore() {
        Scanner sc = new Scanner(System.in);
        System.out.println("수정할 회차와 점수를 입력하세요. ");
        int round=sc.nextInt();
        int score=sc.nextInt();
        scoreList.set(round,score);
    }


    //수강생의 과목별 평균 등급
    public double inquireSubjectAverageScore() {
        int scoreSum = 0;
        int result = 0;
        for (int i : scoreList) {
            scoreSum += scoreList[i];
        } result = scoreSum / scoreList.size();
        Score.setScoreScale(result);
    }


    //수강생의 특정 과목 회차별 등급 조회
    public void inquireSubjectRoundScore() {
        for (int i:scoreList){
            scoreList.get(i).setScoreScale(i);
        }
    }
}
