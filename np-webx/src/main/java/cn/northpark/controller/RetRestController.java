package cn.northpark.controller;

import cn.northpark.model.Movies;
import cn.northpark.model.Soft;
import cn.northpark.result.Result;
import cn.northpark.result.ResultGenerator;
import cn.northpark.service.MoviesService;
import cn.northpark.service.SoftService;
import cn.northpark.threadPool.MultiThread;
import cn.northpark.utils.HTMLParserUtil;
import cn.northpark.utils.IDUtils;
import cn.northpark.utils.NPQueryRunner;
import cn.northpark.utils.PinyinUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
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
public class RetRestController {

    @Autowired
    SoftService softService;

    @Autowired
    MoviesService moviesService;


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

            List<Map<String, Object>> list = NPQueryRunner.findByCondition("bc_movies", " ret_code= '" + bean.getRetCode() + "' ");

            if (CollectionUtils.isEmpty(list)) {
                moviesService.addMovies(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genSuccessResult("ok");
    }


    private void retMoviesPage(List list) {


        MultiThread<Map<String, String>,Integer > multiThread = new MultiThread<Map<String, String>,Integer>(list) {

//            private transient MoviesService moviesService =  (MoviesService)SpringContextUtils.getBean("MoviesService");

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


                    List<Map<String, Object>> list = NPQueryRunner.findByCondition("bc_movies", " ret_code= '" + ret_code+ "' ");

                    if (CollectionUtils.isEmpty(list)) {
                        Movies model = new Movies();
                        model.setMovieName(title);
                        model.setAddTime(date);
                        model.setMovieDesc(article);
                        model.setPrice(Integer.valueOf(1));
                        model.setRetCode(ret_code);
                        model.setTag(tag);
                        model.setTagCode(tag_code);
                        model.setViewNum(Integer.valueOf(HTMLParserUtil.geneview_num()));
                        model.setColor(PinyinUtil.getFirstChar(title));
                        model.setPath(path);
                        moviesService.addMovies(model);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
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

//            private transient SoftService softService =  (SoftService)SpringContextUtils.getBean("SoftService");

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



                List<Map<String, Object>> list = NPQueryRunner.findByCondition("bc_soft", " ret_code= '" + code+ "' or title = '"+title+"' ");

                if (CollectionUtils.isEmpty(list)) {
                    Soft model = new Soft();
                    model.setBrief(brief);
                    model.setContent(article);
                    model.setOs(os);
                    model.setPostDate(date);
                    model.setRetCode(code);
                    model.setRetUrl(a_url);
                    model.setTags(tag);
                    model.setTitle(title);
                    model.setMonth(month);
                    model.setYear(year);
                    model.setTagsCode(tag_code);
                    model.setColor(color);
                    model.setPath(path);
                    softService.addSoft(model);
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