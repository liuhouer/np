package cn.northpark.test.dataclean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author bruce
 * @date 2024年01月10日 20:03:46
 */
public class HttpAgricultureBrand {
    public static void main(String[] args) {
        try {
            String url = "https://jppy.aboc.agri.cn:9000/mlgl/api/general/area/queryPage";
            String parameters = "{\"areaBrandName\":\"\",\"commitStatus\":\"5\",\"currentPage\":1,\"industryType\":\"\",\"industryTypeDetail\":\"\",\"limit\":1000,\"page\":1,\"pageSize\":1000,\"productionPlace\":\"\",\"provinceOrganizationName\":\"河北省\",\"sessionKey\":\"b7984bc1-4aae-41b2-8a60-b65119f146ee\",\"traceLogId\":\"31511112918318362771686459093841\"}";
            byte[] postData = parameters.getBytes(StandardCharsets.UTF_8);

            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setDoOutput(true);
            connection.getOutputStream().write(postData);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println(response.toString());

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
