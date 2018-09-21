class Ball : public Displayable {
public:
  virtual void paint(XInfo& xinfo) {

     // create a simple graphics context
    GC gc = XCreateGC(xinfo.display, xinfo.window, 0, 0);
    int screen = DefaultScreen( xinfo.display );
    XSetForeground(xinfo.display, gc, BlackPixel(xinfo.display, screen));
    XSetBackground(xinfo.display, gc, WhitePixel(xinfo.display, screen));
    XSetFillStyle(xinfo.display,  gc, FillSolid);
    XSetLineAttributes(xinfo.display, gc,
                       3,       // 3 is line width
                       LineSolid, CapButt, JoinRound);         // other line options


    // draw head
    XFillArc(xinfo.display, xinfo.window, gc,
             x - (d / 2), y - (d / 2), d, d, 0, 360 * 64);
  }

  // constructor
  Ball(int x, int y, int d): x(x), y(y), d(d) {}

private:
  int x;
  int y;
  int d; // diameter
};
