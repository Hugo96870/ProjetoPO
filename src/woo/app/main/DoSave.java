package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import woo.app.exception.FileOpenFailedException;
import woo.core.StoreManager;
import woo.core.exception.MissingFileAssociationException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Save current state to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<StoreManager> {

  private Input<String> _output;

  /**
   * @param receiver
   */
  public DoSave(StoreManager receiver) {
    super(Label.SAVE, receiver);
    _output = _form.addStringInput(Message.newSaveAs());
  }

  /**
   * @see pt.tecnico.po.ui.Command#execute()
   */
  @Override
  public final void execute() throws FileOpenFailedException {
    _form.parse();
    try {
      _receiver.save();
    } catch (FileNotFoundException e) {
      try {
        _receiver.setFilename(_output.value());
        _receiver.saveAs(_output.value());
      } catch (MissingFileAssociationException q) {
        throw new FileOpenFailedException(q.getMessage());
      }
    } catch (IOException ex) {
      throw new FileOpenFailedException(ex.getMessage());
    }
  }
}
