package com.bangemofa.customersearch.api.support;

import com.bangemofa.customersearch.api.CustomerSearchException;
import com.bangemofa.customersearch.api.SearchUnit;
import com.bangemofa.customersearch.api.SearchUnitProvider;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bruceyuan on 18-8-10.
 */
@Slf4j
public class ExcelSearchUnitProvider implements SearchUnitProvider {
    private Workbook workbook;

    public ExcelSearchUnitProvider(InputStream excel){
        try {
            workbook = WorkbookFactory.create(excel);
        } catch (Exception e) {
            throw new CustomerSearchException
                    ("Excel输入流不合法，ExcelSearchUnitProvider创建失败", e);
        }
    }
    public List<SearchUnit> get() {
        log.info("开始解析excel...");
        Sheet sheet = workbook.getSheetAt(0);
        int rowCount = sheet.getLastRowNum() + 1;
        List<SearchUnit> result = new ArrayList<SearchUnit>(rowCount);
        //跳过标题行
        for (int i = 1; i < rowCount; i++){
            Row row = sheet.getRow(i);

            //第5列为空，代表为省
            //第6列为空，代表为市
            if (Strings.isNullOrEmpty(getCellValue(row.getCell(4)))
                    || Strings.isNullOrEmpty(getCellValue(row.getCell(5)))){
                continue;
            }

            SearchUnit unit = new SearchUnit();
            unit.setProvince(getCellValue(row.getCell(1)));
            unit.setCity(getCellValue(row.getCell(2)));
            unit.setName(getCellValue(row.getCell(3)));
            result.add(unit);
        }
        log.info("excel解析完成，共【{}】行，找到【{}】个SearchUnit。", rowCount, result.size());
        return result;
    }

    public final static String DATE_OUTPUT_PATTERNS = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
            DATE_OUTPUT_PATTERNS);

    public static String getCellValue(Cell cell) {
        String ret = "";
        if (cell == null) return ret;
        CellType type = cell.getCellTypeEnum();
        switch (type) {
            case BLANK:
                ret = "";
                break;
            case BOOLEAN:
                ret = String.valueOf(cell.getBooleanCellValue());
                break;
            case ERROR:
                ret = null;
                break;
            case FORMULA:
                Workbook wb = cell.getSheet().getWorkbook();
                CreationHelper crateHelper = wb.getCreationHelper();
                FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();
                ret = getCellValue(evaluator.evaluateInCell(cell));
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date theDate = cell.getDateCellValue();
                    ret = simpleDateFormat.format(theDate);
                } else {
                    ret = NumberToTextConverter.toText(cell.getNumericCellValue());
                }
                break;
            case STRING:
                ret = cell.getRichStringCellValue().getString();
                break;
            default:
                ret = "";
        }

        return ret; // 有必要自行trim
    }
}
