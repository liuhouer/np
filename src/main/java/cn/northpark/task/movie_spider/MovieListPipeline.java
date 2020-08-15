package cn.northpark.task.movie_spider;

import cn.northpark.utils.CacheUtil;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.SchedulerContext;
import com.geccocrawler.gecco.spider.HrefBean;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zhangyang
 * @date 2020年08月12日 11:13:39
 */
@PipelineName("MovieListPipeline")
public class MovieListPipeline implements Pipeline<MovieRunner> {


    public static List<HttpRequest> detailRequests = new ArrayList<HttpRequest>();

    @Override
    public void process(MovieRunner movieBrief) {
        List<MovieListPage> list = movieBrief.getList();

        CacheUtil.getCache().put("briefList", list);


        for(MovieListPage category : list) {
            if(Objects.nonNull(category)){
                String hrefs = category.getA_href();
                HttpRequest currRequest = movieBrief.getRequest();
//            SchedulerContext.into(currRequest.subRequest(hrefs));

                System.err.println("href--->"+hrefs);
                if(StringUtils.isNotEmpty(hrefs)){
                    detailRequests.add(currRequest.subRequest(hrefs));
                }
            }

        }
    }


}