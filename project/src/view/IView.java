package view;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public interface IView {
	public String[] getButtons();
	GridPane getPane(int value);
	public BorderPane getCenter();
	VBox getBar(BorderPane bp);
}
