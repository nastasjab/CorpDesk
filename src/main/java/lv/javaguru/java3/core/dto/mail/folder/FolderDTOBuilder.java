package lv.javaguru.java3.core.dto.mail.folder;

/**
 * Created by Andrew on 02.12.2015.
 */
public class FolderDTOBuilder {

    private long id;
    private String name;
    private long userId;
    private boolean isRemovable;
    private long messagesCount;
    private long unreadMessagesCount;

    public FolderDTOBuilder() {
    }

    public static FolderDTOBuilder createFolderDTO() {
        return new FolderDTOBuilder();
    }

    public FolderDTO build() {
        FolderDTO dto = new FolderDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setUserId(userId);
        dto.setIsRemovable(isRemovable);
        dto.setMessageCount(messagesCount);
        dto.setUnreadMessageCount(unreadMessagesCount);
        return dto;
    }

    public FolderDTOBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public FolderDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public FolderDTOBuilder withUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public FolderDTOBuilder isRemovable(boolean isRemovable) {
        this.isRemovable = isRemovable;
        return this;
    }

    public FolderDTOBuilder withMessagesCount(long messagesCount) {
        this.messagesCount = messagesCount;
        return this;
    }

    public FolderDTOBuilder withUnreadMessagesCount(long unreadMessagesCount) {
        this.unreadMessagesCount = unreadMessagesCount;
        return this;
    }

}
