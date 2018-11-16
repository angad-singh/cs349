
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.*;


public class Main {

	public static void main(String[] args) {		

		// add scene graph to the canvas
		Canvas canvas = new Canvas();
		canvas.addSprite(Main.createDoll());

		// create a frame to hold it
		JFrame f = new JFrame();
		
		 // Create a menubar with File menu
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");

        JMenuItem reset = new JMenuItem("Reset (Ctrl-R)");
        JMenuItem quit = new JMenuItem("Quit (Ctrl-Q)");

        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // model.quitDrawing();
                // model.notifyObservers();
                System.exit(0);            
            }
        });

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (Sprite sprite : canvas.sprites) {
				// sprite.transform(canvas.original);
			}

            }
        });

         /*
         * Inspired by
         * https://stackoverflow.com/questions/13042504/keypressed-event-in-java 
         * remove selection on pressing Escape
         */
   //      InputMap im = canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
   //      ActionMap am = canvas.getActionMap();

   //      im.put(KeyStroke.getKeyStroke(java.awt.event.InputEvent.CTRL_DOWN_MASK, KeyEvent.VK_Q), "close");

   //      am.put("close", new AbstractAction() {
   //          @Override
   //          public void actionPerformed(ActionEvent e) {
			// 	System.exit(0);            
			// }
   //      });


         f.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_Q) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                    System.exit(0);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        file.add(reset);
        file.add(quit);

        menuBar.add(file);
		f.setResizable(false);
        f.setJMenuBar(menuBar);
        f.getContentPane().add(canvas);
		f.getContentPane().setLayout(new GridLayout(1, 1));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1024, 768);
		f.setVisible(true);
	}
	
	/* Make sample scene graph for testing purposes. */
	private static Sprite createDoll() {
		// canvas.reset = false;
		// if (at == null){
		Sprite torso = new Torso(60, 100);
		Sprite face = new Face(30, 50);
		Sprite hand_R = new LowerArm(20, 20);
		Sprite hand_L = new LowerArm(20, 20);
		Sprite upperarm_R = new UpperArm(70, 15);
		Sprite lowerarm_R = new LowerArm(70, 15);
		Sprite upperarm_L = new UpperArm(70, 15);
		Sprite lowerarm_L = new LowerArm(70, 15);
		Sprite upperleg_R = new UpperLeg(15, 75);
		Sprite lowerleg_R = new LowerLeg(15, 75);
		Sprite upperleg_L = new UpperLeg(15, 75);
		Sprite lowerleg_L = new LowerLeg(15, 75);

		Sprite foot_R = new LowerArm(30, 15);
		Sprite foot_L = new UpperArm(30, 15);


		// define them based on relative, successive transformations
		torso.transform(AffineTransform.getTranslateInstance(450, 150));
		face.transform(AffineTransform.getTranslateInstance(30, 20)); //15,50
		upperarm_R.transform(AffineTransform.getTranslateInstance(75, 80)); //0,0
		lowerarm_R.transform(AffineTransform.getTranslateInstance(70, 0)); // 70,0
		hand_R.transform(AffineTransform.getTranslateInstance(70, 0)); // 70,0
		// hand_R.anchor_X = 0;
		// hand_R.anchor_Y = 0;
		upperarm_L.transform(AffineTransform.getTranslateInstance(-55, 80));
		upperarm_L.anchor_X = 70;
		upperarm_L.right = false;
		hand_L.right = false;
		lowerarm_L.transform(AffineTransform.getTranslateInstance(-70, 0));
		hand_L.transform(AffineTransform.getTranslateInstance(-15, 0));
		// hand_L.anchor_X = 0;
		// hand_L.anchor_Y = 0;
		lowerarm_L.anchor_X = 70;
		lowerarm_L.right = false;
		upperleg_R.transform(AffineTransform.getTranslateInstance(58, 170)); //0,0
		lowerleg_R.transform(AffineTransform.getTranslateInstance(0, 75)); // 70,0
		upperleg_L.transform(AffineTransform.getTranslateInstance(22, 170)); //0,0
		lowerleg_L.transform(AffineTransform.getTranslateInstance(0, 75)); // 70,0

		foot_L.transform(AffineTransform.getTranslateInstance(-20,75)); // 70,0
		foot_L.right = false;
		foot_R.transform(AffineTransform.getTranslateInstance(5,75)); // 70,0

		// build scene graph
		torso.addChild(face);
		torso.addChild(upperarm_R);
		upperarm_R.addChild(lowerarm_R);
		lowerarm_R.addChild(hand_R);
		torso.addChild(upperleg_R);
		upperleg_R.addChild(lowerleg_R);
		torso.addChild(upperarm_L);
		upperarm_L.addChild(lowerarm_L);
		lowerarm_L.addChild(hand_L);
		torso.addChild(upperleg_L);
		upperleg_L.addChild(lowerleg_L);
		
		lowerleg_L.addChild(foot_L);
		lowerleg_R.addChild(foot_R);
		return torso;
	}

}
