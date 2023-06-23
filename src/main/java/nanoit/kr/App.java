//package nanoit.kr;
//
//import lombok.extern.slf4j.Slf4j;
//import nanoit.kr.cli.InitialSetting;
//import nanoit.kr.cli.PropertyReader;
//import nanoit.kr.config.GlobalConstant;
//import nanoit.kr.manager.SessionManager;
//import nanoit.kr.manager.SocketManager;
//import nanoit.kr.manager.ThreadManager;
//
//import java.util.Date;
//
//@Slf4j
//public class App {
//    public static void main(String[] args) {
//        try {
//            InitialSetting setting = new InitialSetting();
//            PropertyReader propertyReader = new PropertyReader();
//
//            // config 파일에 AgentId.xml 파일이 없을 경우 시스템 Out
//
//            if (!setting.isXmlFileExist()) {
//                log.warn("[SYSTEM]  User Config File does not Exist in config Folder.");
//                System.exit(-1);
//            }
//
//            // 설정 파일이 있다면
//            propertyReader.initializeSettingEnv();
//
//
//            ThreadManager threadM = new ThreadManager();
//            SocketManager socketM = new SocketManager();
//            SessionManager sessionM = new SessionManager();
//
//            //----------------------------------------------------------------------
//            // 각 매니저 들 사전 준비 단계 완료 체크
//
//            if (!socketM.isSocketPoolReady() || !threadM.isThreadManagerReady() || !sessionM.isSessionManagerReady()) {
//                log.error("[SYSTEM] Scheduler status is not executable");
//                Thread.sleep(2000);
//                System.exit(-1);
//            }
//
//            threadM.start();
//            socketM.run();
//            sessionM.start();
//
//            log.info("");
//            log.info("================================================================");
//            log.info("================================================================");
//            System.out.println("                                                                    [[[[   AGENT START  ]]]]              " + GlobalConstant.SIMPLE_DATE_FORMAT.format(new Date()));
//            log.info("================================================================");
//            log.info("================================================================");
//            log.info("");
//            log.info("");
//            log.info("================================================================");
//            System.out.println("                                                                    AGENT VERSION            " + GlobalConstant.AGENT_VERSION);
//            System.out.println("                                                                    AGENT HOME PATH          " + GlobalConstant.SIMPLE_DATE_FORMAT.format(new Date()));
//            System.out.println("                                                                    AGENT DEFAULT ENCODING   " + GlobalConstant.AGENT_DEFAULT_ENCODING);
//            log.info("================================================================");
//            log.info("");
//            log.info("");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
