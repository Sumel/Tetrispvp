package tetrispvp.network.detail.impl;

import tetrispvp.network.MessageContext;
import tetrispvp.network.MessageHandler;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Pattern;

public class HandlerStore {

    private final MessageContext parentContext;
    private final Map<String, Set<MessageHandler>> handlersByName =
            new HashMap<>();

    private final Map<Pattern, Set<MessageHandler>> handlersByPattern =
            new HashMap<>();
    private final ThreadPoolExecutor executor;

    private MessageHandler unexpectedHandler = null;

    private List<MessageHandler> handlersToBeForgotten = new ArrayList<>();
    private List<Pattern> patternsToBeRemoved = new ArrayList<>();

    public HandlerStore(MessageContext parentContext, int threadPoolSize) {
        this.parentContext = parentContext;
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadPoolSize);
    }

    public void expect(String messageName, MessageHandler handler) {
        Set<MessageHandler> handlers;
        if (handlersByName.containsKey(messageName)) {
            handlers = handlersByName.get(messageName);
        } else {
            handlers = new HashSet<>();
            handlersByName.put(messageName, handlers);
        }

        handlers.add(handler);
    }

    public void expect(Pattern messageName, MessageHandler handler) {
        Set<MessageHandler> handlers;
        if (handlersByPattern.containsKey(messageName)) {
            handlers = handlersByPattern.get(messageName);
        } else {
            handlers = new HashSet<>();
            handlersByPattern.put(messageName, handlers);
        }

        handlers.add(handler);
    }

    public void unexpected(MessageHandler handler) {
        unexpectedHandler = handler;
    }

    public void forgetAll() {
        for (Set<MessageHandler> handlers : handlersByName.values())
            for (MessageHandler handler : handlers)
                executor.submit(handler::wasForgotten);
        for (Set<MessageHandler> handlers : handlersByPattern.values())
            for (MessageHandler handler : handlers)
                executor.submit(handler::wasForgotten);

        handlersByName.clear();
        handlersByPattern.clear();
    }

    public void received(String messageName, Object with) {
        boolean handled = false;
        if (handlersByName.containsKey(messageName)) {
            handled = true;
            Set<MessageHandler> handlers = handlersByName.get(messageName);
            callHandlers(handlers, messageName, with);

            if (handlers.isEmpty())
                handlersByName.remove(messageName);
        }

        for (Map.Entry<Pattern, Set<MessageHandler>> entry :
                handlersByPattern.entrySet()) {
            Pattern pattern = entry.getKey();
            if (pattern.matcher(messageName).matches()) {
                handled = true;
                Set<MessageHandler> handlers = entry.getValue();
                callHandlers(handlers, messageName, with);
                if (handlers.isEmpty())
                    patternsToBeRemoved.add(pattern);
            }
        }

        for (Pattern pattern : patternsToBeRemoved)
            handlersByPattern.remove(pattern);
        patternsToBeRemoved.clear();

        if (!handled && unexpectedHandler != null)
            executor.submit(() -> unexpectedHandler.arrived(messageName, with,
                    parentContext));
    }

    private void callHandlers(Set<MessageHandler> handlers, String messageName,
                              Object with) {
        for (MessageHandler handler : handlers) {
            if (handler.shouldBeForgotten())
                handlersToBeForgotten.add(handler);
            else
                executor.submit(() -> handler.arrived(messageName, with,
                        parentContext));
        }

        for (MessageHandler handler : handlersToBeForgotten) {
            handlers.remove(handler);
            executor.submit(handler::wasForgotten);
        }
        handlersToBeForgotten.clear();
    }
}
