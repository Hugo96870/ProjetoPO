package woo.app.main;

import com.sun.source.tree.ImportTree;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.FileOpenFailedException;
import woo.core.StoreManager;
import woo.core.exception.ImportFileException;
import woo.core.exception.MissingFileAssociationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
//FIXME import other classes

/**
 * Save current state to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<StoreManager> {

  //FIXME add input fields
  private Input<String> _output;

  /** @param receiver */
  public DoSave(StoreManager receiver) {
    super(Label.SAVE, receiver);
    //FIXME init input fields
    _output = _form.addStringInput(Message.newSaveAs());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws FileOpenFailedException{
    _form.parse();
    try {
      _receiver.saveAs(_output.value());
    } catch(MissingFileAssociationException | ImportFileException e){
      throw new FileOpenFailedException(e.getMessage());
    }
    //FIXME implement command
  }
}
