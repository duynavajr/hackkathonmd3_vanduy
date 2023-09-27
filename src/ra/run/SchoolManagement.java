package ra.run;

import ra.model.Mark;
import ra.model.Student;
import ra.service.MarkService;
import ra.service.StudentService;
import ra.service.SubjectService;

import java.util.Scanner;

public class SchoolManagement {
    private static StudentService studentService = new StudentService();
    private static SubjectService subjectService = new SubjectService();
    private static MarkService markService = new MarkService();

    public static void main(String[] args) {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            System.out.println("************************STUDENT-MANAGEMENT*************************");
            System.out.println("1. Quản lý học sinh");
            System.out.println("2. Quản lý môn học");
            System.out.println("3. Quản lí điểm thi");
            System.out.println("4. Thoát");
            System.out.print("Chọn 1, 2, 3, hoặc 4: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự Enter

            switch (choice) {
                case 1:
                    manageStudents();
                    break;
                case 2:
                    subjectMenu();
                    break;
                case 3:
                    markMenu();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thoát khỏi chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
                    break;
            }
        }
    }

    private static void manageStudents() {
        boolean backToMainMenu = false;
        Scanner scanner = new Scanner(System.in);

        while (!backToMainMenu) {
            System.out.println("****************** Quản lý học sinh ******************");
            System.out.println("1. Thêm học sinh");
            System.out.println("2. Sửa thông tin học sinh");
            System.out.println("3. Xóa học sinh");
            System.out.println("4. Tìm kiếm học sinh");
            System.out.println("5. Hiển thị danh sách học sinh");
            System.out.println("6. Quay lại menu chính");
            System.out.print("Chọn 1, 2, 3, 4, 5 hoặc 6: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự Enter

            switch (choice) {
                case 1:
                    studentService.addStudent();
                    break;
                case 2:
                    studentService.updateStudent();
                    break;
                case 3:
                    studentService.deleteStudent();
                    break;
                case 4:
                    studentService.searchStudents();
                    break;
                case 5:
                    studentService.displayAllStudents();
                    break;
                case 6:
                    backToMainMenu = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
                    break;
            }
        }
    }

    private static void subjectMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("**********************SUBJECT-MANAGEMENT*************************");
            System.out.println("1. Thêm mới môn học");
            System.out.println("2. Hiển thị danh sách tất cả môn học đã lưu trữ");
            System.out.println("3. Thay đổi thông tin môn học theo mã id");
            System.out.println("4. Xóa môn học theo mã id (kiểm tra điểm thi)");
            System.out.println("5. Thoát");
            System.out.print("Chọn 1, 2, 3, 4 hoặc 5: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự Enter

            switch (choice) {
                case 1:
                    subjectService.addSubject();
                    break;
                case 2:
                    subjectService.displayAllSubjects();
                    break;
                case 3:
                    subjectService.editSubject(scanner.nextLine());
                    break;
                case 4:
                    subjectService.deleteSubject(scanner.nextLine());
                    break;
                case 5:
                    return; // Quay lại menu trước đó
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        }
    }

    private static void markMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean backMenu = false;

        while (!backMenu) {
            System.out.println("**********************MARK-MANAGEMENT*************************");
            System.out.println("1. Thêm mới điểm thi cho sinh viên");
            System.out.println("2. Hiển thị danh sách tất cả điểm thi đã lưu trữ");
            System.out.println("3. Thay đổi điểm thi theo mã id");
            System.out.println("4. Xóa điểm thi theo mã id");
            System.out.println("5. Hiển thị điểm thi theo mã môn học");
            System.out.println("6. Đánh giá học lực của sinh viên theo mã môn học");
            System.out.println("7. Thoát");
            System.out.print("Chọn 1, 2, 3, 4, 5, 6, hoặc 7: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự Enter

            switch (choice) {
                case 1:
                    markService.addMark(new Mark());
                    break;
                case 2:
                    markService.displayAllMarksSortedByPoint();
                    break;
                case 3:
                    markService.updateMarkById();
                    break;
                case 4:
                    markService.deleteMarkById();
                    break;
                case 5:
                    markService.displayMarksBySubjectId();
                    break;
                case 6:
                    markService.displayAcademiBySubjectId();
                    break;
                case 7:
                    backMenu = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
                    break;
            }
        }
    }
}
