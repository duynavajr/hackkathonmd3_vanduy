package ra.service;

import ra.model.Mark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MarkService {

    private static List<Mark> marks;

    private  int markId;
    private double newPoint;
    private int subjectId;

    public MarkService() {
        this.marks = new ArrayList<>();
    }

    // Phương thức thêm mới điểm thi cho 1 sinh viên
    public void addMark(Mark mark) {
        marks.add(mark);
        System.out.println("Thêm diem thành công.");
    }

    // Phương thức hiển thị danh sách tất cả điểm thi theo thứ tự điểm tăng dần
    public void displayAllMarksSortedByPoint() {
        if (marks.isEmpty()) {
            System.out.println("Danh sách điểm trống.");
            return;
        }

        Collections.sort(marks, Comparator.comparingDouble(Mark::getPoint));
        for (Mark mark : marks) {
            mark.displayData();
        }
    }

    // Phương thức thay đổi điểm theo mã id
    public void updateMarkById() {

        for (Mark mark : marks) {
            if (mark.getMarkId() == markId) {
                mark.setPoint(newPoint);
                System.out.println("Đã cập nhật điểm thành công.");
                return;
            }
        }
        System.out.println("Không tìm thấy điểm với mã ID: " + markId);
    }

    // Phương thức xóa điểm theo mã id
    public void deleteMarkById() {
        marks.removeIf(mark -> mark.getMarkId() == markId);
        System.out.println("Đã xóa điểm thành công.");
    }

    // Phương thức hiển thị danh sách điểm thi theo mã môn học
    public void displayMarksBySubjectId() {
        boolean found = false;
        for (Mark mark : marks) {
            if (mark != null && mark.getSubject() != null ) {
                mark.displayData(); // Giả sử có phương thức displayData() trong lớp Mark
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy điểm với mã môn học: " + subjectId);
        }
    }

    public void displayAcademiBySubjectId() {
        boolean found = false;
        for (Mark mark : marks) {
            if (mark != null && mark.getSubject() != null) {
                double point = mark.getPoint();
                String studentName = mark.getStudent().getStudentName(); // Giả sử có phương thức getName() trong lớp Student
                String academicEvaluation = evaluateAcademic(point);
                System.out.println("Học sinh: " + studentName + ", Điểm: " + point + ", Đánh giá: " + academicEvaluation);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy điểm với mã môn học: " + subjectId);
        }
    }

    // Phương thức hiển thị đánh giá học lực của từng học sinh theo mã môn học


    // Phương thức đánh giá học lực dựa trên điểm số
    private String evaluateAcademic(double point) {
        if (point >= 9.0) {
            return "Xuất sắc";
        } else if (point >= 8.0) {
            return "Giỏi";
        } else if (point >= 7.0) {
            return "Khá";
        } else if (point >= 5.0) {
            return "Trung bình";
        } else {
            return "Yếu";
        }
    }

}
