package nanoit.kr.cli;


/* TODO 작성 해야 할 기능 메소드
    1. XML Reader               = ok
    2. XML Create               =
    3. XML Modified             =
    4. XML Duplicate            =
    5. XML IsExist              = ok
    6. Config Folder IsExist    = ok
    8. XML To Object            =
    9. Object To Xml            =
    10. XML Files Delete All    = ok
*/

import lombok.extern.slf4j.Slf4j;
import nanoit.kr.config.GlobalConstant;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;


@Slf4j
public class InitialSetting {

    private final Path path = Paths.get(GlobalConstant.CONFIG_FILE_PATH);


    public boolean isConfigDirectoryExist() {
        return Files.isDirectory(path);
    }


    /**
     * @param dtos 콘솔 에서 사용자 가 입력한 값을 저장 하는 XML DTO list
     *             Filename = <서비스>_<AgentID>.xml
     *             ex) SMS_nano1.xml
     */

    public void makeXmlFile(List<XmlDto> dtos) {
        for (XmlDto xmlDto : dtos) {
            try {
                JAXBContext context = JAXBContext.newInstance(XmlDto.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                String fileName = xmlDto.getAgentId() + GlobalConstant.FILE_FORMAT;
                File file = new File(path.toString() + "/" + fileName);
                marshaller.marshal(xmlDto, file);
            } catch (Exception e) {
                log.warn("[InitialSetting] make XML FAILED");
                System.exit(-1);
            }
        }
    }

    public boolean isXmlFileExist() throws IOException {
        try (Stream<Path> files = Files.list(path)) {
            return files.anyMatch(path -> path.toString().endsWith(GlobalConstant.FILE_FORMAT));
        } catch (NoSuchFileException e) {
            return false;
        }
    }


    public Document parseXml(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.parse(file);
    }

    public void deleteConfigXmlFiles() {
        try {
            Files.walk(path)
                    .filter(path -> path.toString().endsWith(".xml"))
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                            log.info("DELETE CONFIG FILE {}", path.getFileName());
                        } catch (Exception e) {
                            log.error("");
                            e.printStackTrace();
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
