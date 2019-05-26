package resources;

import java.util.ListResourceBundle;

public class LRB extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"title", "Authors"},
                {"authors", "Jan Klamka & Maciej Pracucik"}
        };
    }
}