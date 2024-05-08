import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {
    private static List<Student> studentList = new ArrayList<>();
    // 1-1) 수강생 정보 등록
    public static void addStudentInfo() {

        ArrayList<Subject> subjects = new ArrayList<>();
        int stuID; //수강생 고유번호
        String name; //수강생 이름

        System.out.println("수강생 정보를 입력해주세요.");

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("고유번호 : ");
            stuID = sc.nextInt();
            int finalStuID = stuID;
            boolean exist = studentList.stream().anyMatch(student -> student.getStudentID() == finalStuID);
            if (exist) {
                System.out.println("이미 존재하는 수강생 고유번호입니다. 다시 입력해주세요.");
            } else {
                break;
            }
        }

        System.out.print("이름 : ");
        name = sc.next();
        System.out.println();

        selectMandatorySubjects(subjects, sc);  // 필수과목
        selectOptionalSubjects(subjects, sc);  //선택과목


        for (Subject subject :subjects){
            System.out.print(subject.getSubjectName()+" ");
        }
        System.out.println();


        Subject[] subjectList=new Subject[subjects.size()];
        for(int i=0;i<subjects.size();i++){
            subjectList[i]=subjects.get(i);
        }
        Student student = new Student(stuID, name, subjectList);
        studentList.add(student);
        System.out.println("저장 완료");
    }

    private static void selectMandatorySubjects(ArrayList<Subject> subjects, Scanner sc) {
        System.out.println("<필수 과목 목록>");
        for (SubjectList subject : SubjectList.values()) {
            if (subject.getOrder() <= 5) {
                System.out.println(subject.getOrder() + ". " + subject.getName());
            }
        }
        System.out.println("필수과목 중 최소 3개를 선택해주세요 (선택완료 시 -1 입력)");
        System.out.println();

        int cnt1=0;
        while(cnt1<=4){
            System.out.print((cnt1+1) + "번째 과목번호 : ");
            int select1 = sc.nextInt();

            if (select1 == -1){
                if(cnt1 < 3){
                    System.out.println("아직 "+cnt1+"개의 과목만 선택하셨습니다. 최소 3개는 선택해야합니다.");
                    continue;
                }
                else
                    break;
            }

            SubjectList selectedSubject = SubjectList.getSubjectByOrder(select1);
            if (selectedSubject != null) {
                subjects.add(new Subject(Integer.toString(selectedSubject.getOrder()), selectedSubject.name(), SubjectCode.MANDATORY));
                cnt1++;
            }
        }
    }

    private static void selectOptionalSubjects(ArrayList<Subject> subjects, Scanner sc) {
        System.out.println("<선택 과목 목록>");
        for (SubjectList subject : SubjectList.values()) {
            if (subject.getOrder() > 5) {
                System.out.println(subject.getOrder() + ". " + subject.getName());
            }
        }
        System.out.println("선택과목 중 최소 2개를 선택해주세요 (선택완료 시 -1 입력)");
        System.out.println();

        int cnt2=0;
        while(cnt2<=3){
            System.out.print((cnt2+1) + "번째 과목번호 : ");
            int select2 = sc.nextInt();

            if (select2 == -1){
                if(cnt2 < 2){
                    System.out.println("아직 "+cnt2+"개의 과목만 선택하셨습니다. 최소 3개는 선택해야합니다.");
                    continue;
                }
                else
                    break;
            }

            SubjectList selectedSubject = SubjectList.getSubjectByOrder(select2);
            if (selectedSubject != null) {
                subjects.add(new Subject(Integer.toString(selectedSubject.getOrder()),selectedSubject.name(), SubjectCode.CHOICE));
                cnt2++;
            }
        }
    }

    // 1-2) 수강생 삭제 (점수까지 삭제)
    public static void deleteStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("삭제하고 싶은 수강생의 번호를 입력하세요 : ");
        int studentId = sc.nextInt();
        boolean flag=false;
        for (Student student : studentList) {
            if (student.getStudentID() == studentId) {
                flag=true;
                studentList.remove(student);
            }
        }
        if(flag){
            System.out.println("수강생 삭제가 완료되었습니다.");
        }
        else{
            System.out.println("목록에 없는 번호입니다. 다시 입력하세요.");
        }
    }

    // 1-3) 수강생 점수 등록
    public static void setStudentScore() {
        Scanner sc = new Scanner(System.in);
        System.out.println("성적을 세팅하고 싶은 수강생의 번호를 입력해주세요 : ");
        int studentID = sc.nextInt();
        boolean flag=false;
        for (Student student : studentList) {
            if (student.getStudentID() == studentID) {
                flag=true;
            }
        }
        if(flag){
            inquireStudentInfo(studentID);
            System.out.println("성적을 세팅하시겠습니까? 예는 1, 아니요는 2를 입력하세요 :");
            switch(sc.nextInt()){
                case 1:
                    Student student=findStudentById(studentID);
                    StudentSubject studentSubject=new StudentSubject(student.getSubjectList(), student.getScoresList());
                    studentSubject.createSubjectRoundScore(studentList, studentID);
                    System.out.println("성적 저장 완료되었습니다.");
                    break;
                case 2:
                    System.out.println("취소 완료되었습니다.");
            }
        }
        else{
            System.out.println("리스트에 없는 학생입니다.");
        }
        setStatus(studentID);
    }

    public static Student findStudentById(int studentID) {
        for (Student student : studentList) {
            if(student.getStudentID() == studentID)
                return student;
        }
        return null;
    }



    @FunctionalInterface
    interface StudentModifier {
        void modify(Student student);
    }

    // 1-4) 수강생 정보 수정 (이름/상태)
    public static void modifyStudentInfo(int studentID) {
        if(findStudentByID(studentID)) {
            System.out.println("수정할 항목을 입력하세요");
            System.out.println("1. 이름");
            System.out.println("2. 상태");

            Scanner sc = new Scanner(System.in);
            StudentModifier modifier = null;

            switch (sc.nextInt()) {
                case 1 -> {
                    System.out.print("새로운 이름을 입력하세요 : ");
                    String newName = sc.next();
                    modifier = student -> student.setStudentName(newName);
                }
                case 2 -> {
                    System.out.print("새로운 상태를 입력하세요 : ");
                    Status newStatus = Status.valueOf(sc.next());
                    modifier = student -> student.setStudentStatus(newStatus);
                }
            }

            if (modifier != null) {
                modifyStudent(studentID, modifier);
                System.out.println("수정이 완료되었습니다.");
            } else {
                System.out.println("올바른 선택이 아닙니다.");
            }
        }

        else {
            System.out.println("존재하지 않는 번호입니다.");
        }
    }

    public static void modifyStudent(int studentID, StudentModifier modifier) {
        studentList.stream()
                .filter(student -> student.getStudentID() == studentID)
                .forEach(modifier::modify);
    }






    // 2-1) 전체 수강생 목록 조회
    public static void printAllStudentInfo(){
        studentList.stream().forEach(student -> System.out.println(student.getStudentName()+" : "+student.getStudentID()));
    }

    // 2-1) 수강생 정보 조회
    public static void inquireStudentInfo(int studentID) {
        //수강생 고유번호 전달 받음
        if(findStudentByID(studentID)){
            studentList.stream().filter(student -> student.getStudentID() == studentID)
                    .forEach(student -> {System.out.println("[학생 정보]");
                        System.out.println("이름: " + student.getStudentName());
                        System.out.println("상태: " + student.getStudentStatus());
                        System.out.println("수강 과목목록: " + student.subjectListtoString());});
        }
        else{
            System.out.println("존재하지 않는 번호입니다.");
        }
    }

    public static boolean findStudentByID(int studentID) {
        return studentList.stream().anyMatch(student -> student.getStudentID() == studentID);
    }



    // 2-2) 상태별 수강생 목록 조회
    public static void inquireStudentInfoByStatus(){
        Scanner sc = new Scanner(System.in);
        System.out.println("출력하고 싶은 학생의 상태를 입력하세요 : " );
        if(Arrays.stream(Status.values()).anyMatch(v -> v.name().equals(sc.next()))){
            String status=sc.next();
            studentList.stream().forEach(student -> {
                System.out.println(student.getScoresList().toString());
            });
        }
    }
    // 2-3)
    public static void getScores(){
        Scanner sc = new Scanner(System.in);
        System.out.println("전체 성적을 조회하고 싶은 학생의 번호를 입력하세요.");
        int studentID=sc.nextInt();
        Student student=findStudentById(studentID);
        student.getScores();
    }
    // 2-4) 특정 상태 수강생들의 필수 과목 평균 등급 조회
    public static void inquireStudentAvgScore(){
        Scanner sc = new Scanner(System.in);
        System.out.println("평균 점수를 출력하고 싶은 학생의 번호를 입력하세요 : ");
        for(Student student : studentList){
            if(student.getStudentID()==sc.nextInt()){
                StudentSubject studentSubject=new StudentSubject(student.getSubjectList(), student.getScoresList());
                for (Subject subject : student.getSubjectList()) {
                    studentSubject.inquireSubjectAverageScore(subject, student.getScoresByAray(subject));
                }
            }
        }
    }
    public static void setStatus(int studentID){
        Student student=findStudentById(studentID);
        ArrayList<Double> studentAverageScore=new ArrayList<>();
        for (Subject subject : student.getSubjectList()) {
            StudentSubject studentSubject=new StudentSubject(student.getSubjectList(), student.getScoresList());
            for(Subject subject1:student.getSubjectList()){
                studentAverageScore= studentSubject.subjectAverageScore(student.getScoresByAray(subject));
            }
        }
        for(double score : studentAverageScore){
            switch((int)score/10){
                case 10: case 9:
                    student.setStudentStatus(Status.GREEN);
                    break;
                case 8: case 7: case 6:
                    student.setStudentStatus(Status.YELLOW);
                    break;
                default: student.setStudentStatus(Status.RED);
            }
        }
    }
}
