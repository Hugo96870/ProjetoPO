package woo.app.main;

import pt.tecnico.po.ui.Command;
import woo.core.StoreManager;

/**
 * Show global balance.
 */
public class DoShowGlobalBalance extends Command<StoreManager> {

  public DoShowGlobalBalance(StoreManager receiver) {
    super(Label.SHOW_BALANCE, receiver);
  }

  @Override
  public final void execute() {
    long disponivel = Math.round(_receiver.getSaldoDisponivel());
    long contabilistico = Math.round(_receiver.getSaldoContabilistico());
    _display.popup((Message.currentBalance((int)disponivel, (int)contabilistico)));
    _display.clear();
  }
}
