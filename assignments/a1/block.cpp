class Block : public Displayable {
public:
  virtual void paint(XInfo& xinfo) {
    (this->gc == NULL) ? XFillRectangle(xinfo.display, xinfo.buffer, xinfo.gc, this->x, this->y, 60, 40) :
                         XFillRectangle(xinfo.display, xinfo.buffer, this->gc, this->x, this->y, 60, 40);
  }

  // constructor 
  Block(int x, int y): x(x), y(y) {}

  int x;
  int y;
  int width = 60;
  int height = 40;
  GC gc = NULL;
};
