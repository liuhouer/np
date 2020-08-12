package cn.northpark.utils.movie;

import cn.northpark.utils.CacheUtil;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;

/**
 * @author zhangyang
 * @date 2020年08月12日 11:13:39
 */
@PipelineName("MovieListPipeline")
public class MovieListPipeline implements Pipeline<MovieRunner> {


    @Override
    public void process(MovieRunner movieBrief) {
        CacheUtil.getCache().put("briefList",movieBrief.getList());
    }


}