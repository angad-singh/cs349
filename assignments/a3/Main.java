
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
		f.setSize(1024, 768);
		f.setVisible(true);
	}
	
	/* Make sample scene graph for testing purposes. */
	private static Sprite makeSprite() {
		// create four different parts
		Sprite torso = new Torso(60, 100);
		Sprite face = new Face(30, 50);
		Sprite upperarm_R = new UpperArm(70, 10);
		Sprite lowerarm_R = new LowerArm(70, 10);
		Sprite upperarm_L = new UpperArm(70, 10);
		Sprite lowerarm_L = new LowerArm(70, 10);
		Sprite upperleg_R = new UpperLeg(10, 75);
		Sprite lowerleg_R = new LowerLeg(10, 75);
		Sprite upperleg_L = new UpperLeg(10, 75);
		Sprite lowerleg_L = new LowerLeg(10, 75);
		// Sprite fourthSprite = new LowerArm(10, 10);

		// define them based on relative, successive transformations
		torso.transform(AffineTransform.getTranslateInstance(300, 300));
		face.transform(AffineTransform.getTranslateInstance(30, 20)); //15,50
		upperarm_R.transform(AffineTransform.getTranslateInstance(75, 80)); //0,0
		lowerarm_R.transform(AffineTransform.getTranslateInstance(70, 0)); // 70,0
		upperarm_L.transform(AffineTransform.getTranslateInstance(-55, 80));
		upperarm_L.anchor_X = 70;
		lowerarm_L.transform(AffineTransform.getTranslateInstance(-70, 0));
		lowerarm_L.anchor_X = 70;
		upperleg_R.transform(AffineTransform.getTranslateInstance(58, 170)); //0,0
		lowerleg_R.transform(AffineTransform.getTranslateInstance(0, 75)); // 70,0
		upperleg_L.transform(AffineTransform.getTranslateInstance(22, 170)); //0,0
		lowerleg_L.transform(AffineTransform.getTranslateInstance(0, 75)); // 70,0


		// fourthSprite.transform(AffineTransform.getTranslateInstance(70, 30));
		// fourthSprite.transform(AffineTransform.getScaleInstance(4, 3));

		// build scene graph
		torso.addChild(face);
		torso.addChild(upperarm_R);
		upperarm_R.addChild(lowerarm_R);
		torso.addChild(upperleg_R);
		upperleg_R.addChild(lowerleg_R);
		torso.addChild(upperarm_L);
		upperarm_L.addChild(lowerarm_L);
		torso.addChild(upperleg_L);
		upperleg_L.addChild(lowerleg_L);
		
		// return root of the tree
		return torso;
	}

}
