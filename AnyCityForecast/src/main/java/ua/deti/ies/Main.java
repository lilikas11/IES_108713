package ua.deti.ies;

public class Main {
  Toolkit toolkit;//j  av  a2  s .  c o m

  Timer timer;

  public Main() {
    toolkit = Toolkit.getDefaultToolkit();
    timer = new Timer();
    timer.scheduleAtFixedRate(new RemindTask(), 0, //initial delay
        1 * 1000); //subsequent rate
  }

  class RemindTask extends TimerTask {
    int numWarningBeeps = 3;

    public void run() {
      if (numWarningBeeps-- > 0) {
        long time = System.currentTimeMillis();
        if (time - scheduledExecutionTime() > 5) {
          return;
        }
        toolkit.beep();
        System.out.println("Beep!");
      } else {
        toolkit.beep();
        System.out.println("Time's up!");
        System.exit(0);
      }
    }
  }

  public static void main(String args[]) {
    new Main();
  }
}