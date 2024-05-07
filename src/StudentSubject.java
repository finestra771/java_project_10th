import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentSubject {

    ArrayList<Integer> scoreList;
    private Subject[] subjectList; //수강생 수강 과목 배열
    private Score[][] scoresList;// 수강생의 과목당 점수 배열

    public StudentSubject(int subjectslength) {
        this.scoreList =new ArrayList<>();
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
    public void updateSubjectRoundScore(int round, int score) {
        if (round<=10 && round>=1) {
            if (score<=100 && score>=0) {
                this.scoreList.set(round,score);
            }
        }
        // 스코어, 라운드 제한사항 [[[[[추가]]]]]
    }


    //수강생의 과목별 평균 등급
    public ArrayList<Double> subjectAverageScore() {
        ArrayList<Double> averageScoreList = new ArrayList<>();
        double scoreSum = 0;
        double result = 0;
        for (int i : this.scoreList) {
            scoreSum += this.scoreList.get(i);
        }
        result = scoreSum / this.scoreList.size();
        averageScoreList.add(result);
        return averageScoreList;
        // 어레이리스트 만들고 리턴하는거 [[[[[추가]]]]]
    }


    //수강생의 과목별 평균 등급 조회하는 메서드 [[[[[추가]]]]]
    public void inquireSubjectAverageScore() {
        Subject subject = new Subject();
        ArrayList<Double> averageScoreList = subjectAverageScore();
        System.out.println(subject.getSubjectName() + "의 평균 등급은" + averageScoreList + "입니다.");
    }


    //수강생의 특정 과목 회차별 등급 조회
    public void inquireSubjectRoundScore() {
        Score score = new Score();
        for (int i:this.scoreList){
            this.scoreList.get(i);
            System.out.println(i+1 + " 회차 등급은 " + score.getScoreScale(i) + " 입니다.");
        }
        // 프린트하는것 [[[[[추가]]]]]
    }
}
