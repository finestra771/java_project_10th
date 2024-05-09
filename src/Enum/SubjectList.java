public enum SubjectList {  //과목 목록
    Java(1, SubjectCode.MANDATORY, "Java"),
    객체지향(2, SubjectCode.MANDATORY, "객체지향"),
    Spring(3, SubjectCode.MANDATORY, "Spring"),
    JPA(4, SubjectCode.MANDATORY, "JPA"),
    MySQL(5, SubjectCode.MANDATORY, "MySQL"),
    디자인패턴(6, SubjectCode.CHOICE, "디자인패턴"),
    SpringSecurity(7,  SubjectCode.CHOICE, "SpringSecurity"),
    Redis(8,  SubjectCode.CHOICE, "Redis"),
    MongoDB(9,  SubjectCode.CHOICE, "MongoDB");

    private final int order;
    private final SubjectCode subjectCode;
    private final String name;

    private SubjectList(int order, SubjectCode subjectCode, String name) {
        this.order = order;
        this.subjectCode = subjectCode;
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public SubjectCode getSubjectCode() {
        return subjectCode;
    }

    public String getName() {
        return name;
    }

    // 해당 과목 코드를 가지는 subject 찾아 반환
    public static SubjectList getSubjectByOrder(int order) {
        for (SubjectList subject : values()) {
            if (subject.getOrder() == order) {
                return subject;
            }
        }
        return null;
    }
}
