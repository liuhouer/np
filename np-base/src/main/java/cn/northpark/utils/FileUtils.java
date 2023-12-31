package cn.northpark.utils;


import cn.northpark.constant.BC_Constant;
import cn.northpark.utils.encrypt.EnDecryptUtils;
import cn.northpark.vo.BiliVO;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * @author Bruce
 */
@Slf4j
public class FileUtils {


    /**
     * 头像
     */
    public static final String suffix_head = "heads";

    /**
     * 专辑
     */
    public static final String suffix_album = "album";

    /**
     * 文件
     */
    public static final String suffix_upload = "upload";


    //-------------以下为northpark公用上传 、下载 、删除相关方法-------------------------------------------

    /**
     * 以下为northpark文件删除方法
     *
     * @param old_path
     * @param file
     */
    public static void removeOldFile(String old_path, MultipartFile[] file) {
        if (file.length >= 1) {
            log.info(file[0].getOriginalFilename() + "------------------------------------------------》》");

            String path = BC_Constant.getFileStartByOs();

            if (StringUtils.isNotEmpty(file[0].getOriginalFilename()) && StringUtils.isNotEmpty(old_path)) {// 新上传了图片才把以前的删除
                File f = new File(path + old_path);
                log.info("要删除文件的绝对路径是：" + f.getAbsolutePath());
                if (f.exists()) {
                    f.delete();
                } else {
                    log.error("文件已经丢失!");
                }
            }
        }
    }


    /**
     * 以下为northpark上传upload相关方法
     *
     * @param file
     * @param suffix
     * @return 保存的路径数值集合
     */
    public static List<String> commonUpload(MultipartFile[] file, String suffix) {
        log.info("-------------------------------------->开始");

        List<String> list = Lists.newArrayList();

        String path = BC_Constant.getFileStartByOs();
        String fileName = "";
        String newName = "";

        String pre_path = path + suffix + "/"; //["/mnt/apk/heads/"]

        for (int i = 0; i < file.length; i++) {
            String tail_path = "";         //["/heads/100101.ext"数据库保存的数值]

            fileName = file[i].getOriginalFilename();
            //fileName为空时证明用户没有上传文件
            if (StringUtils.isNotEmpty(fileName)) {
                String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
                newName = System.currentTimeMillis() + "." + ext;
                File targetFile = new File(pre_path, newName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                //保存
                try {
                    file[i].transferTo(targetFile);

                    //图片压缩处理 BRUCE TIPS！
                    synchronized (Thumbnails.class) {
                        Thumbnails.of(pre_path + newName)
                                .scale(1f)
                                .outputQuality(0.5f)
                                .toFile(pre_path + newName);
                    }
                } catch (Exception e) {
                    log.error("上传文件异常------->", e);
                    continue;
                }
                tail_path = "/" + suffix + "/" + newName;

                list.add(tail_path);
            }

        }
        System.gc();
        log.info("-------------------------------------->结束");
        return list;

    }


    //--------------------------------------------------------以下为爬虫相关方法-------------------------------------------

    /**
     * 获得网络图片地址。或者图片地址
     *
     * @param url
     * @return
     */
    public static String getContentFromWeb(String url) {
        String fileContent = "";
        InputStream is = null;
        if (url.startsWith("http")) {
            try {
                HttpURLConnection urlConn = (HttpURLConnection) new URL(url).openConnection();
                is = urlConn.getInputStream();
            } catch (MalformedURLException e) {
                log.error("------->", e);
            } catch (IOException e) {
                log.error("------->", e);
            }
        }
        int n = 0;
        byte[] b = null;
        try {
            while ((n = is.available()) > 0) {
                n = is.read(b);
                if (n == -1) break;
                fileContent = fileContent + Base64.encodeBase64(b);

            }
            is.close();
        } catch (IOException e) {
            log.error("------->", e);
        }
        return fileContent;
    }

    /**
     * 将图片内容用post方式发送到url中
     *
     * @param url
     * @param postContent
     */

    public static void sendImgbyPost(String url, String postContent) {
        try {
            HttpURLConnection huc = (HttpURLConnection) new URL(url).openConnection();
            huc.setDoInput(true);
            huc.setDoOutput(true);
            huc.setRequestMethod("POST");

            PrintWriter pw = new PrintWriter(new OutputStreamWriter(huc.getOutputStream()));
            pw.print(postContent);
            pw.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
            String content = "";
            String line = br.readLine();
            while (line != null) {
                content = content + line;
                line = br.readLine();

            }

        } catch (MalformedURLException e) {

            log.error("------->", e);
        } catch (IOException e) {

            log.error("------->", e);
        }

    }

    /**
     * 在服务器端获取发送过来的内容
     *
     * @param request
     * @return
     */
    public static String receiveContent(HttpServletRequest request) {
        byte[] b = new byte[4096];
        String result = "";
        try {
            ServletInputStream sis = request.getInputStream();
            int line = sis.readLine(b, 0, b.length);
            while (line != -1) {
                result = result + new String(b, 0, line);
                line = sis.readLine(b, 0, b.length);
            }
        } catch (Exception e) {
            log.error("------->", e);
        }
        return result;
    }

    /**
     * 将接受过来的信息生成文件
     *
     * @param request
     * @param filename
     */
    public static void createFile(HttpServletRequest request, String filename) {
        File file = new File(filename);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            String content = receiveContent(request);
            byte[] b = Base64.decodeBase64(content);
            fos.write(b);
            fos.close();

        } catch (FileNotFoundException e) {
            log.error("------->", e);
        } catch (IOException e) {
            log.error("------->", e);
        }

    }

    // 写文件
    public void writerTxt() {
        BufferedWriter fw = null;
        try {
            File file = new File("D://text.txt");
            fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8)); // 指定编码格式，以免读取时中文字符异常
            fw.append("我写入的内容");
            fw.newLine();
            fw.append("我又写入的内容");
            fw.flush(); // 全部写入缓存中的内容
        } catch (Exception e) {
            log.error("------->", e);
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    log.error("------->", e);
                }
            }
        }
    }

    // 读文件
    public static void readTxt() {
        String filePath = "/Users/zhangyang/Downloads/start.txt"; // 文件和该类在同个目录下
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8)); // 指定读取文件的编码格式，要和写入的格式一致，以免出现中文乱码,
            String str = null;
            while ((str = reader.readLine()) != null) {
                long lo = Long.valueOf(str.trim());
                String time = TimeUtils.longToString(lo);
                log.info(str + "------->" + time);
            }
        } catch (FileNotFoundException e) {
            log.error("------->", e);
        } catch (IOException e) {
            log.error("------->", e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                log.error("------->", e);
            }
        }
    }

    public static List<String> showAllFiles(File dir) throws Exception {
        List<String> file_list = Lists.newArrayList();
        File[] fs = dir.listFiles();
        for (int i = 0; i < fs.length; i++) {
            //log.info(fs[i].getAbsolutePath());
            if (fs[i].isDirectory()) {
                try {
                    showAllFiles(fs[i]);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
            file_list.add(fs[i].getAbsolutePath());
        }
        return file_list;
    }

    public static final String pic1 = "A7D82362B79C043782DC04FC8120036A";

    /**
     * 替换某文件夹下面所有默认图片
     *
     * @throws Exception
     */
    public static void replaceFiles() throws Exception {

        File root = new File("/mnt/apk/album");
        List<String> fList = showAllFiles(root);
        for (int i = 0; i < fList.size(); i++) {
            if (EnDecryptUtils.md5Encrypt(fList.get(i)).equals(pic1)) {//替换图片
                log.info(i + "---" + fList.get(i));
                String new_pic = getRandomPic(fList);

                writeFile(fList, i, new_pic);
            }
        }
    }

    /**
     * @param fList
     * @param i
     * @param new_pic
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void writeFile(List<String> fList, int i, String new_pic)
            throws FileNotFoundException, IOException {
        //写入文件
        FileInputStream in = new FileInputStream(new_pic);
        FileOutputStream bos = new FileOutputStream(fList.get(i));
        byte[] buffer = new byte[1024];
        int len = 0;
        while (-1 != (len = in.read(buffer, 0, 1024))) {
            bos.write(buffer, 0, len);
        }
        try {
            in.close();
            bos.close();
        } catch (Exception e) {

            log.error("------->", e);
        }
    }


    /**
     * @return
     * @desc 随机取出一个数【size 为  10 ，取得类似0-9的区间数】
     */
    public static Integer getRandomOne(List<?> list) {


        Random random = new Random();
        int number = -1;
        int max = list.size();

        //size 为  10 ，取得类似0-9的区间数
        number = Math.abs(random.nextInt() % max);

        return number;

    }

    /**
     * 取得一张不是默认图的图片地址
     *
     * @param list
     * @return
     */
    public static String getRandomPic(List<String> list) {
        String path = "";
        try {
            path = list.get(getRandomOne(list));
            if (EnDecryptUtils.md5Encrypt(path).equals(pic1)) {
                getRandomPic(list);
            }
        } catch (Exception e) {
            log.error("------->", e);
        }
        return path;
    }


    /**
     * 读取文件返回string
     *
     * @param Path
     * @return
     */
    public static List<String> ReadFile(String Path) {
        File file = new File(Path);
        List<String> list = Lists.newArrayList();
        Scanner scanner = null;
        try {
            scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                StringBuilder buffer = new StringBuilder();
                buffer.append(scanner.nextLine());
                list.add(buffer.toString());
            }

        } catch (FileNotFoundException e) {


        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return list;
    }


    /**
     * 重命名酷我下载的音乐名字
     */
    private static void reNameMusicFiles() throws UnsupportedEncodingException {
        String path = "/Users/bruce/Music/down";

        File dir = new File(path);

        List<File> fileNames = Arrays.asList(dir.listFiles());

        for (int i = 0; i < fileNames.size(); i++) {
            //拿到  三年二班-周杰伦-238209.mp3
            String fileName = fileNames.get(i).getName();
            log.info(fileName);
            List<String> strings = Splitter.on("-").omitEmptyStrings().splitToList(fileName);
            String reName = strings.get(0) + "-" + new String(strings.get(1).getBytes(), StandardCharsets.UTF_8) + ".mp3";
            log.info(reName);

            String pathname = path + "/" + reName;

            fileNames.get(i).renameTo(new File(pathname));


        }
    }

    /**
     * 重命名酷我下载的音乐名字
     */
    private static void reNameMusicFilesCharSet() throws UnsupportedEncodingException {
        String path = "/Users/bruce/Music/down";

        File dir = new File(path);

        List<File> fileNames = Arrays.asList(dir.listFiles());

        for (int i = 0; i < fileNames.size(); i++) {
            //拿到  三年二班-周杰伦.mp3
            String fileName = fileNames.get(i).getName();
            log.info(fileName);
            String reName = new String(fileName.getBytes("GBK"), StandardCharsets.UTF_8);
            log.info(reName);

//            String pathname = new String((path + "/" + reName).getBytes(),"utf-8");
//
//            fileNames.get(i).renameTo(new File(pathname));


        }
    }

    /**
     * 重命名解析B站下载的视频
     */
    private static void reNameBliBiliFile() {
        String path = "E:\\学习视频\\flink从入门到精通-星火哥";

        File dir = new File(path);

        List<File> fileNames = Arrays.asList(dir.listFiles());

        for (int i = 0; i < fileNames.size(); i++) {
            //拿到eg. 67Flink的Checkpoint详解
            String rename = fileNames.get(i).getName();
            log.info(rename);
            File fileN = fileNames.get(i);
            List<File> filesNNames = Arrays.asList(fileN.listFiles());

            for (File filesNName : filesNNames) {
                File[] realFiles = filesNName.listFiles();
                for (File realFile : realFiles) {
                    String pathname = "E:\\学习视频\\flink从入门到精通-星火哥\\" + rename + ".mp4";
                    log.info(pathname);
                    realFile.renameTo(new File(pathname));
                }
            }
            log.info(JsonUtil.object2jsonWriteNullValue(filesNNames));

        }
    }

    /**
     * 一键根据B站下载文件重命名成实体Json内的名称
     */
    private static void renameBiliFile() throws Exception {
        String basePath = "E:\\电影\\331504090";

        File dir = new File(basePath);

        List<File> fileNames = Arrays.asList(dir.listFiles());

        for (int i = 0; i < fileNames.size(); i++) {
            String ep_path = fileNames.get(i).getName();
            ep_path = basePath + "\\" + ep_path;
            log.info(ep_path);

            File dir_ep = new File(ep_path);
            File[] ep_files = dir_ep.listFiles();
            for (File ep_file : ep_files) {
                if (ep_file.getName().equals("entry.json")) {
                    String json = IOUtils.toString(new FileInputStream(ep_file), StandardCharsets.UTF_8);
                    BiliVO biliVO = JsonUtil.json2object(json, BiliVO.class);
                    String title = biliVO.getPageData().getPart();
                    System.err.println(title);


                    assert StringUtils.isNotEmpty(title);

                    //重命名视频和音频文件
                    String videoPath = ep_path + "\\" + "80";
                    File dir_video = new File(videoPath);
                    File[] vd_list = dir_video.listFiles();
                    for (File vd : vd_list) {
                        if (vd.getName().equals("audio.m4s")) {
                            String audioName = ep_path + "\\" + title + ".mp3";
                            log.info(audioName);
                            vd.renameTo(new File(audioName));
                        }
                        if (vd.getName().equals("video.m4s")) {
                            String videoName = ep_path + "\\" + title + ".mp4";
                            log.info(videoName);
                            vd.renameTo(new File(videoName));
                        }
                    }
                }

            }

        }
    }

    /**
     * 按行读取excel数据
     *
     * @param path
     * @return
     */
    public static List<List<String>> readExcel(String path) {
        List<List<String>> list = new ArrayList<List<String>>();
        try {
            Workbook wb;
            InputStream is = null;
            try {
                is = new FileInputStream(path);
                //读取2007版Excel
                wb = new XSSFWorkbook(is);
            } catch (Exception e) {
                //防止异常导致输入流关闭
                is = new FileInputStream(path);
                //读取2003版Excel
                wb = new HSSFWorkbook(is);
            }
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                //读取Sheet
                Sheet sheet = wb.getSheetAt(i);
                if (sheet == null) {
                    continue;
                }
                //处理当前页，循环每一行
                for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
                    //得到当前行
                    Row row = sheet.getRow(j);
                    //当前行第一个单元格
                    int minCells = row.getFirstCellNum();
                    //当前行最后一个单元格
                    int maxCells = row.getLastCellNum();
                    List<String> sl = new ArrayList<String>();
                    for (int k = minCells; k < maxCells; k++) {
                        //每一个单元格
                        Cell cell = row.getCell(k);
                        if (cell == null) {
                            continue;
                        }
                        sl.add(cell.toString());
                    }
                    list.add(sl);
                }
            }
            if (is != null) {
                is.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 通过url下载图片保存到本地  urlString 图片链接地址  localImgName 图片名称
     *
     * @param urlString
     * @param path
     * @param localImgName
     * @throws Exception
     */
    public static void downloadUrlFile2Local(String urlString, String path, String localImgName) throws Exception {

        byte[] bs = HttpGetUtils.getImg(urlString);
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File file = new File(localImgName);//本地路径及图片名称
        File fold = new File(path);
        if (!file.exists() && !fold.isDirectory()) {
            fold.mkdirs();
            System.out.println("创建文件夹--->" + path);
        }
        //写入文件
        FileOutputStream outputStream = new FileOutputStream(file, true);

        IOUtils.write(bs,outputStream);
        System.out.println(localImgName);
        // 完毕，关闭所有链接
        outputStream.close();

    }


    public static void main(String[] args) throws Exception {


//        renameBiliFile();
        String ulr = "https://www.0daydown.com/wp-content/uploads/2022/03/h6mLmxkjXFkSajAdQ7v63KwfJKrTmmPk-180x180.png";

        byte[] img = HttpGetUtils.getImg(ulr);

        System.err.println(img);

        //写入文件
        FileOutputStream outputStream = new FileOutputStream(new File("C:\\APP\\222.PNG"), true);
        IOUtils.write(img,outputStream);

    }


}

