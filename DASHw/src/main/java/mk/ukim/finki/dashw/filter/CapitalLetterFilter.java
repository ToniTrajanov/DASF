package mk.ukim.finki.dashw.filter;

import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;


public class CapitalLetterFilter implements Filter<String> {

    @Override
    public String execute(String input) {

        Workbook workbook = new Workbook();
        workbook.loadFromFile(input);


        Worksheet worksheet = workbook.getWorksheets().get(0);

        for (int i = 2; i < worksheet.getRows().length; i++) {
            String text = worksheet.get(i, 1).getText();
            if(text.isBlank()){
                continue;
            }
            String firstLetter = text.substring(0, 1).toUpperCase();
            String capitalized = firstLetter + text.substring(1);
            worksheet.get(i, 1).setText(capitalized);
        }


        workbook.saveToFile(input, ExcelVersion.Version2013);

        return input;
    }
}
