package org.qmbupt.grp88.Entity;

import java.util.Date;

public class Course {
    public String Course_name;
    public String Course_Number;
    public double credit;
    public double grade;
    public String Teacher[];
    public Date date;
    public boolean isCompulsory;
    public int semester;

    public String[] getTeacher() {
        return Teacher;
    }

    public void setTeacher(String[] teacher) {
        Teacher = teacher;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCourse_name() {
        return Course_name;
    }

    public void setName(String Course_name) {
        this.Course_name = Course_name;
    }

    public String getNumber() {
        return Course_Number;
    }

    public void setNumber(String number) {
        Course_Number = number;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void setSemester(int semester) { this.semester = semester; }

    public int getSemester() { return semester; }

    public Course(String Course_name, String Course_Number, double credit){
        this.setName(Course_name);
        this.setNumber(Course_Number);
        this.setCredit(credit);
        this.setGrade(0);
    }
}
