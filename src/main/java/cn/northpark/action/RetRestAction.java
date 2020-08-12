package cn.northpark.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.northpark.exception.Result;
import cn.northpark.exception.ResultGenerator;
import cn.northpark.manager.MoviesManager;
import cn.northpark.manager.SoftManager;
import cn.northpark.model.Movies;
import cn.northpark.model.Soft;
import cn.northpark.utils.HTMLParserUtil;
import cn.northpark.utils.IDUtils;
import cn.northpark.utils.PinyinUtil;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RetRestAction {

    @Autowired
    public SoftManager softManager;

    @Autowired
    private MoviesManager moviesManager;


    @RequestMapping({"/ret/soft/data"})
    @ResponseBody
    public Result<String> retSoft(@RequestBody List<Map<String, String>> list) {
        StringBuilder rs = new StringBuilder("success");

        retSoftData(list);


        return ResultGenerator.genSuccessResult(rs.toString());
    }


    @RequestMapping({"/ret/movies/data"})
    @ResponseBody
    public Result<String> retMovies(@RequestBody List<Map<String, String>> list) {
        StringBuilder rs = new StringBuilder("success");
        retMoviesPage(list);
        return ResultGenerator.genSuccessResult(rs.toString());
    }


    @RequestMapping({"/ret/movies/json"})
    @ResponseBody
    public Result<String> retMoviesBean(@RequestBody Movies bean) {
        try {
            int flag = this.moviesManager.countHql(
                    new StringBuilder().append(" where o.retcode= '").append(bean.getRetcode()).append("' ").toString());

            if (flag <= 0) {
                this.moviesManager.addMovies(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genSuccessResult("ok");
    }


    private void retMoviesPage(List list) {

        if (!CollectionUtils.isEmpty(list))
            for (int i = 0; i < list.size(); i++)
                try {
                    Map map = (Map) list.get(i);

                    String title = (String) map.get("title");

                    String date = (String) map.get("date");
                    String article = (String) map.get("article");
                    String retcode = (String) map.get("retcode");
                    String tag = (String) map.get("tag");
                    String tagcode = (String) map.get("tagcode");
                    String path = (String) map.get("path");

                    int flag = this.moviesManager.countHql(
                            new StringBuilder().append(" where o.retcode= '").append(retcode).append("' ").toString());

                    if (flag <= 0) {
                        Movies model = new Movies();
                        model.setMoviename(title);
                        model.setAddtime(date);
                        model.setDescription(article);
                        model.setPrice(Integer.valueOf(1));
                        model.setRetcode(retcode);
                        model.setTag(tag);
                        model.setTagcode(tagcode);
                        model.setViewnum(Integer.valueOf(HTMLParserUtil.geneViewNum()));
                        model.setColor(PinyinUtil.getFirstChar(title));
                        model.setPath(path);
                        this.moviesManager.addMovies(model);
                    }
                } catch (Exception e) {
                }
    }

    private void retSoftData(List list) {
        try {

            if (!CollectionUtils.isEmpty(list))
                for (int i = 0; i < list.size(); i++) {
                    Map map = (Map) list.get(i);

                    String title = (String) map.get("title");
                    String aurl = (String) map.get("aurl");
                    String brief = (String) map.get("brief");
                    String date = (String) map.get("date");
                    String article = (String) map.get("article");
                    String tag = (String) map.get("tag");
                    String code = new StringBuilder().append((String) map.get("code")).append("-")
                            .append(IDUtils.getInstance().generateNumberString(3)).toString();
                    String os = (String) map.get("os");
                    String month = (String) map.get("month");
                    String year = (String) map.get("year");
                    String tagcode = (String) map.get("tagcode");
                    String path = (String) map.get("path");

                    int flag = this.softManager.countHql(new StringBuilder().append(" where o.title= '").append(title)
                            .append("' or o.retcode = '").append(code).append("' ").toString());

                    if (flag <= 0) {
                        Soft model = Soft.builder().brief(brief).content(article).os(os).postdate(date).retcode(code)
                                .returl(aurl).tags(tag).title(title).month(month).year(year).tagscode(tagcode)
                                .path(path).build();
                        this.softManager.addSoft(model);
                    }
                }
        } catch (Exception e) {
            return;
        }
    }
}