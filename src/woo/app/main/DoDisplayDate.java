package woo.app.main;

import pt.tecnico.po.ui.Command;
import woo.core.StoreManager;

/**
 * Show current date.
 */
public class DoDisplayDate extends Command<StoreManager> {

  public DoDisplayDate(StoreManager receiver) {
    super(Label.SHOW_DATE, receiver);
  }

  @Override
  public final void execute(){
    String aux = Message.currentDate(_receiver.getData());
    _display.addLine(aux);
    _display.display();
  }
}