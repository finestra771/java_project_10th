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

        selectSubjects(subjects, sc, SubjectCode.MANDATORY);  // 필수과목
        selectSubjects(subjects, sc, SubjectCode.CHOICE);  //선택과목

        System.out.println();
        System.out.print("수강 과목 목록 : ");
        for (Subject subject :subjects){
            System.out.print(subject.getSubjectName()+" ");
        }
        System.out.println();

        Subject[] subjectList = new Subject[subjects.size()];
        for(int i=0;i<subjects.size();i++){
            subjectList[i]=subjects.get(i);
        }

        studentList.add(new Student(stuID, name, subjectList));
        System.out.println("저장 완료");
        System.out.println();
    }

    // 과목 선택 메소드 (필수/선택에 따라)
    private static void selectSubjects(ArrayList<Subject> subjects, Scanner sc, SubjectCode subjectCode) {
        String subjectType = (subjectCode == SubjectCode.MANDATORY) ? "필수" : "선택";
        System.out.println();
        System.out.println("<" + subjectType + " 과목 목록>");
        for (SubjectList subject : SubjectList.values()) {
            if ((subjectCode == SubjectCode.MANDATORY && subject.getOrder() <= 5) ||
                    (subjectCode == SubjectCode.CHOICE && subject.getOrder() > 5)) {
                System.out.println(subject.getOrder() + ". " + subject.getName());
            }
        }
        int minSelections = (subjectCode == SubjectCode.MANDATORY) ? 3 : 2;
        System.out.println(subjectType + "과목 중 최소 " + minSelections + "개를 선택해주세요 (선택완료 시 -1 입력)");
        System.out.println();

        int cnt = 0;
        ArrayList<Integer> selectedNumbers = new ArrayList<>();
        while (cnt <= (minSelections+1)) {
            System.out.print((cnt + 1) + "번째 과목번호 : ");
            int select = sc.nextInt();
            if (select == -1) {
                if (cnt < minSelections) {
                    System.out.println("아직 " + cnt + "개의 과목만 선택하셨습니다. 최소 " + minSelections + "개는 선택해야합니다.");
                    continue;
                }
                else {
                    break;
                }
            }

            if (!isValidSubjectNumber(select, subjectCode)) {
                System.out.println("선택지에 없는 과목 번호입니다. 다시 입력해주세요.");
                continue;
            }

            if (selectedNumbers.contains(select)) {
                System.out.println("이미 선택한 과목 번호입니다. 다시 입력해주세요.");
                continue;
            }

            SubjectList selectedSubject = SubjectList.getSubjectByOrder(select);
            if (selectedSubject != null) {
                subjects.add(new Subject(selectedSubject.getOrder(), selectedSubject.name(), subjectCode));
                selectedNumbers.add(select);
                cnt++;
            }
        }
    }

    // 유효한 과목 넘버인지 확인
    private static boolean isValidSubjectNumber(int select, SubjectCode subjectCode) {
        return (subjectCode == SubjectCode.MANDATORY && select >= 1 && select <= 5) ||
                (subjectCode == SubjectCode.CHOICE && select >= 6 && select <= 9);
    }

    // 1-2) 수강생 삭제 (점수까지 삭제)
    public static void deleteStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.print("삭제하고 싶은 수강생의 번호를 입력하세요 : ");
        int studentId = sc.nextInt();
        boolean flag=false;
        for (int i=0;i<studentList.size();i++) {
            Student student = studentList.get(i);
            if (student.getStudentID() == studentId) {
                flag=true;
                studentList.remove(student);
            }
        }
        if(flag){
            System.out.println("수강생 삭제가 완료되었습니다.\n");
        }
        else{
            System.out.println("목록에 없는 번호입니다. 다시 입력하세요.\n");
        }
    }

    // 1-3) 수강생 점수 등록
    public static void setStudentScore() {
        Scanner sc = new Scanner(System.in);
        System.out.print("성적을 세팅하고 싶은 수강생의 번호를 입력해주세요 : ");
        int studentID = sc.nextInt();
        boolean flag=false;
        for (Student student : studentList) {
            if (student.getStudentID() == studentID) {
                flag=true;
            }
        }
        if(flag){
            inquireStudentInfo(studentID);
            System.out.println("성적을 세팅하시겠습니까?");
            System.out.print("예는 1, 아니요는 2를 입력하세요 : ");
            switch(sc.nextInt()){
                case 1:
                    Student student=findStudentById(studentID);
                    StudentSubject studentSubject=new StudentSubject(student.getSubjectList(), student.getScoresList());
                    studentSubject.createSubjectRoundScore(studentList, studentID);
                    System.out.println("성적 저장 완료되었습니다.\n");
                    setStatus(studentID);
                    break;
                case 2:
                    System.out.println("취소 완료되었습니다.\n");
            }
        }
        else{
            System.out.println("리스트에 없는 학생입니다.\n");
        }
    }

    // 성적에 따른 학생의 상태 설정
    public static void setStatus(int studentID){
        Student student=findStudentById(studentID);
        ArrayList<Double> studentAverageScore=new ArrayList<>();
        for (Subject subject : student.getSubjectList()) {
            StudentSubject studentSubject=new StudentSubject(student.getSubjectList(), student.getScoresList());
            for(Subject subject1:student.getSubjectList()){
                studentAverageScore= studentSubject.subjectAverageScore(student.getScoresByArray(subject));
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

    // 학생리스트에서 해당 아이디를 가진 학생 객체 반환
    public static Student findStudentById(int studentID) {
        for (Student student : studentList) {
            if(student.getStudentID() == studentID)
                return student;
        }
        return null;
    }

    //함수형 인터페이스
    @FunctionalInterface
    interface StudentModifier {
        void modify(Student student);
    }

    // 1-4) 수강생 정보 수정 (이름/상태)
    public static void modifyStudentInfo(int studentID) {
        if(findStudentByID(studentID)) {
            inquireStudentInfo(studentID);
            System.out.println("수정할 항목의 번호를 입력하세요");
            System.out.println("1. 이름");
            System.out.println("2. 상태");
            System.out.println("3. 성적");
            System.out.println("4. 과목");

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
                case 3 ->{
                    System.out.print("새로운 성적의 과목명을 입력해주세요 : ");
                    SubjectList subjectName=SubjectList.valueOf(sc.next());
                    System.out.print("새로운 성적의 회차를 입력해주세요 : ");
                    int subjectRound=sc.nextInt();
                    System.out.print("새로운 성적을 입력해주세요 : ");
                    int newScoreInt=sc.nextInt();
                    modifier = student -> student.setScoreListOne(newScoreInt, subjectRound-1, subjectName);
                }
                case 4 -> {
                    System.out.println("교체할 과목명을 입력하세요.");
                    String oldName = sc.next();
                    System.out.println("새로운 과목명을 입력하세요.");
                    String newName = sc.next();
                    Student student= findStudentById(studentID);
                    int index=0;
                    for(Subject subject: student.getSubjectList()){
                        if(subject.getSubjectName().equals(oldName)){
                            break;
                        }
                        index++;
                    }
                    Subject studentSubject=student.getSubjectList()[index];
                    modifier = student1 -> studentSubject.updateSubjectName(oldName, newName);
                }
            }

            if (modifier != null) {
                modifyStudent(studentID, modifier);
                System.out.println("수정이 완료되었습니다.\n");
            } else {
                System.out.println("올바른 선택이 아닙니다.\n");
            }
        }

        else {
            System.out.println("존재하지 않는 번호입니다.\n");
        }
    }

    // 해당 아이디를 가진 학생 찾아 정보 수정
    public static void modifyStudent(int studentID, StudentModifier modifier) {
        studentList.stream()
                .filter(student -> student.getStudentID() == studentID)
                .forEach(modifier::modify);
    }


    // 2-1) 전체 수강생 목록 조회
    public static void printAllStudentInfo(){
        System.out.println("<전체 수강생 목록>");
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
                        System.out.println("수강 과목 목록: " + student.subjectListtoString());
                        System.out.println();});
        }
        else{
            System.out.println("존재하지 않는 번호입니다.\n");
        }
    }

    // 학생 리스트에 해당 아이디를 가진 학생 있는지 확인
    public static boolean findStudentByID(int studentID) {
        return studentList.stream().anyMatch(student -> student.getStudentID() == studentID);
    }

    // 2-2) 상태별 수강생 목록 조회
    public static void inquireStudentInfoByStatus(){
        Scanner sc = new Scanner(System.in);
        System.out.print("출력하고 싶은 학생의 상태를 입력하세요 : " );
        Status status=Status.valueOf(sc.next());
        for (Student student : studentList) {
            if(student.getStudentStatus()==status){
                System.out.println(student.getStudentName()+" : "+student.getStudentID());
            }
        }
    }

    // 2-3) 특정 수강생의 전체 성적 조회
    public static void getScores(){
        Scanner sc = new Scanner(System.in);
        System.out.print("전체 성적을 출력하고 싶은 학생의 번호를 입력해주세요 : ");
        int studentID=sc.nextInt();
        Student student=findStudentById(studentID);
        if(student!=null){
            System.out.println(student.getScores());
        }
        else{
            System.out.println("데이터베이스에 없는 학생입니다.\n");
        }
    }

    // 2-4) 특정 상태 수강생들의 필수 과목 평균 등급 조회
    public static void inquireStudentAvgScore(){
        Scanner sc = new Scanner(System.in);
        System.out.print("평균 점수를 출력하고 싶은 학생의 번호를 입력하세요 : ");
        for(Student student : studentList){
            if(student.getStudentID()==sc.nextInt()){
                StudentSubject studentSubject=new StudentSubject(student.getSubjectList(), student.getScoresList());
                for (Subject subject : student.getSubjectList()) {
                    studentSubject.inquireSubjectAverageScore(subject, student.getScoresByArray(subject));
                }
            }
        }
    }

    // 2-5) 특정 상태 수강생들의 필수 과목 평균 등급 조회
    public static void inquireStudentAvgByStatus(){
        Scanner sc = new Scanner(System.in);
        System.out.print("필수 과목 평균 등급을 조회하고 싶은 상태를 입력하세요 (RED / YELLOW / GREEN) : ");
        Status studentStatus=Status.valueOf(sc.next());
        inquireStudentInfoByStatus(studentStatus);
    }

    // 해당 상태 수강생들의 필수 과목 평균 등급 조회
    public static void inquireStudentInfoByStatus(Status studentStatus){
        for (Student student : studentList) {
            if(student.getStudentStatus()!=null){
                if (student.getStudentStatus().equals(studentStatus)) {
                    System.out.println(student.getStudentName() + " : " + student.getStudentID());
                    Score[][] scoresList = student.getScoresList();
                    StudentSubject subject = new StudentSubject(student.getSubjectList(), scoresList);
                    for (Score[] scores : scoresList) {
                        ArrayList<Score> scoresArrayList = new ArrayList<>(Arrays.asList(scores));
                        scoresArrayList.removeIf(score -> score.getSubjectCode() != SubjectCode.MANDATORY);
                        if (!scoresArrayList.isEmpty()) {
                            ArrayList<Double> averageScore = subject.mandatorySubjectAverageScore(scoresArrayList);
                            System.out.println(scores[0].getSubjectName() + " " + averageScore);
                        }
                    }
                }
            }
        }
    }
}
