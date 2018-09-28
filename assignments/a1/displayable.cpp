/*
 * Information to draw on the window.
 */
struct XInfo {
  Display* display;
  Window   window;
  GC       gc;
  Pixmap   buffer;
};


/*
 * An abstract class representing displayable things.
 */
class Displayable {
public:
  virtual void paint(XInfo& xinfo) = 0;
};
