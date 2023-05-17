package nanoit.kr.cli;

import java.io.IOException;

public class Branch {

    private final Screen screen;

    public Branch() {
        this.screen = new Screen();
    }

    void mainBranch() throws IOException {
        while (true) {
            String input = screen.mainScreen();

            switch (input) {
                case "0":
                    exit();
                    break;
                case "1":
                    accountBranch();
                    break;
                case "2":
                    connectionAndSendTest();
                    break;
                case "3":
                    showAllOption();
                    break;
                default:
                    System.out.printf("[%s] 허용되지 않은 입력.%n", input.toUpperCase());
                    enter("Press any key to continue...");
                    break;
            }


        }
    }

    void accountBranch() throws IOException {
        while (true) {
            String input = screen.accountMainScreen();
            switch (input.toUpperCase()) {
                case "0":
                    exit();
                    break;

                case "1":
                    screen.accountInputScreen();
                    break;

                case "2":
                    screen.accountFix();
                    break;

                case "3":
                    screen.printAccount();
                    break;
                default:
                    System.out.printf("[%s] 허용되지 않은 입력.%n", input.toUpperCase());
                    enter("Press any key to continue...");
                    break;
            }
        }
    }

    void connectionAndSendTest() {
    }

    void showAllOption() {
    }

    private void enter(String s) {
        System.out.println(s);
        screen.pause();
    }

    private void exit() {
        System.out.println("[EXIT] 종료합니다.");
        System.exit(-1);
    }
}
