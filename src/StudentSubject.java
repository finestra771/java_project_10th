import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentSubject {

    ArrayList<Integer> scoreList;
    private Subject[] subjectList; //수강생 수강 과목 배열
    private Score[][] scoresList;// 수강생의 과목당 점수 배열

    public StudentSubject(Subject[] subjectList, Score[][] scoresList) {
        this.subjectList = subjectList;
        this.scoresList = scoresList;
    }

    //수강생의 과목별 시험 회차 및  점수 등록 (->자동으로 등급저장)
    public void createSubjectRoundScore(List<Student> studentList, int studentID){
        Scanner sc = new Scanner(System.in);
        Student student=findStudentById(studentList, studentID);
        ArrayList<ArrayList<Score>> scoreList2=new ArrayList<>();
        for (Subject subject : student.getSubjectList()) {
            ArrayList<Score> scoreList=new ArrayList<>();
            for(int i=0;i<10;i++){
                System.out.println(subject.getSubjectName()+"의 "+(i+1)+"회차 성적을 입력해주세요 : ");
                int score=sc.nextInt();
                if(score<=100 && score>=0) {
                    Score scoreset=new Score(Integer.toString(subject.getSubjectNum()), studentID, i, score);
                    scoreset.setSubjectNum(subject.getSubjectNum());
                    scoreList.add(scoreset);
                }
                else{
                    System.out.println("잘못된 입력입니다.");
                    i--;
                }
//                Score score1 = new Score(Integer.toString(subject.getSubjectNum()), studentID, i, 1);
//                score.setSubjectNum(subject.getSubjectNum());
//                scoreList.add(score);
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
    public Student findStudentById(List<Student> studentList, int studentID) {
        for (Student student : studentList) {
            if(student.getStudentID() == studentID)
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
    public ArrayList<Double> subjectAverageScore(ArrayList<Score> scoreList) {
        ArrayList<Double> averageScoreList = new ArrayList<>();
        double scoreSum = 0;
        double result = 0;
        for (Score i : scoreList) {
            scoreSum += i.getScore();
        }
        if(scoreList.size()!=0){
            result = scoreSum / scoreList.size();
        }
        averageScoreList.add(result);
        return averageScoreList;
        // 어레이리스트 만들고 리턴하는거 [[[[[추가]]]]]
    }
    public ArrayList<Double> mandatorySubjectAverageScore(ArrayList<Score> scoreList) {
        ArrayList<Double> averageScoreList = new ArrayList<>();
        double scoreSum = 0;
        double result = 0;
        for (Score i : scoreList) {
            scoreSum += i.getScore();
        }
        if(scoreList.size()!=0){
            result = scoreSum / scoreList.size();
        }
        if(result!=0) averageScoreList.add(result);
        return averageScoreList;
        // 어레이리스트 만들고 리턴하는거 [[[[[추가]]]]]
    }

    //수강생의 과목별 평균 등급 조회하는 메서드 [[[[[추가]]]]]
    public void inquireSubjectAverageScore(Subject subject, ArrayList<Score> scoreList) {
        ArrayList<Double> averageScoreList = subjectAverageScore(scoreList);
        for (int i=0;i<averageScoreList.size();i++) {
            System.out.println(subject.getSubjectName() + "의 평균 성적은" + averageScoreList.get(i) + "입니다.");
        }
    }


    //수강생의 특정 과목 회차별 등급 조회
    public void inquireSubjectRoundScore(Score score) {
        for (int i:this.scoreList){
            this.scoreList.get(i);
            System.out.println(i+1 + " 회차 등급은 " + score.getScoreScale() + " 입니다.");
        } // 프린트하는것 [[[[[추가]]]]]
    }
}