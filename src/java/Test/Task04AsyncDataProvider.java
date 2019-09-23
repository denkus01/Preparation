package Test;

import java.util.function.Consumer;

public class Task04AsyncDataProvider {

    private final Webservice webservice;

    public Task04AsyncDataProvider(final Webservice webservice) {
        this.webservice = webservice;
    }


    /**
     * Suppose you need to develop an efficient method that load user information from remote web service.
     * Sometimes web service is not very reliable, so it can fail with generic error message
     * (thrown as subclass of {@link RuntimeException}), or it may execute too long. We want to handle such cases.
     * Write a function that takes user id, timeout, some default value and callback and
     * executes callback's accept() method with:
     * - result of the webservice call if call take less than timeoutMs.
     * - defaultValue if {@link Webservice#findUserName} method fails or takes more than timeoutMs time.
     * otherwise return the result of {@link Webservice#findUserName} via callback.
     * Make sure method doesn't block while waiting for webservice result.
     * Typical usage scenario is:
     * <pre>
     *     ...
     *     final Task04AsyncDataProvider provider = new Task04AsyncDataProvider(webservice);
     *     final String[] userIds = {"user1", "user2", "user3"};
     *     for(final String id: userIds) {
     *          provider.findUserNameAsync(id, 1000, "unknown", name -> {
     *              System.out.printf("user id: %s, name: %s, %n", id, name);
     *          });
     *     }
     *     ...
     * </pre>
     */
    public void findUserNameAsync(
            final String userId,
            final long timeoutMs,
            final String defaultValue,
            final Consumer<String> callback) {
        throw new UnsupportedOperationException("write your code here");
    }

    @FunctionalInterface
    public interface Webservice {
        String findUserName(String id);
    }
}