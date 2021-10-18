package ru.digitalspirit.asaka.bpm.api.client;


import ru.digitalspirit.asaka.bpm.model.organisation.GroupData;
import ru.digitalspirit.asaka.bpm.model.organisation.GroupDataList;
import ru.digitalspirit.asaka.bpm.model.organisation.UserData;

import javax.annotation.Nonnull;

/**
 * Client for organisation api actions.
 */
public interface OrganisationClient{

    UserData getUserByName(@Nonnull String userName, @Nonnull boolean includeInternalMembership);

    GroupDataList getGroups();

    GroupData getGroupByName(@Nonnull String groupName);

}
