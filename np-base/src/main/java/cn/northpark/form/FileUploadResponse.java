package cn.northpark.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bruce
 * @date 2023年11月22日 21:50:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadResponse {
    private String urlHttp;

    private String urlPath;
}