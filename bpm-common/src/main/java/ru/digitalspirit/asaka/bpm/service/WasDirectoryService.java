package ru.digitalspirit.asaka.bpm.service;

import com.ibm.websphere.wim.SchemaConstants;
import com.ibm.websphere.wim.client.LocalServiceProvider;
import com.ibm.websphere.wim.util.SDOHelper;
import commonj.sdo.DataObject;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


@Service
@Profile("server1")
public class WasDirectoryService implements DirectoryService{

    public String getUserEmail(String userId) {

        String userEmail = null;
        DataObject root;
        com.ibm.websphere.wim.Service service;
        try {
            root = SDOHelper.createRootDataObject();
            service = new LocalServiceProvider();

            DataObject searchCtrl = SDOHelper.createControlDataObject(root, null, SchemaConstants.DO_SEARCH_CONTROL);
            // Set the properties that need to be retrieved in search results
            searchCtrl.getList(SchemaConstants.PROP_PROPERTIES).add("uid");
            searchCtrl.getList(SchemaConstants.PROP_PROPERTIES).add("cn");
            searchCtrl.getList(SchemaConstants.PROP_PROPERTIES).add("sn");
            searchCtrl.getList(SchemaConstants.PROP_PROPERTIES).add("mail");
            // Set the search filter as uid
            searchCtrl.setString(SchemaConstants.PROP_SEARCH_EXPRESSION, String.format("@xsi:type='PersonAccount' and uid='*%s*'", userId));

            root = service.search(root);
            DataObject ent = (DataObject) root.get("entities[1]");
            userEmail = ent.getString("mail");
        } catch (Exception e) {
            // return null
        }
        return userEmail;
    }


}
