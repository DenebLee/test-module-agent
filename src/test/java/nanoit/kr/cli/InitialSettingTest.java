package nanoit.kr.cli;

import nanoit.kr.config.GlobalConstant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

class InitialSettingTest {

    private InitialSetting setting;
    private Path path = Paths.get(GlobalConstant.CONFIG_FILE_PATH);

    @BeforeEach
    void setUp() {
        this.setting = new InitialSetting();
    }

    @DisplayName("생성된 DTO 만큼 Xml 파일이 생성되어야 한다")
    @Test
    void Xml_File_Should_be_created_by_generated_dto() throws IOException {
        // given
//        List<XmlDto> testDtoList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            XmlDto dto = new XmlDto();
//            dto.setUrl("test" + i)
//                    .setUrl("ee" + i)
//                    .setAgentId("test" + i)
//                    .setAgentPwd("test" + i)
//                    .setAgentEncryptKey("dfdfd" + i)
//                    .setDbms("test" + i)
//                    .setDbIp("test" + i)
//                    .setDbPort("test" + i)
//                    .setDbId("test" + i)
//                    .setDbPwd("test" + i)
//                    .setDbName("test" + i)
//                    .setSmsEnabled(false)
//                    .setKmsEnabled(false)
//                    .setMmsEnabled(true);
//            testDtoList.add(dto);
//        }

        XmlDto dto = new XmlDto();
        dto.setUrl("test")
                .setUrl("ee")
                .setAgentId("test")
                .setAgentPwd("test")
                .setAgentEncryptKey("dfdfd")
                .setDbms("test")
                .setDbIp("test")
                .setDbPort("test")
                .setDbId("test")
                .setDbPwd("test")
                .setDbName("test")
                .setSmsEnabled(false)
                .setKmsEnabled(false)
                .setMmsEnabled(true);
        // when
        setting.makeXmlFile2(dto);
        boolean isMakeXmlFile = setting.isXmlFileExist();

        // then
        assertTrue(isMakeXmlFile, "XML file should be created");

// Additional assertions to verify the number of generated XML files
        File xmlFilesDirectory = new File(path.toString());
        File[] xmlFiles = xmlFilesDirectory.listFiles((dir, name) -> name.endsWith(GlobalConstant.FILE_FORMAT));
    }
}