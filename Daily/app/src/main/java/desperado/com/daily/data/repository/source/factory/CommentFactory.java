package desperado.com.daily.data.repository.source.factory;

import javax.inject.Inject;
import javax.inject.Singleton;

import desperado.com.daily.data.repository.source.interfaces.CommentDataStore;
import desperado.com.daily.data.repository.source.remote.CommentRemoteDataStore;

/**
 * Created by desperado on 17-2-3.
 */
@Singleton
public class CommentFactory {

    private CommentRemoteDataStore commentDataStore;

    @Inject
    public CommentFactory(CommentRemoteDataStore commentDataStore) {
        this.commentDataStore = commentDataStore;
    }

    public CommentDataStore getCommentDataStore() {
        return commentDataStore;
    }
}
