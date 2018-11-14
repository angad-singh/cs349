
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {		

		// add scene graph to the canvas
		Canvas canvas = new Canvas();
		canvas.addSprite(Main.makeSprite());

		// create a frame to hold it
		JFrame f = new JFrame();
		f.getContentPane().add(canvas);
		f.getContentPane().setLayout(new GridLayout(1, 1));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400, 300);
		f.setVisible(true);
	}
	
	/* Make sample scene graph for testing purposes. */
	private static Sprite makeSprite() {
		// create four different parts
		Sprite torso = new Torso(60, 100);
		Sprite face = new Face(30, 50);
		Sprite upperarm = new UpperArm(70, 10);
		Sprite lowerarm = new LowerArm(70, 10);
		// Sprite fourthSprite = new LowerArm(10, 10);

		// define them based on relative, successive transformations
		torso.transform(AffineTransform.getTranslateInstance(25, 25));
		face.transform(AffineTransform.getTranslateInstance(30, 20));
		upperarm.transform(AffineTransform.getTranslateInstance(50, 5));
		lowerarm.transform(AffineTransform.getTranslateInstance(70, 25));
		// fourthSprite.transform(AffineTransform.getTranslateInstance(70, 30));
		// fourthSprite.transform(AffineTransform.getScaleInstance(4, 3));

		// build scene graph
		torso.addChild(face);
		torso.addChild(upperarm);
		upperarm.addChild(lowerarm);
		
		// return root of the tree
		return torso;
	}

}
