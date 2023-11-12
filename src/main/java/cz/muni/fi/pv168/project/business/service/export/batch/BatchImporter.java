package cz.muni.fi.pv168.project.business.service.export.batch;

import cz.muni.fi.pv168.project.business.service.export.format.FileFormat;

/**
 * @author Sabrina Orálková, 525089
 */
public interface BatchImporter extends FileFormat {
    Batch importBatch(String filePath);
}
