package cn.northpark.test;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

/**
 * @author bruce
 * @date 2023年11月30日 21:20:10
 */
public class WordDocumentGenerator {
    public static void main(String[] args) {
        String excelFilePath = "C:\\Users\\Bruce\\Desktop\\data.xlsx";
        String templateFilePath = "C:\\Users\\Bruce\\Desktop\\template.docx";
        String outputFolderPath = "C:\\Users\\Bruce\\Desktop\\output\\";

        try {
            // 加载Excel数据
            FileInputStream fileInputStream = new FileInputStream(excelFilePath);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet personalSheet = workbook.getSheet("T_PERSONAL");
            XSSFSheet educationSheet = workbook.getSheet("T_PERSONAL_EDUCATION");
            XSSFSheet professionSheet = workbook.getSheet("T_PERSONAL_PROFESSION");

            // 创建关联数据的映射
            Map<String, Map<String, String>> personalData = new HashMap<>();
            Map<String, List<Map<String, String>>> educationData = new HashMap<>();
            Map<String, List<Map<String, String>>> professionData = new HashMap<>();

            // 读取个人信息表数据
            for (int i = 1; i <= personalSheet.getLastRowNum(); i++) {
                XSSFRow row = personalSheet.getRow(i);
                String id = getStringCellValue(row.getCell(0));
                Map<String, String> personalInfo = new HashMap<>();
                personalInfo.put("ID", id);
                personalInfo.put("NAME", getStringCellValue(row.getCell(1)));
                personalInfo.put("PHONE", getStringCellValue(row.getCell(2)));
                personalInfo.put("BIR_DATE", getStringCellValue(row.getCell(3)));
                personalInfo.put("SCHOOL", getStringCellValue(row.getCell(4)));
                personalInfo.put("PROVINCE", getStringCellValue(row.getCell(5)));
                personalInfo.put("WEIGHT", getStringCellValue(row.getCell(6)));
                personalData.put(id, personalInfo);
            }

            // 读取教育经历表数据
            for (int i = 1; i <= educationSheet.getLastRowNum(); i++) {
                XSSFRow row = educationSheet.getRow(i);
                String userId = getStringCellValue(row.getCell(1));
                Map<String, String> educationInfo = new HashMap<>();
                educationInfo.put("ID", getStringCellValue(row.getCell(0)));
                educationInfo.put("SCHOOL_NAME", getStringCellValue(row.getCell(2)));
                educationInfo.put("SCHOOL_ADDRESS", getStringCellValue(row.getCell(3)));
                educationInfo.put("CREATE_BY", getStringCellValue(row.getCell(4)));
                educationInfo.put("CREATE_TIME", getStringCellValue(row.getCell(5)));
                educationInfo.put("UPDATE_TIME", getStringCellValue(row.getCell(6)));
                educationInfo.put("UPDATE_BY", getStringCellValue(row.getCell(7)));

                if (educationData.containsKey(userId)) {
                    educationData.get(userId).add(educationInfo);
                } else {
                    educationData.put(userId, new ArrayList<>(Arrays.asList(educationInfo)));
                }
            }

            // 读取职业经历表数据
            for (int i = 1; i <= professionSheet.getLastRowNum(); i++) {
                XSSFRow row = professionSheet.getRow(i);
                String userId = getStringCellValue(row.getCell(1));
                Map<String, String> professionInfo = new HashMap<>();
                professionInfo.put("ID", getStringCellValue(row.getCell(0)));
                professionInfo.put("WORK_NAME", getStringCellValue(row.getCell(2)));
                professionInfo.put("WORK_COMPANY", getStringCellValue(row.getCell(3)));
                professionInfo.put("CREATE_BY", getStringCellValue(row.getCell(4)));
                professionInfo.put("CREATE_TIME", getStringCellValue(row.getCell(5)));
                professionInfo.put("UPDATE_BY", getStringCellValue(row.getCell(6)));
                professionInfo.put("UPDATE_TIME", getStringCellValue(row.getCell(7)));
                professionInfo.put("SP_N31", getStringCellValue(row.getCell(8)));
                professionInfo.put("SP_N32", getStringCellValue(row.getCell(9)));

                if (professionData.containsKey(userId)) {
                    professionData.get(userId).add(professionInfo);
                } else {
                    professionData.put(userId, new ArrayList<>(Arrays.asList(professionInfo)));
                }
            }

            // 处理关联数据并生成Word文档
            for (String userId : personalData.keySet()) {
                Map<String, String> personalInfo = personalData.get(userId);
                String name = personalInfo.get("NAME");
                String outputFilePath = outputFolderPath + name + ".docx";

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


                // 插入职业经历表格
                if (professionData.containsKey(userId)) {
                    List<Map<String, String>> professionInfoList = professionData.get(userId);
                    insertProfessionTable(document, professionInfoList);
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

        if (cell.getCellType() == CellType.NUMERIC) {
            // 处理数字类型的学号
            return String.valueOf((int) cell.getNumericCellValue());
        } else {
            // 处理字符串类型的学号
            return cell.getStringCellValue().trim();
        }
    }

    private static void replacePlaceholder(XWPFDocument document, Map<String, String> data) {
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            List<XWPFRun> runs = paragraph.getRuns();
            if (runs != null) {
                for (XWPFRun run : runs) {
                    String text = run.getText(0);
                    if (text != null) {
                        for (String key : data.keySet()) {
                            if (text.contains(key)) {
                                text = text.replace(key, data.get(key));
                                run.setText(text, 0);
                            }
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
