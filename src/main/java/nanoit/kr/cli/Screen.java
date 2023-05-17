package nanoit.kr.cli;

import nanoit.kr.DatabaseType;

import javax.xml.bind.annotation.XmlElement;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Screen {
    private final InitialSetting initial;
    private final XmlDtoStore xmlDtoStore;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);

    public Screen() {
        this.initial = new InitialSetting();
        this.xmlDtoStore = new XmlDtoStore();
    }

    String mainScreen() {
        clearConsole();
        emptySeparateLine(true);
        System.out.println("=  1. AGENT 설정");

        // TODO 테스트 및 실행 환경 변수 확인은 메인 비즈니스 로직 추가 후 수정
        System.out.println("=  2. AGENT 테스트");
        System.out.println("=  3. 실행 환경 변수 확인");
//        System.out.println("=  4. AGENT 발송 상태 모니터링");
        System.out.println("=  0. 종료");
        emptySeparateLine(true);
        return input();
    }

    String accountMainScreen() {
        clearConsole();
        emptySeparateLine(true);
        System.out.println("=  1. [초기 설정]");
        System.out.println("=  2. 계정 정보 수정");
        System.out.println("=  3. 모든 정보 확인");
        System.out.println("=  4. 옵션 설정");
        emptyPrintLine(4);
        System.out.println("=  0. 종료, P. 이전");
        emptySeparateLine(true);
        return input();
    }

    void accountInputScreen() throws IOException {

        clearConsole();
        System.out.println("설정 시 기존 설정 파일은 삭제됩니다. 진행 하시겠습니까?");
        System.out.println("(Y/N) - (Y, 설정하기) (N, 뒤로가기)");
        while (true) {
            String isSetting = input();
            if ("y".equalsIgnoreCase(isSetting)) {
                try {
                    if (initial.isXmlFileExist()) {
                        initial.deleteConfigXmlFiles();
                    }

                    emptySeparateLine(false);

                    XmlDto userInputDto = new XmlDto();
                    Map<Integer, XmlDto> userAccountMap = new HashMap<>();


                    /* URL SETTING*/
                    System.out.print("% 계정 정보 순서 대로 입력\r\n");
                    System.out.println("1. URL (ENTER. 전송 서버 URL )");
                    System.out.print(">");
                    String url = input();
                    if (url == null || url.equals("")) {
                        url = "http://dist.funsms.com/";
                        System.out.println(url);
                    }
                    emptySeparateLine(false);
                    userInputDto.setUrl(url);

                    /* ID SETTING*/
                    System.out.println("2. ID : ");
                    System.out.println(">");
                    String id = input();
                    if (id.equals("")) {
                        while (true) {
                            if (id.equals("")) {
                                System.out.println("잘못 입력됨.");
                                System.out.print(">");
                                id = input();
                            } else {
                                break;
                            }
                        }
                    }
                    emptySeparateLine(false);
                    userInputDto.setAgentId(id);

                    /* PASSWORD SETTING*/
                    System.out.println("3. PASSWORD : ");
                    System.out.print(">");
                    String password = input();
                    if (password.equals("")) {
                        while (true) {
                            if (password.equals("")) {
                                System.out.println("잘못 입력됨.");
                                System.out.print(">");
                                password = input();
                            } else {
                                break;
                            }
                        }
                    }
                    emptySeparateLine(false);
                    userInputDto.setAgentPwd(password);

                    /* ENCRYPTKEY SETTING*/
                    System.out.println("4. ENCRYPT_KEY : ");
                    System.out.print(">");
                    String encryptKey = input();
                    if (encryptKey.equals("")) {
                        while (true) {
                            if (encryptKey.equals("")) {
                                System.out.println("잘못 입력됨.");
                                System.out.print(">");
                                encryptKey = input();
                            } else {
                                break;
                            }
                        }
                    }
                    emptySeparateLine(false);
                    userInputDto.setEncryption(encryptKey);


                    /* SMS SETTING */
                    System.out.println("5. SMS ( Short Message Service ), 단문 메시지 서비스 사용 (Y/NS) (ENTER = Y)");
                    while (true) {
                        System.out.println(">");
                        String yOrN = input();
                        if (yOrN.equals("")) {
                            System.out.println("Y");
                        }
                        emptySeparateLine(false);
                        if ("y".equalsIgnoreCase(yOrN) || yOrN.equals("")) {
                            userInputDto.setIsUseSms("YES");
                            break;
                        } else if ("n".equalsIgnoreCase(yOrN)) {
                            break;
                        } else {
                            System.out.println("[" + yOrN + "] 잘못 입력됨.");
                        }
                    }

                    /* SMS SETTING */
                    System.out.println("6. LMS/MMS ( Long and MultiMedia Message Service ), 장문, 이미지 메시지 서비스 사용 여부 (Y/N) (ENTER. Y)");
                    while (true) {
                        System.out.println(">");
                        String yOrN = input();
                        if (yOrN.equals("")) {
                            System.out.println("Y");
                        }
                        emptySeparateLine(false);
                        if ("y".equalsIgnoreCase(yOrN) || yOrN.equals("")) {
                            userInputDto.setIsUseMms("YES");
                            break;
                        } else if ("n".equalsIgnoreCase(yOrN)) {
                            break;
                        } else {
                            System.out.println("[" + yOrN + "] 잘못 입력됨.");
                        }
                    }

                    /* DBMS SELECT */
                    System.out.println("7. DBMS 종류 선택");
                    System.out.println("[1] POSTGRESQL [2] MYSQL [3] MARIADB [4] ORACLE");
                    String dbms;
                    label:
                    while (true) {
                        System.out.print(">");
                        String database = input();
                        emptySeparateLine(false);
                        switch (database) {
                            case "1":
                                dbms = "POSTGRESQL";
                                break label;
                            case "2":
                                dbms = "MYSQL";
                                break label;
                            case "3":
                                dbms = "MARIADB";
                                break label;
                            case "4":
                                dbms = "ORACLE";
                                break label;
                            case "5":
                                dbms = "MSSQL";
                                break label;
                            default:
                                System.out.println("[" + database + "] 잘못 입력됨.");
                        }
                    }

                    System.out.println("8. DATA BASE 연결 [IP] 입력 : ");
                    System.out.print(">");
                    String ip = input();
                    if (ip.equals("")) {
                        while (true) {
                            if (ip.equals("")) {
                                System.out.println("잘못 입력됨.");
                                System.out.print(">");
                                ip = input();
                            } else {
                                break;
                            }
                        }
                    }
                    emptySeparateLine(false);

                    System.out.println("9. DATA BASE 연결 [PORT] 입력 " + (DatabaseType.getPort(dbms).equals("") ? ""
                            : "(ENTER. " + DatabaseType.getPort(dbms) + ")"));
                    System.out.print(">");
                    String port = input();
                    if (port.toUpperCase().equals("")) {
                        port = DatabaseType.getPort(dbms);
                        System.out.println(port);
                    }

                    emptySeparateLine(false);
                    System.out.println("10. DATA BASE 연결 [ID] 입력 : ");
                    System.out.print(">");
                    String dataBaseId = input();
                    if (dataBaseId.equals("")) {
                        while (true) {
                            if (dataBaseId.equals("")) {
                                System.out.println("잘못 입력됨.");
                                System.out.print(">");
                                dataBaseId = input();
                            } else {
                                break;
                            }
                        }
                    }
                    emptySeparateLine(false);

                    System.out.println("11. DATA BASE 연결 [PASSWORD] 입력 : ");
                    System.out.print(">");
                    String dataBasePassWord = input();
                    if (dataBasePassWord.equals("")) {
                        while (true) {
                            if (dataBasePassWord.equals("")) {
                                System.out.println("잘못 입력됨.");
                                System.out.print(">");
                                dataBasePassWord = input();
                            } else {
                                break;
                            }
                        }
                    }
                    emptySeparateLine(false);

                    System.out.println("12. DATA BASE 연결 [ORACLE = 리스너 이름, 그외 DATA BASE NAME] 입력 : ");
                    System.out.print(">");
                    String dataBaseName = input();
                    if (dataBaseName.equals("")) {
                        while (true) {
                            if (dataBaseName.equals("")) {
                                System.out.println("잘못 입력됨.");
                                System.out.print(">");
                                dataBaseName = input();
                            } else {
                                break;
                            }
                        }
                    }
                    emptySeparateLine(false);
                    System.out.println("이중화 작업은 추후 수정 예정");

                    // TODO 해당 로직 For문으로 이중화 옵션 Yes 선택시 돌면서 추가할 수 있도록 수정 예정 index는 i값 증가 값으로 삽입
                    userAccountMap.put(1, userInputDto);

//        for (Map.Entry<Integer, XmlDto> entry : userAccountMap.entrySet()) {
//            try {
//                FileUtils.write(
//                        new File(GlobalConstant.CONFIG_FILE_PATH + "/" + id + ".xml"),
//                        XmlParser.write(entry.getValue()), StandardCharsets.UTF_8);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
                    emptySeparateLine(true);
                    System.out.println("Press any key to continue...");
                    pause();
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("n".equalsIgnoreCase(isSetting)) {
                break;
            } else {
                System.out.println("[" + isSetting + "] 잘못 입력됨. ");
            }
        }

    }

    //    ==========================================================================================
    /* 계정 수정 */
    void accountFix() {
        while (true) {
            clearConsole();
            emptySeparateLine(true);

            if (xmlDtoStore.getXmlConfigList().isEmpty()) {
                System.out.println("설정된 계정 정보가 존재하지 않습니다. ");
                System.out.println("계정 설정 부터 완료해 주시기 바랍니다.");
                System.out.println("뒤로가기 [P]");
                System.out.print(">");
                String back = input();
                if (back == null || back.equals("")) {
                    System.out.println("잘못된 입력됨 ");
                } else if ("P".equalsIgnoreCase(back)) {
                    System.exit(-1);
                }

                int accountCount;
                for (Map.Entry<Integer, XmlDto> entry : xmlDtoStore.getXmlConfigList().entrySet()) {
                    System.out.println("");
                    System.out.print("설정된 Account === [" + entry.getKey() + "]  => " + entry.getValue().getAgentId());
                    System.out.println("");
                    emptySeparateLine(false);
                }

                System.out.println("계정 수정 메뉴입니다.");
                System.out.println("수정할 계정 번호를 정확하게 입력 해주시기 바랍니다.");
                emptySeparateLine(false);
                System.out.println("=  0. 종료, P. 이전");
                System.out.print(">");

                String AgentIdInput = input();

                if (AgentIdInput == null || AgentIdInput.equals("")) {
                    System.out.println("잘못 입력됨.");
                    pause();
                } else if (AgentIdInput.equals("0")) {
                    System.out.println("저장 후 종료 합니다.");
                    System.out.println("Press any key to continue...");
                    pause();
                    System.exit(-1);
                } else if (AgentIdInput.toUpperCase().equals("P")) {
                    return;
                } else {
                    accountRealFix(AgentIdInput);

                }
            }
        }
    }
    // ============================== 실제 계정 수정 로직 ==========================================================

    private void accountRealFix(String str) {
        while (true) {
            clearConsole();
            emptySeparateLine(true);

            XmlDto xmlDto = xmlDtoStore.getXmlDto(Integer.parseInt(str));
            for (Field field : xmlDto.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    if (field.isAnnotationPresent(XmlElement.class)) {
                        XmlElement annotation = field.getAnnotation(XmlElement.class);
                        String key = annotation.name();
                        String value = (String) field.get(xmlDto);
                        System.out.println("KEY" + "[" + key + "]" + " : " + "VALUE" + "[" + value + "]");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            emptySeparateLine(false);
            System.out.println("변경할 값의 KEY를 입력해 주세요. (ex. URL)");
            emptySeparateLine(false);
            System.out.println("=  P. 이전");
            System.out.print(">");

            String input = input();

            if (input == null || input.equals("")) {
                System.out.println("잘못 입력됨.");
                pause();
            } else if (input.toUpperCase().equals("P")) {
                break;
            } else {
                // Iterate over the fields of the DTO object
                for (Field field : xmlDto.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    try {
                        if (field.isAnnotationPresent(XmlElement.class)) {
                            XmlElement annotation = field.getAnnotation(XmlElement.class);
                            String key = annotation.name();
                            // Compare the key with the user's input
                            if (key.equalsIgnoreCase(input)) {
                                // Modify the value of the field in the DTO object
                                String newValue = input();
                                field.set(xmlDto, newValue);
                                System.out.println("값이 수정되었습니다.");
                                pause();
                                break;
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    // ============================== 최종 설정된 계정 출력  ==========================================================
    void printAccount() {
        for (Map.Entry<Integer, XmlDto> entry : xmlDtoStore.getXmlConfigList().entrySet()) {
            XmlDto dto = entry.getValue();
            for (Field field : dto.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    if (field.isAnnotationPresent(XmlElement.class)) {
                        XmlElement annotation = field.getAnnotation(XmlElement.class);
                        String key = annotation.name();
                        String value = (String) field.get(dto);
                        System.out.println("KEY" + "[" + key + "]" + " : " + "VALUE" + "[" + value + "]");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private String input() {
        return new Scanner(System.in).nextLine();
    }

    void pause() {
        try {
            System.in.read();
            System.in.read(new byte[System.in.available()]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                //Runtime.getRuntime().exec("clear");
                System.out.print("\033[H\033[2J");
            }
        } catch (final Exception e) {
            System.out.println("설정 중 에러 발생. 설정은 계속 진행됨.");
            e.printStackTrace();
        }
    }

    private void emptySeparateLine(boolean isDouble) {
        if (isDouble) {
            System.out.println(
                    "===============================================================================");
        }
        System.out
                .println("===============================================================================");
    }

    private void emptyPrintLine(int count) {
        for (int i = 0; i < count; i++) {
            System.out.println("=");
        }
    }
}
