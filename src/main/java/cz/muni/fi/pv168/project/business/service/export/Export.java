package cz.muni.fi.pv168.project.business.service.export;

import cz.muni.fi.pv168.project.business.service.export.format.Format;

import java.util.Collection;


public interface Export {
    void exportData(String filePath);

    Collection<Format> getFormats();
}
