package powerUps.mocks;

public interface MessageContext{
    MessageSender sender();
    MessageReceiver receiver();
    String groupName();
    MessageContext subgroup(String subgroupName);
}