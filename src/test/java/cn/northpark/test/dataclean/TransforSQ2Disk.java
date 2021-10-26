package cn.northpark.test.dataclean;

import cn.northpark.manager.MoviesManager;
import cn.northpark.model.Movies;
import cn.northpark.utils.HTMLParserUtil;
import cn.northpark.utils.TimeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static cn.northpark.utils.HTMLParserUtil.getLocalFolderByOSMovies;

/**
 * @author zhangyang
 * <p>
 * 处理七牛违规图片到磁盘，并且更新资源
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-dao.xml", "classpath:application-service.xml",
        "classpath:application-transaction.xml"})
public class TransforSQ2Disk {

    @Autowired
    public MoviesManager moviesManager;


    public void runTask() {

        //=========================================================新url的sitemap===========================================================================================


        Set<String> rm_img_set = new HashSet<>();
        // 1 筛选

        String sql = "select * from bc_movies where (tagcode like '%qingse%' or tag like '%大尺度%'  or tag like '%色情%' or tag like '%三级%' or tag like '%伦理%') and description like '%qiniupic.python-project.com%'";
        List<Movies> movies = moviesManager.querySqlEntity(sql);

        //2 jsoup处理图片
        if(!CollectionUtils.isEmpty(movies)){
            for (Movies movie : movies) {
                String desc = movie.getDescription();
                Document parse = Jsoup.parse(desc);
                Elements imgs = parse.select("img");

                for (Element img : imgs) {
                    String src = img.attr("src");
                    rm_img_set.add(src);
                    HashMap<String, String> pic2Disk = HTMLParserUtil.webPic2Disk(src, getLocalFolderByOSMovies(), TimeUtils.nowdate());
                    String trimPan = pic2Disk.get("trimPan");

                    img.attr("src","/"+trimPan);


                }
                movie.setDescription(parse.html());

                moviesManager.updateMovies(movie);

            }

            rm_img_set.stream().forEach(System.err::println);

        }

    }


    //测试多页


    @Test
    public void save() {
        runTask();//NEWLY 2020年12月26日
    }

}
