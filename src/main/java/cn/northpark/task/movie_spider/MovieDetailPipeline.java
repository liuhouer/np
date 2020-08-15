package cn.northpark.task.movie_spider;

import cn.northpark.model.Movies;
import cn.northpark.utils.*;
import cn.northpark.utils.encrypt.EnDecryptUtils;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangyang
 * @date 2020年08月12日 11:13:39
 */
@PipelineName("MovieDetailPipeline")
@Service
public class MovieDetailPipeline implements Pipeline<MovieDetailRunner> {

    List<MovieListPage> briefList = null;

    @Override
    public void process(MovieDetailRunner movieDetailRunner) {
        try {
            briefList = CacheUtil.getCache().get("briefList");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (!CollectionUtils.isEmpty(briefList)) {
            for (MovieListPage brief : briefList) {
                if (brief.getA_href().contains(movieDetailRunner.getA_href())) {

                    Movies model = new Movies();
                    String title = brief.getTitle();
                    model.setMoviename(title);
                    model.setAddtime(brief.getDate());
                    //处理a标签====================================
                    String detail = movieDetailRunner.getDetail();
                    Document parseDetail = Jsoup.parse(detail);
                    parseDetail.select("a").attr("href", "");
                    detail = parseDetail.html();
                    //处理a标签====================================
                    model.setDescription(detail);
                    model.setPrice(Integer.valueOf(1));
                    model.setRetcode(EnDecryptUtils.md5Encrypt(title));
                    String tags = brief.getTags();
                    tags = tags.replace(" ", "").replace("/", ",");
                    while (tags.startsWith(",")) {
                        tags = tags.substring(1);
                    }
                    model.setTag(tags);
                    model.setTagcode(PinyinUtil.paraseStringToPinyin(tags).toLowerCase());
                    model.setViewnum(Integer.valueOf(HTMLParserUtil.geneViewNum()));
                    model.setColor(PinyinUtil.getFirstChar(title));
                    model.setPath(movieDetailRunner.getDownload());


                    String jsonData = JsonUtil.object2json(model);


                    //需要写入的文件的路径
//                    String filePath = "D:/beanList.txt";
//
//                    try {
//                        File file = new File(filePath);
//                        FileOutputStream fos = null;
//                        if (!file.exists()) {
//                            file.createNewFile();//如果文件不存在，就创建该文件
//                            fos = new FileOutputStream(file);//首次写入获取
//                        } else {
//                            //如果文件已存在，那么就在文件末尾追加写入
//                            fos = new FileOutputStream(file, true);//这里构造方法多了一个参数true,表示在文件末尾追加写入
//                        }
//
//                        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");//指定以UTF-8格式写入文件
//
//                        osw.write(jsonData);
//
//                        //每写入一个Map就换一行
//                        osw.write("\r\n");
//                        //写入完成关闭流
//                        osw.close();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }


                    String url = "http://localhost:8082/ret/movies/json";
                    try {
                        HttpGetUtils.sendPostJsonData(url, jsonData);
                    } catch (ClientProtocolException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


                }
            }
        }

    }


}