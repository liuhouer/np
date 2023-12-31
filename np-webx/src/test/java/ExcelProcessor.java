import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author bruce
 * @date 2023年09月05日 17:17:35
 */
public class ExcelProcessor {
    public static void updateContactInfo(String filePath) {
        try (Workbook workbook = WorkbookFactory.create(new FileInputStream(filePath))) {
            Sheet industrySheet = workbook.getSheet("行业信息");
            Sheet contactSheet = workbook.getSheet("通讯录");

            // 获取学号和手机号所在的列索引
            int industryIdColumnIndex = getColumnIndex(industrySheet, "学号");
            int contactIdColumnIndex = getColumnIndex(contactSheet, "学号");
            int contactPhoneColumnIndex = getColumnIndex(contactSheet, "手机号");

            if (industryIdColumnIndex != -1 && contactIdColumnIndex != -1 && contactPhoneColumnIndex != -1) {
                // 遍历行业信息工作表的每一行（跳过标题行）
                for (int i = 1; i <= industrySheet.getLastRowNum(); i++) {

                    Row industryRow = industrySheet.getRow(i);

                    // 获取学号单元格
                    Cell industryIdCell = industryRow.getCell(industryIdColumnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    String industryId;
                    if (industryIdCell.getCellType() == CellType.NUMERIC) {
                        // 处理数字类型的学号
                        industryId = String.valueOf((int) industryIdCell.getNumericCellValue());
                    } else {
                        // 处理字符串类型的学号
                        industryId = industryIdCell.getStringCellValue();
                    }

                    // 检查学号是否为纯数字
                    if (industryId.contains("组")) {
                        continue;  // 跳过非纯数字学号的行
                    }
                    // 在通讯录工作表中查找匹配的学号
                    for (int j = 1; j <= contactSheet.getLastRowNum(); j++) {

                        Row contactRow = contactSheet.getRow(j);
                        String contactId = getStringCellValue(contactRow, contactIdColumnIndex);

                        if (industryId.equals(contactId)) {
                            // 获取手机号单元格
                            Cell phoneCell = contactRow.getCell(contactPhoneColumnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                            phoneCell.setCellType(CellType.STRING);  // 设置单元格类型为字符串

                            String phoneNumber = phoneCell.getStringCellValue();

                            // 更新联系方式字段
                            Cell contactCell = industryRow.getCell(industryIdColumnIndex + 5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                            contactCell.setCellValue(phoneNumber);
                            break;  // 找到匹配学号后跳出循环
                        }
                    }
                }

                // 保存更新后的 Excel 文件
                try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                    workbook.write(outputStream);
                }
                System.out.println("联系方式已更新成功！");
            } else {
                System.out.println("未找到学号或手机号字段！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getColumnIndex(Sheet sheet, String columnName) {
        Row firstRow = sheet.getRow(0);
        for (Cell cell : firstRow) {
            if (cell.getStringCellValue().equals(columnName)) {
                return cell.getColumnIndex();
            }
        }
        return -1;  // 未找到指定列名
    }

    private static String getStringCellValue(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

        if (cell.getCellType() == CellType.NUMERIC) {
            // 处理数字类型的学号
            return String.valueOf((int) cell.getNumericCellValue());
        } else {
            // 处理字符串类型的学号
            return cell.getStringCellValue().trim();
        }

    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\Bruce\\Desktop\\信息收集.xlsx";
        updateContactInfo(filePath);
    }
}
