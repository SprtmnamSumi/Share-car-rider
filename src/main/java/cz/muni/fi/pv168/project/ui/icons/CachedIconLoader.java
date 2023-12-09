package cz.muni.fi.pv168.project.ui.icons;

import com.google.inject.Singleton;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.tinylog.Logger;

@Singleton
public class CachedIconLoader implements IconLoader {
    private final Map<String, Icon> cachedIcons = new HashMap<>();

    public Icon getIcon(String iconName) {
        if (!cachedIcons.containsKey(iconName)) {
            try {
                Image image = ImageIO.read(new File("src/main/resources/cz/muni/fi/pv168/project/ui/icons/" + iconName));
                cachedIcons.put(iconName, new ImageIcon(image));
            } catch (IOException ex) {
                Logger.warn("Could not load image of name: " + iconName + ". Reason: " + ex);
            }
        }
        return cachedIcons.get(iconName);
    }

    public Icon getIcon(String iconName, int width, int height) {
        if (!cachedIcons.containsKey(iconName)) {
            try {
                Image image = ImageIO.read(new File("src/main/resources/cz/muni/fi/pv168/project/ui/icons/" + iconName))
                        .getScaledInstance(width, height, Image.SCALE_SMOOTH);
                cachedIcons.put(iconName, new ImageIcon(image));
            } catch (IOException ex) {
                Logger.warn("Could not load image of name: " + iconName + ". Reason: " + ex);
            }
        }
        return cachedIcons.get(iconName);
    }
}
