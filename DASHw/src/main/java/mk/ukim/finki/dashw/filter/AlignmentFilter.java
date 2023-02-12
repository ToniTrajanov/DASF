package mk.ukim.finki.dashw.filter;

import com.spire.xls.ExcelVersion;
import com.spire.xls.HorizontalAlignType;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

public class AlignmentFilter implements Filter<String> {
    @Override
    public String execute(String input) {
        Workbook workbook = new Workbook();
        workbook.loadFromFile(input);

        Worksheet worksheet = workbook.getWorksheets().get(0);

        for (int i = 1; i < worksheet.getRows().length; i++) {
            String text = worksheet.get(i, 2).getText();
            if (text.isBlank()) {
                worksheet.get(i, 2).setHorizontalAlignment(HorizontalAlignType.Left);
            }
        }
        for (int i = 1; i < worksheet.getRows().length; i++) {
            String text = worksheet.get(i, 3).getText();
            if (text.isBlank()) {
                worksheet.get(i, 3).setHorizontalAlignment(HorizontalAlignType.Left);
            }
        }
        for (int i = 1; i < worksheet.getRows().length; i++) {
            String text = worksheet.get(i, 9).getText();
            if (text.isBlank()) {
                worksheet.get(i, 9).setHorizontalAlignment(HorizontalAlignType.Left);
            }
        }

        workbook.saveToFile(input, ExcelVersion.Version2013);

        return input;
    }
}
