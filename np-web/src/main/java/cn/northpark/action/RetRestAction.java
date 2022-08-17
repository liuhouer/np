package cn.northpark.action;

import cn.northpark.exception.Result;
import cn.northpark.exception.ResultGenerator;
import cn.northpark.manager.MoviesManager;
import cn.northpark.manager.SoftManager;
import cn.northpark.model.Movies;
import cn.northpark.model.Soft;
import cn.northpark.threadpool.MultiThread;
import cn.northpark.utils.HTMLParserUtil;
import cn.northpark.utils.IDUtils;
import cn.northpark.utils.PinyinUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Controller
@Slf4j
public class RetRestAction {

    @Autowired
    SoftManager softManager;

    @Autowired
    MoviesManager moviesManager;


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
                    new StringBuilder().append(" where o.ret_code= '").append(bean.getRet_code()).append("' ").toString());

            if (flag <= 0) {
                this.moviesManager.addMovies(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genSuccessResult("ok");
    }


    private void retMoviesPage(List list) {


        MultiThread<Map<String, String>,Integer > multiThread = new MultiThread<Map<String, String>,Integer>(list) {

            @Autowired
            private MoviesManager moviesManager;

            @Override
            public Integer outExecute(int currentThread, Map<String, String> map) {

                System.err.println("currentThread===>"+currentThread);
                System.err.println("代理处map数据 ===>"+map);

                //逻辑处理start=====================================================
                try {

                    String title = (String) map.get("title");

                    String date = (String) map.get("date");
                    String article = (String) map.get("article");
                    String ret_code = (String) map.get("ret_code");
                    String tag = (String) map.get("tag");
                    String tag_code = (String) map.get("tag_code");
                    String path = (String) map.get("path");

                    int flag = this.moviesManager.countHql(
                            new StringBuilder().append(" where o.ret_code= '").append(ret_code).append("' ").toString());

                    if (flag <= 0) {
                        Movies model = new Movies();
                        model.setMovie_name(title);
                        model.setAdd_time(date);
                        model.setMovie_desc(article);
                        model.setPrice(Integer.valueOf(1));
                        model.setRet_code(ret_code);
                        model.setTag(tag);
                        model.setTag_code(tag_code);
                        model.setView_num(Integer.valueOf(HTMLParserUtil.geneview_num()));
                        model.setColor(PinyinUtil.getFirstChar(title));
                        model.setPath(path);
                        this.moviesManager.addMovies(model);
                    }
                } catch (Exception e) {
                }
                //逻辑处理end==========================================================



                return currentThread;
            }
        };
        //调度查看执行结果
        try {
            System.err.println("线程"+multiThread.getResult()+"正在执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private void retSoftData(List list) {

        MultiThread<Map<String, String>,Integer > multiThread = new MultiThread<Map<String, String>,Integer>(list) {

            @Autowired
            SoftManager softManager;

            @Override
            public Integer outExecute(int currentThread, Map<String, String> map) {

                System.err.println("currentThread===>"+currentThread);
                System.err.println("代理处map数据 ===>"+map);

                //逻辑处理start=====================================================
                String title = (String) map.get("title");
                String a_url = (String) map.get("a_url");
                String brief = (String) map.get("brief");
                String date = (String) map.get("date");
                String article = (String) map.get("article");
                String tag = (String) map.get("tag");
                String code = new StringBuilder().append((String) map.get("code")).append("-")
                        .append(IDUtils.getInstance().generateNumberString(3)).toString();
                String os = (String) map.get("os");
                String month = (String) map.get("month");
                String year = (String) map.get("year");
                String tag_code = (String) map.get("tag_code");
                String path = (String) map.get("path");
                String color = (String) map.get("color");

                int flag = softManager.countHql(new StringBuilder().append(" where o.title= '").append(title)
                        .append("' or o.ret_code = '").append(code).append("' ").toString());

                if (flag <= 0) {
                    Soft model = Soft.builder().brief(brief).content(article).os(os).post_date(date).ret_code(code)
                            .ret_url(a_url).tags(tag).title(title).month(month).year(year).tags_code(tag_code).color(color)
                            .path(path).build();
                    softManager.addSoft(model);
                }
                //逻辑处理end==========================================================
                return currentThread;
            };

        };
            //调度查看执行结果
            try {
                System.err.println("线程"+multiThread.getResult()+"正在执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
    }
}