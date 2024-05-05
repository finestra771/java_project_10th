public class Main {

    private static List<Student> studentList = new ArrayList<>();;
    private static int studentID;



    public static void main(String[] args) {




        //수강생 정보 조회
        Scanner sc = new Scanner(System.in);
        System.out.println("수강생의 고유번호를 입력해주세요 : ");
        inquireStudentInfo(sc.nextInt());





        //수강생 목록조회

        //상태별 수강생 목록조회

        //수강생 삭제

        //특정 상태 수강생들의 필수 과목 평균 등급을 조회

        //수강생 점수 목록 세팅 (점수 입력받음)
        
        // 주석
    }






    //수강생 정보 등록
    public static void addStudentInfo() {

        int stuID; //수강생 고유번호
        String name; //수강생 이름

        System.out.println("수강생 정보를 입력해주세요.");

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("고유번호 : ");
            stuID = sc.nextInt();
            boolean exist = studentList.stream().anyMatch(student -> student.getStudentID() == stuID);
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
            }

            if (select1 == -1){
                if(cnt1 < 3){
                    System.out.println("아직 "+cnt1+"개의 과목만 선택하셨습니다. 최소 3개는 선택해야합니다.");
                    continue;
                }
                else
                    break;
            }

            subjects[cnt1] = new Subject(select1, subjectName1, 과목타입: 필수과목, 과목회차:0);
            cnt1++;

        }



        System.out.println("<선택 과목 목록>");
        System.out.println("6. 디자인 패턴");
        System.out.println("7. Spring Security");
        System.out.println("8. Redis");
        System.out.println("9. MongoDB");
        System.out.println("선택과목 중 최소 2개를 선택해주세요 (선택완료 시 -1 입력)");
        System.out.println();

        int cnt2=0;
        while(cnt2<=3){
            System.out.print((cnt2+1) + "번째 과목번호 : ");
            int select2 = sc.nextInt();
            String subjectName2;

            switch (select2) {
                case 6 -> subjectName2 = "디자인 패턴";
                case 7 -> subjectName2 = "Spring Security";
                case 8 -> subjectName2 = "Redis";
                case 9 -> subjectName2 = "MongoDB";

            }

            if (select2 == -1){
                if(cnt2 < 2){
                    System.out.println("아직 "+cnt2+"개의 과목만 선택하셨습니다. 최소 2개는 선택해야합니다.");
                    continue;
                }
                else
                    break;
            }

            subjects[cnt1+cnt2] = new Subject(select2, subjectName1, 과목타입: 선택과목, 과목회차:0);
            cnt2++;

        }


        Student student = new Student(stuID, name, subjects);
        studentList.add(student);
    }




    //수강생 정보 조회
    private static void inquireStudentInfo(int studentID) {  //수강생 고유번호 전달 받음
        studentList.stream().filter(student -> student.getStudentID() == studentID)
                .forEach(student -> {System.out.println("[학생 정보]");
                        System.out.println("이름: " + student.getStudentName());
                        System.out.println("상태: " + student.getStudentStatus());
                        System.out.println("수강 과목목록: " + student.getSubjectList()});
    }



    //수강생 정보 수정
    private static void modifyStudentInfo(int studentID) {
        System.out.println("수정할 항목을 입력하세요");
        System.out.println("1. 이름");
        System.out.println("2. 상태");

        Scanner sc = new Scanner(System.in);
        switch(sc.nextInt()){
            case 1 -> {
                System.out.print("새로운 이름을 입력하세요 : ");
                String newName = sc.next();
                modifyStudentName(newName);
            }
            case 2 -> {
                System.out.print("새로운 상태를 입력하세요 : ");
                String newStatus = sc.next();
                modifyStudentStatus(newStatus);
            }
        }
        System.out.println("수정이 완료되었습니다.");

    }

}