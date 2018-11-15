import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Application {
	
	public Application() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/titleIcon.png"));
		} catch (IOException e){
			System.out.println("File \"titleIcon.png\" not found.");
		}
		JFrame frame = new JFrame();
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setTitle("Maze Game!");
		frame.setIconImage(img);
		frame.setLocation(450, 200);
		Maze maze = new Maze(25, 25);
		frame.add(maze);
		maze.generateMaze();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//maze.repaint();
	}

	public static void main(String[] args) {
		new Application();
	}

}
