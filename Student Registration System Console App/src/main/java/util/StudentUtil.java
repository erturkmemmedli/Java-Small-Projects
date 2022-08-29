/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import beans.Student;
import main.Config;

/**
 *
 * @author Erturk Memmedli
 */
public class StudentUtil {

    public static Student fillStudent() {
        String name = MenuUtil.requireName();
        String surname = MenuUtil.requireSurname();
        int age = MenuUtil.requireAge();
        String className = MenuUtil.requireClassName();
        return new Student(name, surname, age, className);
    }

    public static void printAllRegisteredStudents() {
        if (Config.students == null) {
            return;
        }
        for (int i = 0; i < Config.students.length; i++) {
            Student st = Config.students[i];
            System.out.println((i + 1) + " " + st.getFullInfo());
        }
    }

    public static void registerStudents() {
        int count = InputUtil.requireNumber("How many students will be registered?");
        Config.students = new Student[count];

        for (int i = 0; i < count; i++) {
            System.out.println("Register No " + (i + 1));
            Config.students[i] = StudentUtil.fillStudent();
        }

        System.out.println("Registration completed successfully!");
        StudentUtil.printAllRegisteredStudents();
    }

    public static void findStudents() {
        String text = InputUtil.requireText("Enter name, surname, or class name");
        Student[] result = findStudentsArray(text);
        for (int i = 0; i < result.length; i++) {
            Student s = result[i];
            System.out.println(s.getFullInfo());
        }
    }

    public static Student[] findStudentsArray(String text) {
        int count = 0;
        for (int i = 0; i < Config.students.length; i++) {
            Student st = Config.students[i];
            if (st.getName().contains(text) || st.getSurname().contains(text) || st.getClassName().contains(text)) {
                count++;
            }
        }
        Student[] result = new Student[count];
        int found = 0;
        for (int i = 0; i < Config.students.length; i++) {
            Student st = Config.students[i];
            if (st.getName().contains(text) || st.getSurname().contains(text) || st.getClassName().contains(text)) {
                result[found++] = st;
            }
        }
        return result;
    }

    public static void updateStudentWithNewObject() {
        StudentUtil.printAllRegisteredStudents();
        int i = InputUtil.requireNumber("Which person should be updated?");
        System.out.println("Enter new data");
        Student s = StudentUtil.fillStudent();
        Config.students[i - 1] = s;
    }

    public static void updateStudentWithSameObject() {
        StudentUtil.printAllRegisteredStudents();
        int i = InputUtil.requireNumber("Which person should be updated?");
        System.out.println("Enter new data");
        Student selectedStudent = Config.students[i - 1];
        String changeParams = InputUtil.requireText("Which parts to be changed? For example: 'name', 'surname', etc.");
        if (changeParams.contains("'name'")) {
            selectedStudent.setName(MenuUtil.requireName());
        }
        if (changeParams.contains("'surname'")) {
            selectedStudent.setSurname(MenuUtil.requireSurname());
        }
        if (changeParams.contains("'age'")) {
            selectedStudent.setAge(MenuUtil.requireAge());
        }
        if (changeParams.contains("'classname'")) {
            selectedStudent.setClassName(MenuUtil.requireClassName());
        }
    }

    public static void updateStudentWithSameObjectBySplit() {
        StudentUtil.printAllRegisteredStudents();
        int index = InputUtil.requireNumber("Which person should be updated?");
        System.out.println("Enter new data");
        Student selectedStudent = Config.students[index - 1];
        String changeParams = InputUtil.requireText("Which parts to be changed? For example: name, surname, etc.");
        String[] parameters = changeParams.split(",");
        for (int i = 0; i < parameters.length; i++) {
            String p = parameters[i];
            if (p.equalsIgnoreCase("name")) {
                selectedStudent.setName(MenuUtil.requireName());
            } else if (p.equalsIgnoreCase("surname")) {
                selectedStudent.setSurname(MenuUtil.requireSurname());
            } else if (p.equalsIgnoreCase("age")) {
                selectedStudent.setAge(MenuUtil.requireAge());
            } else if (p.equalsIgnoreCase("class name")) {
                selectedStudent.setClassName(MenuUtil.requireClassName());
            }
        }
    }
}
