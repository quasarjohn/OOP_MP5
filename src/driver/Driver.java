package driver;

import control.MainController;
import control.UpdateController;
import view.UpdateView;
import view.ViewHome;

/**
 * Created by John on 1/18/2017.
 */
public class Driver {

    public static void main(String[] args) {

        ViewHome viewHome = new ViewHome();
        MainController tableController = new MainController(viewHome);
        tableController.renderTable();

    }
}
