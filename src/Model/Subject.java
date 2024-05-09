public class Subject {
    private int subjectNum; //고유 번호
    private String subjectName; // 과목명
    private SubjectCode subjectType; //과목타입

    public int getSubjectNum() {
        return subjectNum;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Subject(int subjectNum, String subjectName, SubjectCode subjectType) {
        this.subjectNum = subjectNum;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
    }

    // 과목명 수정
    public void updateSubjectName(String oldSubjectName, String newSubjectName) {
        SubjectList[] subjectLists = SubjectList.values();
        for (SubjectList subjectList : subjectLists) {
            if (!oldSubjectName.equals(newSubjectName)) {
                this.subjectName = newSubjectName;
                this.subjectType = subjectList.getSubjectCode();
                this.subjectNum = subjectList.getOrder();
            }
        }
    }

}


