import javafx.application.Application;
import javafx.stage.Stage;
import util.Routes;
import util.ViewManager;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewManager.initView(primaryStage);
        ViewManager.Manager.toView(Routes.Main.MAIN);
    }
}
