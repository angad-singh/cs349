class Block : public Displayable {
public:
  virtual void paint(XInfo& xinfo) {
    XFillRectangle(xinfo.display, xinfo.window, xinfo.gc, this->x, this->y, 60, 40);
  }

  // constructor 
  Block(int x, int y): x(x), y(y) {}

private:
  int x;
  int y;
};
