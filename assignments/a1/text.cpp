//#include "displayable.cpp"
using namespace std;

/*
 * A text displayable
 */
class Text : public Displayable {
public:
  virtual void paint(XInfo& xinfo) {
    XDrawImageString( xinfo.display, xinfo.buffer, xinfo.gc,
                      this->x, this->y, this->s.c_str(), this->s.length() );
  }

  // constructor
  Text(int x, int y, string s): x(x), y(y), s(s)  {}

private:
  int x;
  int y;
  string s; // string to show
};
