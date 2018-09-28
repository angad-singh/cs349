## Overview

The game has been implemented using C++ and X11 graphics library. To run the game simply issue the command `make run`. The game supports FPS and ball speed as arguments while executing the program from command line. It can be done as follows:
`make`, then issue `./breakout FPS ball_speed` where FPS and ball_speed can be replaced with integers ranging from [10-60] and [1-10] respectively.

The game also implements double buffering for smooth gameplay and the event loop in engineered to not overload the CPU.

## Gameplay
Use `a`, `A` or left arrow key to move the paddle left. 
Use `d`, `D` or right arrow key to move the paddle right.
Pressing `q` will quit the game at anytime. If the ball misses the paddle, the user will be prompted to press `p` to play again or `q` to quit the game.
User can press `p` on the main menu to start the game.

<details>
<summary>Main menu </summary>

![image](/uploads/0739e22c7b10ec602f9eb5aedef6c1f8/image.png)

</details>

<details>
<summary>Gameplay</summary>

![image](/uploads/be065553830ce9b0265c2b8754180dfa/image.png)

</details>

<details>
<summary>Game over menu </summary>

![image](/uploads/bfb7fd0481ef26744f4b9dd3bc447cfa/image.png)


</details>
