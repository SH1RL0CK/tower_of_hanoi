module com.sh1rl0ck.tower_of_hanoi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.sh1rl0ck.tower_of_hanoi to javafx.fxml;
    exports com.sh1rl0ck.tower_of_hanoi;
}