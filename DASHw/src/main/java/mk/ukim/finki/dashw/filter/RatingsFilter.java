package mk.ukim.finki.dashw.filter;

import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;
import mk.ukim.finki.dashw.filter.Filter;

public class RatingsFilter implements Filter<String> {
    @Override
    public String execute(String input) {
        Workbook workbook = new Workbook();
        workbook.loadFromFile(input);

        Worksheet worksheet = workbook.getWorksheets().get(0);

        for (int i = 2; i < worksheet.getRows().length; i++) {
            String text = worksheet.get(i, 2).getText();
            if (text.isBlank()) {
                worksheet.get(i, 2).setNumberValue(0.0);
            }
            else {
                if(worksheet.get(i,2).getText() == "0.0")
                {
                    continue;
                }
                String [] parts = text.split(",");
                String num = parts[0] + "." + parts[1];
                worksheet.get(i,2).setNumberValue(Double.parseDouble(num));
            }
        }
//        for (int i = 2; i < worksheet.getRows().length; i++) {
//            String text = worksheet.get(i, 3).getText();
//            if (text.isBlank()) {
//                worksheet.get(i, 3).setNumberValue(0);
//            }
//            else {
//                worksheet.get(i,3).setNumberValue(Double.parseDouble(text));
//            }
//        }
        workbook.saveToFile(input, ExcelVersion.Version2013);

        return input;
    }
}
