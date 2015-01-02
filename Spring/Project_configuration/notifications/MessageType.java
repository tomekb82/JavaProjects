package com.tbelina.notifications;


/**
 * Message type of notifications used by {@link NotificationsService}
 */
public enum MessageType {
	WARNING("warning"), SUCCESS("success"), ERROR("error"), INFO("info");

	private String type;

	private MessageType(String type) {
		this.type = type;
	}

	public static MessageType getMessageType(String type) {
		for (MessageType mType : MessageType.values()) {
			if (mType.getType().equals(type)) {
				return mType;
			}
		}
		return MessageType.INFO;
	}

	private String getType() {
		return type;
	}
}
