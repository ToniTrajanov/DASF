package mk.ukim.finki.dashw.filter;

import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;


public class DeleteRowsFilter implements Filter<String> {
    @Override
    public String execute(String input) {

        Workbook workbook = new Workbook();
        workbook.loadFromFile(input);

        Worksheet worksheet = workbook.getWorksheets().get(0);

        worksheet.deleteColumn(11);
        worksheet.deleteColumn(10);
        worksheet.deleteColumn(8);
        worksheet.deleteColumn(6);



        workbook.saveToFile(input, ExcelVersion.Version2013);

        return input;
    }
}
