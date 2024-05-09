import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    private int studentID; //수강생 고유번호
    private String studentName; //수강생 이름
    private Status studentStatus; //수강생 상태
    private Subject[] subjectList; //수강생 수강 과목 배열
    private Score[][] scoresList;// 수강생의 과목당 점수 배열

    public Student(int studentID, String studentName, Subject[] subjectList) {  //생성자
        this.studentID = studentID;
        this.studentName = studentName;
        this.subjectList = subjectList;
    }

    // Getter & Setter
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Status getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(Status studentStatus) {
        this.studentStatus = studentStatus;
    }

    public Subject[] getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(Subject[] subjectList) {
        this.subjectList = subjectList;
    }

    public Score[][] getScoresList() {
        return scoresList;
    }

    public void setScoresList(Score[][] scoresList) {
        this.scoresList = scoresList;
    }

    public ArrayList<Score> getScoresByArray(Subject subject){
        ArrayList<Score> scores = new ArrayList<>();
        int index=0;
        for (Subject subject1 : subjectList){
            if(subject1.getSubjectName().equals(subject.getSubjectName())){
                break;
            }
            index++;
        }
        for(Score score : scoresList[index]){
            scores.add(score);
        }
        return scores;
    }

    public String getScores() {
        String res="";
        int index=0;
        if(scoresList!=null){
            for(Score[] score : scoresList){
                res=res+subjectList[index].getSubjectName()+" ";
                for(Score s : score){
                    res=res+s.getScoretoString()+" ";
                    res+=s.getScoreScale()+" ";
                }
                res+="\n";
                index++;
            }
            return res;
        }
        else{
            System.out.println("성적을 입력한 뒤 실행해주세요.");
            return "";
        }
    }

    public String subjectListtoString(){
        String res="";
        for(Subject s : subjectList){
            res=res+s.getSubjectName()+" ";
        }
        return res;
    }

    public void setScoreListOne(int score, int round, SubjectList subject){
        for(int i=0;i<subjectList.length;i++){
            if(subjectList[i].getSubjectName().equals(subject.getName())){
                scoresList[i][round]=new Score(subjectList[i].getSubjectName(), studentID, round, score);
            }
        }
    }
}
