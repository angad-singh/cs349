// CS 349 Fall 2018
// A1: Breakout code sample
// You may use any or all of this code in your assignment!
// See makefile for compiling instructions

#include <cstdlib>
#include <iostream>
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

// fixed frames per second animation
int FPS = 60;

// get current time
unsigned long now() {
	timeval tv;
	gettimeofday(&tv, NULL);
	return tv.tv_sec * 1000000 + tv.tv_usec;
}

std::vector <Displayable *> board;

int init_blocks() {
	int x = 50;
	int y = 120;
	int counter = 0;
	for (int i = 0; i < 25; ++i) {
		Block * block = new Block (x,y);
		board.emplace_back(block);
		counter++;
		if (counter%19 == 0){
			x = 50;
			y += 45;
		} else {
			x += 65;
		}
	}
}

// entry point
int main( int argc, char *argv[] ) {

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
	int ballSize = 50;

	XPoint ballDir;
	ballDir.x = 3;
	ballDir.y = 3;

	// block position, size
	XPoint rectPos;
	rectPos.x = 225;
	rectPos.y = 400;

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

	XClearWindow(display, window);
	XFillRectangle(display, window, gc1, 50, 50, 1255, 615);
	XFillArc(display, window, gc1,
		ballPos.x - ballSize/2,
		ballPos.y - ballSize/2,
		ballSize, ballSize,
		0, 360*64);
	XFlush( display );

	// save time of last window paint
	unsigned long lastRepaint = 0;

	// event handle for current event
	XEvent event;

	int counter = 0;

	init_blocks();

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
				if ( i == 1 && text[0] == 'p' && counter == 0 ) {
					counter++;
					XClearWindow(display, window);
				}

					// move right
				if ( i == 1 && text[0] == 'd' ) {
					rectPos.x += 10;
				}

					// move left
				if ( i == 1 && text[0] == 'a' ) {
					rectPos.x -= 10;
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
				XFillRectangle(display, window, gc1, 50, 50, 1255, 615);
				XFlush( display );
				lastRepaint = now();
			} else {

			// XDrawRectangle(display, window, gc, rectPos.x, rectPos.y, 50, 50);

			// draw ball from centre
				XFillArc(display, window, gc1, 
					ballPos.x - ballSize/2, 
					ballPos.y - ballSize/2, 
					ballSize, ballSize,
					0, 360*64);

			// update ball position
				ballPos.x += ballDir.x;
				ballPos.y += ballDir.y;

			// bounce ball
				if (ballPos.x + ballSize/2 > w.width ||
					ballPos.x - ballSize/2 < 0)
					ballDir.x = -ballDir.x;
				if (ballPos.y + ballSize/2 > w.height ||
					ballPos.y - ballSize/2 < 0)
					ballDir.y = -ballDir.y;

				vector<Displayable *>::iterator it;
				int ii = 0;
				for (it = board.begin(); it != board.end(); it++) {
				// check for collision and remove block is true and repaint
					++ii;
					if (ii%10 == 0){
						XColor xcolour;

					// I guess XParseColor will work here
						xcolour.red = 32000; xcolour.green = 65000; xcolour.blue = 32000;
						xcolour.flags = DoRed | DoGreen | DoBlue;
						XSetForeground(display, xinfo.gc, xcolour.pixel);
					}
					(*it)->paint(xinfo);
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
