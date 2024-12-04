module org.zero.ds4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;


    opens org.zero.ds4 to javafx.fxml;
    opens org.zero.ds4.model to javafx.base;
    exports org.zero.ds4;
}