/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Erturk Memmedli
 */
public class MenuUtil {

    public static String menu_introduction
            = "What do you want to do?\n"
            + "1. Register student\n"
            + "2. Show all students\n"
            + "3. Find student\n"
            + "4. Update student";

    public static void processMenu(int menu) {
        switch (menu) {
            case 1:
                StudentUtil.registerStudents();
                break;
            case 2:
                StudentUtil.printAllRegisteredStudents();
                break;
            case 3:
                StudentUtil.findStudents();
                break;
            case 4:
                StudentUtil.updateStudentWithSameObjectBySplit();
                break;
            default:
                break;
        }
    }

    public static String requireName() {
        return InputUtil.requireText("Enter name");
    }

    public static String requireSurname() {
        return InputUtil.requireText("Enter surname");
    }

    public static String requireClassName() {
        return InputUtil.requireText("Enter class");
    }

    public static int requireAge() {
        return InputUtil.requireNumber("Enter age");
    }

}
