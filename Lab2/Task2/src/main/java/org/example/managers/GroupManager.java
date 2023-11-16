package org.example.managers;

import org.example.Group;

import java.util.List;

public class GroupManager {
    public static Group tryToFindGroup(List<Group> listOfGroups, String groupName) {
        Group foundGroup = null;

        for (Group group : listOfGroups) {
            if (group.getName().equals(groupName)) {
                foundGroup = group;
                break;
            }
        }

        return foundGroup;
    }
}
