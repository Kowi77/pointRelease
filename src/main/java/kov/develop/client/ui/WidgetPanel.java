package kov.develop.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

import java.util.Set;

public class WidgetPanel extends Composite {

    interface MyUiBinder extends UiBinder<Widget, WidgetPanel> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    ListBox listBox;

    public WidgetPanel(Set<String> names) {
        // sets listBox
        initWidget(uiBinder.createAndBindUi(this));
        refreshPanel(names);
    }

    public void refreshPanel(Set<String> names) {
        String item = listBox.getSelectedItemText();
        listBox.clear();
        listBox.addItem("");

        for (String name : names) {
            listBox.addItem(name);
            if (name.equals(item))
                listBox.setItemSelected(listBox.getItemCount() - 1, true);
        }
    }

    public ListBox getListBox() {
        return listBox;
    }

}
