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

@Singleton
public class CachedIconLoader implements IconLoader {
    private final Map<String, Icon> cachedIcons = new HashMap<>();

    public Icon getIcon(String iconName) {
        if (!cachedIcons.containsKey(iconName)) {
            try {
                Image image = ImageIO.read(new File("src/main/java/cz/muni/fi/pv168/project/ui/icons/" + iconName));
                cachedIcons.put(iconName, new ImageIcon(image));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return cachedIcons.get(iconName);
    }
}
