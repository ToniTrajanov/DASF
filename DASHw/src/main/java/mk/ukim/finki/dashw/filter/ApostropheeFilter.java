package mk.ukim.finki.dashw.filter;

import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

public class ApostropheeFilter implements Filter<String> {
    @Override
    public String execute(String input) {
        Workbook workbook = new Workbook();
        workbook.loadFromFile(input);

        Worksheet worksheet = workbook.getWorksheets().get(0);

        for (int i = 2; i < worksheet.getRows().length; i++) {
                String text = "'" + worksheet.get(i, 2).getText();
                worksheet.get(i, 2).setText(text);

        }
        for (int i = 2; i < worksheet.getRows().length; i++) {

                String text = "'" + worksheet.get(i, 3).getText();
                worksheet.get(i, 3).setText(text);

        }



        workbook.saveToFile(input, ExcelVersion.Version2013);

        return input;
    }
}
