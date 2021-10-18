package ru.digitalspirit.asaka.bpm.api.client;


import ru.digitalspirit.asaka.bpm.model.other.exposed.ExposedItems;
import ru.digitalspirit.asaka.bpm.model.other.exposed.Item;
import ru.digitalspirit.asaka.bpm.model.other.exposed.ItemType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

//TODO: Add full api possibilities

/**
 * Client for exposed api actions.
 */
public interface ExposedClient {

	/**
	 * Retrieve items of all types that are exposed to an end user.
	 * @return {@link ExposedItems}
	 */
    ExposedItems listItems();
	
	/**
	 * Retrieve items of a specific type that are exposed to an end user.
	 * If itemType is null, then all service subtypes will be included in the result set.
	 * @param itemType is a filter of items (see {@link ItemType})
	 * @return {@link ExposedItems}
	 */
    ExposedItems listItems(@Nullable ItemType itemType);
	
	/**
	 * Retrieve item by the specified name.
	 * If itemName is null, then {@link IllegalArgumentException} should be thrown.
	 * @param itemName is a full name of item (see {@link Item#getName()})
	 * @return {@link Item}
	 * @throws IllegalArgumentException if itemName is null
	 */
    Item getItemByName(@Nonnull String itemName);

	/**
	 * Retrieve item by the specified name and type.
	 * If itemName or itemType is null, then {@link IllegalArgumentException} should be thrown.
	 * @param itemName is a full name of item (see {@link Item#getName()})
	 * @param itemType is a filter of items (see {@link ItemType})
	 * @return {@link Item}
	 * @throws IllegalArgumentException if itemName or itemType are null
	 */
    Item getItemByName(@Nonnull ItemType itemType, @Nonnull String itemName);
	
}
