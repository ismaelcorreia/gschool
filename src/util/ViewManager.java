package util;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeInLeftBig;
import animatefx.animation.FadeInRightBig;
import animatefx.animation.FadeInUpBig;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;

public class ViewManager {

    private static Stage MAIN_STAGE;
    public static AnchorPane VIEW_ROOT;
    public static AnchorPane PAGE_ROOT;
    public static ViewManager Manager;
    public static HBox screenActionBox;
    private static int state = 2;

    public static void initView(Stage primary) {
        MAIN_STAGE = primary;
        MAIN_STAGE.setTitle("GoodFood");
        MAIN_STAGE.getIcons().add(new Image(IconsPath.ICON_APP));
        initView();
    }
    private static void screenSize(int i)  {
        state = i;
        screenSize();
    }
    private static void screenSize()  {
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        switch (state)
        {
            case 0:
                MAIN_STAGE.setIconified(true);
                break;
            case 1:
                width = Toolkit.getDefaultToolkit().getScreenSize().getWidth() - Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3;
                height = Toolkit.getDefaultToolkit().getScreenSize().getHeight() - Toolkit.getDefaultToolkit().getScreenSize().getHeight()/3;
                MAIN_STAGE.setWidth(width);
                MAIN_STAGE.setHeight(height);
                break;
            default:
                MAIN_STAGE.setWidth(width);
                MAIN_STAGE.setHeight(height);
                break;
        }

    }

    private static void initScreenAction()  {
        screenActionBox = new HBox();

        JFXButton minimizeButton = new JFXButton();
        JFXButton resizeButton = new JFXButton();
        JFXButton closeButton = new JFXButton();

        minimizeButton.setGraphic(IconsPath.iconFrom(IconsPath._18x.MINIMIZE));
        resizeButton.setGraphic(IconsPath.iconFrom(state==2?IconsPath._18x.COMPRESSEDSCREEN:IconsPath._18x.FULLSCREEN));
        closeButton.setGraphic(IconsPath.iconFrom(IconsPath._18x.CLOSE));

        minimizeButton.getStyleClass().add("actionButton");
        resizeButton.getStyleClass().add("actionButton");
        closeButton.getStyleClass().addAll("actionButton", "close");

        minimizeButton.setOnAction(event -> screenSize(0));
        resizeButton.setOnAction(event -> {
            screenSize(state == 1 ? 2 : 1);
            resizeButton.setGraphic(IconsPath.iconFrom(state==2?IconsPath._18x.COMPRESSEDSCREEN:IconsPath._18x.FULLSCREEN));
        });
        closeButton.setOnAction(event -> closeApplication());

        screenActionBox.getChildren().add(minimizeButton);
        screenActionBox.getChildren().add(resizeButton);
        screenActionBox.getChildren().add(closeButton);

        AnchorPane.setRightAnchor(screenActionBox, 0.0);
        AnchorPane.setTopAnchor(screenActionBox, 0.0);

    }
    public static void initView() {
        // #FULL SCREEN APP
        Manager = new ViewManager();
        VIEW_ROOT = new AnchorPane();
        Scene scene = new Scene(VIEW_ROOT);

        screenSize();
        initScreenAction();
        VIEW_ROOT.getChildren().add(screenActionBox);

        MAIN_STAGE.initStyle(StageStyle.UNDECORATED);
        MAIN_STAGE.setScene(scene);
        VIEW_ROOT.getStylesheets().add("assets/css/style.css");
        VIEW_ROOT.getStyleClass().add("main");
        MAIN_STAGE.show();
    }

    public static void closeApplication() {
        MAIN_STAGE.close();
    }

    public void showModal(String route, Pos pos, boolean fit) throws Exception {

        Node node = FXMLLoader.load(getClass().getResource(route));

        HBox modal = new HBox();
        AnchorPane.setRightAnchor(modal, 0.0);
        AnchorPane.setTopAnchor(modal, 0.0);
        AnchorPane.setBottomAnchor(modal, 0.0);
        AnchorPane.setLeftAnchor(modal, 0.0);
        modal.setAlignment(pos);
        modal.setFillHeight(fit);

        modal.setStyle("-fx-background-color: rgba(0,0,0, 0.6)");


        if (fit) {
            try {
                if (node instanceof VBox) {
                    VBox box = (VBox) node;
                    node.setStyle(box.getStyle()+";-fx-effect: shadow;");
                    double h = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
                    box.setPrefHeight(h);
                }
            }catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        if (ViewManager.VIEW_ROOT.getChildren().contains(modal))
            ViewManager.VIEW_ROOT.getChildren().remove(modal);
        ViewManager.VIEW_ROOT.getChildren().add(modal);
        modal.getChildren().add(node);
        switch (pos) {

            case CENTER:
                new FadeIn(node).play();
                break;
            case CENTER_RIGHT:
                new FadeInRightBig(node).setSpeed(.8).play();
                break;
            case CENTER_LEFT:
                new FadeInLeftBig(node).setSpeed(.4).play();
                break;
        }
    }

    public static void closeModal() {
        int index = ViewManager.VIEW_ROOT.getChildren().size() - 1;
        ViewManager.VIEW_ROOT.getChildren().remove(index);
    }

    public static void toView(Node node) {

        VIEW_ROOT.getChildren().clear();
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);

        VIEW_ROOT.getChildren().add(node);
        VIEW_ROOT.getChildren().add(screenActionBox);
        FadeIn animation = new FadeIn(node);
        animation.play();
    }

    public static void setRootPage(AnchorPane container) {
        PAGE_ROOT = container;
    }
    public static void toPage(Node node) {
        PAGE_ROOT.getChildren().clear();
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
        PAGE_ROOT.getChildren().add(0, node);
        FadeIn animation = new FadeIn(node);
        animation.play();
    }

    public void toPage(String route) throws Exception {
        Routes.addToHistory(route);
        Node node = FXMLLoader.load(getClass().getResource(route));
        toPage(node);
    }

    public void getBack() throws Exception {
        if (Routes.getHistory().size()>=1) {
            String route = Routes.getHistory().pop();
            if (Routes.getActual_page().equalsIgnoreCase(route))
                route = Routes.getHistory().pop();
            Node node = FXMLLoader.load(getClass().getResource(route));
            toPage(node);
        }else {
            Alert.Toast.showToast("Já não é possível voltar!", Alert.Type.WARNING);
        }
    }
    public void getBack(AnchorPane container) throws Exception {
        if (Routes.getHistory().size()>=1) {
            String route = Routes.getHistory().pop();
            if (Routes.getActual_page().equalsIgnoreCase(route))
                route = Routes.getHistory().pop();
            Node node = FXMLLoader.load(getClass().getResource(route));
            toPage(node);
        }else {
            Alert.Toast.showToast("Já não é possível voltar!", Alert.Type.WARNING);
        }
    }
    public void toView(String route) throws Exception {
        Node node = FXMLLoader.load(getClass().getResource(route));
        toView(node);
    }


}
