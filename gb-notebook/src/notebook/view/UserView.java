package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com;



        while (true) {
            String command = prompt("������� �������: ");
            boolean done = false;
            while(!done){
                try {
                    com = Commands.valueOf(command);
                    done = true;
                } catch (IllegalArgumentException ex){
                    System.out.println("Wrong entry");
                    command = prompt("������� �������: ");
                }
            }

            com = Commands.valueOf(command);

            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    String firstName = prompt("���: ");
                    String lastName = prompt("�������: ");
                    String phone = prompt("����� ��������: ");
                    userController.saveUser(new User(firstName, lastName, phone));
                    break;
                case READ:
                    String id = prompt("������������� ������������: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case LIST:
                    try {
                        List <User> userList = userController.readAllUsers();
                        System.out.println(userList);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case UPDATE:
                    try {
                        Long uid = Long.parseLong(prompt("������������� ������������: "));
                        String frstName = prompt("������� ����� ���: ");
                        String lstName = prompt("������� ����� �������: ");
                        String phoneNum = prompt("������� ����� ����� ��������: ");
                        User updatedUser = new User(uid, frstName, lstName, phoneNum);
                        userController.updateUser(uid, updatedUser);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case DELETE:
                    try {
                        Long usId = Long.parseLong(prompt("������������� ������������: "));
                        userController.deleteUser(usId);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case NONE:
                    while (prompt("������� �������: ").equals("NONE")){
                        prompt("������� �������: ");
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + com);
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
