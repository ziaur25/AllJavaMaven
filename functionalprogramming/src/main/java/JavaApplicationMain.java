import java.util.logging.Logger;

public class JavaApplicationMain {

    static final Logger logger = Logger.getLogger("JavaApplicationMain");

    public static void main(String[] args) {
        final Double costs = TimingLog.timed("Cost Calculation", JavaApplicationMain::calculateCosts);
        final Double revenue = TimingLog.timed("Revenue Calculation", JavaApplicationMain::calculateRevenue);
        //final Double profit = TimingLog.timed("Cost Calculation", () -> calculateProfit(costs, revenue));
        final Double profit = TimingLog.timed("Profit Calculation"
                , logger::info
                , () -> calculateProfit(costs, revenue));
        System.out.println("Profit:"+profit);

    }

    private static Double calculateProfit(Double costs, Double revenue) {
        pretendLikeWorkingHard();
        return revenue - costs;
    }

    private static Double calculateRevenue() {
        pretendLikeWorkingHard();
        return 343.343;
    }


    private static Double calculateCosts() {
        pretendLikeWorkingHard();
        return 200.000;
    }

    private static void pretendLikeWorkingHard()  {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}


