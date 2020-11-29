package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import woo.app.exception.*;
import woo.core.StoreManager;
import woo.core.exception.UnavailableFileException;
import java.io.IOException;

/**
 * Open existing saved state.
 */
public class DoOpen extends Command<StoreManager> {

  private Input<String> _input;

  /** @param receiver */
  public DoOpen(StoreManager receiver) {
    super(Label.OPEN, receiver);
    _input = _form.addStringInput(Message.openFile());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws FileOpenFailedException {
    _form.parse();
    try {
      _receiver.setFilename(_input.value());
      _receiver.load(_input.value());
    } catch (IOException | UnavailableFileException e){
      throw new FileOpenFailedException(_input.value());
    }
  }
}