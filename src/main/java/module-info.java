module com.igrica.coveceneljutiseklijent {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.igrica.coveceneljutiseklijent to javafx.fxml;
    exports com.igrica.coveceneljutiseklijent;
    exports com.igrica.coveceneljutiseklijent.kontrolori;
    opens com.igrica.coveceneljutiseklijent.kontrolori to javafx.fxml;
}