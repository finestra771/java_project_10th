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


    public void modifyStudentName(String name) {  //이름 입력받아 수정
        this studentName = name;
    }

    public void modifyStudentStatus(Status status) {  //상태 입력받아 수정
        this studentStatus = status;
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
}
