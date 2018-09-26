// CS 349 Fall 2018
// A1: Breakout code sample
// You may use any or all of this code in your assignment!
// See makefile for compiling instructions

#include <cstdlib>
#include <iostream>
#include <string>
#include <unistd.h>
#include <sys/time.h>
#include <vector>

#include <X11/Xlib.h>
#include <X11/Xutil.h>

#include "displayable.cpp"
#include "text.cpp"
#include "ball.cpp"
#include "block.cpp"

using namespace std;

// X11 structures
Display* display;
Window window;

int FPS = 60;
int speed = 5;

// get current time
unsigned long now() {
	timeval tv;
	gettimeofday(&tv, NULL);
	return tv.tv_sec * 1000000 + tv.tv_usec;
}

std::vector <Block *> board;

int init_blocks(vector<Block *> *board) {
	int x = 50;
	int y = 120;
	int counter = 0;
	board->clear();
	for (int i = 0; i < 95; ++i) {
		Block * block = new Block (x,y);
		board->emplace_back(block);
		counter++;
		if (counter%19 == 0){
			x = 50;
			y += 45;
		} else {
			x += 65;
		}
	}
}

int quit_game(int *counter, XInfo &xinfo, Ball *ball, XPoint *ballPos) {
	XClearWindow(xinfo.display, xinfo.window);
	*counter = 0;
	ball->x = 50;
	ball->y = 50;
	ballPos->x = 50;
	ballPos->y = 50;
}

bool contactWtihBlock(Ball ball, Block block) {
	return (ball.x >= (block.x - ball.d/2)) &&
	(ball.x <= (block.x + ball.d/2 + block.width)) &&
	(ball.y >= (block.y - ball.d/2)) &&
	(ball.y <= (block.y + ball.d/2 + block.height));
}

int splash_screen(XInfo &xinfo) {
	XFillRectangle(xinfo.display, xinfo.window, xinfo.gc, 50, 50, 1255, 615);
	string game_name = "BREAKOUT!";
	string name = "Angad Singh";
	string id = "20597494";
	string info = "Use 'a' and 'd' to move the paddle left and right respectively. Press 'p' to play!";

	Text * game_name_obj = new Text (650, 350, game_name);
	Text * name_obj = new Text (650, 375, name);
	Text * id_obj = new Text (650, 400, id);
	Text * info_obj = new Text (650, 425, info);

	game_name_obj->paint(xinfo);
	name_obj->paint(xinfo);
	id_obj->paint(xinfo);
	info_obj->paint(xinfo);

	XFlush(xinfo.display);
}

// entry point
int main( int argc, char *argv[] ) {

	// fixed frames per second animation
	if (argc == 3) {
		FPS = atoi(argv[1]);
		int input_speed = atoi(argv[2]);
		speed = 60/FPS * input_speed;
	}

	int score = 0;

	// create window
	display = XOpenDisplay("");
	if (display == NULL) exit (-1);
	int screennum = DefaultScreen(display);
	long foreground = WhitePixel(display, screennum);
	long background = BlackPixel(display, screennum);
	window = XCreateSimpleWindow(display, DefaultRootWindow(display),
		10, 10, 1200, 800, 2, foreground, background);

	// set events to monitor and display window
	XSelectInput(display, window, ButtonPressMask | KeyPressMask);
	XMapRaised(display, window);
	XFlush(display);

	// ball postition, size, and velocity
	XPoint ballPos;
	ballPos.x = 50;
	ballPos.y = 50;
	int ballSize = 20;

	Ball * ball = new Ball(ballPos.x, ballPos.y, ballSize);

	XPoint ballDir;
	ballDir.x = speed;
	ballDir.y = speed;

	// block position, size
	XPoint rectPos;
	rectPos.x = 225;
	rectPos.y = 675;

	Block * paddle = new Block (225, 675);
	paddle->width = 100;
	paddle->height = 10;

	// create gc for drawing
	GC gc = XCreateGC(display, window, 0, 0);
	XWindowAttributes w;
	XGetWindowAttributes(display, window, &w);

	GC gc1 = XCreateGC(display, window, 0, 0);
	XSetForeground(display, gc1, WhitePixel(display, screennum));
	XSetBackground(display, gc1, BlackPixel(display, screennum));
	XSetFillStyle(display, gc1, FillSolid);
	XSetLineAttributes(display, gc1,
		1, LineSolid, CapButt, JoinRound);

	XInfo xinfo{
		display,
		window,
		gc1
	};

	// save time of last window paint
	unsigned long lastRepaint = 0;

	// event handle for current event
	XEvent event;

	int counter = 0;

	init_blocks(&board);

	// event loop
	while ( true ) {

		// process if we have any events
		if (XPending(display) > 0) { 
			XNextEvent( display, &event ); 

			switch ( event.type ) {

				// mouse button press
				case ButtonPress:
				cout << "CLICK" << endl;
				break;

				case KeyPress: // any keypress
				KeySym key;
				char text[10];
				int i = XLookupString( (XKeyEvent*)&event, text, 10, &key, 0 );

					// start game
				if ( i == 1 && (text[0] == 'p' || text[0] == 'P') && counter == 0 ) {
					counter++;
					XClearWindow(display, window);
				}

					// move right
				if (key == XK_Right || (i == 1 && text[0] == 'd')) {
					rectPos.x += 10;
					paddle->x += 10;
				}

					// move left
				if (key == XK_Left || (i == 1 && text[0] == 'a')) {
					rectPos.x -= 10;
					paddle->x -= 10;
				}

					// quit game
				if ( i == 1 && text[0] == 'q' ) {
					XCloseDisplay(display);
					exit(0);
				}
				break;
			}
		}

		unsigned long end = now();	// get current time in microsecond

		if (end - lastRepaint > 1000000 / FPS) { 

			// clear background
			XClearWindow(display, window);

			// draw rectangle
			if (counter == 0) {
				splash_screen(xinfo);
				lastRepaint = now();
			} else {

				Text * score_text = new Text (20,20,"Score:");
				Text * score_num = new Text (60,20, to_string(score));
				score_text->paint(xinfo);
				score_num->paint(xinfo);

				// Draw Paddle
				XFillRectangle(display, window, gc1, rectPos.x, rectPos.y, 100, 10);

				// draw ball from centre
				XFillArc(display, window, gc1, 
					ballPos.x - ballSize/2, 
					ballPos.y - ballSize/2, 
					ballSize, ballSize,
					0, 360*64);

				// check for collision with the paddle and change position of the ball
				// if interects(ball, paddle) false, then prompt to restart

				if ((ballPos.y+ballSize/2) >= rectPos.y) {
					if (contactWtihBlock(*ball, *paddle)){
						if ((ballPos.x >= (paddle->x-ballSize/2)) && (ballPos.x < (paddle->x + paddle->width/2))) {
							// ballDir.x = -ballDir.x;
							ballDir.x = (ballDir.x < 0) ? ballDir.x : -ballDir.x;
						} else if ((ballPos.x >= (paddle->x + paddle->width/2)) && (ballPos.x <= (paddle->x + paddle->width + ballSize/2))) {
							// reverse bounce dir
							ballDir.x = (ballDir.x > 0) ? ballDir.x : -ballDir.x;
							// +ballDir.x;
						}
						if (ballPos.y + ballSize/2 > paddle->height ||
							ballPos.y - ballSize/2 < 0)
							ballDir.y = -ballDir.y;

						ballPos.x += ballDir.x;
						ball->x += ballDir.x;
						ball->y += ballDir.y;
						ballPos.y += ballDir.y;
					} else {
						// exit(1);
						quit_game(&counter, xinfo, ball, &ballPos);
						init_blocks(&board);
						score = 0;
					}
				} else {
					// update ball position
					ballPos.x += ballDir.x;
					ball->x += ballDir.x;
					ball->y += ballDir.y;
					ballPos.y += ballDir.y;

					// bounce ball
					if (ballPos.x + ballSize/2 > w.width ||
						ballPos.x - ballSize/2 < 0)
						ballDir.x = -ballDir.x;
					if (ballPos.y + ballSize/2 > w.height ||
						ballPos.y - ballSize/2 < 0)
						ballDir.y = -ballDir.y;
				}

				vector<Block *>::iterator it;
				for (it = board.begin(); it != board.end();) {
					if (contactWtihBlock(*ball, **it)){
						board.erase(it);
						++score;
					} else {
						(*it)->paint(xinfo);
						++it;
					}
				}

				XFlush( display );

			lastRepaint = now(); // remember when the paint happened
		}
	}

	// IMPORTANT: sleep for a bit to let other processes work
	if (XPending(display) == 0) {
		usleep(1000000 / FPS - (now() - lastRepaint));
	}
}
XCloseDisplay(display);
}
