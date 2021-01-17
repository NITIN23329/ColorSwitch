module ColorSwitchGame {
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.controls;
    requires javafx.media;

    opens pauseWindow;
    opens Screen_move;
    opens Obstacle_Collide;
    opens mainWindow;
    opens  All_Obstacle;
}