package daoLayer.sqlDao;

public class DaoUtil {

    public static void main(String[] args) {
        DaoController controller = DaoController.getInstance();
        controller.initDBConnection();
        controller.createBookingPatternInputFieldTable();
        controller.closeDBConnection();
    }
}
