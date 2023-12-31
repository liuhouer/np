package cn.northpark.test;

import cn.northpark.utils.IDUtils;
import cn.northpark.utils.NPQueryRunner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;

import java.io.FileInputStream;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author bruce
 * @date 2023年11月30日 21:20:10
 */
@Slf4j
public class WordGeneratorBatch2TAGED {
    public static void main(String[] args) {
        String excelFilePath = "C:\\Users\\Bruce\\Desktop\\np\\2\\data_prod_2023.xlsx";

        try {
            // 加载Excel数据
            FileInputStream fileInputStream = new FileInputStream(excelFilePath);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet personalSheet = workbook.getSheet("基本信息");
            XSSFSheet jobSheet = workbook.getSheet("工作信息");
            XSSFSheet memberSheet = workbook.getSheet("家庭成员");

            // 创建关联数据的映射
            Map<String, Map<String, String>> personalData = new HashMap<>();
            Map<String, List<Map<String, String>>> jobData = new HashMap<>();
            Map<String, List<Map<String, String>>> memberData = new HashMap<>();

            // 读取个人信息表数据

            // 基本信息标题：
            String[] fieldTitles1 = {
                    "一级部门","二级部门","三级部门","部门","人员编号","人员类别","用工类型","姓名","三级部门",
                    "性别","出生日期","年龄","入职日期","政治面貌","参加党派日期","民族","婚姻状况","籍贯",
                    "开户行","银行卡号","国籍","手机号码","个人邮箱","证件类型","证件号码","户口所在地",
                    "现住址","户口性质","户籍住址","上班地点","工作类别","一级工作类别","上班地点描述",
                    "集团首次入职时间","职级","职位职级","司龄","转正日期","社会工龄","合同开始日期",
                    "合同结束日期","企业邮箱","首次参加工作时间","退休日期","职务","最高学历","最高毕业院校",
                    "毕业时间","所学专业","第一学历","第一学历所学专业","第一学历毕业院校","第一学历毕业时间",
                    "紧急联系人","紧急联系人电话","与本人关系","合同期限（年）","档案编号","合同归属","核算主体归属",
                    "出勤卡号","社保类别","计税日期","离职日期","离职类型","离职原因","面试官","数据安全员","任职形式"
            };

            String[] fieldTitles = {
                    "一级部门","二级部门","三级部门","人员编号","人员类别","用工类型","姓名","登录名","性别",
                    "出生日期","年龄","入职日期","政治面貌","参加党派日期","民族","婚姻状况","籍贯","开户行",
                    "银行卡号","国籍","手机号码","证件类型","证件号码","户口所在地","现住址","户口性质","户籍住址",
                    "上班地点","工作类别","一级工作类别","财务类别","上班地点描述","直属上级","个人邮箱","兼职岗位",
                    "集团首次入职时间","职级","职位职级","司龄","转正日期","试用期期限","社会工龄","合同开始日期",
                    "合同结束日期","企业邮箱","首次参加工作时间","退休日期","职务","数据安全员","最高学历","最高学位",
                    "学位获得时间","最高毕业院校","毕业时间","所学专业","第一学历","第一学历所学专业","第一学历毕业院校",
                    "第一学历毕业时间","紧急联系人","紧急联系人电话","与本人关系","合同期限（年）","合同类型","档案编号",
                    "合同归属","核算主体归属","出勤卡号","发薪单位","社保类别","计税日期","离职日期","离职类型","离职原因",
                    "面试官","实际离职原因","部门","岗位","任职形式","部门全称"
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


            // 工作经历信息标题：
            String[] jobTitles1 = {

                    "一级部门","人员编号","姓名","人员类别","籍贯","辅助","职务","集团首次入职时间","开始时间","终止时间",
                    "所在单位","所从事的工作/担任的职位","证明人","工作内容","工作业绩","任职形式","审批标记"
            };

            String[] jobTitles = {

                    "一级部门","人员编号","姓名","籍贯","职务","集团首次入职时间","开始时间","终止时间","所在单位","所从事的工作/担任的职位",
                    "证明人","工作内容","工作业绩","任职形式","审批标记"
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


            // 家庭成员信息标题：
            String[] memberTitles1 = {

                    "一级部门","二级部门","用工类型","人员类别","姓名","性别","出生日期","年龄","民族","婚姻状况",
                    "政治面貌","入职日期","人员编号","成员姓名","与本人关系","成员年龄","登录名","籍贯","国籍",
                    "户口所在地","户口性质","户籍住址","上班地点","工作类别","一级工作类别","集团首次入职时间","职级",
                    "职位职级","司龄","社会工龄","合同开始日期","合同结束日期","企业邮箱","首次参加工作时间","退休日期",
                    "职务","数据安全员","最高学历","最高学位","学位获得时间","最高毕业院校","毕业时间","所学专业","紧急联系人",
                    "紧急联系人电话","与本人关系","合同期限（年）","合同类型","合同归属","离职日期","离职类型","离职原因","实际离职原因","任职形式","审批标记"
            };

            String[] memberTitles = {

                    "一级部门","二级部门","用工类型","人员类别","人员编号","姓名","性别","年龄","集团首次入职时间",
                    "婚姻状况","入职日期","成员姓名","与本人关系","成员年龄","工作单位","职务/岗位","联系电话","成员出生日期","任职形式","审批标记"
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

            List<String> HAVE_IDS = Arrays.asList(

                    "0001"
                      );
            // 处理关联数据
            for (String userId : personalData.keySet()) {
                if(!HAVE_IDS.contains(userId) && StringUtils.isNotBlank(userId)) {

                    //表1=======================================================================
                    Map<String, String> personalInfo = personalData.get(userId);
                    //插入基本信息
                    String insSQL = "INSERT INTO `flink`.`g_employee`(`id`, `first_dept`, `second_dept`, `dept`, `emp_number`, `emp_category`, `empt_type`, " +
                            "`e_name`, `gender`, `birth_date`, `age`, `hire_date`, `political_status`, `party_join_date`, `ethnicity`, `marital_status`, " +
                            "`hometown`, `bank_name`, `bank_account_number`, `nationality`, `phone_number`, `document_type`, `document_number`, `registered_residence`," +
                            " `current_address`, `household_type`, `registered_address`, `work_location`, `job_category`, `first_job_category`, `financial_category`, " +
                            "`work_location_description`, `immediate_supervisor`, `personal_email`, `part_time_position`, `group_first_entry_date`, `job_level`, " +
                            "`position_level`, `company_seniority`, `regularization_date`, `probation_period_duration`, `social_work_experience`, `contract_start_date`, " +
                            "`contract_end_date`, `corporate_email`, `first_empt_date`, `retirement_date`, `position`, `data_security_officer`, `highest_education`, " +
                            "`highest_degree`, `degree_obtained_date`, `highest_graduation_school`, `graduation_date`, `major_study`, `first_education`, `first_education_major`," +
                            " `first_education_graduation_school`, `first_education_graduation_date`, `emergency_contact_person`, `emergency_contact_number`, " +
                            "`relationship_with_employee`, `contract_duration_years`, `contract_type`, `file_number`, `contract_ownership`, `tax_calculation_date`, " +
                            "`departure_date`, `departure_type`, `departure_reason`, `empt_form`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    NPQueryRunner.insert(insSQL,
                            IDUtils.getInstance().getUniqueSceneid(),

                            getStringOrDefaultNull(personalInfo,"一级部门") ,

                            getStringOrDefaultNull(personalInfo,"二级部门") ,

                            getStringOrDefaultNull(personalInfo,"部门") ,

                            getStringOrDefaultNull(personalInfo,"人员编号") ,

                            getStringOrDefaultNull(personalInfo,"人员类别") ,

                            getStringOrDefaultNull(personalInfo,"用工类型") ,

                            getStringOrDefaultNull(personalInfo,"姓名") ,

                            getStringOrDefaultNull(personalInfo,"性别") ,

                            getStringOrDefaultNull(personalInfo,"出生日期") ,

                            getStringOrDefaultNull(personalInfo,"年龄") ,

                            getStringOrDefaultNull(personalInfo,"入职日期") ,

                            getStringOrDefaultNull(personalInfo,"政治面貌") ,

                            getStringOrDefaultNull(personalInfo,"参加党派日期") ,

                            getStringOrDefaultNull(personalInfo,"民族") ,

                            getStringOrDefaultNull(personalInfo,"婚姻状况") ,

                            getStringOrDefaultNull(personalInfo,"籍贯") ,

                            getStringOrDefaultNull(personalInfo,"开户行") ,

                            getStringOrDefaultNull(personalInfo,"银行卡号") ,

                            getStringOrDefaultNull(personalInfo,"国籍") ,

                            getStringOrDefaultNull(personalInfo,"手机号码") ,

                            getStringOrDefaultNull(personalInfo,"证件类型") ,

                            getStringOrDefaultNull(personalInfo,"证件号码") ,

                            getStringOrDefaultNull(personalInfo,"户口所在地") ,

                            getStringOrDefaultNull(personalInfo,"现住址") ,

                            getStringOrDefaultNull(personalInfo,"户口性质") ,

                            getStringOrDefaultNull(personalInfo,"户籍住址") ,

                            getStringOrDefaultNull(personalInfo,"上班地点") ,

                            getStringOrDefaultNull(personalInfo,"工作类别") ,

                            getStringOrDefaultNull(personalInfo,"一级工作类别") ,
                            getStringOrDefaultNull(personalInfo,"财务类别") ,

                            getStringOrDefaultNull(personalInfo,"上班地点描述") ,
                            getStringOrDefaultNull(personalInfo,"直属上级") ,
                            getStringOrDefaultNull(personalInfo,"个人邮箱") ,
                            getStringOrDefaultNull(personalInfo,"兼职岗位") ,

                            getStringOrDefaultNull(personalInfo,"集团首次入职时间") ,

                            getStringOrDefaultNull(personalInfo,"职级") ,

                            getStringOrDefaultNull(personalInfo,"职位职级") ,

                            getStringOrDefaultNull(personalInfo,"司龄") ,

                            getStringOrDefaultNull(personalInfo,"转正日期") ,
                            getStringOrDefaultNull(personalInfo,"试用期期限") ,

                            getStringOrDefaultNull(personalInfo,"社会工龄") ,

                            getStringOrDefaultNull(personalInfo,"合同开始日期") ,

                            getStringOrDefaultNull(personalInfo,"合同结束日期") ,

                            getStringOrDefaultNull(personalInfo,"企业邮箱") ,

                            getStringOrDefaultNull(personalInfo,"首次参加工作时间") ,

                            getStringOrDefaultNull(personalInfo,"退休日期") ,

                            getStringOrDefaultNull(personalInfo,"职务") ,
                            getStringOrDefaultNull(personalInfo,"数据安全员") ,

                            getStringOrDefaultNull(personalInfo,"最高学历") ,
                            getStringOrDefaultNull(personalInfo,"最高学位") ,
                            getStringOrDefaultNull(personalInfo,"学位获得时间") ,

                            getStringOrDefaultNull(personalInfo,"最高毕业院校") ,

                            getStringOrDefaultNull(personalInfo,"毕业时间") ,

                            getStringOrDefaultNull(personalInfo,"所学专业") ,

                            getStringOrDefaultNull(personalInfo,"第一学历") ,

                            getStringOrDefaultNull(personalInfo,"第一学历所学专业"),
                            getStringOrDefaultNull(personalInfo,"第一学历毕业院校"),
                            getStringOrDefaultNull(personalInfo,"第一学历毕业时间"),

                            getStringOrDefaultNull(personalInfo,"紧急联系人") ,

                            getStringOrDefaultNull(personalInfo,"紧急联系人电话" ) ,

                            getStringOrDefaultNull(personalInfo,"与本人关系") ,

                            getStringOrDefaultNull(personalInfo,"合同期限（年）" ) ,
                            getStringOrDefaultNull(personalInfo,"合同类型"   ) ,

                            getStringOrDefaultNull(personalInfo,"档案编号") ,

                            getStringOrDefaultNull(personalInfo,"合同归属") ,

                            getStringOrDefaultNull(personalInfo,"计税日期") ,

                            getStringOrDefaultNull(personalInfo,"离职日期") ,

                            getStringOrDefaultNull(personalInfo,"离职类型") ,

                            getStringOrDefaultNull(personalInfo,"离职原因") ,


                            getStringOrDefaultNull(personalInfo,"任职形式"   )
                            );


                    //表3=======================================================================

                    List<Map<String, String>> memberInfos = memberData.get(userId);
                    if (CollectionUtils.isNotEmpty(memberInfos)) {
                        for (Map<String, String> memberInfo : memberInfos) {

                            String inSQL = "INSERT INTO `flink`.`g_employee_member`(`id`, `first_dept`, `second_dept`, " +
                                    "`empt_type`, `emp_category`, `emp_number`, `name`, `gender`, `age`, `marital_status`," +
                                    " `hire_date`, `family_member_name`, `relationship`, `family_member_age`, `work_unit`, " +
                                    "`position`, `contact_number`, `family_member_birthdate`, `emp_form`, `approval_status`)" +
                                    " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                            NPQueryRunner.insert(inSQL,
                                    IDUtils.getInstance().getUniqueSceneid(),
                                    getStringOrDefaultNull(memberInfo, "一级部门"),
                                    getStringOrDefaultNull(memberInfo, "二级部门"),
                                    getStringOrDefaultNull(memberInfo, "用工类型"),
                                    getStringOrDefaultNull(memberInfo, "人员类别"),
                                    getStringOrDefaultNull(memberInfo, "人员编号"),
                                    getStringOrDefaultNull(memberInfo, "姓名"),
                                    getStringOrDefaultNull(memberInfo, "性别"),
                                    getStringOrDefaultNull(memberInfo, "年龄"),
                                    getStringOrDefaultNull(memberInfo, "婚姻状况"),
                                    getStringOrDefaultNull(memberInfo, "入职日期"),
                                    getStringOrDefaultNull(memberInfo, "成员姓名"),
                                    getStringOrDefaultNull(memberInfo, "与本人关系"),
                                    getStringOrDefaultNull(memberInfo, "成员年龄"),
                                    getStringOrDefaultNull(memberInfo, "工作单位"),
                                    getStringOrDefaultNull(memberInfo, "职务/岗位"),
                                    getStringOrDefaultNull(memberInfo, "联系电话"),
                                    getStringOrDefaultNull(memberInfo, "成员出生日期"),
                                    getStringOrDefaultNull(memberInfo, "任职形式"),
                                    getStringOrDefaultNull(memberInfo, "审批标记"));
                        }

                    }


                    //表2
                    List<Map<String, String>> jobInfos = jobData.get(userId);
                    if (CollectionUtils.isNotEmpty(jobInfos)) {
                        for (Map<String, String> jobInfo : jobInfos) {

                            String inSQL = "INSERT INTO `flink`.`g_employee_work`(`id`, `first_department`, `emp_number`, " +
                                    "`name`, `hometown`, `position`, `start_date`, `end_date`, `organization`, `job_title`," +
                                    " `reference_person`, `job_description`, `job_performance`, `emp_form`, `approval_status`) " +
                                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                            NPQueryRunner.insert(inSQL,
                                    IDUtils.getInstance().getUniqueSceneid(),
                                    getStringOrDefaultNull(jobInfo, "一级部门"),
                                    getStringOrDefaultNull(jobInfo, "人员编号"),
                                    getStringOrDefaultNull(jobInfo, "姓名"),
                                    getStringOrDefaultNull(jobInfo, "籍贯"),
                                    getStringOrDefaultNull(jobInfo, "职务"),
                                    getStringOrDefaultNull(jobInfo, "开始时间"),
                                    getStringOrDefaultNull(jobInfo, "终止时间"),
                                    getStringOrDefaultNull(jobInfo, "所在单位"),
                                    getStringOrDefaultNull(jobInfo, "所从事的工作/担任的职位"),
                                    getStringOrDefaultNull(jobInfo, "证明人"),
                                    getStringOrDefaultNull(jobInfo, "工作内容"),
                                    getStringOrDefaultNull(jobInfo, "工作业绩"),
                                    getStringOrDefaultNull(jobInfo, "任职形式"),
                                    getStringOrDefaultNull(jobInfo, "审批标记")
                                    );


                        }

                    }

                    System.out.println("Generated document for user: " + userId);
                }



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
                                    log.info("key,,,,{}",key);
                                    log.info("replacement,,,,{}",replacement);
                                     combinedText = combinedText.replace("{" + key + "}", StringUtils.isEmpty(replacement)?"":replacement);
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

    private static String getStringOrDefaultNull(Map<String, String> map, String key) {
        String value = map.getOrDefault(key, null);
        return (value != null && !value.isEmpty()) ? value : null;
    }
}
