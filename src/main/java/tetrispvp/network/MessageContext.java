package tetrispvp.network;

/**
 * Message context responsible for managing a logical subset of all
 * messages being passed between peers, identified by a group name.
 */
public interface MessageContext {

    /**
     * @return A sender for messages in this context.
     */
    MessageSender sender();

    /**
     * @return A receiver for messages in this context.
     */
    MessageReceiver receiver();

    /**
     * @return Name of the group of messages managed by this context.
     */
    String groupName();

    /**
     * @param subgroupName Group name to identify nested context.
     * @return A nested context.
     * Note: Messages within a nested context are a disjoint set from messages
     * within a parent context, much like the way Java packages nest.
     */
    MessageContext subgroup(String subgroupName);
}
