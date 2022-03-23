package in.exploreit.slc.fragments.Lists;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import in.exploreit.slc.data.models.Events;
import in.exploreit.slc.data.models.ListItem;

public class ListsViewModel extends ViewModel {
    List<ListItem> getListItems() {
        List<ListItem> list = new ArrayList<>();
        list.add(new Events("Title", "Desc", "https://i.imgur.com/KoRBWXM.jpeg", 500));
        list.add(new Events("Title", "Desc", "https://i.imgur.com/KoRBWXM.jpeg", 500));
        list.add(new Events("Title", "Desc", "https://i.imgur.com/KoRBWXM.jpeg", 500));
        list.add(new Events("Title", "Desc", "https://i.imgur.com/KoRBWXM.jpeg", 500));
        list.add(new Events("Title", "Desc", "https://i.imgur.com/KoRBWXM.jpeg", 500));
        list.add(new Events("Title", "Desc", "https://i.imgur.com/KoRBWXM.jpeg", 500));
        list.add(new Events("Title", "Desc", "https://i.imgur.com/KoRBWXM.jpeg", 500));
        return list;
    }
}
