package cz.muni.fi.pv168.project.export;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import org.json.JSONObject;

public interface IImporter<T> {
    Boolean importData(Path filePath, Function<JSONObject, List<T>> importer, Function<List<T>, Void> initialize);
}
