package mk.ukim.finki.dashw.filter;

import mk.ukim.finki.dashw.pipe.Pipe;

public class PipeAndFilterProblem {
    public static void main(String[] args) {

        Pipe<String> pipe = new Pipe<>();

        CapitalLetterFilter capitalLetterFilter = new CapitalLetterFilter();
        DeleteRowsFilter deleteRowsFilter = new DeleteRowsFilter();
        RatingsFilter ratingsFilter = new RatingsFilter();
        AlignmentFilter alignmentFilter = new AlignmentFilter();
        StreetFilter streetFilter = new StreetFilter();
        ApostropheeFilter apostropheeFilter = new ApostropheeFilter();

        pipe.addFilter(deleteRowsFilter);
        pipe.addFilter(capitalLetterFilter);
        pipe.addFilter(ratingsFilter);
        pipe.addFilter(streetFilter);
        pipe.addFilter(alignmentFilter);
        pipe.addFilter(apostropheeFilter);



        pipe.runFilter("hospitals_data.xlsx");
        pipe.runFilter("pharmacies_data.xlsx");
    }
}