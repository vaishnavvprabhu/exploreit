package in.exploreit.slc.data.models;

import java.util.List;

public interface ListResultCallback {
    void responseReturned(List<ListItem> list);
}
