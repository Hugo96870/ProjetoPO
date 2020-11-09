package woo.app.main;

import woo.app.exception.InvalidDateException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
import woo.core.exception.InvalidDateToAdvanceException;

/**
 * Advance current date.
 */
public class DoAdvanceDate extends Command<StoreManager> {
  private Input<Integer> _dataAAvancar;

  public DoAdvanceDate(StoreManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
    _dataAAvancar = _form.addIntegerInput(Message.requestDaysToAdvance());
  }

  @Override
  public final void execute() throws InvalidDateException {
    _form.parse();
    try{
      _receiver.avancarData(_dataAAvancar.value());
    } catch(InvalidDateToAdvanceException e){
      throw new InvalidDateException(e.getValue());
    }
  }
}
