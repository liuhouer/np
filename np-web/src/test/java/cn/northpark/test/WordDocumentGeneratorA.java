package cn.northpark.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author bruce
 * @date 2023年11月30日 21:20:10
 */
@Slf4j
public class WordDocumentGeneratorA {
    public static void main(String[] args) {
        String excelFilePath = "C:\\Users\\Bruce\\Desktop\\data_prod.xlsx";
        String templateFilePath = "C:\\Users\\Bruce\\Desktop\\templateA.docx";
        String outputFolderPath = "C:\\Users\\Bruce\\Desktop\\output\\";

        try {
            // 加载Excel数据
            FileInputStream fileInputStream = new FileInputStream(excelFilePath);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet personalSheet = workbook.getSheet("基本信息");
            XSSFSheet educationSheet = workbook.getSheet("学历信息");
            XSSFSheet jobSheet = workbook.getSheet("工作信息");
            XSSFSheet professionalSheet = workbook.getSheet("职称情况");
            XSSFSheet memberSheet = workbook.getSheet("家庭成员");

            // 创建关联数据的映射
            Map<String, Map<String, String>> personalData = new HashMap<>();
            Map<String, List<Map<String, String>>> educationData = new HashMap<>();
            Map<String, List<Map<String, String>>> jobData = new HashMap<>();
            Map<String, List<Map<String, String>>> professionData = new HashMap<>();
            Map<String, List<Map<String, String>>> memberData = new HashMap<>();

            // 读取个人信息表数据

            // 基本信息标题：
            String[] fieldTitles = {
                    "一级部门","二级部门","部门","人员编号","人员类别","用工类型","姓名","性别",
                    "出生日期","年龄","入职日期","政治面貌","参加党派日期","民族","婚姻状况",
                    "籍贯","开户行","银行卡号","国籍","手机号码","证件类型","证件号码","户口所在地",
                    "现住址","户口性质","户籍住址","上班地点","工作类别","一级工作类别","财务类别",
                    "上班地点描述","直属上级","个人邮箱","兼职岗位","集团首次入职时间","职级","职位职级",
                    "司龄","转正日期","试用期期限","社会工龄","合同开始日期","合同结束日期","企业邮箱",
                    "首次参加工作时间","退休日期","职务","数据安全员","最高学历","最高学位","学位获得时间",
                    "最高毕业院校","毕业时间","所学专业","第一学历","第一学历所学专业","第一学历毕业院校",
                    "第一学历毕业时间","紧急联系人","紧急联系人电话","与本人关系","合同期限（年）","合同类型",
                    "档案编号","合同归属","计税日期","离职日期","离职类型","离职原因","任职形式"
            };

            // 读取个人信息表数据
            for (int i = 1; i <= personalSheet.getLastRowNum(); i++) {
                XSSFRow row = personalSheet.getRow(i);
                String id = getStringCellValue(row.getCell(3));//人员编号
                Map<String, String> personalInfo = new HashMap<>();
                for (int j = 0; j < fieldTitles.length; j++) {
                    String fieldValue = getStringCellValue(row.getCell(j));
                    personalInfo.put(fieldTitles[j], fieldValue);
                }
                personalData.put(id, personalInfo);
            }

//            log.info("personalData-----,{}",personalData);


            // 教育信息标题：
            String[] eduTitles = {
                    "部门", "人员编号", "姓名", "任职形式", "学历", "学位", "学习形式", "所学专业",
                    "入学时间", "毕业时间", "毕业学校", "毕业证书号", "备注", "是否最高学历", "是否第一学历", "操作者"
            };

            // 读取教育经历表数据
//            for (int i = 1; i <= educationSheet.getLastRowNum(); i++) {
//                XSSFRow row = educationSheet.getRow(i);
//                String userId = getStringCellValue(row.getCell(0));
//                Map<String, String> educationInfo = new HashMap<>();
//                for (int j = 0; j < eduTitles.length; j++) {
//                    String fieldValue = getStringCellValue(row.getCell(j));
//                    educationInfo.put(eduTitles[j], fieldValue);
//                }
//
//                if (educationData.containsKey(userId)) {
//                    educationData.get(userId).add(educationInfo);
//                } else {
//                    educationData.put(userId, new ArrayList<>(Arrays.asList(educationInfo)));
//                }
//            }


            // 工作经历信息标题：
            String[] jobTitles = {
                    "一级部门","人员编号","姓名","籍贯","职务","开始时间","终止时间",
                    "所在单位","所从事的工作/担任的职位","证明人","工作内容","工作业绩","任职形式","审批标记"
            };
            // 读取工作经历表数据
            for (int i = 1; i <= jobSheet.getLastRowNum(); i++) {
                XSSFRow row = jobSheet.getRow(i);
                String userId = getStringCellValue(row.getCell(1));
                Map<String, String> jobInfo = new HashMap<>();
                for (int j = 0; j < jobTitles.length; j++) {
                    String fieldValue = getStringCellValue(row.getCell(j));
                    jobInfo.put(jobTitles[j], fieldValue);
                }

                if (jobData.containsKey(userId)) {
                    jobData.get(userId).add(jobInfo);
                } else {
                    jobData.put(userId, new ArrayList<>(Arrays.asList(jobInfo)));
                }
            }

            // 职称情况信息标题：
//            String[] professionalTitles = {
//                    "部门",	"人员编号",	"姓名",	"任职形式",	"证书",	"职称系列",
//
//                    "职称名称", "职称等级", "职称专业", "职称证号", "职称获取时间", "授予单位",
//                    "是否最高职称", "备注", "操作者", "操作时间", "审批标记"
//            };
//
//            // 读取职称情况信息数据
//            for (int i = 1; i <= professionalSheet.getLastRowNum(); i++) {
//                XSSFRow row = professionalSheet.getRow(i);
//                String userId = getStringCellValue(row.getCell(1));
//                Map<String, String> professionInfo = new HashMap<>();
//                for (int j = 0; j < professionalTitles.length; j++) {
//                    String fieldValue = getStringCellValue(row.getCell(j));
//                    professionInfo.put(professionalTitles[j], fieldValue);
//                }
//
//                if (professionData.containsKey(userId)) {
//                    professionData.get(userId).add(professionInfo);
//                } else {
//                    professionData.put(userId, new ArrayList<>(Arrays.asList(professionInfo)));
//                }
//            }

            // 家庭成员信息标题：
            String[] memberTitles = {
                    "一级部门","二级部门","用工类型","人员类别","人员编号","姓名","性别","年龄","婚姻状况","入职日期",
                    "成员姓名","与本人关系","成员年龄","工作单位","职务/岗位","联系电话","成员出生日期","任职形式","审批标记"
            };

            // 读取家庭成员信息数据
            for (int i = 1; i <= memberSheet.getLastRowNum(); i++) {
                XSSFRow row = memberSheet.getRow(i);
                String userId = getStringCellValue(row.getCell(4));
                Map<String, String> memberInfo = new HashMap<>();
                for (int j = 0; j < memberTitles.length; j++) {
                    String fieldValue = getStringCellValue(row.getCell(j));
                    memberInfo.put(memberTitles[j], fieldValue);
                }

                if (memberData.containsKey(userId)) {
                    memberData.get(userId).add(memberInfo);
                } else {
                    memberData.put(userId, new ArrayList<>(Arrays.asList(memberInfo)));
                }
            }

            // 处理关联数据并生成Word文档
            for (String userId : personalData.keySet()) {
                Map<String, String> personalInfo = personalData.get(userId);
                String name = personalInfo.get("姓名");
                String outputFilePath = outputFolderPath + name+"_"+userId + ".docx";

                // 加载Word文档模板
                FileInputStream templateInputStream = new FileInputStream(templateFilePath);
                        XWPFDocument document = new XWPFDocument(templateInputStream);

                // 替换文档中的占位符
                replacePlaceholder(document, personalInfo);

                // 插入教育经历表格
                if (educationData.containsKey(userId)) {
                    List<Map<String, String>> educationInfoList = educationData.get(userId);
                    insertEducationTable(document, educationInfoList);
                }


                // 插入职称等级表格
                if (professionData.containsKey(userId)) {
                    List<Map<String, String>> professionInfoList = professionData.get(userId);

                    StringBuilder stringBuilder = new StringBuilder();
                    for (Map<String, String> professionInfo : professionInfoList) {
                        String level = professionInfo.get("职称等级");
                        String specialty = professionInfo.get("职称专业");

                        stringBuilder.append(level).append("|").append(specialty).append(" ");
                    }

                    String levelSpec = stringBuilder.toString().trim();

                    Map levelMap = new HashMap();
                    levelMap.put("levelSpec",levelSpec);
                    // 替换文档中的占位符
                    replacePlaceholder(document, levelMap);
                }

                // 插入职业经历表格
                if (jobData.containsKey(userId)) {
                    List<Map<String, String>> jobList = jobData.get(userId);

                    StringBuilder stringBuilder = new StringBuilder();
                    for (Map<String, String> jobInfo : jobList) {
                        String startTime = jobInfo.get("开始时间");
                        String endTime = jobInfo.get("终止时间");
                        String company = jobInfo.get("所在单位");
                        String position = jobInfo.get("所从事的工作/担任的职位");

                        stringBuilder.append(startTime).append("\t")
                                .append(endTime).append("\t")
                                .append(company).append("\t")
                                .append(position).append("\n");
                    }

                    String jobDetail = stringBuilder.toString();

                    Map jobDetailMap = new HashMap();
                    jobDetailMap.put("jobDetail",jobDetail);
                    // 替换文档中的占位符
                    replaceJobDetail(document, jobDetail);
                }else {
                    String jobDetail = "";
                    // 替换文档中的占位符
                    replaceJobDetail(document, jobDetail);
                }

                //与本人关系	成员姓名	成员年龄	政治面貌	工作单位
                // 插入家庭成员
                if (memberData.containsKey(userId)) {
                    List<Map<String, String>> memberList = memberData.get(userId);

                    for (int i = 0; i < memberList.size(); i++) {

                        Map<String, String> memberInfo = memberList.get(i);
                        String rel = memberInfo.get("与本人关系");
                        String memberName = memberInfo.get("成员姓名");
                        String age = memberInfo.get("成员年龄");
                        String politic = memberInfo.get("政治面貌");
                        String company = memberInfo.get("工作单位");
                        String job = memberInfo.get("职务/岗位");
                        Map memberMap = new HashMap();
                        memberMap.put("与本人关系"+"_"+i,rel);
                        memberMap.put("成员姓名"+"_"+i,memberName);
                        memberMap.put("成员年龄"+"_"+i,age);
                        memberMap.put("政治面貌"+"_"+i,politic);
                        memberMap.put("工作单位"+"_"+i,company);
                        memberMap.put("职务/岗位"+"_"+i,job);
                        // 替换文档中的占位符
                        replacePlaceholder(document, memberMap);
                    }
                    if(memberList.size()<6){
                        for (int i = memberList.size(); i < 7; i++) {
                            Map memberMap = new HashMap();
                            memberMap.put("与本人关系"+"_"+i,"");
                            memberMap.put("成员姓名"+"_"+i,"");
                            memberMap.put("成员年龄"+"_"+i,"");
                            memberMap.put("政治面貌"+"_"+i,"");
                            memberMap.put("工作单位"+"_"+i,"");
                            memberMap.put("职务/岗位"+"_"+i,"");
                            // 替换文档中的占位符
                            replacePlaceholder(document, memberMap);
                        }
                    }

                }else{
                    for (int i = 0; i < 7; i++) {
                        Map memberMap = new HashMap();
                        memberMap.put("与本人关系"+"_"+i,"");
                        memberMap.put("成员姓名"+"_"+i,"");
                        memberMap.put("成员年龄"+"_"+i,"");
                        memberMap.put("政治面貌"+"_"+i,"");
                        memberMap.put("工作单位"+"_"+i,"");
                        memberMap.put("职务/岗位"+"_"+i,"");
                        // 替换文档中的占位符
                        replacePlaceholder(document, memberMap);
                    }
                }

                // 保存生成的Word文档
                File outputFile = new File(outputFilePath);
                File parentDirectory = outputFile.getParentFile();

                // 检查父目录是否存在，如果不存在则创建
                if (!parentDirectory.exists()) {
                    boolean created = parentDirectory.mkdirs();
                    if (!created) {
                        // 目录创建失败，进行相应的错误处理
                        System.out.println("无法创建目录：" + parentDirectory.getAbsolutePath());
                        return;
                    }
                }
                FileOutputStream outputStream = new FileOutputStream(outputFilePath);
                document.write(outputStream);
                outputStream.close();

                System.out.println("Generated document for user: " + name);
            }

            // 关闭工作簿和文件流
            workbook.close();
            fileInputStream.close();

            System.out.println("Document generation completed.");
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }

    private static String getStringCellValue(org.apache.poi.ss.usermodel.Cell cell) {
        if (cell == null) {
            return "";
        }

        try {
            if (cell.getCellType() == CellType.NUMERIC) {
                // 处理数字类型的学号
                return String.valueOf((int) cell.getNumericCellValue());
            } else {
                // 处理字符串类型的学号
                return cell.getStringCellValue().trim();
            }
        }catch (Exception e){

            return "";
        }


    }


    /**
     * 替换工作经历
     * @param document
     * @param jobDetail
     */
    private static void replaceJobDetail(XWPFDocument document, String jobDetail) {
        for (XWPFTable table : document.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    List<XWPFParagraph> paragraphs = cell.getParagraphs();
                    CopyOnWriteArrayList<XWPFParagraph> paras = new CopyOnWriteArrayList(paragraphs);
                    for (XWPFParagraph paragraph : paras) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        if (runs != null) {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (XWPFRun run : runs) {
                                String text = run.getText(0);
                                if (text != null) {
                                    stringBuilder.append(text);
                                }
                            }
                            String combinedText = stringBuilder.toString();
                            if (combinedText.contains("{jobDetail}")) {
                                // 移除原始段落中的所有运行
                                for (int i = paragraph.getRuns().size() - 1; i >= 0; i--) {
                                    paragraph.removeRun(i);
                                }
                                // 添加替换后的文本
                                String[] lines = jobDetail.split("\n");
                                for (int i = 0; i < lines.length; i++) {
                                    XWPFParagraph newParagraph = cell.addParagraph();
                                    XWPFRun newRun = newParagraph.createRun();
                                    newRun.setText(lines[i]);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void replacePlaceholder(XWPFDocument document, Map<String, String> data) {
        // 替换段落中的文本
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            List<XWPFRun> runs = paragraph.getRuns();
            if (runs != null) {
                StringBuilder stringBuilder = new StringBuilder();
                for (XWPFRun run : runs) {
                    String text = run.getText(0);
                    if (text != null) {
                        stringBuilder.append(text);
                    }
                }
                String combinedText = stringBuilder.toString();

                log.info(combinedText);
                for (String key : data.keySet()) {
                    if (combinedText.contains("{" + key + "}")) {
                        String replacement = data.get(key);
                        // 如果替换文本中包含换行符，则进行处理
                            combinedText = combinedText.replace("{" + key + "}", replacement);
                    }
                }
                // 清除原始运行
                for (int i = runs.size() - 1; i >= 0; i--) {
                    paragraph.removeRun(i);
                }
                // 添加替换后的文本
                XWPFRun newRun = paragraph.createRun();
                newRun.setText(combinedText);
            }
        }

        // 替换表格中的文本
        for (XWPFTable table : document.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph paragraph : cell.getParagraphs()) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        if (runs != null) {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (XWPFRun run : runs) {
                                String text = run.getText(0);
                                if (text != null) {
                                    stringBuilder.append(text);
                                }
                            }
                            String combinedText = stringBuilder.toString();
                            log.info(combinedText);
                            for (String key : data.keySet()) {
                                if (combinedText.contains("{" + key + "}")) {
                                    String replacement = data.get(key);
                                    // 如果替换文本中包含换行符，则进行处理
                                     combinedText = combinedText.replace("{" + key + "}", replacement);
                                }
                            }
                            // 清除原始运行
                            for (int i = runs.size() - 1; i >= 0; i--) {
                                paragraph.removeRun(i);
                            }
                            // 添加替换后的文本
                            XWPFRun newRun = paragraph.createRun();
                            newRun.setText(combinedText);
                        }
                    }
                }
            }
        }
    }



    private static void insertEducationTable(XWPFDocument document, List<Map<String, String>> educationData) {
        XWPFTable table = document.createTable();

        // 获取表格的CTTbl对象
        CTTbl ctTbl = table.getCTTbl();

        // 创建CTTblBorders对象并设置边框样式
        CTTblBorders tblBorders = ctTbl.getTblPr().addNewTblBorders();
        tblBorders.addNewTop().setVal(STBorder.SINGLE); // 设置顶部边框样式
        tblBorders.addNewBottom().setVal(STBorder.SINGLE); // 设置底部边框样式
        tblBorders.addNewLeft().setVal(STBorder.NONE); // 设置左侧边框样式
        tblBorders.addNewRight().setVal(STBorder.NONE); // 设置右侧边框样式
        tblBorders.addNewInsideH().setVal(STBorder.NONE); // 设置水平内部边框样式
        tblBorders.addNewInsideV().setVal(STBorder.NONE); // 设置垂直内部边框样式

        // 创建表头
        XWPFTableRow headerRow = table.getRow(0);
        headerRow.getCell(0).setText("学校名称");
        headerRow.createCell().setText("学校地址");
        headerRow.createCell().setText("创建人");
        headerRow.createCell().setText("创建时间");
        headerRow.createCell().setText("更新时间");
        headerRow.createCell().setText("更新人");

        // 插入数据
        for (Map<String, String> educationInfo : educationData) {
            XWPFTableRow dataRow = table.createRow();
            dataRow.getCell(0).setText(educationInfo.get("SCHOOL_NAME"));
            dataRow.getCell(1).setText(educationInfo.get("SCHOOL_ADDRESS"));
            dataRow.getCell(2).setText(educationInfo.get("CREATE_BY"));
            dataRow.getCell(3).setText(educationInfo.get("CREATE_TIME"));
            dataRow.getCell(4).setText(educationInfo.get("UPDATE_TIME"));
            dataRow.getCell(5).setText(educationInfo.get("UPDATE_BY"));

        }


        // 插入空行
        XWPFTableRow emptyRow2 = table.createRow();
        emptyRow2.setHeight(20); // 设置空行的高度，可根据需要进行调整
    }

    private static void insertProfessionTable(XWPFDocument document, List<Map<String, String>> professionData) {
        XWPFTable table = document.createTable();

        // 获取表格的CTTbl对象
        CTTbl ctTbl = table.getCTTbl();
        // 创建CTTblBorders对象并设置边框样式
        CTTblBorders tblBorders = ctTbl.getTblPr().addNewTblBorders();
        tblBorders.addNewTop().setVal(STBorder.SINGLE); // 设置顶部边框样式
        tblBorders.addNewBottom().setVal(STBorder.SINGLE); // 设置底部边框样式
        tblBorders.addNewLeft().setVal(STBorder.NONE); // 设置左侧边框样式
        tblBorders.addNewRight().setVal(STBorder.NONE); // 设置右侧边框样式
        tblBorders.addNewInsideH().setVal(STBorder.NONE); // 设置水平内部边框样式
        tblBorders.addNewInsideV().setVal(STBorder.NONE); // 设置垂直内部边框样式

        // 创建表头
        XWPFTableRow headerRow = table.getRow(0);

        // 加粗
        for (int i = 0; i < headerRow.getTableCells().size(); i++) {
            XWPFTableCell cell = headerRow.getCell(i);

            // 创建XWPFRun对象并设置字体加粗
            XWPFRun run = cell.getParagraphs().get(0).createRun();
            run.setBold(true);
        }

        headerRow.getCell(0).setText("工作名称");
        headerRow.createCell().setText("工作公司");
        headerRow.createCell().setText("创建人");
        headerRow.createCell().setText("创建时间");
        headerRow.createCell().setText("更新人");
        headerRow.createCell().setText("更新时间");
        headerRow.createCell().setText("SP_N31");
        headerRow.createCell().setText("SP_N32");



        // 插入数据
        for (Map<String, String> professionInfo : professionData) {
            XWPFTableRow dataRow = table.createRow();
            dataRow.getCell(0).setText(professionInfo.get("WORK_NAME"));
            dataRow.getCell(1).setText(professionInfo.get("WORK_COMPANY"));
            dataRow.getCell(2).setText(professionInfo.get("CREATE_BY"));
            dataRow.getCell(3).setText(professionInfo.get("CREATE_TIME"));
            dataRow.getCell(4).setText(professionInfo.get("UPDATE_BY"));
            dataRow.getCell(5).setText(professionInfo.get("UPDATE_TIME"));
            dataRow.getCell(6).setText(professionInfo.get("SP_N31"));
            dataRow.getCell(7).setText(professionInfo.get("SP_N32"));
        }
    }
}
