package za.ac.acsse.teboho.position;

import java.io.File;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	/**
	 * Program entry-point
	 * @param args Program cli arguments
	 */
	public static void main(String[] args) {
		System.out.println("Hello World!");
		launch(args);
	}

	boolean paused = false;
	
	@Override
	public void start(Stage arg0) throws Exception {
		// A parent for the scene
		Pane pane = new GridPane();
//		((GridPane)pane).setGridLinesVisible(true);
		((GridPane)pane).setHgap(20);
		((GridPane)pane).setVgap(20);
		((GridPane)pane).setAlignment(Pos.CENTER);
		// ((GridPane)pane).set // styling a javafx gridpane
		pane.setPadding(new Insets(10));
		// Top left element
		Text tlText = new Text("Top Left");
//		((GridPane)pane).add(tlText, 0, 0);
		// Top mid element
		Text tmText = new Text("Top Middle");
			ProgressBar progress = new ProgressBar();
			GridPane.setHalignment(progress, HPos.CENTER);
		((GridPane)pane).add(progress, 1, 0);
		// Top right element
		Text trText = new Text("Top Right");
//		((GridPane)pane).add(trText, 2, 0);
		// Middle left element
		Text mlText = new Text("Middle Left");
//		((GridPane)pane).add(mlText, 0, 1);
		// Center element
		Text text = new Text("Center");
		text.setFill(Color.AQUA);
		text.setFont(new Font("Times New Roman", 30));
		text.setStrokeWidth(50);
		Image image = new Image(
				new FileInputStream("resources/200h.gif"), 200, 200, true, false);
		ImageView iv = new ImageView(image);
		StackPane stack = new StackPane(iv, text);
			File video = new File("resources/saka-1280.mp4");
			Media media = new Media(video.toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			MediaView mediaView = new MediaView(mediaPlayer);
			mediaView.setFitHeight(500);
			mediaPlayer.setAutoPlay(true);
			mediaPlayer.setVolume(0.1);
			
			Group videoGroup = new Group();
			videoGroup.getChildren().add(mediaView);
		((GridPane)pane).add(videoGroup, 1, 1);
		// Middle right element
		Text mrText = new Text("Middle Right");
//		((GridPane)pane).add(mrText, 2, 1);
		// Bottom left element
		Text blText = new Text("Bottom Left");
//		((GridPane)pane).add(blText, 0, 2);
		// Bottom middle element
		Text bmText = new Text("Bottom Middle");
			Button btnPlayPause = new Button("Pause");
			btnPlayPause.setOnAction(e -> {
				if (paused) {
					mediaPlayer.play();
					paused = false;
					Runnable process = new Runnable() {
						@Override
						public void run() {
							btnPlayPause.setText("Pause");							
						}
					};
					Platform.runLater(process);
//					new Thread(process, "Playing").start();
				} else {
					mediaPlayer.pause();
					paused = true;
					Runnable process = new Runnable() {
						@Override
						public void run() {
							btnPlayPause.setText("Play");							
						}
					};
					Platform.runLater(process);
//					new Thread(process, "Pausing").start();
				}
			});
		((GridPane)pane).add(btnPlayPause, 1, 2);
		GridPane.setHalignment(btnPlayPause, HPos.CENTER);
		// Bottom right element
		Text brText = new Text("Bottom Right");
//		((GridPane)pane).add(brText, 2, 2);
		// Make a scene
		Scene scene = new Scene(pane, 1100, 750);
		// Put the scene on the stage
		arg0.setScene(scene);
		arg0.getIcons().add(new Image(new FileInputStream("resources/200h.gif")));
//		arg0.setFullScreen(true);
		arg0.show();
	}

}