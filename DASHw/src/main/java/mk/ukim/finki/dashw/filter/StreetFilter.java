package mk.ukim.finki.dashw.filter;

import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

public class StreetFilter implements Filter<String> {
    @Override
    public String execute(String input) {

        Workbook workbook = new Workbook();
        workbook.loadFromFile(input);


        Worksheet worksheet = workbook.getWorksheets().get(0);

        for (int i = 2; i < worksheet.getRows().length; i++) {
            String text = worksheet.get(i, 5).getText();

            if (text.isBlank()) {
                continue;
            }

            String[] parts = text.split(" · ");
            String phoneNumColumn = worksheet.get(i, 9).getText();

            if (parts.length == 2) {
                if (phoneNumColumn.isBlank()) {
                    worksheet.get(i, 9).setText(parts[1]);
                    worksheet.get(i, 5).setText(parts[0]);
                } else {
                    worksheet.get(i, 5).setText(parts[0]);
                }

            } else {
                worksheet.get(i, 5).setText(parts[0]);
            }
        }


        workbook.saveToFile(input, ExcelVersion.Version2013);

        return input;
    }
}
