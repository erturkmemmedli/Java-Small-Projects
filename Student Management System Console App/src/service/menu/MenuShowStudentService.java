package service.menu;

import bean.Config;
import bean.Student;
import service.inter.menu.MenuShowStudentServiceInter;

public class MenuShowStudentService implements MenuShowStudentServiceInter {
    @Override
    public void processLogic() {
        Student[] allStudents = Config.instance().getStudents();
        for (int i = 0; i < allStudents.length; i++) {
            System.out.println(allStudents[i]);
        }
    }
}
