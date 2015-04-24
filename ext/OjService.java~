import java.io.IOException;
        
import org.jruby.Ruby;
import org.jruby.runtime.load.BasicLibraryService;

public class FibsService implements BasicLibraryService {
    public boolean basicLoad(final Ruby runtime) throws IOException {
        new org.jruby.ext.FibsLibrary().load(runtime, false);
        return true;
    }
}
