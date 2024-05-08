
public class Subject {
    private int subjectNum; //고유 번호
    private String subjectName; // 과목명
    private SubjectCode subjectType; //과목타입
    private int subjectRound;//과목 회차


    public int getSubjectNum() {
        return subjectNum;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public SubjectCode getSubjectType() {
        return subjectType;
    }


    public int getSubjectRound() {
        return subjectRound;
    }



    public Subject(int subjectNum, String subjectName, SubjectCode subjectType) {
        this.subjectNum = subjectNum;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
        this.subjectRound = subjectRound;
    }

    //과목명 수정 매서드

    public void updateSubjectName(String newSubjectName) {
        SubjectList[] subjectLists = SubjectList.values();
        for (SubjectList subjectList : subjectLists) {
            if (subjectList.getName().equals(newSubjectName)) {
                this.subjectName = newSubjectName;
                this.subjectType = subjectList.getSubjectCode();
                this.subjectNum = subjectList.getOrder();
            }
        }
    }
}


