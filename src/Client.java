import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {
    private static List<Student> studentList = new ArrayList<>();

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



        System.out.println("<필수 과목 목록>");
        System.out.println("1. Java");
        System.out.println("2. 객체지향");
        System.out.println("3. Spring");
        System.out.println("4. JPA");
        System.out.println("5. MySQL");
        System.out.println("필수과목 중 최소 3개를 선택해주세요 (선택완료 시 -1 입력)");
        System.out.println();

        int cnt1=0;
        while(cnt1<=4){
            System.out.print((cnt1+1) + "번째 과목번호 : ");
            int select1 = sc.nextInt();
            String subjectName1;

            switch (select1) {
                case 1 -> subjectName1 = "Java";
                case 2 -> subjectName1 = "객체지향";
                case 3 -> subjectName1 = "Spring";
                case 4 -> subjectName1 = "JPA";
                case 5 -> subjectName1 = "MySQL";
                default -> subjectName1 = null;
            }

            if (select1 == -1){
                if(cnt1 < 3){
                    System.out.println("아직 "+cnt1+"개의 과목만 선택하셨습니다. 최소 3개는 선택해야합니다.");
                    continue;
                }
                else
                    break;
            }

            if(subjectName1!=null){
                subjects.add(new Subject(select1, subjectName1,"Mandatory"));
                cnt1++;
            }
            for (Subject subject :subjects){
                System.out.print(subject.getSubjectName()+" ");
            }
            System.out.println();
        }



        System.out.println("<선택 과목 목록>");
        System.out.println("6. 디자인 패턴");
        System.out.println("7. Spring Security");
        System.out.println("8. Redis");
        System.out.println("9. MongoDB");
        System.out.println("선택과목 중 최소 2개를 선택해주세요 (선택완료 시 -1 입력)");
        System.out.println();

        int cnt2=0;
        while(cnt2<=4){
            System.out.print((cnt2+1) + "번째 과목번호 : ");
            int select2 = sc.nextInt();
            String subjectName2;

            switch (select2) {
                case 6 -> subjectName2 = "디자인 패턴";
                case 7 -> subjectName2 = "Spring Security";
                case 8 -> subjectName2 = "Redis";
                case 9 -> subjectName2 = "MongoDB";
                default -> subjectName2 = null;
            }
            if (select2 == -1){
                if(cnt2< 2){
                    System.out.println("아직 "+cnt1+"개의 과목만 선택하셨습니다. 최소 3개는 선택해야합니다.");
                    continue;
                }
                else
                    break;
            }

            if(subjectName2!=null){
                subjects.add(new Subject(select2, subjectName2,"ELECTIVE"));
                cnt2++;
            }
            for (Subject subject :subjects){
                System.out.print(subject.getSubjectName()+" ");
            }
            System.out.println();
        }

        Subject[] subjectList=new Subject[subjects.size()];
        for(int i=0;i<subjects.size();i++){
            subjectList[i]=subjects.get(i);
        }
        Student student = new Student(stuID, name, subjectList);
        studentList.add(student);
        System.out.println("저장 완료");
    }

    public static void printAllStudentInfo(){
        studentList.stream().forEach(student -> System.out.println(student.getStudentName()+" : "+student.getStudentID()));
    }


    //수강생 정보 조회
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


    //수강생 정보 수정
    public static void modifyStudentInfo(int studentID) {
        if(findStudentByID(studentID)) {
            System.out.println("수정할 항목을 입력하세요");
            System.out.println("1. 이름");
            System.out.println("2. 상태");

            Scanner sc = new Scanner(System.in);
            switch (sc.nextInt()) {
                case 1 -> {
                    System.out.print("학생의 아이디를 입력하세요 : ");
                    String studentId = sc.next();
                    System.out.print("새로운 이름을 입력하세요 : ");
                    String newName = sc.next();
                    modifyStudentName(studentID, newName);
                }
                case 2 -> {
                    System.out.print("학생의 아이디를 입력하세요 : ");
                    String studentId = sc.next();
                    System.out.print("새로운 상태를 입력하세요 : ");
                    Status newStatus = Status.valueOf(sc.next());
                    modifyStudentStatus(studentID, newStatus);
                }
            }
            System.out.println("수정이 완료되었습니다.");
        }
        else{
            System.out.println("존재하지 않는 번호입니다.");
        }
    }
    public static void modifyStudentName(int studentID, String newName) {
        studentList.stream().filter(student -> student.getStudentID() == studentID)
                .forEach(student -> {
                    if (student.getStudentName().equals(newName)) {
                        student.setStudentName(newName);
                    }
                });
    }
    public static void modifyStudentStatus(int studentID, Status newStatus) {
        studentList.stream().filter(student -> student.getStudentID() == studentID)
                .forEach(student -> {
                    if (student.getStudentStatus().equals(newStatus)) {
                        student.setStudentStatus(newStatus);
                    }
                });
    }
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
                    System.out.println("성적 저장 완료되었습니다.");
                    break;
                case 2:
                    System.out.println("취소 완료되었습니다.");
            }
        }
        else{
            System.out.println("리스트에 없는 학생입니다.");
        }
    }
    public static Student findStudentById(int studentScore) {
        for (Student student : studentList) {
            if(student.getStudentID() == studentScore)
                return student;
        }
        return null;
    }
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
    public static void inquireStudentAvgScore(){
        Scanner sc = new Scanner(System.in);
        System.out.println("평균 점수를 출력하고 싶은 학생의 번호를 입력하세요 : ");
        for(Student student : studentList){
            if(student.getStudentID()==sc.nextInt()){
                System.out.println(student.getScores());
            }
        }
    }
}
