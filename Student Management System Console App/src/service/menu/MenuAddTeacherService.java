package service.menu;

import bean.Config;
import bean.Teacher;
import service.inter.menu.MenuAddTeacherServiceInter;

import java.util.Scanner;

public class MenuAddTeacherService implements MenuAddTeacherServiceInter {
    @Override
    public void processLogic() {
        Scanner sc1 = new Scanner(System.in);
        System.out.println("enter teacher's name");
        String name = sc1.nextLine();

        Scanner sc2 = new Scanner(System.in);
        System.out.println("enter teacher's surname");
        String surname = sc2.nextLine();

        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setSurname(surname);

        Config.instance().appendTeacher(teacher);
        System.out.println("teacher added");
    }
}
