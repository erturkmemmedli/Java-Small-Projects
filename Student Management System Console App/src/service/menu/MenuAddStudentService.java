package service.menu;

import bean.Config;
import bean.Student;
import service.inter.menu.MenuAddStudentServiceInter;
import java.util.Scanner;

public class MenuAddStudentService implements MenuAddStudentServiceInter {
    @Override
    public void processLogic() {
        Scanner sc1 = new Scanner(System.in);
        System.out.println("enter student's name");
        String name = sc1.nextLine();

        Scanner sc2 = new Scanner(System.in);
        System.out.println("enter student's surname");
        String surname = sc2.nextLine();

        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);

        Config.instance().appendStudent(student);
        System.out.println("student added");
    }

}
