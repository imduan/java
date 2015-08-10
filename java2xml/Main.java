
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring.xml"})
public class Main {

    @Test
    public void doFromFile(String strFileName){

        String filePath = Main.class.getResource("/").getFile();
        filePath += "../classes/test.xml";

        StringBuffer buffer  = null;
        JAXBContext jaxbContext;
        try {
            //读入xml文件流
            InputStream is = Main.class.getResourceAsStream("/test.xml");
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            buffer = new StringBuffer();
            String line = "";
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }

            //加载映射bean类
            jaxbContext = JAXBContext.newInstance(Student.class);
            //创建解析
            Unmarshaller um = jaxbContext.createUnmarshaller();
            StreamSource streamSource = new StreamSource(new StringReader(buffer.toString()));
            Student root = (Student) um.unmarshal(streamSource);
            System.out.print(root.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
