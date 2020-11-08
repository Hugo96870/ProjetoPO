package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.FileOpenFailedException;
import woo.core.StoreManager;
import woo.core.exception.ImportFileException;
import woo.core.exception.UnavailableFileException;

import java.io.BufferedReader;
import java.io.FileReader;
//FIXME import other classes

/**
 * Open existing saved state.
 */
public class DoOpen extends Command<StoreManager> {

  //FIXME add input fields
  private Input<String> _input;

  /** @param receiver */
  public DoOpen(StoreManager receiver) {
    super(Label.OPEN, receiver);
    _input = _form.addStringInput(Message.openFile());
    //FIXME init input fields
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws FileOpenFailedException {
    _form.parse();
    try {
      //FIXME implement command
      _receiver.importFile(_input.value());
    } catch (ImportFileException e){
      throw new FileOpenFailedException(e.getMessage());
    }
  }
}
