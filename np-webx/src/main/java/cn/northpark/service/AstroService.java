
package cn.northpark.service;

import cn.northpark.model.Astro;

import java.util.List;

/**
 * @author bruce
 * @date 2016-12-01
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
public interface AstroService {

     Astro findAstro(Integer id);

     List<Astro> findAll();

     void addAstro(Astro astro);

     boolean delAstro(Integer id);

     boolean updateAstro(Astro astro);

}


