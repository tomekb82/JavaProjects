package com.tbelina.spring.notifications;

/**
 * Notification is used by {@link NotificationsService}.
 * 
 */
public class Notification {

	private MessageType type;
	private String content;
	private boolean coded = false;

	/**
	 * May contain comma-separated strings used as settable parameters in
	 * message.
	 */
	private String attributes;

	/**
	 * Indicates if it is possible such notification should provide on view
	 * action button, which will remove such notification from database. Default
	 * set to false.
	 */
	private boolean removable;

	/**
	 * ID of notification kept in database.
	 */
	private long id;

	public Notification(MessageType type, String content, String attributes,
			boolean removable, long id) {
		this.type = type;
		this.content = content;
		this.attributes = attributes;
		this.removable = removable;
		this.id = id;
	}

	public Notification(MessageType type, String content, String attributes) {
		this(type, content, attributes, false, 0l);
	}

	public Notification(MessageType type, String content) {
		this(type, content, new String());
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the attributes
	 */
	public String getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes
	 *            the attribute to set
	 */
	public void setAttribute(String attributes) {
		this.attributes = attributes;
	}

	public boolean isRemovable() {
		return removable;
	}

	/**
	 * @return id
	 *  	of notification in database
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 * 		of notification in database
	 */
	public void setId(long id) {
		this.id = id;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public void setRemovable(boolean removable) {
		this.removable = removable;
	}

	/**
	 * Set notification as removable.
	 */
	public void removable() {
		setRemovable(true);
	}

	/**
	 * Set notification as non-removable.
	 */
	public void nonremovable() {
		setRemovable(false);
	}

	@Override
	public String toString() {
		return "Notification [type=" + type + ", content=" + content
				+ ", attributes=" + attributes + ", removable=" + removable
				+ ", " + id + "]";
	}

	public boolean isCoded() {
		return coded;
	}

	public void setCoded(boolean coded) {
		this.coded = coded;
	}
}
