package desperado.com.daily.utils.network;

/**
 * Created by desperado on 16-12-31.
 */

public class NetworkRequestImpl extends NetworkRequest {
    private String url = null;

    public NetworkRequestImpl(String url) {
        this.url = url;
    }
    @Override
    public String getUrl() {
        return url;
    }
}
