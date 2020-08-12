package cn.northpark.utils.movie;

import cn.northpark.manager.impl.MoviesManagerImpl;
import cn.northpark.model.Movies;
import cn.northpark.utils.*;
import cn.northpark.utils.encrypt.EnDecryptUtils;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import org.apache.http.client.ClientProtocolException;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.PrePersist;
import java.io.IOException;
import java.util.Collections;
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

        if(!CollectionUtils.isEmpty(briefList)){
            for (MovieListPage brief : briefList) {
                if(brief.getA_href().contains( movieDetailRunner.getA_href())){

                    Movies model = new Movies();
                    String title = brief.getTitle();
                    model.setMoviename(title);
                    model.setAddtime(brief.getDate());
                    model.setDescription(movieDetailRunner.getDetail());
                    model.setPrice(Integer.valueOf(1));
                    model.setRetcode(EnDecryptUtils.md5Encrypt(title));
                    String tags = brief.getTags();
                    model.setTag(tags);
                    model.setTagcode(PinyinUtil.paraseStringToPinyin(tags).toLowerCase());
                    model.setViewnum(Integer.valueOf(HTMLParserUtil.geneViewNum()));
                    model.setColor(PinyinUtil.getFirstChar(title));
                    model.setPath(movieDetailRunner.getDownload());


                    String jsonData = JsonUtil.object2json(model);


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