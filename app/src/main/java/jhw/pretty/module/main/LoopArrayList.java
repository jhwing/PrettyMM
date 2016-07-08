package jhw.pretty.module.main;

import java.util.ArrayList;

/**
 * Created by jihongwen on 16/7/8.
 */

public class LoopArrayList<T> extends ArrayList<T> {

    @Override
    public T get(int index) {
        if (index >= size()) {
            index = 0;
        }
        return super.get(index);

    }
}
