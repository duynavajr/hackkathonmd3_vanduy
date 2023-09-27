package ra.service;

import ra.model.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StudentService {
    private List<Student> students;
    private Scanner scanner;

    public StudentService() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addStudent() {
        System.out.println("Nhập thông tin học sinh mới:");

        String studentName = getStringInput("Tên học sinh: ");
        Date birthDay = getDateInput("Ngày sinh (dd/MM/yyyy): ");
        String address = getStringInput("Địa chỉ: ");
        boolean gender = getGenderInput("Giới tính (Nam/Nữ): ");
        String phone = getStringInput("Số điện thoại (10 hoặc 11 số, bắt đầu bằng số 0): ");

        int newStudentId = generateStudentId();
        Student newStudent = new Student(newStudentId, studentName, birthDay, address, gender, phone);
        addStudentToList(newStudent);
    }
    private int generateStudentId() {

        int maxId = 0;
        for (Student student : students) {
            if (student.getStudentId() > maxId) {
                maxId = student.getStudentId();
            }
        }
        return maxId + 1;
    }


    public void addStudentToList(Student student) {
        students.add(student);
        System.out.println("Thêm học sinh thành công.");
    }
    public void updateStudent() {
        int studentId = getIntInput("Nhập mã học sinh cần sửa: ");
        Student existingStudent = findStudentById(studentId);
        if (existingStudent != null) {
            System.out.println("Thông tin học sinh:");
            existingStudent.displayData();
            System.out.println("Nhập thông tin mới:");

            String studentName = getStringInput("Tên học sinh: ");
            Date birthDay = getDateInput("Ngày sinh (dd/MM/yyyy): ");
            String address = getStringInput("Địa chỉ: ");
            boolean gender = getGenderInput("Giới tính (Nam/Nữ): ");
            String phone = getStringInput("Số điện thoại (10 hoặc 11 số, bắt đầu bằng số 0): ");

            // Cập nhật thông tin học sinh
            existingStudent.setStudentName(studentName);
            existingStudent.setBirthDay(birthDay);
            existingStudent.setAddress(address);
            existingStudent.setGender(gender);
            existingStudent.setPhone(phone);

            System.out.println("Sửa thông tin học sinh thành công.");
        } else {
            System.out.println("Không tìm thấy học sinh có mã " + studentId);
        }
    }

    public void deleteStudent() {
        int studentId = getIntInput("Nhập mã học sinh cần xóa: ");
        Student existingStudent = findStudentById(studentId);
        if (existingStudent != null) {
            students.remove(existingStudent);
            System.out.println("Xóa học sinh thành công.");
        } else {
            System.out.println("Không tìm thấy học sinh có mã " + studentId);
        }
    }

    public List<Student> searchStudents() {
        String name = getStringInput("Nhập tên học sinh cần tìm: ");
        List<Student> matchingStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getStudentName().equalsIgnoreCase(name)) {
                matchingStudents.add(student);
            }
        }

        if (!matchingStudents.isEmpty()) {
            System.out.println("Kết quả tìm kiếm:");
            for (Student student : matchingStudents) {
                student.displayData();
            }
        } else {
            System.out.println("Không tìm thấy học sinh có tên " + name);
        }

        return matchingStudents;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    private int getIntInput(String prompt) {
        int input = -1;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên.");
            }
        }
        return input;
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private Date getDateInput(String prompt) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        Date date = null;
        boolean validDate = false;
        while (!validDate) {
            try {
                System.out.print(prompt);
                date = dateFormat.parse(scanner.nextLine());
                validDate = true;
            } catch (ParseException e) {
                System.out.println("Định dạng ngày không hợp lệ. Sử dụng định dạng dd/MM/yyyy.");
            }
        }
        return date;
    }

    private boolean getGenderInput(String prompt) {
        String genderStr = getStringInput(prompt);
        return genderStr.equalsIgnoreCase("Nam");
    }

    public Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }
    public void displayAllStudents() {
        if (!students.isEmpty()) {
            System.out.println("Danh sách học sinh:");
            for (Student student : students) {
                student.displayData();
            }
        } else {
            System.out.println("Danh sách học sinh trống.");
        }
    }
}
