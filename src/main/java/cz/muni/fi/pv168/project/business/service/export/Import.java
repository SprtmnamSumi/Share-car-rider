package cz.muni.fi.pv168.project.business.service.export;

import cz.muni.fi.pv168.project.business.service.export.format.Format;

import java.util.Collection;

/**
 * @author Sabrina Orálková, 525089
 */
public interface Import {
    void importData(String filePath);

    Collection<Format> getFormats();
}
